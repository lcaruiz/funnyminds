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

/**
 * La clase Botones representa un bot�n personalizado utilizado en la interfaz de usuario.
 * Extiende la clase Button de JavaFX.
 */
public class Botones extends Button {
    private static final int TAMANO = 105;
    private int tipo, identificador;
    private String tema;
    private Border borde;

    /**
     * Constructor de la clase Botones.
     * Configura el tama�o, el borde y la accesibilidad del bot�n.
     */
    public Botones() {
        setPrefSize(TAMANO, TAMANO);
        setFocusTraversable(false);
        borde = new Border(new BorderStroke(Color.rgb(228, 200, 200), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4)));
        setBorder(borde);
    }

    /**
     * Establece el n�mero del bot�n y actualiza su fondo.
     *
     * @param num El n�mero del bot�n.
     */
    public void setNumero(int num) {
        tipo = num;
        setBackground(new Background(new BackgroundFill(Color.rgb(num * 5, 30, 20), CornerRadii.EMPTY, null)));
    }

    /**
     * Establece el tema del bot�n y muestra la imagen correspondiente.
     *
     * @param tem El tema del bot�n.
     */
    public void setTema(String tem) {
        tema = tem;
        Image img = new Image(getClass().getResourceAsStream("/Imagenes/" + tema + tipo + ".png"));
        Platform.runLater(() -> {
            setGraphic(new ImageView(img));
        });
    }

    /**
     * Establece el identificador del bot�n.
     *
     * @param ident El identificador del bot�n.
     */
    public void setIdentificador(int ident) {
        identificador = ident;
    }

    /**
     * Devuelve el tipo del bot�n.
     *
     * @return El tipo del bot�n.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Devuelve el identificador del bot�n.
     *
     * @return El identificador del bot�n.
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Muestra u oculta la imagen del bot�n dependiendo de la operaci�n especificada.
     *
     * @param tipoOperacion El tipo de operaci�n a realizar (0 para ocultar, 1 para mostrar).
     */
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
