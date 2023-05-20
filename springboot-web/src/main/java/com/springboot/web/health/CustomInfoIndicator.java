package com.springboot.web.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoIndicator implements InfoContributor {

	@Value("${productRestApi.base-url}")
	private String baseURL;
	
	@Value("${server.port}")
	private int serverPort;
	
	
	@Override
	public void contribute(Builder builder) {
		builder.withDetail("ApplicationUrl", baseURL);
		builder.withDetail("serverPort", serverPort);

	}

}
