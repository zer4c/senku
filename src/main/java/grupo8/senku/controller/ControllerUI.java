/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.controller;

/**
 *
 * @author pablo
 */
public class ControllerUI {
    private javax.swing.JFrame actualPantalla;
    private javax.swing.JFrame anteriorPantalla;
    
    public ControllerUI(javax.swing.JFrame inicio){
        actualPantalla = inicio;
        anteriorPantalla = null;
    }
    public void iniciarPantalla(){

        actualPantalla.setVisible(true);
        actualPantalla.setLocationRelativeTo(null);
    }
}
