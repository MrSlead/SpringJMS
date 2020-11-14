package com.almod.spring_jms.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.TextMessage;

public class Sender {
    private final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.destination}")
    private String destination;

    public void send(int i) {
        jmsTemplate.send(destination,
                session -> {
                    TextMessage message = session.createTextMessage();
                    message.setJMSType("STRING");
                    message.setText("Text " + i);
                    LOG.debug("The sent message: "+ message.getText());

                    return message;
                }
        );

    }
}
