package com.matsvei.photosapp.login;

import com.matsvei.photosapp.home.HomeController;
import com.matsvei.photosapp.home.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class LoginController {
    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button signupButton;

    public void onSignup(javafx.event.ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = new User(username, password);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/matsvei/photosapp/home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.setUser(user);

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onLogin(javafx.event.ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = DataStore.getUser(username);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/matsvei/photosapp/home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.setUser(user);

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
