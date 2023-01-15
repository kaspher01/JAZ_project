package com.hoth.data;

import com.hoth.data.repositories.ICatalogData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HothDataApplication implements CommandLineRunner {

    private final ICatalogData db;

    public HothDataApplication(ICatalogData dataCatalog) {
        this.db = dataCatalog;
    }

    public static void main(String[] args) {
        SpringApplication.run(HothDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var films = db.getFilms();
        var tst = "sdafa";
    }
}
