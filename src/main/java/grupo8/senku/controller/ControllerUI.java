/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.controller;
import java.util.List;
import grupo8.senku.UI.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pablo
 */
public class ControllerUI {
    private javax.swing.JFrame actualPantalla;
    private List<javax.swing.JFrame> histPantallas;
    
    public ControllerUI(){
        histPantallas = List.of(new FrameWelcomeS(this),
                                new FrameJugar(this),
                                new Seleccionjuego(this), 
                                new SeleccionNiveles(this), 
                                new VentanaJuego(this));
        actualPantalla = histPantallas.get(0);
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
        cambiarPantalla(3);
    }

    public void seleccionarDificil() {
        cambiarPantalla(3);
    }

    public void iniciarJuego() {
        cambiarPantalla(4);
    }

    public void irHome() {
        cambiarPantalla(1);
    }
    public void jugar(){
        cambiarPantalla(2);
    }
    
    private void cambiarPantalla(int indice) {
        actualPantalla.setVisible(false);
        actualPantalla = histPantallas.get(indice);
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(histPantallas.get(0));
    }
}
