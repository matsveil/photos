package com.matsvei.photosapp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PhotosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240, Color.BLACK);
        stage.setTitle("Photos");
        stage.setScene(scene);
        stage.show();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
