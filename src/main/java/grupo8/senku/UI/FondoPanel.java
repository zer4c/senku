/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.senku.UI;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author brenda
 */
public class FondoPanel extends JPanel {
    private Image imagen;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        imagen = new ImageIcon(getClass().getResource("/grupo8/senku/UI/img/borr.jpg")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}
