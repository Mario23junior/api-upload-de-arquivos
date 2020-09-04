package com.uploadArquivos.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uploadArquivos.exception.StorageException;

@Service
public class StorageService {
    
	@Value("${upload.path}")
	private String path;
	
	public void uploadFile(MultipartFile file ) {
		 
		if(file.isEmpty()) {
			 
			throw new StorageException("Falha ao realizar upload campo vazio");
		}
		
		try {
			
			var fileName = file.getOriginalFilename();
			var is = file.getInputStream();
			
			Files.copy(is, Paths.get(path + fileName),StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			
			var msg = String.format("Falha ao armazenar o arquivo %f", file.getName());
			
			throw new StorageException(msg, e);
		}
	}
	
	
}
