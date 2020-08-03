package com.pelime.ecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.pelime.ecms.*"})
public class EcmsApplication extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(EcmsApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(EcmsApplication.class);
    }
}



