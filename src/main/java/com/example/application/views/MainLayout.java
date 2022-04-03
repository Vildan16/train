package com.example.application.views;

import com.example.application.views.LoginView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.apache.commons.math3.analysis.function.Log;
import org.springframework.cglib.transform.ClassFilter;

@Route("main")
public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Ж/Д касса");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        Tabs tabs = getTabs();
        Button logout = new Button("Выйти", e -> getUI().ifPresent(ui -> ui.navigate(LoginView.class)));
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        addToDrawer(tabs);
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), title, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(title);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        addToNavbar(header);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(
                createTab(VaadinIcon.MENU, "Меню", MenuView.class),
                createTab(VaadinIcon.TRAIN, "Поезда", TrainView.class),
                createTab(VaadinIcon.CART, "Билеты", TicketView.class),
                createTab(VaadinIcon.USERS, "Экипаж", CrewView.class),
                createTab(VaadinIcon.FILE, "Документы", PassportView.class)
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class class_) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(class_);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}