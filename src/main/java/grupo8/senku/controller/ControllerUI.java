/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.controller;
import java.util.ArrayList;
/**
 *
 * @author pablo
 */
public class ControllerUI {
    private javax.swing.JFrame actualPantalla;
    private ArrayList<javax.swing.JFrame> histPantallas;
    private int contadorPantalla;
    
    public ControllerUI(javax.swing.JFrame inicio){
        actualPantalla = inicio;
        histPantallas = new ArrayList<>();
        contadorPantalla = 0;
    }
    public void iniciarPantalla(){
        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
        histPantallas.add(actualPantalla);
        contadorPantalla += 1;
    }
    public void regresarPantalla(){
        contadorPantalla -= 1;
        actualPantalla = histPantallas.get(contadorPantalla);
        actualPantalla.setVisible(true);
    }
}
