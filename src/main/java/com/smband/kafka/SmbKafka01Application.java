package com.smband.kafka;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.ConsumerFactory;

//import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@PropertySource(value= {
		"classpath:property/${server.mode}/jdbc.properties",
}, encoding="UTF-8")
public class SmbKafka01Application {

	@Autowired
	private ApplicationContext appContext;
	
	public static void main(String[] args) {
		// default app mode 설정
		String serverMode = (String)System.getProperty("server.mode", "debug");
		System.setProperty("server.mode", serverMode);
		
		SpringApplication.run(SmbKafka01Application.class, args);
	}

	@PostConstruct
	private void logInit() {
		Map<String, ConsumerFactory> map = appContext.getBeansOfType(ConsumerFactory.class);
		if(map != null) {
			map.entrySet().stream().forEach(entry->{
				log.info("consumer factory key: {}", entry.getKey());
			});
		}
	}
}
