/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

/**
 *
 * @author INTEL
 */
public class ModelFicha {
    private boolean bactiva; // es pacman
    private boolean beliminada; //es fantasma
    private boolean binvisible; //no forma parte del tablero, es invisible
    
    //le puse un estado por defecto tipo tablero vacio
    public ModelFicha(){
        this.binvisible = true;
        this.bactiva = false;
        this.beliminada = false;
    }
    
    //Cambia el estado de las fichas
    public void vactivar(){
        this.bactiva = true;
        this.beliminada = false;
        this.binvisible = false;
    }
    public void veliminar(){
        this.bactiva = false;
        this.beliminada = true;
        this.binvisible = false;
    }
    
    public void vhacerInvisible(){
        this.bactiva = false;
        this.beliminada = false;
        this.binvisible = true;
    }
    
    //Pregunta estado de las fichas 
    public boolean bestaActiva(){
        return bactiva;
    }
    
    public boolean bestaEliminada(){
        return beliminada;
    }
    
    public boolean besInvisible(){
        return binvisible;
    }
}
