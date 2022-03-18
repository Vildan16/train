package com.example.application.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

@PageTitle("ЖД касса")
@Route(value = "")
public class ListView extends VerticalLayout {

    public ListView() {
        setSizeFull();
        Button button = new Button("Logout");
        Tabs tabs = configureTabs();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        setHorizontalComponentAlignment(Alignment.CENTER, button);
        horizontalLayout.add(tabs, button);
        add(horizontalLayout);
    }

    private Tabs configureTabs() {
        Tab profile = new Tab(
                VaadinIcon.USER.create(),
                new Span("Profile")
        );
        Tab settings = new Tab(
                VaadinIcon.COG.create(),
                new Span("Settings")
        );
        Tab notifications = new Tab(
                VaadinIcon.BELL.create(),
                new Span("Notifications")
        );

        for (Tab tab : new Tab[] { profile, settings, notifications }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        return (new Tabs(profile, settings, notifications));
    }

}
