package com.educerts.esignet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.educerts.esignet", "com.educerts.esignet.audit", "io.mosip.esignet"})
public class EsignetApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsignetApplication.class, args);
    }
}