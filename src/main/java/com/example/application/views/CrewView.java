package com.example.application.views;

import com.example.application.data.entity.Crew;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route(value = "crew", layout = MainLayout.class)
@PageTitle("Работники")
public class CrewView extends VerticalLayout {
    Grid<Crew> grid = new Grid<>(Crew.class);
    TextField filterText = new TextField();
    CrewForm crewForm;
    private CrmService service;

    public CrewView(CrmService service) {
        this.service = service;
        addClassName("crew-view");
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
        crewForm.setCrew(null);
        crewForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllCrew(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, crewForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, crewForm);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        crewForm = new CrewForm(service.findAllTrain());
        crewForm.setWidth("30em");

        crewForm.addListener(CrewForm.SaveEvent.class, this::saveCrew);
        crewForm.addListener(CrewForm.DeleteEvent.class, this::deleteCrew);
        crewForm.addListener(CrewForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveCrew(CrewForm.SaveEvent event) {
        service.saveCrew(event.getCrew());
        updateList();
        closeEditor();
    }

    private void deleteCrew(CrewForm.DeleteEvent event) {
        service.deleteCrew(event.getCrew());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Поиск по имени");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Добавить");
        addButton.addClickListener(e -> addCrew());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addCrew() {
        grid.asSingleSelect().clear();
        editCrew(new Crew());
    }

    private void configureGrid() {
        grid.addClassNames("crew-grid");
        grid.setSizeFull();

        grid.setColumns("firstName", "lastName", "thirdName");
        grid.getColumns().get(0).setHeader("Имя");
        grid.getColumns().get(1).setHeader("Фамилия");
        grid.getColumns().get(2).setHeader("Отчество");
        grid.addColumn(crew -> crew.getTrain().getTitle()).setHeader("Поезд");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editCrew(e.getValue()));
    }

    private void editCrew(Crew crew) {
        if (crew == null)
            closeEditor();
        else {
            crewForm.setCrew(crew);
            crewForm.setVisible(true);
            addClassName("editing");
        }
    }


}