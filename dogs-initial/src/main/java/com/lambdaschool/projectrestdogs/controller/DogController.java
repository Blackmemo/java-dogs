package com.lambdaschool.projectrestdogs.controller;

import com.lambdaschool.projectrestdogs.exception.ResourceNotFoundException;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{

    private static final Logger logger = LoggerFactory.getLogger(Dog.class);
    private RabbitTemplate rt; // send

//    @Autowired
//    MessageSender msgSender;

    public DogController(RabbitTemplate rt)
    {
        this.rt = rt; // send
    }


    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("/dogs Accessed");
        for (Dog d: ProjectrestdogsApplication.ourDogList.dogList)
        {
            MessageDetail message = new MessageDetail("Listing all Dogs " + d.toString());
            logger.info(d.toString());
            rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_HIGH, message);
        }
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;
        logger.info("/dogs/" + id + " Accessed");
        if ((ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId()) == id)) == null)
        {
            throw new ResourceNotFoundException("Dog with id " + id + " not found");
        } else
        {
            rtnDog = ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId() == id));
            MessageDetail message = new MessageDetail("Finding dog with id: " + id);
            rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_HIGH, message);
        }

        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        logger.info("/breeds/" + breed + " Accessed");
        MessageDetail message = new MessageDetail("Finding dog breed of: " + breed);
        rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_HIGH, message);
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }
}
