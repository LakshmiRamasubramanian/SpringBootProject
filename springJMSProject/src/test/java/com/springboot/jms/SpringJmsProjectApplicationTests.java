package com.springboot.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJmsProjectApplicationTests {
	
	@Autowired
	MessageSender sender;

	@Test
	public void testSendAndReceive() {
		sender.send("Hello Message Sent");
	}

}
