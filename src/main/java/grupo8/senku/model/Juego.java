/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

import grupo8.senku.controller.ControllerUI;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author pablo
 */
public class Juego {
    private ModelTablero tablero;
    private ArrayList<ModelTablero> histTablero;
    private int tiempo;
    private Timer cronometro;
    private ControllerUI control;
    
    public Juego(ControllerUI control, ModelTablero tablero){
        this.control = control;
        this.tablero = tablero;
        this.histTablero = new ArrayList();
        this.cronometro = new Timer();
        histTablero.add(tablero);
        tiempo = 0;
    }
    
    public void iniciarCronometro() {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                int minutos = tiempo / 60;
                int seg = tiempo % 60;
                tiempo++;
            }
        };
        cronometro.scheduleAtFixedRate(tarea, 0, 1000);
    }
    
    public void detenerCronometro() {
        cronometro.cancel();
        cronometro = null;
    }

    public int getTiempoEnSegundos() {
        return tiempo;
    }
    
}
