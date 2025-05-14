/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku;
import grupo8.senku.controller.ControllerUI;
import grupo8.senku.UI.Seleccionjuego;
/**
 *
 * @author pablo
 */
public class Senku {
    public static void main(String[] args){
        javax.swing.JFrame inicio = new Seleccionjuego();
        ControllerUI control = new ControllerUI(inicio);
        control.iniciarPantalla();        
    }
}
