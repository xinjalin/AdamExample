module com.example.adamexample {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.adamexample to javafx.fxml;
    exports com.example.adamexample;
}