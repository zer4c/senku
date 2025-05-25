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
 * @author pablo
 */
public class FondoGanaste extends JPanel{
    private Image Iimage;
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Iimage = new ImageIcon(getClass().getResource("/ganaste.jpg")).getImage();
        g.drawImage(Iimage, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}
