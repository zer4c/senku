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
    protected ArrayList<ArrayList<ModelFicha>> ALfichas; 
    
    
    // para decirle el tamaño de las filas y columas segun el nivel por ahora 7x7
    public ModelTablero(int ifilas, int icolumnas){
        this.ifilas = ifilas;
        this.icolumnas = icolumnas;
        ALfichas = new ArrayList<>();
        vinicializarTablero(); 
    } 
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void vinicializarTablero(){
        for(int i = 0; i < ifilas; i++){
            ArrayList<ModelFicha> ALfila = new ArrayList<>();
            for (int j = 0; j < icolumnas; j++){
                ModelFicha MFficha = new ModelFicha();
                ALfila.add(MFficha);
            }
            ALfichas.add(ALfila);
        }
    }
    
    public ModelFicha MFgetFicha(int ifila, int icolumna){
        if (ifila >= 0 && ifila < ifilas && icolumna >= 0 && icolumna < icolumnas) {
            return ALfichas.get(ifila).get(icolumna);
        }
        return null;
    }
    
    public int igetFilas() {
        return this.ifilas;
    }
    
    public int igetColumnas() {
        return this.icolumnas;
    }
    public abstract boolean bfichaActiva(int ifila, int icolumna);
    public abstract boolean beliminarFicha(int ifila, int icolumna);
    public abstract boolean bpuedeComer(int ifila, int icolumna, String sdireccion);
    public abstract boolean bmoverFicha(int ifilaOrigen, int icolOrigen, String sdireccion);
}