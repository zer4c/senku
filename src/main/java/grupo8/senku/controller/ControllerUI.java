/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.controller;
import java.util.List;
import grupo8.senku.UI.*;
import grupo8.senku.model.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
/**
 *
 * @author pablo
 */
public class ControllerUI {
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
        Vjuego.vIniciarBotones(tablero.igetFilas(), tablero.igetColumnas());
        for(int i = 0; i < tablero.igetColumnas(); i++){
            for(int j = 0; j < tablero.igetFilas(); j++){
                if(!tablero.getFicha(j, i).besInvisible()){
                    Vjuego.vAgregarBotonesTablero(new JButton(), 1);
                }else{
                    Vjuego.vAgregarBotonesTablero(new JButton(), 0);                    
                }
            }
        }
        cambiarPantalla(4);
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
