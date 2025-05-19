/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.model;

/**
 *
 * @author brenda
 */

public class ModelFicha {
    private boolean visible;

    public ModelFicha() {
        this.visible = false;
    }

    public boolean bestaVisible() {
        return visible;
    }

    public void vsetVisible(boolean visible) {
        this.visible = visible;
    }
}

