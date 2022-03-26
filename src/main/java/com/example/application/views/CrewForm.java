package com.example.application.views;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Train;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class CrewForm extends FormLayout {
    TextField firstName = new TextField("Имя");
    TextField lastName = new TextField("Фамилия");
    ComboBox<Train> train = new ComboBox<>("Поезд");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public CrewForm(List<Train> trains) {
        addClassName("contact-form");

        train.setItems(trains);
        train.setItemLabelGenerator(Train::getTitle);

        add(firstName,
                lastName,
                train,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }
}