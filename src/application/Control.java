package application;

import java.util.Random;
import java.util.Vector;

public class Control {
    private static final int FILAS = 5, COLUMNAS = 6; // constantes de la cantidad de botones.
    private Vector<String> numero;
    private Botones[] botones;
    private Random aleatorio;
    private int ramdon, fichas, cambio, tipo1, tipo2, identi1, identi2, contador;

    public Control() {
        fichas = FILAS * COLUMNAS;
        botones = new Botones[FILAS * COLUMNAS];
        numero = new Vector<String>();
        aleatorio = new Random();
        prepararRandom();
    }

    public void prepararRandom() {
        for (int i = 0; i < (FILAS * COLUMNAS / 2); i++) {
            numero.addElement(String.valueOf(i + 1));
            numero.addElement(String.valueOf(i + 1));
        }
    }

    public int ramdonNumero() {
        int retorno;
        ramdon = aleatorio.nextInt(fichas);
        retorno = Integer.parseInt(numero.elementAt(ramdon));
        numero.removeElementAt(ramdon);
        fichas -= 1;
        return retorno;
    }

    public Botones[] crearBotones() {
        for (int i = 0; i < FILAS * COLUMNAS; i++) {
            botones[i] = new Botones();
            botones[i].setNumero(ramdonNumero());
            botones[i].setIndentificador(i);
        }
        return botones;
    }

    public void asignarImagenes(Botones[] arreglo, String tema) {
        for (int i = 0; i < FILAS * COLUMNAS; i++) {
            arreglo[i].setTema(tema);
        }
    }

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

    public boolean ganador() {
        boolean gano = false;
        if (contador == (FILAS * COLUMNAS / 2)) {
            gano = true;
        }
        return gano;
    }

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
