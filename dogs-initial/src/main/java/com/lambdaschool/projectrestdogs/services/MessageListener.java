package com.lambdaschool.projectrestdogs.services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    // Queue to Listen to
    // Can have multiple Queues (similar to endpoints)
    //@RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_HIGH)
    public void receiveMessage(MessageDetail msg)
    {
        logger.info("Received message {}", msg.toString());
    }
}
