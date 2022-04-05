package com.example.application.views;

import com.example.application.data.entity.Race;
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

public class RaceForm extends FormLayout {
    Binder<Race> binder = new BeanValidationBinder<>(Race.class);

    TextField placeFrom = new TextField("Место отправления");
    TextField placeTo = new TextField("Место прибытия");
    ComboBox<Train> train = new ComboBox<>("Поезд");

    Button saveButton = new Button("Сохранить");
    Button deleteButton = new Button("Удалить");
    Button cancelButton = new Button("Отмена");
    private Race race;

    public RaceForm(List<Train> trains) {

        binder.bindInstanceFields(this);

        train.setItems(trains);
        train.setItemLabelGenerator(Train::getTitle);

        add(
                placeFrom,
                placeTo,
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
        deleteButton.addClickListener(event -> fireEvent(new DeleteEvent(this, race)));
        cancelButton.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(race);
            fireEvent(new SaveEvent(this, race));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setRace(Race race) {
        this.race = race;
        binder.readBean(race);
    }

    // Events
    public static abstract class RaceFormEvent extends ComponentEvent<RaceForm> {
        private Race race;

        protected RaceFormEvent(RaceForm source, Race race) {
            super(source, false);
            this.race = race;
        }

        public Race getRace() {
            return race;
        }
    }

    public static class SaveEvent extends RaceFormEvent {
        SaveEvent(RaceForm source, Race race) {
            super(source, race);
        }
    }

    public static class DeleteEvent extends RaceFormEvent {
        DeleteEvent(RaceForm source, Race race) {
            super(source, race);
        }

    }

    public static class CloseEvent extends RaceFormEvent {
        CloseEvent(RaceForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}