module com.example.pratc8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.pratc8 to javafx.fxml;
    exports com.example.pratc8;
}