package org.example.projectbackend;

import org.springframework.boot.SpringApplication;

public class TestProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProjectBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
