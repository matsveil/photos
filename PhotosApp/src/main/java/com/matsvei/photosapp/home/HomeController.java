package com.matsvei.photosapp.home;

import com.matsvei.photosapp.Album;
import com.matsvei.photosapp.login.DataStore;  
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;  
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;  
import javafx.scene.control.TextInputDialog;  
import javafx.scene.control.Alert;  
import javafx.scene.control.Alert.AlertType;  

import java.io.IOException;  
import java.util.Optional;  

public class HomeController {
    public Button logoutButton;
    private User user;

    @FXML
    private Label welcomeText;


    @FXML
    private ListView<Album> albumListView;

    @FXML
    private Button createAlbumButton;

    @FXML
    private Button deleteAlbumButton;

    @FXML
    private Button renameAlbumButton;

    @FXML
    private Button openAlbumButton;

    // This list will automatically update the ListView in the UI
    private ObservableList<Album> observableAlbumList;


    // This @FXML annotation is not needed for this method
    // @FXML
    // protected void onHelloButtonClick() {
    //     welcomeText.setText("Welcome to JavaFX Application!");
    // }

    public void setUser(User user) {
        this.user = user;
        welcomeText.setText("Welcome, " + user.getUsername() + "!");
        // Load the user's albums into the ObservableList
        observableAlbumList = FXCollections.observableArrayList(user.getAlbums());
        
        // Tell the ListView to display the items from this list
        albumListView.setItems(observableAlbumList);
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
                // 1. Create the new album
                Album newAlbum = new Album(name.trim());
                // 2. Add it to the user's list (for saving)
                user.addAlbum(newAlbum);
                // 3. Add it to the observable list (for the UI)
                observableAlbumList.add(newAlbum);
                // 4. Select the new album in the list
                albumListView.getSelectionModel().select(newAlbum);
            }
        });
    }

    @FXML
    private void onDeleteAlbum(ActionEvent event) {
        Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
        if (selectedAlbum == null) {
            showError("Please select an album to delete.");
            return;
        }

        // TODO: Show a confirmation dialog here

        // 1. Remove from the user's list (for saving)
        user.removeAlbum(selectedAlbum);
        // 2. Remove from the observable list (for the UI)
        observableAlbumList.remove(selectedAlbum);
    }

    @FXML
    private void onRenameAlbum(ActionEvent event) {
        Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
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
                // 1. Update the album object
                selectedAlbum.setName(newName.trim());
                
                // 2. Refresh the list view to show the change
                // We get the index, remove the item, and add it back
                // This forces the ListView to re-render the item
                int selectedIndex = albumListView.getSelectionModel().getSelectedIndex();
                observableAlbumList.set(selectedIndex, selectedAlbum);
            }
        });
    }

    @FXML
    private void onOpenAlbum(ActionEvent event) {
        Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
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