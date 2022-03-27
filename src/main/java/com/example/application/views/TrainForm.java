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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class TrainForm extends FormLayout {
    Binder<Train> binder = new BeanValidationBinder<>(Train.class);

    TextField title = new TextField("Название");
    TextField trainNumber = new TextField("Номер");
    ComboBox<Crew> pilot = new ComboBox<>("Машинист");

    Button saveButton = new Button("Сохранить");
    Button deleteButton = new Button("Удалить");
    Button cancelButton = new Button("Отмена");
    private Train train;

    public TrainForm(List<Crew> pilots) {

        binder.bindInstanceFields(this);

        pilot.setItems(pilots);
        pilot.setItemLabelGenerator(Crew::getLastName);

        add(
                title,
                trainNumber,
                pilot,
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
        deleteButton.addClickListener(event -> fireEvent(new DeleteEvent(this, train)));
        cancelButton.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(train);
            fireEvent(new SaveEvent(this, train));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setTrain(Train train) {
        this.train = train;
        binder.readBean(train);
    }

    // Events
    public static abstract class TrainFormEvent extends ComponentEvent<TrainForm> {
        private Train train;

        protected TrainFormEvent(TrainForm source, Train train) {
            super(source, false);
            this.train = train;
        }

        public Train getTrain() {
            return train;
        }
    }

    public static class SaveEvent extends TrainFormEvent {
        SaveEvent(TrainForm source, Train train) {
            super(source, train);
        }
    }

    public static class DeleteEvent extends TrainFormEvent {
        DeleteEvent(TrainForm source, Train train) {
            super(source, train);
        }

    }

    public static class CloseEvent extends TrainFormEvent {
        CloseEvent(TrainForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}