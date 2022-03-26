package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "ticket", layout = MainLayout.class)
@PageTitle("Поезда")
class TicketView extends VerticalLayout {
    Button button = new Button("Билеты");

    public TicketView() {
        add(button);
    }
}