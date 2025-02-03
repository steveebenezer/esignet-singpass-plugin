package com.educerts.esignet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan("com.educerts.esignet.audit")
public class ESignetApplication {
    public static void main(String[] args) {
        SpringApplication.run(ESignetApplication.class, args);
    }
}