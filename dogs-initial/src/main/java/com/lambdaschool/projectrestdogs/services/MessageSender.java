package com.lambdaschool.projectrestdogs.services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageSender
{
    private RabbitTemplate rt;

    public MessageSender(RabbitTemplate rt)
    {
        this.rt = rt;
    }

    public void sendMessage(String s)
    {
        MessageDetail message = new MessageDetail(s);
        rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_HIGH, message);
    }
}
