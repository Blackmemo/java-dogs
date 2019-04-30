package com.lambdaschool.projectrestdogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ProjectrestdogsApplication
{

    public static DogList ourDogList;
    public static void main(String[] args)
    {
        ourDogList = new DogList();
        ApplicationContext ctx = SpringApplication.run(ProjectrestdogsApplication.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}

