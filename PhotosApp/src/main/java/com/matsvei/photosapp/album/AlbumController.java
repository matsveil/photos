package com.matsvei.photosapp.album;
import com.matsvei.photosapp.login.DataStore;
import com.matsvei.photosapp.photo.Photo;
import com.matsvei.photosapp.photo.PhotoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class AlbumController {
    public Button backButton;
    private Album album;
    private Photo selectedPhoto;

    public void setAlbum(Album album) {
        this.album = album;
        this.albumName.setText(album.getName());
        displayPhotos(); // optional: show existing photos immediately
    }

    @FXML
    private Button addPhotoButton;

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

            // Rounded corners by masking via a clip
            Rectangle clip = new Rectangle(120, 120);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            imageView.setClip(clip);

            // Wrap the ImageView in a StackPane so we can apply styles on it
            StackPane container = new StackPane(imageView);
            container.setStyle("-fx-background-radius: 20; -fx-padding: 3;");

            container.setOnMouseClicked(event -> {
                selectedPhoto = photo;
                highlightSelected(container);
            });

            photoTilePane.getChildren().add(container);
        }
    }

    private void highlightSelected(StackPane selectedContainer) {
        for (Node node : photoTilePane.getChildren()) {
            node.setStyle("-fx-background-radius: 20; -fx-padding: 3;");
        }

        selectedContainer.setStyle(
                "-fx-background-color: #00aaff33; -fx-background-radius: 20; -fx-padding: 3;"
        );
    }

    private void highlightSelected(ImageView selectedView) {
        for (Node node : photoTilePane.getChildren()) {
            node.setStyle(""); // clear previous highlight
        }
        selectedView.setStyle("-fx-effect: dropshadow(gaussian, #00aaff, 10, 0.5, 0, 0);");
    }

    @FXML
    private void onOpenPhoto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/matsvei/photosapp/photo.fxml"));
            Scene scene = new Scene(loader.load());

            PhotoController controller = loader.getController();
            controller.setPhoto(selectedPhoto);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
