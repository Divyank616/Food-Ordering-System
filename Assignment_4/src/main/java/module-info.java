module com.example.assignment_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;
    requires org.testng;
    requires org.junit.jupiter.api;
    requires junit;

    opens com.example.assignment_4 to javafx.fxml;
    exports com.example.assignment_4;
}