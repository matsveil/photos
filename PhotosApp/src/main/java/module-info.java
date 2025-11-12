module com.matsvei.photosapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;

    opens com.matsvei.photosapp to javafx.fxml;
    exports com.matsvei.photosapp;
    exports com.matsvei.photosapp.login;
    opens com.matsvei.photosapp.login to javafx.fxml;
    exports com.matsvei.photosapp.home;
    opens com.matsvei.photosapp.home to javafx.fxml;
    exports com.matsvei.photosapp.album;
    opens com.matsvei.photosapp.album to javafx.fxml;
    exports com.matsvei.photosapp.photo;
    opens com.matsvei.photosapp.photo to javafx.fxml;
}