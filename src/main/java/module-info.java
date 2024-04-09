module com.example.project213 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project213 to javafx.fxml;
    exports com.example.project213;
}