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
import javax.swing.JButton;
/**
 *
 * @author pablo
 */
public class ControllerUI implements PropertyChangeListener{
    private javax.swing.JFrame actualPantalla;
    private List<javax.swing.JFrame> histPantallas;
    private int ancho;
    private int alto;
    private Juego juego;

    public ControllerUI(){
        histPantallas = List.of(new FrameWelcomeS(this),
                                new FrameJugar(this),
                                new Seleccionjuego(this), 
                                new SeleccionNiveles(this), 
                                new VentanaJuego(this));
        actualPantalla = histPantallas.get(0);
        ancho = 720;
        alto = 1280;
    }
    public void iniciarApp(){
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
        try {
            Thread.sleep(2000);
            cambiarPantalla(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControllerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void regresarPantalla(){
        actualPantalla.setVisible(false);
        int contPant = 0; 
        boolean Encontrado = false;
        while(false == Encontrado){
            if(actualPantalla.equals(histPantallas.get(contPant))){
                Encontrado = true;
                contPant--;
            }else{
                contPant++;
            }
        }
        actualPantalla = histPantallas.get(contPant);
        actualPantalla.setVisible(true);
    }   
    public void seleccionarFacil() {
        ModelTablero triangulo = new ModelTableroTriangular();
        juego = new Juego(this, triangulo);
        
        cambiarPantalla(3);
    }

    public void seleccionarDificil() {
        ModelTablero cruz = new ModelTableroCruz();
        juego = new Juego(this, cruz);
        cambiarPantalla(3);
    }

    public void iniciarJuego() {
        ModelTablero tablero = juego.MTgetTablero();
        VentanaJuego Vjuego = (VentanaJuego)histPantallas.get(4);
        Vjuego.vIniciarBotones( tablero.igetFilas(),tablero.igetColumnas());
        for(int i = 0; i < tablero.igetFilas(); i++){
            for(int j = 0; j < tablero.igetColumnas(); j++){
                if(!tablero.getFicha(i, j).besInvisible()){
                    if(tablero.getFicha(i, j).bestaEliminada()){
                        Vjuego.vAgregarBotonesTablero(new JButton(), 0);
                    }else{
                        Vjuego.vAgregarBotonesTablero(new JButton(), 1);                    
                    }
                }else{
                    Vjuego.vAgregarBotonesTablero(new JButton(), -1);                    
                }
            }
        }
        vsetCronometro();
        vusarCronometro();
        cambiarPantalla(4);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Juego.SactTiempo.equals(evt.getPropertyName())) {
            int itiempo = (int) evt.getNewValue();
            VentanaJuego Vjuego = (VentanaJuego)histPantallas.get(4);
            Vjuego.setCronometro(itiempo);
        }
    }
    
    public void vusarCronometro(){
        if(juego.bestaPausa()){
            juego.viniciarCronometro();
        }else{
            juego.vdetenerCronometro();
        }
    }
    
    
    private void vsetCronometro(){
        juego.vañadirObservador(this);
    }
   
    //pruebas unitarias diagramas de clases,la evolucion.
    public void irHome() {
        cambiarPantalla(1);
    }
    public void jugar(){
        cambiarPantalla(2);
    }  
    private void cambiarPantalla(int indice) {
        actualPantalla.setVisible(false);
        actualPantalla = histPantallas.get(indice);
        actualPantalla.setSize(alto,ancho);
        actualPantalla.setLocationRelativeTo(histPantallas.get(0));
        actualPantalla.setVisible(true);
    }
}
