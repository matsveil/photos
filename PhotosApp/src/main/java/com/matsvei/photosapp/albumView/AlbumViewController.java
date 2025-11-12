package com.matsvei.photosapp.albumView;
import com.matsvei.photosapp.home.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;

import java.io.File;
import javafx.fxml.FXML;

public class AlbumViewController {

    public Label albumName;
    public Button backButton;
    public Button addPhotoButton;
    private Album album;

    public void setAlbum(Album album) {
        this.album = album;
        displayPhotos(); // optional: show existing photos immediately
    }

    @FXML
    private TilePane photoTilePane;

    @FXML
    public void onAddPhoto(javafx.event.ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");

        // Optional: restrict to photos
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            album.addPhoto(new Photo(file.getAbsolutePath()));
            displayPhotos();
            System.out.println("Added: " + file.getAbsolutePath());
        }
    }

    private void displayPhotos() {
        photoTilePane.getChildren().clear();

        for (Photo photo : album.getPhotos()) {
            File file = new File(photo.getFilePath());
            Image image = new Image(file.toURI().toString(), 120, 120, true, true);
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(120);
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);

            photoTilePane.getChildren().add(imageView);
        }
    }
}
