package disegni;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nodi
{

    private ArrayList<Nodo> nodi;
    Liste l;

    public Nodi(Liste l)
    {
        this.l = l;
        nodi = new ArrayList<>();
    }

    @Override
    public Nodi clone()
    {
        try
        {
            Nodi n = new Nodi(l.clone());
            n.nodi = (ArrayList<Nodo>) nodi.clone();
            return n;
        }
        catch (Exception ex)
        {
            Logger.getLogger(Nodi.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void sistemaPrimoEUltimoDelleListe()
    {

    }

    public void rimuovi(int pos)
    {
        nodi.remove(pos);
        int i;
        for (i = 0; i < nodi.size(); i++)
            if (i >= pos)
            {
                Nodo n = nodi.get(i);
                n.indice = n.indice - 1;
            }
        for (Lista e : l.l)
        {
            if (e.idPrimo == pos)
                e.idPrimo = -1;
            else
                if (e.idPrimo > pos)
                    e.idPrimo = e.idPrimo - 1;
            if (e.idUltimo == pos)
                e.idUltimo = -1;
            else
                if (e.idUltimo > pos)
                    e.idUltimo = e.idUltimo - 1;
        }

    }

    public int aggiungi(Punto p)
    {
        System.out.println("Voglio aggiungere il punto " + p);
        for (int i = 0; i < nodi.size(); i++)
        {
            Punto pp = nodi.get(i).punto;
            if (pp.equals(p))
            {
                System.out.println("C'era già in posizione " + i);
                return i;
            }

        }
        int newPos = nodi.size();
        nodi.add(new Nodo((int) (p.c), (int) (p.r), 10, "" + newPos, newPos));
        System.out.println("Lo aggiungo in posizione " + newPos);
        return newPos;
    }

    @Override
    public String toString()
    {
        String s = "Nodi del grafo:\n";
        for (int i = 0; i < nodi.size(); i++)
        {
            Nodo n = nodi.get(i);
            s = s + i + ") " + n.punto.toStringForMachine() + "\n"; // + "    " + n.infoAccorpamento + "\n";
        }
        s = s + "------------------------------";
        return s;
    }

    public int trovaPos(Punto p)
    {
        for (int i = 0; i < nodi.size(); i++)
            if (nodi.get(i).punto.equals(p))
                return i;
        return -1;
    }

    public int size()
    {
        return nodi.size();
    }

    public Nodo get(int i)
    {
        if(i<0 || i>=nodi.size())
            return null;
        return nodi.get(i);
    }
}
