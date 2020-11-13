package com.almod.spring_jms.messaging;

import com.almod.spring_jms.db.JmsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class Receiver {
    private final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private JmsRepo jmsRepo;

    @JmsListener(destination = "${spring.activemq.destination}")
    public void receive(TextMessage textMessage) {
        try {
            LOG.info("The received message: " + textMessage.getText());
            jmsRepo.test();
        } catch (JMSException e) {
            LOG.error(e.getMessage());
        }
    }
}
