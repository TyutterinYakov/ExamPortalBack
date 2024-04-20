//package portal.api.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Objects;
//import java.util.UUID;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import portal.api.exception.InvalidFileException;
//import portal.api.exception.NotFoundException;
//
//@Component
//@Slf4j
//public class ImageUtil {
//	private final String uploadDirectory;
//    private final String defaultImage;
//
//	public ImageUtil(@Value("${image.upload-directory}") String uploadDirectory,
//                     @Value("${image.default}") String defaultImage) {
//		this.uploadDirectory = uploadDirectory;
//        this.defaultImage = defaultImage;
//	}
//
//
//    public String upload(MultipartFile file) {
//        String imageUUID;
//        if(file != null && !file.isEmpty()) {
//            int position = Objects.requireNonNull(file.getContentType()).indexOf("/");
//            String type = file.getContentType().substring(position + 1);
//            imageUUID = System.currentTimeMillis() + "." + type;
//            Path fileNameAndPath = Path.of(uploadDirectory, imageUUID);
//            try {
//                Files.write(fileNameAndPath, file.getBytes());
//            } catch (IOException e) {
//                throw new InvalidFileException(e.getMessage(), e);
//            }
//        } else {
//            throw new InvalidFileException("Изображение отсутствует");
//        }
//        return imageUUID;
//    }
//
//    public void delete(String imageName) {
//        if(!imageName.equals(defaultImage)) {
//            File file = new File(String.valueOf(Path.of(uploadDirectory, imageName)));
//            if(file.exists()) {
//                file.delete();
//            }
//        }
//    }
//
//	public byte[] get(String imageName) {
//		try {
//			File file = new File(uploadDirectory + imageName);
//			Path path = Paths.get(file.toURI());
//			return Files.readAllBytes(path);
//		} catch(IOException ex) {
//			log.error("Ошибка при получении фотографии профиля", ex);
//			throw new NotFoundException("Фотогорафия профиля не найдена");
//		}
//	}
//
//}
