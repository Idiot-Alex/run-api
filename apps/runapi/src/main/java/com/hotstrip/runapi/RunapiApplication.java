package com.hotstrip.runapi;

import com.hotstrip.runapi.config.PlaywrightServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class RunapiApplication {

	public static void main(String[] args) {
		PlaywrightServer.startPlaywright();
		SpringApplication.run(RunapiApplication.class, args);
	}

	@PreDestroy
	public void destroy() {
		PlaywrightServer.closePlaywrightServer();
	}

}
