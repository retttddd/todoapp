package com.ivan.todoapp;

import com.ivan.todoapp.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TodoappApplication {
    @Autowired
    private ToDoItemRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }
}
