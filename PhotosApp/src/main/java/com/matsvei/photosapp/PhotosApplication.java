package com.matsvei.photosapp;

import com.matsvei.photosapp.login.DataStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhotosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataStore.load();

        FXMLLoader fxmlLoader = new FXMLLoader(PhotosApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Photos");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
