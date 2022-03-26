package com.example.application.views;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Train;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class CrewForm extends FormLayout {
    Binder<Crew> binder = new BeanValidationBinder<>(Crew.class);

    TextField firstName = new TextField("Имя");
    TextField lastName = new TextField("Фамилия");
    TextField thirdName = new TextField("Отчество");
    ComboBox<Train> train = new ComboBox<>("Поезд");

    Button saveButton = new Button("Сохранить");
    Button deleteButton = new Button("Удалить");
    Button cancelButton = new Button("Отмена");
    private Crew crew;

    public CrewForm(List<Train> trains) {
        addClassName("crew-form");

        binder.bindInstanceFields(this);

        train.setItems(trains);
        train.setItemLabelGenerator(Train::getTitle);

        add(
                firstName,
                lastName,
                thirdName,
                train,
                createButtonLayout()
        );
    }

    private Component createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickShortcut(Key.ENTER);
        cancelButton.addClickShortcut(Key.ESCAPE);

        saveButton.addClickListener(event -> validateAndSave());
        deleteButton.addClickListener(event -> fireEvent(new DeleteEvent(this, crew)));
        cancelButton.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(crew);
            fireEvent(new SaveEvent(this, crew));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
        binder.readBean(crew);
    }

    // Events
    public static abstract class CrewFormEvent extends ComponentEvent<CrewForm> {
        private Crew crew;

        protected CrewFormEvent(CrewForm source, Crew crew) {
            super(source, false);
            this.crew = crew;
        }

        public Crew getCrew() {
            return crew;
        }
    }

    public static class SaveEvent extends CrewFormEvent {
        SaveEvent(CrewForm source, Crew crew) {
            super(source, crew);
        }
    }

    public static class DeleteEvent extends CrewFormEvent {
        DeleteEvent(CrewForm source, Crew crew) {
            super(source, crew);
        }

    }

    public static class CloseEvent extends CrewFormEvent {
        CloseEvent(CrewForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}