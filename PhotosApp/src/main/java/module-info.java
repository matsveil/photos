module com.matsvei.photosapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.matsvei.photosapp to javafx.fxml;
    exports com.matsvei.photosapp;
}