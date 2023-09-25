package com.ivan.todoapp.repository;

import com.ivan.todoapp.model.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoItemRepository extends MongoRepository<ToDoItem, String> {
}
