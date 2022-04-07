package com.example.application.views;

import com.example.application.data.entity.Kassa;
import com.example.application.data.entity.Race;
import com.example.application.data.entity.Ticket;
import com.example.application.data.entity.Train;
import com.example.application.data.repository.KassaRepository;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Locale;
import java.util.Optional;

@Route(value = "raceKassa", layout = KassaLayout.class)
@PageTitle("Поезда")
public class RaceKassaView extends VerticalLayout {
    Grid<Race> grid = new Grid<>(Race.class);
    TextField filterText = new TextField();
    private final CrmService service;
    Dialog dialogAddTicket = new Dialog();
    Ticket ticket;
    Kassa kassa;
    Race race;
    String currentUser;
    DatePicker dateFrom = new DatePicker("Дата отправления");
    DatePicker dateTo = new DatePicker("Дата прибытия");
    TimePicker timeFrom = new TimePicker("Время отправления");
    TimePicker timeTo = new TimePicker("Время прибытия");
    NumberField wagonNumber = new NumberField("Номер вагона");
    NumberField seatNumber = new NumberField("Номер места");
    NumberField kassaNumber = new NumberField("Номер кассы");
    ComboBox<String> class_ = new ComboBox<>("Класс");
    NumberField price = new NumberField("Цена");

    public RaceKassaView(CrmService service) {
        this.service = service;
        setSizeFull();

        dateFrom.setLocale(Locale.UK);
        dateTo.setLocale(Locale.UK);
        timeFrom.setLocale(Locale.UK);
        timeTo.setLocale(Locale.UK);

        VerticalLayout dialogLayout = createDialogLayout(dialogAddTicket);
        dialogAddTicket.add(dialogLayout);
        configureGrid();

        add(
                getToolbar(),
                getContent()
        );

        updateList();
    }

    private VerticalLayout createDialogLayout(Dialog dialog) {
        dialog.getElement().setAttribute("aria-label", "New ticket");

        H2 headline = new H2("Купить билет");
        headline.getStyle().set("margin-top", "0");

        class_.setItems("Общий", "Купейный", "СВ");

        Button cancelButton = new Button("Отмена", e -> dialog.close());
        Button saveButton = new Button("Добавить", e -> {validateAndSave(); dialog.close();});
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.getStyle().set("margin-top", "var(--lumo-space-m)");

        VerticalLayout dialogLayout = new VerticalLayout(
                headline,
                dateFrom,
                dateTo,
                timeFrom,
                timeTo,
                wagonNumber,
                seatNumber,
                class_,
                price,
                kassaNumber,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;
    }


    private void updateList() {
        grid.setItems(service.findAllRace(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1);
        content.setSizeFull();

        return content;
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Поиск");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Добавить билет в продажу");
        addButton.addClickListener(e -> {dialogAddTicket.open();});

        return new HorizontalLayout(filterText, addButton);
    }

    private void validateAndSave() {
        ticket = new Ticket();
        ticket.setDateFrom(dateFrom.getValue());
        ticket.setDateTo(dateTo.getValue());
        ticket.setTimeFrom(timeFrom.getValue());
        ticket.setTimeTo(timeTo.getValue());
        ticket.setRace(race);
        ticket.setWagonNumber(wagonNumber.getValue().longValue());
        ticket.setSeatNumber(seatNumber.getValue().longValue());
        ticket.setKassaNumber(kassaNumber.getValue().longValue());
        ticket.setPrice(price.getValue().longValue());
        ticket.setClass_(class_.getValue());
        service.saveTicket(ticket);
    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.setColumns("placeFrom", "placeTo");
        grid.getColumns().get(0).setHeader("От");
        grid.getColumns().get(1).setHeader("До");
        grid.addColumn(race -> race.getTrain() != null ? race.getTrain().getTitle() : "-").setHeader("Поезд");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addSelectionListener(selection -> {
            Optional<Race> optionalRace = selection.getFirstSelectedItem();
            optionalRace.ifPresent(value -> race = value);
            });
    }
}