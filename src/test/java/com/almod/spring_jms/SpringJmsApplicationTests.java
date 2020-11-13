package com.almod.spring_jms;

import com.almod.spring_jms.messaging.Sender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringJmsApplicationTests {
    @Autowired
    private Sender sender;

    @Test
    public void testReceive() throws Exception {
        sender.send(0);
    }

}
