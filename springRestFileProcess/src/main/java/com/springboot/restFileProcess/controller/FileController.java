package com.springboot.restFileProcess.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class FileController {
	
	@Value("${UPLOAD_DIR}")
	private String upload_dir;

	
	@PostMapping("/upload")
	public boolean upload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(upload_dir+file.getOriginalFilename());
		file.transferTo(new File(upload_dir+file.getOriginalFilename()));
		return true;
		
	}
	
	@GetMapping("/downloads/{fileName}")
	public ResponseEntity<byte[]> download(@PathVariable("fileName" ) String file) throws IOException{
		
		byte[] readFile = Files.readAllBytes(new File(upload_dir+file).toPath());
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(readFile, httpHeaders,HttpStatus.OK);
		
	}
	
}
