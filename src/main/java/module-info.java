module org.example.agendatelefonica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.agendatelefonica to javafx.fxml;
    exports org.example.agendatelefonica;
    exports agenda.modelo;
    exports agenda.ui;
    exports agenda.exceptions;

}