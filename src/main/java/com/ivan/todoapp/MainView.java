package com.ivan.todoapp;


import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Route
public class MainView extends VerticalLayout {
    private final ToDoItemRepository repository;

    public MainView(ToDoItemRepository toDoItemRepository) {
        this.repository = toDoItemRepository;

        NewToDoItemDialog dialog = new NewToDoItemDialog(repository);

        Button button = new Button("new task", e -> dialog.open());
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //Button button = new Button("new task", e -> dialog.open());

        Grid<ToDoItem> grid = new Grid<>(ToDoItem.class, false);
        add(grid, button, dialog);

        grid.addColumn(ToDoItem::getTaskDescription).setHeader("task");
        grid.addColumn(ToDoItem::getDeadLine).setHeader("deadline");
        grid.addColumn(ToDoItem::getDone).setHeader("checkbox");

        List<ToDoItem> todos = repository.findAll();
        grid.setItems(todos);


    }



    class NewToDoItemDialog extends Dialog{
        private final ToDoItemRepository repository;
        private TextField taskName;
        private DatePicker deadLine;
        public NewToDoItemDialog(ToDoItemRepository repository) {
            this.repository = repository;



            setHeaderTitle("New task");

            VerticalLayout dialogLayout = createDialogLayout();
            add(dialogLayout);

            Button saveButton = createSaveButton();
            Button cancelButton = new Button("Cancel", e -> close());
            getFooter().add(cancelButton);
            getFooter().add(saveButton);
        }
        private  Button createSaveButton() {
            Button saveButton = new Button("Add", e -> {
                this.repository.save(new ToDoItem(this.taskName.getValue(), false, this.deadLine.getValue().atStartOfDay()));
                close();
            });
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }

        private  VerticalLayout createDialogLayout() {

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
                    if (value.compareTo(deadLine.getMin()) < 0) {
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

}
