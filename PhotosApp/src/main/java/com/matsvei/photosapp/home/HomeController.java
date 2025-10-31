package com.matsvei.photosapp.home;

import com.matsvei.photosapp.albums.Album;
import com.matsvei.photosapp.login.DataStore;   
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;  
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.TilePane;

import java.io.IOException;  
import java.util.Optional;  

public class HomeController {
    public Button logoutButton;
    private User user;

    private Album selectedAlbum;

    @FXML
    public TilePane albumTilePane;

    @FXML
    private Label welcomeText;

    @FXML
    private Button createAlbumButton;

    @FXML
    private Button deleteAlbumButton;

    @FXML
    private Button renameAlbumButton;

    @FXML
    private Button openAlbumButton;

    // This @FXML annotation is not needed for this method
    // @FXML
    // protected void onHelloButtonClick() {
    //     welcomeText.setText("Welcome to JavaFX Application!");
    // }

    public void setUser(User user) {
        this.user = user;
        welcomeText.setText("Welcome, " + user.getUsername() + "!");

        refreshAlbumTiles();
    }

    private void refreshAlbumTiles() {
        albumTilePane.getChildren().clear();

        for (Album album : user.albums) {
            Button tile = new Button(album.getName());
            tile.setPrefSize(140, 140);
            tile.setWrapText(true);
            tile.getStyleClass().add("album-tile");

            tile.setOnAction(e -> {
                selectedAlbum = album;

                // Clear highlight from all tiles
                albumTilePane.getChildren().forEach(node -> node.getStyleClass().remove("album-tile-selected"));

                // Highlight this tile
                tile.getStyleClass().add("album-tile-selected");

                System.out.println("Selected album: " + album.getName());
            });

            albumTilePane.getChildren().add(tile);
        }
    }




    @FXML
    private void onCreateAlbum(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Album");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter album name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (name.trim().isEmpty()) {
                showError("Album name cannot be empty.");
                return;
            }

            // Check for duplicate album names
            boolean nameExists = user.getAlbums().stream()
                                    .anyMatch(album -> album.getName().equalsIgnoreCase(name.trim()));

            if (nameExists) {
                showError("An album with this name already exists.");
            } else {
                Album newAlbum = new Album(name.trim());

                user.addAlbum(newAlbum);
                refreshAlbumTiles();

            }
        });
    }

    @FXML
    private void onDeleteAlbum(ActionEvent event) {

        if (selectedAlbum == null) {
            showError("Please select an album to delete.");
            return;
        }

        // TODO: Show a confirmation dialog here

        user.removeAlbum(selectedAlbum);
        refreshAlbumTiles();
        selectedAlbum = null;
    }

    @FXML
    private void onRenameAlbum(ActionEvent event) {
        if (selectedAlbum == null) {
            showError("Please select an album to rename.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selectedAlbum.getName());
        dialog.setTitle("Rename Album");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newName -> {
            if (newName.trim().isEmpty()) {
                showError("Album name cannot be empty.");
                return;
            }

            // Check if new name is the same as old name
            if (newName.trim().equalsIgnoreCase(selectedAlbum.getName())) {
                return; // Nothing to do
            }

            // Check for duplicate album names
            boolean nameExists = user.getAlbums().stream()
                    .anyMatch(album -> album.getName().equalsIgnoreCase(newName.trim()));

            if (nameExists) {
                showError("An album with this name already exists.");
            } else {
                selectedAlbum.setName(newName.trim());
                int index = user.albums.indexOf(selectedAlbum);
                user.albums.set(index, selectedAlbum);
                refreshAlbumTiles();
            }
        });
    }

    @FXML
    private void onOpenAlbum(ActionEvent event) {
        if (selectedAlbum == null) {
            showError("Please select an album to open.");
            return;
        }

        // TODO: Implement album opening logic
        System.out.println("Opening album: " + selectedAlbum.getName());
        // We'll implement this part later
    }

    // A helper method for showing errors
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void onLogout(ActionEvent actionEvent) {
        DataStore.save(); // Save all changes before logging out

        user = null;
        try {
            // Updated this path to be more robust
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/matsvei/photosapp/login.fxml"));
            Parent root = loader.load();

            Scene scene = logoutButton.getScene();
            javafx.stage.Stage stage = (javafx.stage.Stage) scene.getWindow();

            stage.setScene(new Scene(root)); 
            stage.show();

        } catch (IOException e) { // <-- Catch IOException specifically
            e.printStackTrace();
        }
    }
}