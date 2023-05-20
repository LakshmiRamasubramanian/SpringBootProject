package com.springboot.web.cache;

import org.apache.catalina.webresources.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
@Component
public class ProductCacheConfig {
 
	
	@Bean
	public Config getCacheConfig() {
		return new Config()
				.setInstanceName("hazel-instance")
				.addMapConfig(
						new MapConfig().setName("product-cache").setTimeToLiveSeconds(3000));
	}
}  