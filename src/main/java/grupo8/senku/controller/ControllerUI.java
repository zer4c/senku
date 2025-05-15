/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.controller;
import java.util.List;
import grupo8.senku.UI.*;
/**
 *
 * @author pablo
 */
public class ControllerUI {
    private javax.swing.JFrame actualPantalla;
    private List<javax.swing.JFrame> histPantallas;
    private int contadorPantalla;
    
    public ControllerUI(){
        histPantallas = List.of(new Seleccionjuego(this), 
                                        new SeleccionNiveles(this), 
                                        new VentanaJuego(this));
        contadorPantalla = 0;
        actualPantalla = histPantallas.get(contadorPantalla);
    }
    public void iniciarApp(){
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
    }
    public void regresarPantalla(){
        actualPantalla.setVisible(false);
        contadorPantalla -= 1;
        actualPantalla = histPantallas.get(contadorPantalla);
        actualPantalla.setVisible(true);
     
    }
    public void seleccionarFacil(){
        actualPantalla.setVisible(false);
        actualPantalla = histPantallas.get(1);
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
        contadorPantalla += 1;
    }
    public void seleccionarDificil(){
        actualPantalla.setVisible(false);
        actualPantalla = histPantallas.get(1);
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
        contadorPantalla += 1;
    }
    public void iniciarJuego(){
        actualPantalla.setVisible(false);
        actualPantalla = histPantallas.get(2);
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
        contadorPantalla += 1;
    }
    public void irHome(){
        actualPantalla.setVisible(false);
        actualPantalla = histPantallas.get(0);
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
        contadorPantalla += 1;
    }
}
