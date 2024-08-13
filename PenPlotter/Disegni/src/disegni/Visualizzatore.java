package disegni;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Visualizzatore extends JPanel implements MouseListener, MouseMotionListener {

    public BufferedImage img = null;
    public boolean disegnare;
    private Point startPoint;
    private Point endPoint;
    public double scala;
    public String label;
    private List<ElaboraInterface> listeners = new ArrayList<>();

    public Visualizzatore() {
        addMouseListener(this);
        addMouseMotionListener(this);
        scala = 1;
        disegnare = true;
        label="";
    }

    public void addListener(ElaboraInterface daAggiungere) {
        listeners.add(daAggiungere);
    }

    public void elabora() {
        for (ElaboraInterface i : listeners)
            i.elabora();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle r = getBounds();
        if (img == null)
        {
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, r.width, r.height);
            g.drawString(label, 20,20);
            return;
        }
        g.drawImage(img, 0, 0, r.width, r.height, 0, 0, img.getWidth(), img.getHeight(), null);
        g.drawString(label, 20,20);

        if (startPoint != null && endPoint != null)
        {
            g.setColor(Color.BLACK);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!disegnare)
            return;
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!disegnare)
            return;
        endPoint = e.getPoint();
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.drawLine((int) (startPoint.x / scala), (int) (startPoint.y / scala), (int) (endPoint.x / scala), (int) (endPoint.y / scala));
        endPoint = null;
        repaint();
        elabora();
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!disegnare)
            return;
        endPoint = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
