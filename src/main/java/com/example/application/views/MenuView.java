package com.example.application.views;

import com.example.application.data.entity.Race;
import com.example.application.data.entity.Ticket;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "menu", layout = MainLayout.class)
@PageTitle("Меню")
public class MenuView extends VerticalLayout {
    Grid<Ticket> grid = new Grid<>(Ticket.class);
    CrmService service;

    MenuView(CrmService service) {
        this.service = service;
        configureGrid();

        add(getToolBar(),grid);
        updateList();
    }

    private Component getToolBar() {
        Button buyTicketButton = new Button("Купить билет");

        HorizontalLayout toolbar = new HorizontalLayout(buyTicketButton);
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.findAllTicket());
    }

    private void configureGrid() {
        grid.setColumns();
        grid.addColumn(ticket -> ticket.getRace().getFrom() != null ? ticket.getRace().getFrom() : "").setHeader("От");
        grid.addColumn(ticket -> ticket.getRace().getTo() != null ? ticket.getRace().getTo() : "").setHeader("До");
        grid.addColumn(Ticket::getDateFrom).setHeader("Дата отправления");
        grid.addColumn(Ticket::getDateTo).setHeader("Дата отправления");
        grid.addColumn(Ticket::getTimeFrom).setHeader("Дата отправления");
        grid.addColumn(Ticket::getTimeTo).setHeader("Дата отправления");
        grid.addColumn(Ticket::getPrice).setHeader("Цена");
    }
}
