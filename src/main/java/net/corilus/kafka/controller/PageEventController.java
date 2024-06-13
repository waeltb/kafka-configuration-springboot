package net.corilus.kafka.controller;

import net.corilus.kafka.entities.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Date;
import java.util.Random;


@RestController
public class PageEventController {
    @Autowired
    private StreamBridge streamBridge;
    @GetMapping("publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String name, @PathVariable String topic){
        PageEvent pageEvent=new PageEvent();
        pageEvent.setName(name);
        pageEvent.setDate(new Date());
        pageEvent.setDuration(new Random().nextInt(1000));
        pageEvent.setUser(Math.random()>0.5?"U1":"U2");
        streamBridge.send(topic,pageEvent);
        return pageEvent;
    }

}
