package disegni;

import disegni.Nodi;
import java.awt.Color;
import java.awt.Graphics;

public class Lista {

    static final int RAGGIO_PER_PUNTI_DI_MEZZO = 6;
    Elem<Punto> testa;
    Elem<Punto> penultimo;
    int idPrimo;
    int idUltimo;
    Color colore;
    int lunghezza;

    public Lista() {
        testa = null;
        penultimo = null;
        lunghezza = 0;
        colore = new Color((int) (128 + Math.random() * 128), (int) (128 + Math.random() * 128), (int) (128 + Math.random() * 128));
    }

    @Override
    public Lista clone()
    {
        Lista l = new Lista();
        l.testa = testa;
        l.penultimo = penultimo;
        l.idPrimo = idPrimo;
        l.idUltimo = idUltimo;
        l.colore = colore;
        l.lunghezza = lunghezza;
        return l;
    }
    
    public void inserisciInTesta(Punto p) {
        testa = new Elem(p, testa);
        if (testa.next != null && testa.next.next == null)
            penultimo = testa;
        lunghezza++;
    }

    public void inserisciInFondo(Punto p) {
        if (testa == null)
        {
            inserisciInTesta(p);
            penultimo = null;
            return;
        }
        if (testa.next == null)
        {
            penultimo = testa;
            testa.next = new Elem(p, null);
            lunghezza++;
            return;
        }
        Elem ultimo = penultimo.next;
        ultimo.next = new Elem(p, null);
        penultimo = ultimo;
        lunghezza++;
    }

    public boolean provaAdInserire(Punto da, Punto a) {
        if (testa == null)
            return false;
        if (da.equals(testa.inf))
        {
            inserisciInFondo(a);
            return true;
        }
        if (a.equals(testa.inf))
        {
            inserisciInTesta(a);
            return true;
        }
        if (penultimo != null)
        {
            Elem<Punto> ultimo = penultimo.next;
            if (da.equals(ultimo.inf))
            {
                inserisciInFondo(a);
                return true;
            }
            else
                return false;
        }
        return false;
    }

    public void disegnati(Graphics g) {
        Elem<Punto> e = testa;
        Color c = g.getColor();
        g.setColor(colore);
        Punto da = null;
        Punto a = testa.inf;
        while (e != null)
        {
            da = a;
            a = e.inf;
            g.drawLine(da.c, da.r, a.c, a.r);
            //System.out.println(da.toString()+" --> "+a.toString());
            e = e.next;
        }
        g.setColor(c);
    }

    public void sistematiConPuntoDiMezzo(Punto p) {
        if (testa.inf.distaDa(p) <= RAGGIO_PER_PUNTI_DI_MEZZO)
        {
            inserisciInTesta(p);
            return;
        }
        if (penultimo != null && penultimo.next != null)
        {
            Elem<Punto> ultimo = penultimo.next;
            if (ultimo.inf.distaDa(p) < RAGGIO_PER_PUNTI_DI_MEZZO)
                inserisciInFondo(p);
        }
    }

    public void aggiungitiA(Nodi nodi) {
        if (testa == null)
            return;
        idPrimo = nodi.aggiungi(testa.inf);
        idUltimo = -1;
        if (penultimo!=null && penultimo.next != null)
            idUltimo = nodi.aggiungi(penultimo.next.inf);
    }

    public String toString() {
        String s = "";
        Elem<Punto> e = testa;
        while (e != null)
        {
            s = s + e.inf.toStringForMachine();
            e = e.next;
            if (e != null)
                s = s + ";";
        }
        return s;
    }
    
    public String toStringReverse() {
        String s = "";
        Elem<Punto> e = testa;
        while (e != null)
        {
            if (e!=testa)
                s = ";" + s;
            s = e.inf.toStringForMachine() + s;
            e = e.next;
        }
        return s;
    }
}
