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
package com.smband.kafka.configure;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.smband.kafka.common.SimpleUtil;
import com.smband.kafka.model.SmsBodyVO;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 개요:
 * </pre>
 * @author ytkim
 * @create 2023. 4. 2.
 * @version 
 * @since 
 */
@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

	private String bootstrapServers = "127.0.0.1:9092";
	private String groupId = "smb-group";
	
	@Bean
	public ConsumerFactory<String, String> smbConsumerFactory(){
		
		return new DefaultKafkaConsumerFactory<>(
				smbConsumerFactoryConfig(),
				new StringDeserializer(),
				new StringDeserializer()
				);
	}
	@Bean
	public ConsumerFactory<String, String> smbConsumer2Factory(){
		
		return new DefaultKafkaConsumerFactory<>(
				smbConsumerFactoryConfig(),
				new StringDeserializer(),
				new StringDeserializer()
				);
	}
	
	private Map<String, Object> smbConsumerFactoryConfig(){
		Map<String, Object> props = SimpleUtil.newMap(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
				ConsumerConfig.GROUP_ID_CONFIG, groupId,
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
				);
		return props;
	}
	
	private JsonDeserializer<SmsBodyVO> gcmPushEntityJsonDeserializer() {
        JsonDeserializer<SmsBodyVO> deserializer = new JsonDeserializer<>(SmsBodyVO.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*"); // producer에서 사용하는 package명과 다르다면 사용한다. 
        deserializer.setUseTypeMapperForKey(true);
        return deserializer;
    }
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> smbKafkaListenerContainerFactory(
			ConsumerFactory<String, String> smbConsumerFactory
			){
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(smbConsumerFactory);
		return factory;
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> smbKafkaListenerContainer2Factory(
			ConsumerFactory<String, String> smbConsumer2Factory
			){
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(smbConsumer2Factory);
		return factory;
	}
}
