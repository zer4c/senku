/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;
import java.util.ArrayList;
/**
 *
 * @author INTEL
 */
public abstract class ModelTablero {
    protected int filas;
    protected int columnas;
    protected ArrayList<ArrayList<ModelFicha>> fichas; 
    
    
    // para decirle el tamaño de las filas y columas segun el nivel por ahora 7x7
    public ModelTablero(int filas, int columnas){
        this.filas = 7;
        this.columnas = 7;
        
        fichas = new ArrayList<>();
        inicializarTablero(); 
    }
    
    private void inicializarTablero(){
        for(int i = 0; i < filas; i++){
            ArrayList<ModelFicha> fila = new ArrayList<>();
            for (int j = 0; i < columnas; j++){
                ModelFicha ficha = new ModelFicha();
                fila.add(ficha); //va a ser invisible pq puse todo invisible como estado inicial creo xd
            }
            fichas.add(fila);
        }
    }
    
    public ModelFicha getFicha(int fila, int columna){
    return null;
    }
    
    public boolean activarFicha(int fila, int columna){
    return false;
    }
    
    public boolean eliminarFicha(){
    return false;
    }
    
    public boolean puedeComer(){
    return false;
    }
    public boolean moverFicha(){
    return false;
    }
}
