/*
 * Copyright (c) 2015 SM band, Inc.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SM band
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SM band.
 *
 */
package com.smband.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 개요:
 * </pre>
 * @author ytkim
 * @create 2023. 3. 31.
 * @version 
 * @since 
 */
@Slf4j
@Service
public class SmbConsumerService {

	@KafkaListener(topics = {"usersRegistrations"}, groupId = "smb-group", containerFactory = "smbKafkaListenerContainerFactory")
	public void consume(String message) {
		log.info("consumer1 receive message: {}", message);
	}
	
	@KafkaListener(topics = {"usersRegistrations"} , groupId = "smb-group", containerFactory = "smbKafkaListenerContainer2Factory")
	public void consume2(String message) {
		log.info("consumer2 receive message: {}", message);
	}
}
