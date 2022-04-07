package com.example.application.views;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Passport;
import com.example.application.data.entity.Train;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "passport", layout = KassaLayout.class)
@PageTitle("Документы")
class PassportView extends VerticalLayout {
    Grid<Passport> grid = new Grid<>(Passport.class);
    TextField filterText = new TextField();
    PassportForm passportForm;
    private CrmService service;

    public PassportView(CrmService service) {
        this.service = service;
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();
        closeEditor();
    }

    private void closeEditor() {
        passportForm.setPassport(null);
        passportForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllPassport(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, passportForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, passportForm);
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        passportForm = new PassportForm(service.findAllCrew(null));
        passportForm.setWidth("30em");

        passportForm.addListener(PassportForm.SaveEvent.class, this::savePassport);
        passportForm.addListener(PassportForm.DeleteEvent.class, this::deletePassport);
        passportForm.addListener(PassportForm.CloseEvent.class, e -> closeEditor());
    }

    private void savePassport(PassportForm.SaveEvent event) {
        service.savePassport(event.getPassport());
        updateList();
        closeEditor();
    }

    private void deletePassport(PassportForm.DeleteEvent event) {
        service.deletePassport(event.getPassport());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Поиск");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Добавить");
        addButton.addClickListener(e -> addPassport());

        return new HorizontalLayout(filterText, addButton);
    }

    private void addPassport() {
        grid.asSingleSelect().clear();
        editPassport(new Passport());
    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.setColumns("firstName", "lastName", "thirdName", "number", "date", "region");
        grid.getColumns().get(0).setHeader("Имя");
        grid.getColumns().get(1).setHeader("Фамилия");
        grid.getColumns().get(2).setHeader("Отчество");
        grid.getColumns().get(3).setHeader("Номер");
        grid.getColumns().get(4).setHeader("Дата выдачи");
        grid.getColumns().get(5).setHeader("Кем выдан");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editPassport(e.getValue()));
    }

    private void editPassport(Passport passport) {
        if (passport == null)
            closeEditor();
        else {
            passportForm.setPassport(passport);
            passportForm.setVisible(true);
        }
    }
}