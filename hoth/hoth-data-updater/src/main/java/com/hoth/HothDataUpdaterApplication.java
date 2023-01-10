package com.hoth;

import com.hoth.data.updater.IUpdateData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.hoth")
public class HothDataUpdaterApplication implements CommandLineRunner {

    private final IUpdateData updater;

    public HothDataUpdaterApplication(IUpdateData updater) {
        this.updater = updater;
    }

    public static void main(String[] args) {
        SpringApplication.run(HothDataUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        updater.updateEverything();
    }
}
