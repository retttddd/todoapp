package com.ivan.todoapp.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class ToDoItem {

    @Id
    private String id;

    private String taskDescription;
    private Boolean isDone;
    private LocalDateTime deadLine;

    private String owner;


    public ToDoItem() {
    }

    public ToDoItem(String taskDescription, Boolean isDone, LocalDateTime deadLine, String owner) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.deadLine = deadLine;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
