package com.example.application.views;

import com.example.application.data.entity.Ticket;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route(value = "ticket", layout = MainLayout.class)
@PageTitle("Поезда")
class TicketView extends VerticalLayout {
    Grid<Ticket> grid = new Grid<>(Ticket.class);
    CrmService service;
    Ticket ticket;
    Dialog dialogBuyTicket = new Dialog();

    TextField number;
    TextField kassanumber;
    TextField wagonNumber;
    TextField seatNumber;
    TextField class_;
    DatePicker dateFrom;
    TimePicker timeFrom;
    DatePicker dateTo;
    TimePicker timeTo;
    TextField firstName;
    TextField lastName;
    TextField thirdName;
    TextField passnumber;
    DatePicker date;
    TextField region;

    TicketView(CrmService service) {
        this.service = service;
        VerticalLayout dialogLayout = createDialogLayout(dialogBuyTicket);
        dialogBuyTicket.add(dialogLayout);
        configureGrid();

        add(getToolBar(),grid);
        updateList();
    }

    private VerticalLayout createDialogLayout(Dialog dialog) {
        dialog.getElement().setAttribute("aria-label", "New ticket");

        H3 headline = new H3("Билет");
        headline.getStyle().set("margin-top", "0");


            number = new TextField("Номер билета");

            number.setEnabled(false);

            kassanumber = new TextField("Номер кассы");
            kassanumber.setEnabled(false);

            wagonNumber = new TextField("Номер вагона");
            wagonNumber.setEnabled(false);

            seatNumber = new TextField("Номер места");
            seatNumber.setEnabled(false);

            class_ = new TextField("Класс");
            class_.setEnabled(false);

            dateFrom = new DatePicker("Дата отправления");
            dateFrom.setEnabled(false);

            timeFrom = new TimePicker("Время отправления");
            timeFrom.setEnabled(false);

            dateTo = new DatePicker("Дата прибытия");
            dateTo.setEnabled(false);

            timeTo = new TimePicker("Время прибытия");
            timeTo.setEnabled(false);

            assert ticket.getOwner() != null;
            firstName = new TextField("Имя");
            firstName.setEnabled(false);

            lastName = new TextField("Фамилия");
            lastName.setEnabled(false);

            thirdName = new TextField("Отчество");
            thirdName.setEnabled(false);

            passnumber = new TextField("Номер паспорта");
            passnumber.setEnabled(false);

            date = new DatePicker("Дата выдачи паспорта");
            date.setEnabled(false);

            region = new TextField("Место выдачи паспорта");
            region.setEnabled(false);

            Button saveButton = new Button("Закрыть", e -> {
                dialog.close();
            });
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            HorizontalLayout buttonLayout = new HorizontalLayout(saveButton);
            buttonLayout
                    .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
            buttonLayout.getStyle().set("margin-top", "var(--lumo-space-m)");

            VerticalLayout dialogLayout = new VerticalLayout(
                    headline,
                     number,
             kassanumber,
             wagonNumber,
             seatNumber,
             class_,
             dateFrom,
             timeFrom,
             dateTo,
             timeTo,
             firstName,
             lastName,
             thirdName,
             passnumber,
             date,
             region,
                    buttonLayout);

            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
    }

    private void buyTicket() {

    }

    private Component getToolBar() {
        Button buyTicketButton = new Button("Просмотр", e -> dialogBuyTicket.open());

        HorizontalLayout toolbar = new HorizontalLayout(buyTicketButton);
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.findAllTicketWhereOwnerIsNotNull());
    }

    private void configureGrid() {
        grid.setColumns();
        grid.addColumn(ticket -> ticket.getRace().getPlaceFrom() != null ? ticket.getRace().getPlaceFrom() : "-").setHeader("От");
        grid.addColumn(ticket -> ticket.getRace().getPlaceTo() != null ? ticket.getRace().getPlaceTo() : "-").setHeader("До");
        grid.addColumn(Ticket::getDateFrom).setHeader("Дата отправления");
        grid.addColumn(Ticket::getDateTo).setHeader("Дата отправления");
        grid.addColumn(Ticket::getTimeFrom).setHeader("Дата отправления");
        grid.addColumn(Ticket::getTimeTo).setHeader("Дата отправления");
        grid.addColumn(Ticket::getPrice).setHeader("Цена");
        grid.addSelectionListener(selection -> {
            Optional<Ticket> optionalRace = selection.getFirstSelectedItem();
            optionalRace.ifPresent(value -> ticket = value);

                number.setValue(String.valueOf(ticket.getNumber()));
                kassanumber.setValue(String.valueOf(ticket.getKassaNumber()));
                wagonNumber.setValue(String.valueOf(ticket.getWagonNumber()));
                seatNumber.setValue(String.valueOf(ticket.getSeatNumber()));
                class_.setValue(ticket.getClass_());
                dateFrom.setValue(ticket.getDateFrom());
                timeFrom.setValue(ticket.getTimeFrom());
                dateTo.setValue(ticket.getDateTo());
                timeTo.setValue(ticket.getTimeTo());
                firstName.setValue(ticket.getOwner().getFirstName());
                lastName.setValue(ticket.getOwner().getLastName());
                thirdName.setValue(ticket.getOwner().getThirdName());
                passnumber.setValue(ticket.getOwner().getNumber());
            date.setValue(ticket.getOwner().getDate());

            region.setValue(ticket.getOwner().getRegion());

        });
    }
}
