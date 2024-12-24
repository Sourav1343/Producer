package com.producer.service;

import com.producer.constant.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String,String> template;

    private Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public boolean updateLocation(String location){
        this.template.send(AppConstant.LOCATION_TOPIC,location);
        logger.info("Message Produced on Kafka Server ...Great");
        return true;
    }
}
