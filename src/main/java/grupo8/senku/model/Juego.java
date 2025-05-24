/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

import grupo8.senku.controller.ControllerUI;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author pablo
 */
public class Juego {
    private ModelTablero tablero;
    private final Stack<ModelTablero> histTablero;
    private int tiempo;
    private boolean enJuego;
    private int movimientos;
    private Timer cronometro;
    private final ControllerUI control;
    
    public Juego(ControllerUI control, ModelTablero tablero){
        this.control = control;
        this.tablero = tablero;
        this.histTablero = new Stack<>();
        this.enJuego = true;
        this.movimientos = 0;
        this.cronometro = new Timer();
        histTablero.push(tablero);
        tiempo = 0;
        viniciarCronometro();
    }
    
    public final void viniciarCronometro() {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                int minutos = tiempo / 60;
                int seg = tiempo % 60;
                tiempo++;
                //control.actualizarVista();
            }
        };
        cronometro.scheduleAtFixedRate(tarea, 0, 1000);
    }
    
    public boolean deshacerMovimiento() {
        boolean bres = false;
        if (histTablero.size() > 1) { 
            histTablero.pop(); 
            tablero = histTablero.peek();
            movimientos--;
            //control.actualizarVista();
            bres = true;
        }
        return bres;
    }  
     
     
    public void vreiniciar() {
        if (!histTablero.isEmpty()) {
            tablero = histTablero.firstElement();
            histTablero.clear();
            histTablero.push(tablero);
            tiempo = 0;
            movimientos = 0;
            enJuego = true;
            //control.actualizarVista();
        }
    }
    
    
    private void vguardarEstado() {
        try {
            histTablero.push((ModelTablero) tablero.clone());
        } catch (CloneNotSupportedException e) {
            System.err.println("Error al clonar el tablero: " + e.getMessage());
        }
    }
    
    public boolean bjuegoTerminado() {
        boolean bres = true;
        int ifichasActivas = 0;
        //Verificar  si hay mas  de  una  ficha
        for (int i = 0; i < tablero.igetFilas() && ifichasActivas>1; i++) {
            for (int j = 0; j < tablero.igetColumnas() && ifichasActivas>1 ; j++) {
                if (tablero.bactivarFicha(i, j)) {
                    ifichasActivas++;
                }
            }
        } 
        // Verificar si hay movimientos posibles}
        if(ifichasActivas != 1){
            for (int i = 0; i < tablero.igetFilas(); i++) {
                for (int j = 0; j < tablero.igetColumnas(); j++) {
                    if (tablero.bactivarFicha(i, j)) {
                        // Verificar todas las direcciones posibles
                        String[] direcciones = tablero instanceof ModelTableroTriangular ? 
                                new String[]{"ARRIBA_IZQUIERDA", "ARRIBA_DERECHA", "IZQUIERDA", 
                                            "DERECHA", "ABAJO_IZQUIERDA", "ABAJO_DERECHA"} :
                                new String[]{"ARRIBA", "ABAJO", "IZQUIERDA", "DERECHA"};

                        for (String dir : direcciones) {
                            if (tablero.bpuedeComer(i, j, dir)) {
                                bres= false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return bres;
    }
    
    public void detenerCronometro() {
        if (cronometro != null) {
            cronometro.cancel();
            cronometro.purge(); // Limpia las tareas canceladas
            cronometro = null;
        }
    }
    
    public ModelTablero MTgetTablero(){
        return this.tablero;
    }

    public int igetTiempo() {
        return tiempo;
    }
    
    public int igetIfilasTablero(){
        return tablero.igetFilas();
    }
    public int igetIcolumnasTablero(){
        return tablero.igetColumnas();
    }
}
