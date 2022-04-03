package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

@Route("")
public class LoginView extends VerticalLayout {
    String currentUser;

    public LoginView() {
        setSizeFull();
        getStyle()
                .set("background-color", "var(--lumo-contrast-5pct)")
                .set("display", "flex")
                .set("justify-content", "center")
                .set("padding", "var(--lumo-space-l)");
        TextField textFieldLogin = new TextField("Логин");
        Button button = new Button("Войти");
        button.addClickListener(e -> {
            this.currentUser = textFieldLogin.getValue();
            System.out.println(currentUser);
            getUI().ifPresent(ui -> ui.navigate(MenuView.class));
        });
        this.setAlignItems(Alignment.CENTER);
        add(new H4("Авторизация"), textFieldLogin, new PasswordField("Пароль"),
                button);

    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
