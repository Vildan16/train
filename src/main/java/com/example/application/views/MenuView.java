package com.example.application.views;

import com.example.application.data.entity.Race;
import com.example.application.data.entity.Ticket;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "menu", layout = MainLayout.class)
@PageTitle("Меню")
public class MenuView extends VerticalLayout {
    Grid<Ticket> grid = new Grid<>(Ticket.class);
    CrmService service;
    Dialog dialogBuyTicket = new Dialog();

    MenuView(CrmService service) {
        this.service = service;
        VerticalLayout dialogLayout = createDialogLayout(dialogBuyTicket);
        dialogBuyTicket.add(dialogLayout);
        configureGrid();

        add(getToolBar(),grid);
        updateList();
    }

    private VerticalLayout createDialogLayout(Dialog dialog) {
        dialog.getElement().setAttribute("aria-label", "New ticket");

        H2 headline = new H2("Купить билет");
        headline.getStyle().set("margin-top", "0");

        TextField firstName = new TextField("Имя");
        TextField lastName = new TextField("Фамилия");
        TextField thirdName = new TextField("Отчество");
        TextField number = new TextField("Номер паспорта");
        DatePicker date = new DatePicker("Дата выдачи паспорта");
        TextField region = new TextField("Место выдачи паспорта");

        Button cancelButton = new Button("Отмена", e -> dialog.close());
        Button saveButton = new Button("Купить", e -> {buyTicket(); dialog.close();});
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.getStyle().set("margin-top", "var(--lumo-space-m)");

        VerticalLayout dialogLayout = new VerticalLayout(
                headline,
                firstName,
                lastName,
                thirdName,
                number,
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
        Button buyTicketButton = new Button("Купить билет", e -> dialogBuyTicket.open());

        HorizontalLayout toolbar = new HorizontalLayout(buyTicketButton);
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.findAllTicket());
    }

    private void configureGrid() {
        grid.setColumns();
        grid.addColumn(ticket -> ticket.getRace().getFrom() != null ? ticket.getRace().getFrom() : "-").setHeader("От");
        grid.addColumn(ticket -> ticket.getRace().getTo() != null ? ticket.getRace().getTo() : "-").setHeader("До");
        grid.addColumn(Ticket::getDateFrom).setHeader("Дата отправления");
        grid.addColumn(Ticket::getDateTo).setHeader("Дата отправления");
        grid.addColumn(Ticket::getTimeFrom).setHeader("Дата отправления");
        grid.addColumn(Ticket::getTimeTo).setHeader("Дата отправления");
        grid.addColumn(Ticket::getPrice).setHeader("Цена");
    }
}
