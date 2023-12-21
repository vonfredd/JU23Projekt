module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jpa;
    requires jakarta.persistence;
    opens org.example.demo.combiner to javafx.base;
    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}