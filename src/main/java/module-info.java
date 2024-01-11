module com.example.twodots {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.twodots to javafx.fxml;
    exports com.example.twodots;
}