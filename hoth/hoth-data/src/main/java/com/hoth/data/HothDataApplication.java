package com.hoth.data;

import com.hoth.data.repositories.ICatalogData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HothDataApplication{

    public static void main(String[] args) {
        SpringApplication.run(HothDataApplication.class, args);
    }

}
