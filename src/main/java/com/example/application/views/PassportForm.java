package com.example.application.views;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Passport;
import com.example.application.data.entity.Train;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class PassportForm extends FormLayout {
    Binder<Passport> binder = new BeanValidationBinder<>(Passport.class);

    TextField firstName = new TextField("Имя");
    TextField lastName = new TextField("Фамилия");
    TextField thirdName = new TextField("Отчество");
    TextField number = new TextField("Номер");
    DatePicker date = new DatePicker("Дата выдачи");
    TextField region = new TextField("Место выдачи");

    Button saveButton = new Button("Сохранить");
    Button deleteButton = new Button("Удалить");
    Button cancelButton = new Button("Отмена");
    private Passport passport;

    public PassportForm(List<Crew> owners) {

        binder.bindInstanceFields(this);

        add(
                firstName,
                lastName,
                thirdName,
                number,
                date,
                region,
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
        deleteButton.addClickListener(event -> fireEvent(new PassportForm.DeleteEvent(this, passport)));
        cancelButton.addClickListener(event -> fireEvent(new PassportForm.CloseEvent(this)));
        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(passport);
            fireEvent(new PassportForm.SaveEvent(this, passport));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
        binder.readBean(passport);
    }

    // Events
    public static abstract class PassportFormEvent extends ComponentEvent<PassportForm> {
        private Passport passport;

        protected PassportFormEvent(PassportForm source, Passport passport) {
            super(source, false);
            this.passport = passport;
        }

        public Passport getPassport() {
            return passport;
        }
    }

    public static class SaveEvent extends PassportForm.PassportFormEvent {
        SaveEvent(PassportForm source, Passport passport) {
            super(source, passport);
        }
    }

    public static class DeleteEvent extends PassportForm.PassportFormEvent {
        DeleteEvent(PassportForm source, Passport passport) {
            super(source, passport);
        }

    }

    public static class CloseEvent extends PassportForm.PassportFormEvent {
        CloseEvent(PassportForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

