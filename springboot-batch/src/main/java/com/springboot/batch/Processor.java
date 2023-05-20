package com.springboot.batch;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		System.out.println("Inside Processing ");
		return "Processed Item"+item.toUpperCase();
	}

}
