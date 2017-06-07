package com.gmc.jdops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
/*
 * Added to check jenkins auto deploy :)
 */
@SpringBootApplication
public class JDopsDemo extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JDopsDemo.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JDopsDemo.class, args);
    }
}
