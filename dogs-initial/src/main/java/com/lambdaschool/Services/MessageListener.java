package com.lambdaschool.Services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_HIGH)
    public void recieveHighMessage(MessageDetail msg)
    {
        // process the message
        logger.info("Received message {}", msg.toString());
    }
    @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_LOW)
    public void recieveLowMessage(MessageDetail msg)
    {
        // process the message
        logger.info("Received message {}", msg.toString());
    }
}
