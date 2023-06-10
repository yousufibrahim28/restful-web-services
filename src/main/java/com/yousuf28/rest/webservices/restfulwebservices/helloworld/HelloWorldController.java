package com.yousuf28.rest.webservices.restfulwebservices.helloworld;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@AllArgsConstructor
public class HelloWorldController {


    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String hellowWorld(){
        return "Hello World!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean hellowWorldBean(){
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean hellowWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World Bean %s", name));
    }

    @GetMapping(path = "/hello-world-i18n")
    public String hellowWorldBeani18n(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Deafult Messsage", locale);
    }
}
