package com.matsvei.photosapp.photo;

import com.matsvei.photosapp.album.AlbumController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PhotoController {
    private Photo photo;

    @FXML private ImageView photoImageView;
    @FXML private Label captionLabel;
    @FXML private Label dateLabel;

    public void setPhoto(Photo photo) {
        this.photo = photo;

        Image image = new Image(new File(photo.getFilePath()).toURI().toString());
        photoImageView.setImage(image);

        captionLabel.setText(photo.getCaption());

        dateLabel.setText(photo.getDate().toString());
    }
}
