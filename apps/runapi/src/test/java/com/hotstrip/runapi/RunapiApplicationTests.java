package com.hotstrip.runapi;

import com.alibaba.fastjson.JSON;
import com.hotstrip.runapi.domain.model.Res;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@WebAppConfiguration
public class RunapiApplicationTests {

	@Resource
	private WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;


	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void contextLoads() {
		log.info("Base Test....");
		Res res = Res.okData("data");
		log.info("{}", JSON.toJSONString(res));
		Assertions.assertEquals(res.get("code"), "0");
	}

}
