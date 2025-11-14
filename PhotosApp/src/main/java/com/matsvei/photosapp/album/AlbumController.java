package com.matsvei.photosapp.album;
import com.matsvei.photosapp.navigation.NavigationService;
import com.matsvei.photosapp.login.DataStore;
import com.matsvei.photosapp.photo.Photo;
import com.matsvei.photosapp.session.AlbumSession;
import com.matsvei.photosapp.session.PhotoSession;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;

public class AlbumController {
    public Button backButton;
    public Button openPhotoButton;
    private Album album;
    private Photo selectedPhoto;

    @FXML
    public void initialize() {
        this.album = AlbumSession.get();
        this.albumName.setText(album.getName());
        displayPhotos();
    }

    @FXML
    private Label albumName;

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
            DataStore.save();
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
            imageView.setPreserveRatio(false);

            Rectangle clip = new Rectangle(120, 120);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            imageView.setClip(clip);

            StackPane container = new StackPane(imageView);
            container.setStyle("-fx-background-radius: 20; -fx-padding: 0;");

            container.setOnMouseClicked(event -> {
                selectedPhoto = photo;
                highlightSelected(container);
            });

            photoTilePane.getChildren().add(container);
        }
    }

    private void highlightSelected(StackPane selectedContainer) {
        for (Node node : photoTilePane.getChildren()) {
            node.setStyle("");
        }
        selectedContainer.setStyle(
                "-fx-border-color: #1E90FF; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 12; " +
                        "-fx-padding: 0;"
        );
    }

    @FXML
    private void onOpenPhoto(ActionEvent event) {
        try {
            PhotoSession.set(selectedPhoto);
            NavigationService.navigate("/com/matsvei/photosapp/photo.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            NavigationService.navigate("/com/matsvei/photosapp/home.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
