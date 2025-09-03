package agenda.ui;

import agenda.exceptions.*;
import agenda.modelo.Agenda;
import agenda.modelo.Contacto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Agenda agenda = new Agenda();
    private ObservableList<Contacto> datos = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        // === Tabla de contactos ===
        TableView<Contacto> tabla = new TableView<>();
        tabla.setItems(datos);

        TableColumn<Contacto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombre()));
        colNombre.setPrefWidth(120);

        TableColumn<Contacto, String> colApellido = new TableColumn<>("Apellido");
        colApellido.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getApellido()));
        colApellido.setPrefWidth(120);

        TableColumn<Contacto, String> colTel = new TableColumn<>("Teléfono");
        colTel.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTelefono()));
        colTel.setPrefWidth(120);

        tabla.getColumns().addAll(colNombre, colApellido, colTel);

        // === Campos de texto ===
        TextField txtNom = new TextField(); txtNom.setPromptText("Nombre");
        TextField txtApe = new TextField(); txtApe.setPromptText("Apellido");
        TextField txtTel = new TextField(); txtTel.setPromptText("Teléfono");

        // === Botones principales ===
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            try {
                Contacto c = new Contacto(txtNom.getText(), txtApe.getText(), txtTel.getText());
                agenda.anadirContacto(c);
                datos.add(c);
                txtNom.clear(); txtApe.clear(); txtTel.clear();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            try {
                Contacto c = agenda.buscarContacto(txtNom.getText(), txtApe.getText());
                new Alert(Alert.AlertType.INFORMATION, "Teléfono: " + c.getTelefono()).showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> {
            Contacto seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                try {
                    agenda.eliminarContacto(seleccionado);
                    datos.remove(seleccionado);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Seleccione un contacto para eliminar").showAndWait();
            }
        });

        Button btnModificar = new Button("Modificar Teléfono");
        btnModificar.setOnAction(e -> {
            Contacto seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                TextInputDialog dialog = new TextInputDialog(seleccionado.getTelefono());
                dialog.setTitle("Modificar Teléfono");
                dialog.setHeaderText("Modificar número de " + seleccionado.getNombre() + " " + seleccionado.getApellido());
                dialog.setContentText("Nuevo teléfono:");

                dialog.showAndWait().ifPresent(nuevoTel -> {
                    try {
                        agenda.modificarTelefono(seleccionado.getNombre(), seleccionado.getApellido(), nuevoTel);
                        seleccionado.setTelefono(nuevoTel);
                        tabla.refresh();
                    } catch (Exception ex) {
                        new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Seleccione un contacto para modificar").showAndWait();
            }
        });

        Button btnEspacios = new Button("Espacios Libres");
        btnEspacios.setOnAction(e -> {
            int libres = agenda.espaciosLibres();
            new Alert(Alert.AlertType.INFORMATION, "Espacios libres: " + libres).showAndWait();
        });

        Button btnLlena = new Button("¿Agenda Llena?");
        btnLlena.setOnAction(e -> {
            if (agenda.agendaLlena()) {
                new Alert(Alert.AlertType.WARNING, "La agenda está llena").showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Todavía hay espacio").showAndWait();
            }
        });

        // === Layout ===
        HBox fila1 = new HBox(10, txtNom, txtApe, txtTel, btnAgregar);
        HBox fila2 = new HBox(10, btnBuscar, btnEliminar, btnModificar, btnEspacios, btnLlena);
        VBox root = new VBox(10, tabla, fila1, fila2);

        stage.setScene(new Scene(root, 650, 450));
        stage.setTitle("Agenda Telefónica");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
