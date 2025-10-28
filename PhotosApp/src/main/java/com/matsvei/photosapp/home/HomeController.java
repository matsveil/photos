package com.matsvei.photosapp.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {
    public Button logoutButton;
    private User user;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setUser(User user) {
        this.user = user;
        welcomeText.setText("Welcome, " + user.getUsername() + "!");
    }


    public void onLogout(ActionEvent actionEvent) {
        user = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/matsvei/photosapp/login.fxml"));
            Parent root = loader.load();

            Scene scene = logoutButton.getScene();
            javafx.stage.Stage stage = (javafx.stage.Stage) scene.getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
