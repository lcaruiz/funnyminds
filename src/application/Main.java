package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * La clase Main es la clase principal que inicia la aplicación de memoria.
 */
public class Main extends Application {
    
    /**
     * El método start se ejecuta al iniciar la aplicación y crea la vista de la memoria.
     *
     * @param primaryStage El escenario principal de la aplicación.
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
     * El método main es el punto de entrada de la aplicación.
     *
     * @param args Los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
