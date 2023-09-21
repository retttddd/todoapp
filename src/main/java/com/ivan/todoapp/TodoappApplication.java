package com.ivan.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class TodoappApplication implements CommandLineRunner {

	@Autowired
	private ToDoItemRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new ToDoItem("First task", false,LocalDateTime.now() ));
		repository.save(new ToDoItem("second task", false, LocalDateTime.now()));


		for (ToDoItem item : repository.findAll()) {
			System.out.println(item);
		}
		for (ToDoItem item : repository.findAll()) {
			item.isDone = true;
			repository.save(item);
		}
		for (ToDoItem item : repository.findAll()) {
			System.out.println(item);
		}
	}
}
