package portal.api.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import portal.api.exception.InvalidFileException;
import portal.api.exception.NotFoundException;

@Component
public class UploadAndRemoveImage {
	private static final Logger logger = LoggerFactory.getLogger(UploadAndRemoveImage.class);
	private static final String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/";
	
	public String uploadImage(MultipartFile file, String src){
		try {
			String imageUUID;
			if(!file.isEmpty()) {
				int position = file.getContentType().indexOf("/");
				String type = file.getContentType().substring(position+1);
				imageUUID = UUID.randomUUID()+"."+type;
				Path fileNameAndPath = Paths.get(uploadDir+src, imageUUID);
				
				Files.write(fileNameAndPath, file.getBytes());
			} else {
				imageUUID = "noimage.png";
			}
			return imageUUID;
		} catch(IOException ex) {
			logger.error("Загрузка файла",ex);
			throw new InvalidFileException(String.format("Ошибка при загрузке файла: %s", file.getContentType()));
		}
		
	}
	
	public void deleteImage(String imageName, String src) {
		if(!imageName.equals("noimage.png")) {
		File file = new File(uploadDir+src+imageName);
		if(file.exists()) {
			file.delete();
		}
		}
	}
	
	public byte[] getImage(String imageName, String src) {
		try {
			File file = new File(uploadDir+src+imageName);
			Path path = Paths.get(file.toURI());
			return Files.readAllBytes(path);
		} catch(IOException ex) {
			logger.error("Ошибка при получении фотографии профиля", ex);
			throw new NotFoundException("Фотогорафия профиля не найдена");
		}
	}
	
}
