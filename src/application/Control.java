package application;

import java.util.Random;
import java.util.Vector;

/**
 * La clase Control es responsable de controlar el juego de memoria.
 * Administra la generación de botones, asignación de imágenes, intercambio de botones, ocultamiento de botones,
 * comprobación de victoria y otras funciones relacionadas con el juego.
 */
public class Control {
    private static final int FILAS = 5, COLUMNAS = 6; // Constantes de la cantidad de botones.
    private Vector<String> numero;
    private Botones[] botones;
    private Random aleatorio;
    private int ramdon, fichas, cambio, tipo1, tipo2, identi1, identi2, contador;

    /**
     * Constructor de la clase Control.
     * Inicializa las variables y prepara los números aleatorios para los botones.
     */
    public Control() {
        fichas = FILAS * COLUMNAS;
        botones = new Botones[FILAS * COLUMNAS];
        numero = new Vector<String>();
        aleatorio = new Random();
        prepararRandom();
    }

    /**
     * Prepara los números aleatorios para los botones.
     * Los números se agregan al vector número dos veces cada uno.
     */
    public void prepararRandom() {
        for (int i = 0; i < (FILAS * COLUMNAS / 2); i++) {
            numero.addElement(String.valueOf(i + 1));
            numero.addElement(String.valueOf(i + 1));
        }
    }

    /**
     * Genera un número aleatorio y lo obtiene del vector número.
     * El número seleccionado se elimina del vector y se reduce el contador de fichas disponibles.
     *
     * @return El número aleatorio obtenido.
     */
    public int ramdonNumero() {
        int retorno;
        ramdon = aleatorio.nextInt(fichas);
        retorno = Integer.parseInt(numero.elementAt(ramdon));
        numero.removeElementAt(ramdon);
        fichas -= 1;
        return retorno;
    }

    /**
     * Crea y configura los botones del juego.
     *
     * @return Un arreglo de objetos Botones.
     */
    public Botones[] crearBotones() {
        for (int i = 0; i < FILAS * COLUMNAS; i++) {
            botones[i] = new Botones();
            botones[i].setNumero(ramdonNumero());
            botones[i].setIdentificador(i);
        }
        return botones;
    }

    /**
     * Asigna las imágenes correspondientes a los botones.
     *
     * @param arreglo El arreglo de objetos Botones.
     * @param tema    El tema de las imágenes.
     */
    public void asignarImagenes(Botones[] arreglo, String tema) {
        for (int i = 0; i < FILAS * COLUMNAS; i++) {
            arreglo[i].setTema(tema);
        }
    }

    /**
     * Realiza el intercambio de botones y verifica si coinciden.
     * Si se selecciona el primer botón, se guarda su tipo y su identificador.
     * Si se selecciona el segundo botón, se verifica si coincide con el primero.
     * Si coinciden, se desactivan ambos botones y se incrementa el contador de aciertos.
     * Si no coinciden, se muestra y oculta nuevamente la imagen de ambos botones.
     *
     * @param boton   El botón seleccionado.
     * @param arreglo El arreglo de objetos Botones.
     */
    public void cambiar(Botones boton, Botones[] arreglo) {
        cambio += 1;
        boton.mostrarOcultarImg(1);
        if (cambio % 2 != 0) {
            tipo1 = boton.getTipo();
            identi1 = boton.getIdentificador();
        } else {
            tipo2 = boton.getTipo();
            identi2 = boton.getIdentificador();
            if (tipo1 == tipo2 && identi1 != identi2) {
                boton.setDisable(true);
                arreglo[identi1].setDisable(true);
                contador += 1;
            } else {
                boton.mostrarOcultarImg(0);
                arreglo[identi1].mostrarOcultarImg(0);
            }
        }
    }

    /**
     * Oculta todas las imágenes de los botones después de un tiempo determinado.
     *
     * @param boton El arreglo de objetos Botones.
     */
    public void ocultar(Botones[] boton) {
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(1500);
                for (int i = 0; i < FILAS * COLUMNAS; i++) {
                    boton[i].setGraphic(null);
                    Thread.sleep(30);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        hilo.start();
    }

    /**
     * Verifica si se ha alcanzado el estado de victoria.
     *
     * @return true si se ha ganado el juego, false de lo contrario.
     */
    public boolean ganador() {
        boolean gano = false;
        if (contador == (FILAS * COLUMNAS / 2)) {
            gano = true;
        }
        return gano;
    }

    /**
     * Desbloquea los botones después de un tiempo determinado.
     *
     * @param boton El arreglo de objetos Botones.
     */
    public void desbloquearBotones(Botones[] boton) {
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(900);
                for (int i = FILAS * COLUMNAS - 1; i >= 0; i--) {
                    boton[i].setDisable(false);
                    Thread.sleep(30);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        hilo.start();
    }

    /**
     * Prepara el juego para continuar después de completar un nivel.
     *
     * @param botones El arreglo de objetos Botones.
     */
    public void seguirJugando(Botones[] botones) {
        prepararRandom();
        fichas = FILAS * COLUMNAS;
        contador = 0;
        cambio = 0;
        for (int i = 0; i < FILAS * COLUMNAS; i++) {
            botones[i].setNumero(ramdonNumero());
        }
    }
}
