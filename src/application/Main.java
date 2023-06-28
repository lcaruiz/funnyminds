package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * La clase Main es la clase principal que inicia la aplicaci�n de memoria.
 */
public class Main extends Application {
    
    /**
     * El m�todo start se ejecuta al iniciar la aplicaci�n y crea la vista de la memoria.
     *
     * @param primaryStage El escenario principal de la aplicaci�n.
     */
    @Override
    public void start(Stage primaryStage) {
        MemoriaGUI vista = new MemoriaGUI();
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Bienvenidos a funny minds");
        });
        vista.start(primaryStage);
    }

    /**
     * El m�todo main es el punto de entrada de la aplicaci�n.
     *
     * @param args Los argumentos de l�nea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
