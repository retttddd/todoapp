package com.ivan.todoapp;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class ToDoItem {

        @Id
        public String id;

        public String taskDescription;
        public Boolean isDone;
        public LocalDateTime deadLine;


        public ToDoItem() {}

    public ToDoItem(String taskDescription, Boolean isDone, LocalDateTime deadLine) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id='" + id + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", isDone=" + isDone +
                ", deadLine=" + deadLine +
                '}';
    }
}
