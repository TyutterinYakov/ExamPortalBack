package ru.pet.portal.api.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.api.exception.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@UtilityClass
@Slf4j
public class FileUtil {

    public byte[] readFile(String dir, String imageName) {
        try {
            return Files.readAllBytes(Path.of(dir, imageName));
        } catch (IOException ex) {
            throw new NotFoundException("Фотография профиля не найдена");
        }
    }

    public static String save(String dir, MultipartFile file) {
        String imageUUID;
        if(file != null && !file.isEmpty()) {
            int position = Objects.requireNonNull(file.getContentType()).indexOf("/");
            String type = file.getContentType().substring(position + 1);
            imageUUID = UUID.randomUUID() + "." + type;
            Path fileNameAndPath = Path.of(dir, imageUUID);
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Изображение отсутствует");
        }
        return imageUUID;
    }

    public static void delete(String dir, String oldImage, String defaultImage) {
        if(!Objects.equals(oldImage, defaultImage)) {
            File file = new File(String.valueOf(Path.of(dir, oldImage)));
            if (!file.delete()) {
                log.info("File from path: {} not found!!!", file.getAbsolutePath());
            }
        }
    }
}
