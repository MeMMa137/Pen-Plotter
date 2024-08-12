package penplotterjavacomunicator;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

class Pannello extends JPanel implements MouseMotionListener{
    private Communicator c;
    private ArrayList<Point> p;
    Pannello(Communicator c) {
        this.c=c;
        p = new ArrayList<>();
        addMouseMotionListener(this);
        JButton b = new JButton("Invia");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String msg = Protocol.encode(p);
                c.write(msg);
            }
        });
        add(b);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int r=3;
        for(int i=0;i<p.size();i++) {
            g.fillOval(p.get(i).x-r, p.get(i).y-r, 2*r, 2*r);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        p.add(me.getPoint());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}