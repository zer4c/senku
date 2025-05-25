/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.controller;

import java.util.List;
import grupo8.senku.UI.*;
import grupo8.senku.model.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author pablo
 */
public class ControllerUI implements PropertyChangeListener {
    private javax.swing.JFrame JFpantallaActuals;
    private final List<javax.swing.JFrame> LhistPantallas;
    private final int iancho;
    private final int ialto;
    private Juego Jjuego;
    private int ifilJugada;
    private int icolJugada;
    private final ReproductorSonido RS;
    private boolean bhayEfectos;
    
    public ControllerUI() {
        LhistPantallas = List.of(new FrameWelcomeS(),
                new FrameJugar(this),
                new Seleccionjuego(this),
                new SeleccionNiveles(this),
                new VentanaJuego(this),
                new Ganaste(this),
                new Perdiste(this));
        JFpantallaActuals = LhistPantallas.get(0);
        RS = new ReproductorSonido();
        iancho = 1280;
        ialto = 720;
        ifilJugada = -100;
        icolJugada = -100;
        bhayEfectos = true;
    }

    public void viniciarApp() {
        JFpantallaActuals.setVisible(true);
        JFpantallaActuals.setLocationRelativeTo(null);
        try {
            Thread.sleep(2000);
            RS.vreproducirAudioFondo();
            vcambiarPantalla(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControllerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vregresarPantalla() {
        if(bhayEfectos)RS.vreproducirBotonMenu();
        JFpantallaActuals.setVisible(false);
        int icontPant = 0;
        boolean bEncontrado = false;
        while (false == bEncontrado) {
            if (JFpantallaActuals.equals(LhistPantallas.get(icontPant))) {
                bEncontrado = true;
                icontPant--;
            } else {
                icontPant++;
            }
        }
        JFpantallaActuals = LhistPantallas.get(icontPant);
        JFpantallaActuals.setVisible(true);
    }

    public void vseleccionarFacil() {
        ModelTablero MTtriangulo = new ModelTableroTriangular();
        Jjuego = new Juego(MTtriangulo);

        vcambiarPantalla(3);
    }

    public void vseleccionarDificil() {
        ModelTablero MTcruz = new ModelTableroCruz();
        Jjuego = new Juego( MTcruz);
        vcambiarPantalla(3);
    }

    public void viniciarJuego() {
        vrellenarTablero();
        vsetCronometro();
        vusarCronometro();
        vcambiarPantalla(4);
    }
    
    private void vrellenarTablero(){
        ModelTablero MTtablero = Jjuego.MTgetTablero();
        VentanaJuego Vjuego = (VentanaJuego) LhistPantallas.get(4);
        Vjuego.vvaciarTablero();
        Vjuego.vsetError("");
        Vjuego.vIniciarBotones(MTtablero.igetFilas(), MTtablero.igetColumnas());
        for (int i = 0; i < MTtablero.igetFilas(); i++) {
            for (int j = 0; j < MTtablero.igetColumnas(); j++) {
                if (!MTtablero.MFgetFicha(i, j).besInvisible()) {
                    if (MTtablero.MFgetFicha(i, j).bestaEliminada()) {
                        Vjuego.vAgregarBotonesTablero(i, j, 0);
                    } else {
                        Vjuego.vAgregarBotonesTablero(i, j, 1);
                    }
                } else {
                    Vjuego.vAgregarBotonesTablero(i, j, -1);
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Juego.SactTiempo.equals(evt.getPropertyName())) {
            int itiempo = (int) evt.getNewValue();
            VentanaJuego Vjuego = (VentanaJuego) LhistPantallas.get(4);
            Vjuego.setCronometro(itiempo);
        }
    }

    public void vmandarJugada(int ifilaAct, int icolAct) {
        if (ifilJugada < 0) {
            if(bhayEfectos)RS.vreproducirBotonMenu();
            ifilJugada = ifilaAct;
            icolJugada = icolAct;
        } else {
            int iauxFil = ifilaAct - ifilJugada;
            int iauxCol = icolAct - icolJugada;
            ModelTablero tablero = Jjuego.MTgetTablero();
            VentanaJuego VJvent = (VentanaJuego) LhistPantallas.get(4);
            boolean bpudoComer = false;
            if (tablero instanceof ModelTableroCruz) {
                if (iauxCol == 0) {
                    if (iauxFil == -2) {
                        bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "ARRIBA");
                    } else if (iauxFil == 2) {
                        bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "ABAJO");
                    }
                } else if (iauxFil == 0) {
                    if (iauxCol == -2) {
                        bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "IZQUIERDA");
                    } else if (iauxCol == 2) {
                        bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "DERECHA");
                    } else {

                    }
                }
            } else {
                if (iauxFil == 0) {
                    if (iauxCol == -4) {
                        bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "IZQUIERDA");
                    } else if (iauxCol == 4) {
                        bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "DERECHA");
                    }
                } else if (iauxFil == 2 && iauxCol == 2) {
                    bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "ABAJO_DERECHA");

                } else if (iauxFil == 2 && iauxCol == -2) {
                    bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "ABAJO_IZQUIERDA");

                } else if (iauxFil == -2 && iauxCol == 2) {
                    bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "ARRIBA_DERECHA");

                } else if (iauxFil == -2 && iauxCol == -2) {
                    bpudoComer = tablero.bmoverFicha(ifilJugada, icolJugada, "ARRIBA_IZQUIERDA");
                }
            }
            if(bpudoComer){
                VJvent.vsetError("");
                VJvent.vactivarBoton(ifilaAct, icolAct, tablero.igetColumnas());
                VJvent.veliminarBoton((ifilaAct + ifilJugada) / 2, (icolAct + icolJugada) / 2, tablero.igetColumnas());
                VJvent.veliminarBoton(ifilJugada, icolJugada, tablero.igetColumnas());
                RS.vreproducirComido();
                VJvent.repaint();
                if(Jjuego.ijuegoTerminado() == 1){
                    vcambiarPantalla(5);
                }else if(Jjuego.ijuegoTerminado() == -1){
                    vcambiarPantalla(6);
                }
            }else{
                VJvent.vsetError("movimiento invalido");
            }
            ifilJugada = -100;
            icolJugada = -100;
        }
    }

    public void vusarCronometro() {
        if(bhayEfectos)RS.vreproducirBotonMenu();
        if (Jjuego.bestaPausa()) {
            Jjuego.viniciarCronometro();
        } else {
            Jjuego.vdetenerCronometro();
        }
    }

    public void vreiniciar() {
        if(bhayEfectos)RS.vreproducirBotonMenu();
        Jjuego.vdetenerCronometro();
        Jjuego.veliminarObservador(this);
        if(Jjuego.MTgetTablero() instanceof ModelTableroCruz){
            Jjuego = new Juego( new ModelTableroCruz());
        }else{
            Jjuego = new Juego(new ModelTableroTriangular());
        }
        viniciarJuego();
    }

    private void vsetCronometro() {
        Jjuego.vañadirObservador(this);
    }

    public void virHome() {
        Jjuego.vdetenerCronometro();
        Jjuego.vañadirObservador(this);
        vcambiarPantalla(1);
    }

    public void vjugar() {
        vcambiarPantalla(2);
    }
    
    public void vcontrolarMusica(){
        if(bhayEfectos)RS.vreproducirBotonMenu();
        if(RS.bestaSonando()){
            RS.vdetenerAudioFondo();
        }else{
            RS.vreproducirAudioFondo();
        }
    }
    
    public void vcontrolarEfectoSonido(){
        if(bhayEfectos)RS.vreproducirBotonMenu();
        bhayEfectos = !bhayEfectos;
    }
    
    public void vSalirJuego(){
        if(bhayEfectos)RS.vreproducirBotonMenu();
        try{
            for(JFrame JFpantalla : LhistPantallas){
                JFpantalla.dispose();
            }
            System.exit(0);
        }catch(Exception e){
            System.exit(0);
        }
    }

    private void vcambiarPantalla(int indice) {
        if(bhayEfectos)RS.vreproducirBotonMenu();
        JFpantallaActuals.setVisible(false);
        JFpantallaActuals = LhistPantallas.get(indice);
        JFpantallaActuals.setSize(iancho, ialto);
        JFpantallaActuals.setLocationRelativeTo(LhistPantallas.get(0));
        JFpantallaActuals.setVisible(true);
    }
}
