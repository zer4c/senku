/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

import javax.sound.sampled.*;
import java.io.InputStream;

public class ReproductorSonido {

    private Clip Cfondo;
    private Clip Cmenu;
    private Clip Ccomido;

    public ReproductorSonido() {
        Cfondo = CcargarSonido("/audioFondo.wav");
        Cmenu = CcargarSonido("/menus.wav");
        Ccomido = CcargarSonido("/come.wav");
    }

    public void vreproducirComido() {
        vreproducirSonido(Ccomido);
    }

    public void vreproducirBotonMenu() {
        vreproducirSonido(Cmenu);
    }

    public void vreproducirAudioFondo() {
        if (Cfondo != null) {
            Cfondo.setFramePosition(0);
            Cfondo.loop(Clip.LOOP_CONTINUOUSLY);
            Cfondo.start();
        }
    }

    public void vdetenerAudioFondo() {
        if (Cfondo != null && Cfondo.isRunning()) {
            Cfondo.stop();
        }
    }

    private void vreproducirSonido(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    private Clip CcargarSonido(String Sruta) {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream is = getClass().getResourceAsStream(Sruta);
            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
            clip.open(ais);
            return clip;
        } catch (Exception e) {
            System.err.println("Error al cargar el sonido: " + Sruta);
            e.printStackTrace();
            return null;
        }
    }

    public void vcerrar() {
        if (Cfondo != null) Cfondo.close();
        if (Cmenu != null) Cmenu.close();
        if (Ccomido != null) Ccomido.close();
    }
}