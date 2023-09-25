package com.ivan.todoapp.view;


import com.ivan.todoapp.SecurityService;
import com.ivan.todoapp.model.ToDoItem;
import com.ivan.todoapp.repository.ToDoItemRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@Route
@PermitAll
public class MainView extends VerticalLayout {
    private final ToDoItemRepository repository;
    private final SecurityService securityService;

    public MainView(ToDoItemRepository toDoItemRepository, SecurityService securityService) {
        this.repository = toDoItemRepository;
        this.securityService = securityService;

        Grid<ToDoItem> grid = new Grid<>(ToDoItem.class, false);
        grid.setAllRowsVisible(true);

        NewToDoItemDialog dialog = new NewToDoItemDialog(repository, grid);

        Button button = new Button("new task", e -> dialog.open());
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        add(grid, button, dialog);

        grid.addColumn(ToDoItem::getTaskDescription).setHeader("task");
        grid.addColumn(ToDoItem::getDeadLine).setHeader("deadline");
        grid.addColumn(ToDoItem::getDone).setHeader("checkbox");

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (bbb, toDoItem) -> {
                    bbb.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    bbb.addClickListener(e -> {
                        toDoItem.setDone(true);
                        this.repository.delete(toDoItem);
                        grid.setItems(this.repository.findAll());
                        Notification notification = Notification
                                .show("task deleted!");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.BOTTOM_CENTER);

                    });
                    bbb.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage");


        List<ToDoItem> todos = repository.findAll();
        grid.setItems(todos);


    }


}
