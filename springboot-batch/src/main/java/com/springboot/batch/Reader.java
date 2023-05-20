package com.springboot.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

	private String[] coursesArray= {"java web developer","end to end project","front end"};
	private int count;
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Inside Reader");
		if(count<coursesArray.length) {
			return coursesArray[count++];
		}
		else {
			count=0;
		}
		return null;
	}

}
