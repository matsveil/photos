package com.matsvei.photosapp.login;

import com.matsvei.photosapp.home.HomeController;
import com.matsvei.photosapp.home.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button signupButton;

    private void navigateToHome(User user, javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/matsvei/photosapp/home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        controller.setUser(user);

        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new  Scene(root);

        scene.getStylesheets().add(
                getClass().getResource("/com/matsvei/photosapp/home.css").toExternalForm()
        );
        stage.setScene(scene);

        stage.show();
    }

    public void onSignup(javafx.event.ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = new User(username, password);

        try {
            navigateToHome(user, actionEvent);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onLogin(javafx.event.ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = DataStore.getUser(username);

        try {
            navigateToHome(user, actionEvent);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
