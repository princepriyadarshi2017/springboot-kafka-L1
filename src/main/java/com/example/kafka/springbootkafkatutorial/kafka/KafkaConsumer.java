package com.example.kafka.springbootkafkatutorial.kafka;

import com.example.kafka.springbootkafkatutorial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId="myGroup")
    public void consume(String message){

        LOGGER.info(String.format("Message received -> %s",message));

    }

    @KafkaListener(topics = "${spring.kafka.topic-json.name}",groupId="myGroup")
    public void consume(User user){

        LOGGER.info(String.format("JSON Message received -> %s",user.toString()));

    }
}

