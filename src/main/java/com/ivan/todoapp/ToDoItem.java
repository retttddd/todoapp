package com.ivan.todoapp;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class ToDoItem {

        @Id
        private String id;

        private String taskDescription;
        private Boolean isDone;
        private LocalDateTime deadLine;


        public ToDoItem() {}

    public ToDoItem(String taskDescription, Boolean isDone, LocalDateTime deadLine) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.deadLine = deadLine;
    }

    public String getId() {
        return id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Boolean getDone() {
        return isDone;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public void setDeadLine(LocalDateTime deadLine) {
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
