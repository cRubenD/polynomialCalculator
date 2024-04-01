module com.example.GraphicalUserInterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.GraphicalUserInterface to javafx.fxml;
    exports com.example.GraphicalUserInterface;
}