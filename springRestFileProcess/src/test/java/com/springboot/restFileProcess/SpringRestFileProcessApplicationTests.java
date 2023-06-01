package com.springboot.restFileProcess;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.PathContainer.Options;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class SpringRestFileProcessApplicationTests {
	
    @Autowired
	RestTemplate restTemplate;
 
    @Value("${Download_DIR}")
    public String downloadDir;
    
	@Test
	void testUpload() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body=new LinkedMultiValueMap<String, Object>();
		body.add("file", new ClassPathResource("TestImage.jpg"));
		
		HttpEntity<MultiValueMap<String, Object>> entity=new HttpEntity<>(body,httpHeaders);
		
		ResponseEntity<Boolean> postForEntity = restTemplate.postForEntity("http://localhost:8080/fileProcess/upload",entity ,Boolean.class);
		System.out.println(postForEntity.getBody());
	}
	
	@Test
	void testDownload() throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity= new HttpEntity<>(httpHeaders);
		ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/fileProcess/downloads/TestImage.jpg", HttpMethod.GET, entity, byte[].class);
		Files.write(Paths.get(downloadDir+"TestImage.jpg"),response.getBody());     
	}
	
	
	

}
