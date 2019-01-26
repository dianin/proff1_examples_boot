package ua.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.demo.test.service.DataHttpService;

@SpringBootApplication
public class Application {
    @Autowired
    DataHttpService dataHttpService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(DataHttpService dataHttpService) {
        return args -> {
            dataHttpService.run();
        };
    }
}