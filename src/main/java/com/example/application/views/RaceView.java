package com.example.application.views;

import com.example.application.data.entity.Race;
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

@Route(value = "race", layout = AdminLayout.class)
@PageTitle("Рейсы")
class RaceView extends VerticalLayout {
    Grid<Race> grid = new Grid<>(Race.class);
    TextField filterText = new TextField();
    RaceForm raceForm;
    private CrmService service;

    public RaceView(CrmService service) {
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
        raceForm.setRace(null);
        raceForm.setVisible(false);
    }

    private void updateList() {
        grid.setItems(service.findAllRace(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, raceForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, raceForm);
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        raceForm = new RaceForm(service.findAllTrain(null));
        raceForm.setWidth("30em");

        raceForm.addListener(RaceForm.SaveEvent.class, this::saveRace);
        raceForm.addListener(RaceForm.DeleteEvent.class, this::deleteRace);
        raceForm.addListener(RaceForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveRace(RaceForm.SaveEvent event) {
        service.saveRace(event.getRace());
        updateList();
        closeEditor();
    }

    private void deleteRace(RaceForm.DeleteEvent event) {
        service.deleteRace(event.getRace());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Поиск");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Добавить");
        addButton.addClickListener(e -> addRace());

        return new HorizontalLayout(filterText, addButton);
    }

    private void addRace() {
        grid.asSingleSelect().clear();
        editRace(new Race());
    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.setColumns("placeFrom", "placeTo");
        grid.getColumns().get(0).setHeader("От");
        grid.getColumns().get(1).setHeader("До");
        grid.addColumn(race -> race.getTrain() != null ? race.getTrain().getTitle() : "-").setHeader("Поезд");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editRace(e.getValue()));
    }

    private void editRace(Race race) {
        if (race == null)
            closeEditor();
        else {
            raceForm.setRace(race);
            raceForm.setVisible(true);
        }
    }
}