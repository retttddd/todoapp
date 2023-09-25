package com.ivan.todoapp.repository;

import com.ivan.todoapp.model.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToDoItemRepository extends MongoRepository<ToDoItem, String> {

    List<ToDoItem> findByOwner(String owner);
}
