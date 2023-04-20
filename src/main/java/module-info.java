module com.example.projekti_knk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projekti_knk to javafx.fxml;
    exports com.example.projekti_knk;

    exports service to javafx.graphics;
    exports models to javafx.graphics;
    exports repository to javafx.graphics;
    exports controller to javafx.graphics;
}