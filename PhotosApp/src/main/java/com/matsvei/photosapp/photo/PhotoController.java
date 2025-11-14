package com.matsvei.photosapp.photo;
import com.matsvei.photosapp.album.Album;
import com.matsvei.photosapp.navigation.NavigationService;
import com.matsvei.photosapp.session.AlbumSession;
import com.matsvei.photosapp.session.PhotoSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;

public class PhotoController {
    private Photo photo;

    @FXML private ImageView photoImageView;
    @FXML private Label captionLabel;
    @FXML private Label dateLabel;

    @FXML
    private void initialize() {
        this.photo = PhotoSession.get();

        Image image = new Image(new File(photo.getFilePath()).toURI().toString());
        photoImageView.setImage(image);

        captionLabel.setText(photo.getCaption());

        dateLabel.setText(photo.getDate().toString());
    }

    @FXML
    private void onBack() {
        try {
            NavigationService.navigate("/com/matsvei/photosapp/album.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onNext() {
        try {
            Album currentAlbum = AlbumSession.get();
            int index = currentAlbum.getPhotos().indexOf(photo);

            if (index < currentAlbum.getPhotos().size() - 1) {
                PhotoSession.set(currentAlbum.getPhotos().get(index + 1));
                NavigationService.navigate("/com/matsvei/photosapp/photo.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onPrevious() {
        try {
            Album currentAlbum = AlbumSession.get();
            int index = currentAlbum.getPhotos().indexOf(photo);

            if (index > 0) {
                PhotoSession.set(currentAlbum.getPhotos().get(index - 1));
                NavigationService.navigate("/com/matsvei/photosapp/photo.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
