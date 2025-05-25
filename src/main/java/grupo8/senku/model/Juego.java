/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author pablo
 */
public class Juego {
    private final ModelTablero MTtablero;
    private int itiempo;
    private Timer Tcronometro;
    private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
    
    public static String SactTiempo = "tiempo";
           /**
     *
     * @param MTtablero
     */

    public Juego(ModelTablero MTtablero){
        this.MTtablero = MTtablero;
        this.Tcronometro = null;
        itiempo = 0;
    }
    
    public final void viniciarCronometro() {
        Tcronometro = new Timer();
        TimerTask TTtarea = new TimerTask() {
            @Override
            public void run() {
                int ianteriorTiempo = itiempo;
                itiempo++;
                PCS.firePropertyChange(SactTiempo, ianteriorTiempo, itiempo);
            }
        };
        Tcronometro.scheduleAtFixedRate(TTtarea, 0, 1000);
    }
    
    public void vañadirObservador(PropertyChangeListener PCLescucha) {
        PCS.addPropertyChangeListener(PCLescucha);
    }

    public void veliminarObservador(PropertyChangeListener PCLescucha) {
        PCS.removePropertyChangeListener(PCLescucha);
    }

    public int ijuegoTerminado() {
        int ires = 1;
        int ifichasActivas = 0;

        for (int i = 0; i < MTtablero.igetFilas(); i++) {
            for (int j = 0; j < MTtablero.igetColumnas(); j++) {
                if (MTtablero.bfichaActiva(i, j)) {
                    ifichasActivas++;
                }
                if (ifichasActivas > 1) break;
            }
            if (ifichasActivas > 1) break;
        }

        if (ifichasActivas != 1) {
            boolean bhayMovimiento = false;
            for (int i = 0; i < MTtablero.igetFilas() && !bhayMovimiento; i++) {
                for (int j = 0; j < MTtablero.igetColumnas() && !bhayMovimiento; j++) {
                    if (MTtablero.bfichaActiva(i, j)) {

                        String[] Sdirecciones = MTtablero instanceof ModelTableroTriangular ?
                                new String[]{"ARRIBA_IZQUIERDA", "ARRIBA_DERECHA", "IZQUIERDA",
                                             "DERECHA", "ABAJO_IZQUIERDA", "ABAJO_DERECHA"} :
                                new String[]{"ARRIBA", "ABAJO", "IZQUIERDA", "DERECHA"};

                        for (String Sdir : Sdirecciones) {
                            if (MTtablero.bpuedeComer(i, j, Sdir)) {
                                ires = 0;
                                bhayMovimiento = true;
                            }
                        }
                    }
                }
            }

            if (!bhayMovimiento) {
                ires = -1;
            }
        }
        return ires;
    }
    
    public void vdetenerCronometro() {
        if (Tcronometro != null) {
            Tcronometro.cancel();
            Tcronometro.purge(); 
            Tcronometro = null;
        }
    }
    
    public boolean bestaPausa(){
        boolean brespuesta;
        if(Tcronometro == null){
            brespuesta = true;
        }else{
            brespuesta = false;
        }
        return brespuesta;
    }
    
    public void vreiniciarTiempo(){
        itiempo = 0;
    }
    
    public ModelTablero MTgetTablero(){
        return this.MTtablero;
    }

    public int igetTiempo() {
        return itiempo;
    }
    
    public int igetIfilasTablero(){
        return MTtablero.igetFilas();
    }
    public int igetIcolumnasTablero(){
        return MTtablero.igetColumnas();
    }
}
