package com.hotstrip.runapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RunapiApplicationTests {

//	@LocalServerPort
//	protected int port;

//	@Resource
//	protected TestRestTemplate restTemplate;

	@Resource
	protected MockMvc mockMvc;

//	protected String prefixUrl() {
//		return String.format("http://127.0.0.1:%d", port);
//	}

	@Test
	void contextLoads() {
		log.info("Base Test....");
	}

}
