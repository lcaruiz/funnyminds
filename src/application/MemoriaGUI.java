package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * La clase MemoriaGUI es la interfaz de usuario para el juego de memoria.
 */
public class MemoriaGUI extends Application {

    private static final int FILAS = 5, COLUMNAS = 6;
    private Botones[] botones;
    private Control control;
    private BorderPane main;
    private GridPane panel;
    private VBox panelInicial;
    private HBox panelSur;
    private ImageView logo;
    private ComboBox<String> comboBox;
    private Button botonInicio;
    private EventoBoton eventoBoton = new EventoBoton();
    private EventoCombo eventoCombo = new EventoCombo();
    private String elegido = "Pokemon";

    @Override
    public void start(Stage primaryStage) {
        main = new BorderPane();
        ventanaInicial(primaryStage);
        primaryStage.setTitle("Funny minds!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Crea la ventana inicial de la aplicación.
     *
     * @param primaryStage El escenario principal de la aplicación.
     */
    public void ventanaInicial(Stage primaryStage) {
        panelInicial = new VBox();
        panelSur = new HBox();
        botonInicio = new Button("Jugar!");
        botonInicio.setOnAction(eventoBoton);
        botonInicio.setPrefSize(91, 40);
        botonInicio.setStyle("-fx-background-color: rgb(230,200,10);");
        logo = new ImageView(new Image(getClass().getResourceAsStream("/Imagenes/logo.png")));
        String[] temas = { "Pokemon", "Bleach" };
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(temas);
        comboBox.setOnAction(eventoCombo);
        comboBox.setPrefSize(100, 40);
        botonInicio.setDisable(true);
        panelSur.getChildren().addAll(comboBox, botonInicio);
        panelSur.setSpacing(10);
        panelSur.setAlignment(Pos.CENTER);
        panelInicial.getChildren().add(logo);
        panelInicial.getChildren().add(panelSur);
        panelInicial.setSpacing(10);
        panelInicial.setAlignment(Pos.CENTER);
        main.setTop(panelInicial);
        BorderPane.setMargin(panelInicial, new Insets(10, 10, 10, 10));
        Scene scene = new Scene(main, 1280, 1024);
        primaryStage.setScene(scene);
    }

    /**
     * Clase interna para manejar el evento de cambio en el ComboBox.
     */
    private class EventoCombo implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            elegido = comboBox.getValue();
            botonInicio.setDisable(false);
        }
    }

    /**
     * Clase interna para manejar los eventos de los botones.
     */
    private class EventoBoton implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (e.getSource() == botonInicio) {
                control = new Control();
                botones = control.crearBotones();
                control.asignarImagenes(botones, elegido);
                panel = new GridPane();
                panel.setAlignment(Pos.TOP_CENTER);
                panel.setHgap(10);
                panel.setVgap(10);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                for (int i = 0; i < FILAS * COLUMNAS; i++) {
                    botones[i].setOnAction(eventoBoton);
                    panel.add(botones[i], i % COLUMNAS, i / COLUMNAS);
                    botones[i].fire();
                }
                main.setCenter(panel);
                panelInicial.setVisible(false);
            } else {
                Botones boton = (Botones) e.getSource();
                control.cambiar(boton, botones);
                if (control.ganador()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Has ganado");
                    alert.setHeaderText("Felicidades, has ganado el juego!");
                    alert.setContentText("¿Quieres jugar de nuevo?");
                    alert.showAndWait();
                    panel.setVisible(false);
                    panelInicial.setVisible(true);
                    control.seguirJugando(botones);
                }
            }
        }
    }
}
