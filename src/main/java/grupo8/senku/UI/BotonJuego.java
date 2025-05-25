/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.UI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author pablo
 */
public class BotonJuego extends JButton{
    private int ifila;
    private int icol; 
    private int iestado;
    public BotonJuego(int ifila, int icol, int iestado){
        super();
        this.ifila = ifila;
        this.icol = icol;
        this.iestado = iestado;
        viniciarBoton();
        vobtenerIcono();
    }
    public int igetfila(){
        return this.ifila;
    }
    public int igetcol(){
        return this.icol;
    }
    public void vsetEstado(int iestado){
        this.iestado = iestado;
        vobtenerIcono();
    }
    private void viniciarBoton(){
        Color CcolorBorde = new Color(0xcca46d);
        Border Bborde = BorderFactory.createLineBorder(CcolorBorde,3);
        if(iestado >= 0){
            this.setBorder(Bborde);
        }else{
            this.setEnabled(false);           
            this.setOpaque(false);            
            this.setFocusPainted(false);
            this.setBorderPainted(false);
        }
        this.setContentAreaFilled(false);
        this.setText("");
        this.setPreferredSize(new Dimension(50,50));
    }
    private void vobtenerIcono(){
        if(iestado > 0){
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fichaActiva.png")));
        }else if(iestado == 0){
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha.png")));
        }
    }
}
