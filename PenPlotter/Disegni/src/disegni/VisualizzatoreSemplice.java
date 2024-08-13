package disegni;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class VisualizzatoreSemplice extends JPanel {

   private BufferedImage img = null;
   public String label="";

   @Override
   public void paint(Graphics g) {
      super.paint(g);
      Rectangle r = getBounds();
      if (img == null) {
         g.setColor(Color.GREEN);
         g.fillRect(0, 0, r.width, r.height);
         g.drawString(label, 20,20);
         return;
      }
      g.drawImage(img, 0, 0, r.width, r.height, 0, 0, img.getWidth(), img.getHeight(), null);
      g.drawString(label, 20,20);
      
   }

   public void cambiaImmagine(BufferedImage img){
      this.img = img;
      repaint();
   }
}

