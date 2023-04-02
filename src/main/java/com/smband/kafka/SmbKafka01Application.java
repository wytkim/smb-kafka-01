package com.smband.kafka;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.ConsumerFactory;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SmbKafka01Application {

	@Autowired
	private ApplicationContext appContext;
	
	public static void main(String[] args) {
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
