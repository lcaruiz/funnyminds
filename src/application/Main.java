package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	 @Override
	    public void start(Stage primaryStage) {
	        MemoriaGUI vista = new MemoriaGUI();
	        primaryStage.setOnCloseRequest(event -> {
	            System.out.println("Bienvenidos a funny minds");
	        });
	        vista.start(primaryStage);
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
}
