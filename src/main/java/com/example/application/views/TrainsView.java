package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "train", layout = MainLayout.class)
@PageTitle("Поезда")
class TrainsView extends VerticalLayout {
    Button button = new Button("Поезда");

    public TrainsView() {
        add(button);
    }
}