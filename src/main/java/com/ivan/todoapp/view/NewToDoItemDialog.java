package com.ivan.todoapp.view;

import com.ivan.todoapp.model.ToDoItem;
import com.ivan.todoapp.repository.ToDoItemRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;
import java.time.ZoneId;

class NewToDoItemDialog extends Dialog {
    private final ToDoItemRepository repository;
    private final Grid grid;
    private TextField taskName;
    private DatePicker deadLine;

    public NewToDoItemDialog(ToDoItemRepository repository, Grid grid) {
        this.repository = repository;

        this.grid = grid;

        setHeaderTitle("New task");

        VerticalLayout dialogLayout = createDialogLayout();
        add(dialogLayout);

        Button saveButton = createSaveButton();
        Button cancelButton = new Button("Cancel", e -> close());
        getFooter().add(cancelButton);
        getFooter().add(saveButton);
    }

    private Button createSaveButton() {

        Button saveButton = new Button("Add", e -> {
            this.repository.save(new ToDoItem(this.taskName.getValue(), false, this.deadLine.getValue().atStartOfDay()));
            grid.setItems(this.repository.findAll());
            close();
            this.taskName.setValue("");
            this.deadLine.setValue(LocalDate.now(ZoneId.systemDefault()));
            Notification notification = Notification
                    .show("Task added!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        return saveButton;
    }

    private VerticalLayout createDialogLayout() {

        this.taskName = new TextField("task name");
        this.deadLine = new DatePicker("deadline");
        LocalDate now = LocalDate.now(ZoneId.systemDefault());

        deadLine.setMin(now);
        deadLine.setMax(now.plusDays(32));
        deadLine.setHelperText("Must be within 30 days from today or not yesterday bro wtf...");
        deadLine.addValueChangeListener(event -> {
            LocalDate value = deadLine.getValue();
            String errorMessage = null;
            if (value != null) {
                if (value.isBefore(deadLine.getMin())) {
                    errorMessage = "Too early, choose another date";
                }
            }
            deadLine.setErrorMessage(errorMessage);

        });


        VerticalLayout dialogLayout = new VerticalLayout(taskName,
                deadLine);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;
    }
}
