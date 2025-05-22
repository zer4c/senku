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
public abstract class ModelTablero implements Cloneable{
    protected int ifilas;
    protected int icolumnas;
    protected ArrayList<ArrayList<ModelFicha>> fichas; 
    
    
    // para decirle el tamaño de las filas y columas segun el nivel por ahora 7x7
    public ModelTablero(int ifilas, int icolumnas){
        this.ifilas = ifilas;
        this.icolumnas = icolumnas;
        fichas = new ArrayList<>();
        inicializarTablero(); 
    } 
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void inicializarTablero(){
        for(int i = 0; i < ifilas; i++){
            ArrayList<ModelFicha> fila = new ArrayList<>();
            for (int j = 0; j < icolumnas; j++){
                ModelFicha ficha = new ModelFicha();
                fila.add(ficha); //va a ser invisible pq puse todo invisible como estado inicial creo xd
            }
            fichas.add(fila);
        }
    }
    
    public ModelFicha getFicha(int ifila, int icolumna){
        if (ifila >= 0 && ifila < ifilas && icolumna >= 0 && icolumna < icolumnas) {
            return fichas.get(ifila).get(icolumna);
        }
        return null;
    }
    
    public int igetFilas() {
        return this.ifilas;
    }
    
    public int igetColumnas() {
        return this.icolumnas;
    }
    public abstract boolean bactivarFicha(int ifila, int icolumna);
    public abstract boolean beliminarFicha(int ifila, int icolumna);
    public abstract boolean bpuedeComer(int ifila, int icolumna, String sdireccion);
    public abstract boolean bmoverFicha(int ifilaOrigen, int icolOrigen, String sdireccion);
}