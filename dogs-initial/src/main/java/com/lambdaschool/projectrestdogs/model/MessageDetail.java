package com.lambdaschool.projectrestdogs.model;

import java.io.Serializable;

public class MessageDetail implements Serializable
{
    private String text;

    public MessageDetail()
    {
    }

    public MessageDetail(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "MessageDetail{" +
                "text='" + text + '\'' +
                '}';
    }
}
