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
    private boolean activa; // es pacman
    private boolean eliminada; //es fantasma
    private boolean invisible; //no forma parte del tablero, es invisible
    
    //le puse un estado por defecto tipo tablero vacio
    public ModelFicha(){
        this.invisible = true;
        this.activa = false;
        this.eliminada = false;
    }
    
    //Cambia el estado de las fichas
    public void activar(){
        this.activa = true;
        this.eliminada = false;
        this.invisible = false;
    }
    
    public void eliminar(){
        this.activa = false;
        this.eliminada = true;
        this.invisible = false;
    }
    
    public void hacerInvisible(){
        this.activa = false;
        this.eliminada = false;
        this.invisible = true;
    }
    
    //Pregunta estado de las fichas 
    public boolean estaActiva(){
        return activa;
    }
    
    public boolean estaEliminada(){
        return eliminada;
    }
    
    public boolean esInvisible(){
        return invisible;
    }
}
