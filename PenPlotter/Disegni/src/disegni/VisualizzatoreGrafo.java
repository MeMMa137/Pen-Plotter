package disegni;

import disegni.Grafo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class VisualizzatoreGrafo extends JPanel implements MouseMotionListener, MouseListener
{

    private Nodi nodi;
    private Grafo grafo;
    Point2D puntoIniziale;
    int nodoSelezionato;
    int nodoDaEvidenziare;

    public VisualizzatoreGrafo()
    {
        nodi = null;
        grafo = null;
        nodoDaEvidenziare = -1;
        /*
        Nodo n1 = new Nodo(100, 100, 50, "Nodo1", 0);
        Nodo n2 = new Nodo(200, 200, 50, "Nodo2", 1);
        Nodo n3 = new Nodo(100, 300, 50, "Nodo3", 2);
        nodi.add(n1);
        nodi.add(n2);
        nodi.add(n3);
        grafo = new Grafo(3);
        grafo.impostaPesiCasualmente();
         */
        //offsetX = 0;
        //offsetY = 0;
        puntoIniziale = null;
        nodoSelezionato = -1;
        addMouseMotionListener(this);
    }
    
    public void setGrafo(Grafo grafo) {
        this.grafo=grafo;
        this.nodi = grafo.nodi;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (grafo == null)
            return;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        int i, j, n;
        n = grafo.numeroNodi();
        for (i = 0; i < n; i++)
        {
            Nodo a = nodi.get(i);
            if(a==null)
                continue;
            Rectangle r = new Rectangle();
            int mr = a.raggio / 2;  // metà raggio
            r.x = a.x() - mr;
            r.y = a.y() - mr;
            r.width = a.raggio;
            r.height = a.raggio;
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawOval(r.x, r.y, r.width, r.height);
        }

        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
            {
                double p = grafo.getPeso(i, j);
                if (p != 0.0d)
                {
                    Nodo n1, n2;
                    n1 = nodi.get(i);
                    n2 = nodi.get(j);
                    if(n1!=null && n2!=null)
                        g.drawLine(n1.x(), n1.y(), n2.x(), n2.y());
                    /*
                    int pmx = (n1.x() + n2.x()) / 2;
                    int pmy = (n1.y() + n2.y()) / 2;
                    g.drawString(String.format("%.2f", p), pmx, pmy);
                     */
                }
            }
        if (nodoDaEvidenziare != -1)
        {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
            Nodo nn = nodi.get(nodoDaEvidenziare);
            if(nn==null)
                return;
            g.setColor(Color.RED);
            g.drawString(nn.punto.toString()+" ["+nn.indice+"]", nn.x(), nn.y());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (puntoIniziale == null)
            return;

        if (nodoSelezionato == -1)
        {
            int i;
            for (i = 0; i < nodi.size(); i++)
            {
                nodi.get(i).aggiornaOffset((int) (e.getX() - puntoIniziale.getX()),
                        (int) (e.getY() - puntoIniziale.getY()));
                repaint();
            }
        }
        if (nodoSelezionato >= 0)
        {
            Nodo n = nodi.get(nodoSelezionato);
            n.aggiornaOffset((int) (e.getX() - puntoIniziale.getX()),
                    (int) (e.getY() - puntoIniziale.getY()));
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (nodi==null)
            return;
        int i;
        Nodo nn;
        for (i = 0; i < nodi.size(); i++)
        {
            if(nodi.get(i)==null)
                continue;
            nn = nodi.get(i).clone();
            nn.punto.c /= Finestra.scala;
            nn.punto.r /= Finestra.scala;
            //System.out.println(nn.punto.toString()+" x="+e.getX()+" y="+e.getY());
            if (nn.interno((int)(e.getX()/Finestra.scala),(int)( e.getY()/Finestra.scala)))
            {
                nodoDaEvidenziare = i;
                repaint();
                return;
            }
        }
        nodoDaEvidenziare = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        puntoIniziale = e.getPoint();
        int i;
        Nodo n;
        nodoSelezionato = -1;
        for (i = 0; i < nodi.size(); i++)
        {
            n = nodi.get(i);
            if (n.interno(e.getX(), e.getY()))
            {
                nodoSelezionato = i;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

        if (puntoIniziale != null)
        {
            int i;
            for (i = 0; i < nodi.size(); i++)
                nodi.get(i).fissaOffset();
        }
        if (nodoSelezionato >= 0)
        {
            Nodo n = nodi.get(nodoSelezionato);
            n.fissaOffset();
        }
        puntoIniziale = null;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    String getNodiString() {
        return nodi.toString();
    }

    String getGrafoString() {
        return grafo.toString();
    }

}
