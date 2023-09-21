package com.ivan.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class TodoappApplication {
    @Autowired
    private ToDoItemRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(ToDoItemRepository repository) {
        return (args) -> {
            repository.deleteAll();
            repository.save(new ToDoItem("First task", false, LocalDateTime.now()));
            repository.save(new ToDoItem("second task", false, LocalDateTime.now()));

        };
    }
}
