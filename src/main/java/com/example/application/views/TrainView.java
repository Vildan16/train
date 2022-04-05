package com.example.application.views;

import com.example.application.data.entity.Crew;
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

@Route(value = "train", layout = AdminLayout.class)
@PageTitle("Поезда")
class TrainView extends VerticalLayout {
    Grid<Train> grid = new Grid<>(Train.class);
    TextField filterText = new TextField();
    TrainForm trainForm;
    private CrmService service;

    public TrainView(CrmService service) {
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
        trainForm.setTrain(null);
        trainForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllTrain(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, trainForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, trainForm);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        trainForm = new TrainForm(service.findAllCrew(null));
        trainForm.setWidth("30em");

        trainForm.addListener(TrainForm.SaveEvent.class, this::saveTrain);
        trainForm.addListener(TrainForm.DeleteEvent.class, this::deleteTrain);
        trainForm.addListener(TrainForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveTrain(TrainForm.SaveEvent event) {
        service.saveTrain(event.getTrain());
        updateList();
        closeEditor();
    }

    private void deleteTrain(TrainForm.DeleteEvent event) {
        service.deleteTrain(event.getTrain());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Поиск");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Добавить");
        addButton.addClickListener(e -> addTrain());

        return new HorizontalLayout(filterText, addButton);
    }

    private void addTrain() {
        grid.asSingleSelect().clear();
        editTrain(new Train());
    }

    private void configureGrid() {
        grid.addClassNames("crew-grid");
        grid.setSizeFull();

        grid.setColumns("title", "trainNumber");
        grid.getColumns().get(0).setHeader("Название");
        grid.getColumns().get(1).setHeader("Номер");
        grid.addColumn(train -> {
            if (train.getPilot() == null)
                return "";
            return train.getPilot().getLastName();
        }).setHeader("Машинист");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editTrain(e.getValue()));
    }

    private void editTrain(Train train) {
        if (train == null)
            closeEditor();
        else {
            trainForm.setTrain(train);
            trainForm.setVisible(true);
        }
    }
}