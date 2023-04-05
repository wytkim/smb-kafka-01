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
package com.smband.kafka.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smband.kafka.common.SimpleUtil;
import com.smband.kafka.model.SmsBodyVO;
import com.smband.kafka.producer.SmsProducerService;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 개요:
 * </pre>
 * @author ytkim
 * @create 2023. 4. 4.
 * @version 
 * @since 
 */
@Slf4j
@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private SmsProducerService smsProdService;
	
	@RequestMapping("sendSms")
	public ResponseEntity<Map<String, Object>> sendSms(@RequestBody SmsBodyVO smsBodyVo){
		this.smsProdService.sendSms(smsBodyVo);
		log.info("send sms!");
		return ResponseEntity.ok(SimpleUtil.newMap(
				"result", "success"));
	}
}
