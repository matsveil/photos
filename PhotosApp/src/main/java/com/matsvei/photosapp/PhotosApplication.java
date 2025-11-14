package com.matsvei.photosapp;

import com.matsvei.photosapp.login.DataStore;
import com.matsvei.photosapp.navigation.NavigationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhotosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataStore.load();

        FXMLLoader fxmlLoader = new FXMLLoader(PhotosApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        NavigationService.initialize(scene);
        stage.setTitle("Photos");
        stage.setScene(scene);
        stage.show();

        System.out.println(DataStore.getAllUsers());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
