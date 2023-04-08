module com.example.projekti_knk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekti_knk to javafx.fxml;
    exports com.example.projekti_knk;
}