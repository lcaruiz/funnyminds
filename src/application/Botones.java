package application;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderWidths;

public class Botones extends Button {
    private static final int TAMANO = 105; 
    private int tipo, identificador;
    private String tema;
    private Border borde;

    public Botones() {
        setPrefSize(TAMANO, TAMANO);
        setFocusTraversable(false);
        borde = new Border(new BorderStroke(Color.rgb(228, 200, 200), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4)));
        setBorder(borde);
    }

    public void setNumero(int num) {
        tipo = num;
        setBackground(new Background(new BackgroundFill(Color.rgb(num * 5, 30, 20), CornerRadii.EMPTY, null)));
    }

    public void setTema(String tem) {
        tema = tem;
        Image img = new Image(getClass().getResourceAsStream("/Imagenes/" + tema + tipo + ".png"));
        Platform.runLater(() -> {
            setGraphic(new ImageView(img));
        });
    }

    public void setIndentificador(int ident) {
        identificador = ident;
    }

    public int getTipo() {
        return tipo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void mostrarOcultarImg(int tipoOperacion) {
        Thread hilo = new Thread(() -> {
            try {
                int aux = 0;
                switch (tipoOperacion) {
                    case 0:
                        Thread.sleep(500);
                        aux = 92;
                        break;
                    case 1:
                        aux = 0;
                        break;
                    default:
                        break;
                }
                for (int i = 1; i < 92; i += 5) {
                    Image img = new Image(getClass().getResourceAsStream("/Imagenes/" + tema + tipo + ".png"), Math.abs(aux - i), 91, true, true);
                    Platform.runLater(() -> {
                        setGraphic(new ImageView(img));
                    });
                    Thread.sleep(5);
                }
                if (tipoOperacion == 0) {
                    Platform.runLater(() -> {
                        setGraphic(null);
                    });
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        hilo.start();
    }
}
