package com.almod.spring_jms;

import com.almod.spring_jms.messaging.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringJmsApplication implements ApplicationRunner {

    private Sender sender;

    @Autowired
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        for (int i = 0; i < 1; i++){
            TimeUnit.SECONDS.sleep(1);
            sender.send(i);
        }
        //LOGGER.info("Waiting for all ActiveMQ JMS Messages to be consumed");
        TimeUnit.SECONDS.sleep(3);
        //jmsRepository.showDataInDb();
        System.exit(0);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsApplication.class, args);
    }
}
