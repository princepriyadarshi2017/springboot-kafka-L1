package com.example.kafka.springbootkafkatutorial.controller;

import com.example.kafka.springbootkafkatutorial.kafka.KafkaProducer;
import com.example.kafka.springbootkafkatutorial.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;

//    we not need to use @autowired because it spring 4.1 > if spring beans conatins only one parametrized construtor
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message){

        kafkaProducer.sendMessage(message);

        return ResponseEntity.ok("Message sent to the topic");

    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
        kafkaProducer.sendJSONMessage(user);
        return ResponseEntity.ok("JSON Message sent to kafka topic");
    }


}
