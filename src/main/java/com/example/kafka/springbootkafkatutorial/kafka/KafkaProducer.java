package com.example.kafka.springbootkafkatutorial.kafka;


import com.example.kafka.springbootkafkatutorial.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("message sent %s",message));
        kafkaTemplate.send(topicName,message);
    }

    public  void sendJSONMessage(User data){

        LOGGER.info(String.format("Message sent -> %s",data.toString()));

        Message<User> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,topicJsonName)
                .build();

        kafkaTemplate.send(message);

    }
}
