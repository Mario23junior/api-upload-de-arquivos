package com.uploadArquivos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
  
	 @Autowired
	 private StoregeService storegeService;
	 
	 @RequestMapping(value = "/Upload", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	 public String upload(@RequestParam MultipartFile file) {
		 
		 storegeService.uploadFile(file);
		 
		 return "redirect:/success.html";
	 }
	 
	 @ExceptionHandler(StoregeException.class)
	 public String handleStringFileNotFoundString(StoregeService e) {
		 return "redirect:/failed.html"
	 }
	 
}
