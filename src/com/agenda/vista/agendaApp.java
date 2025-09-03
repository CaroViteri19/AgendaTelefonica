package com.agenda.vista;

import controlador.AgendaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AgendaApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        AgendaController controller = new AgendaController();
        Scene scene = new Scene(controller.getVista(), 600, 400);
        primaryStage.setTitle("Agenda Telefónica");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


