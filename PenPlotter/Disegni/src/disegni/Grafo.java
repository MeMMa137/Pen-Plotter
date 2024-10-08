package disegni;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Grafo
{

    private int numeroNodi;
    private double[][] pesi;
    private String[][] percorsi;
    Nodi nodi;

    public Grafo(Nodi nodi)
    {
        this.nodi = nodi.clone();
        this.numeroNodi = nodi.size();
        pesi = new double[numeroNodi][numeroNodi];
        percorsi = new String[numeroNodi][numeroNodi];
        int i, j;
        for (i = 0; i < numeroNodi; i++)
            for (j = 0; j < numeroNodi; j++)
            {
                percorsi[i][j] = "";
                pesi[i][j] = 0.0d;
            }
    }

    public Grafo clone()
    {
        try
        {
            Grafo g = new Grafo(nodi);
            g.numeroNodi = numeroNodi;
            g.pesi = Engine.cloneArrayDouble(pesi);
            g.percorsi = Engine.cloneArrayString(percorsi);
            return g;
        }
        catch (Exception ex)
        {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void rimuoviNodo(int pos)
    {
        if (pos == pesi.length - 1)
            System.out.println("tolgo ultimo");
        System.out.println(toString() + "\n\n---------------------------------\n");
        Nodo n = nodi.get(pos);
        if(n==null)
            return;
        System.out.println("Elimino il nodo:" + n.indice + ") " + n.punto);
        int i, j, N;
        N = numeroNodi;
        int posConnessa = -1;
        for (i = 0; i < N && posConnessa == -1; i++)
            if (i != pos && pesi[pos][i] != 0)
                posConnessa = i;
        if (posConnessa == -1)
            return;
        Lista l = nodi.l.trovaListaDaA(pos, posConnessa);
        if (l == null)
        {
            System.out.println("Non trovo una lista che collega questi due punti:" + pos + " e " + posConnessa);
            System.exit(1);
        }
        Nodo nc = nodi.get(posConnessa);
        nc.infoAccorpamento += l.toStringReverse() + l.toString();
        nc.nodiAccorpati.add(n);
        nodi.rimuovi(pos);
        double[][] pesi2 = new double[N - 1][N - 1];
        for (i = 0; i < pos; i++)
            for (j = 0; j < pos; j++)
                pesi2[i][j] = pesi[i][j];
        for (i = pos + 1; i < N; i++)
            for (j = 0; j < pos; j++)
                pesi2[i - 1][j] = pesi[i][j];
        for (i = 0; i < pos; i++)
            for (j = pos + 1; j < N; j++)
                pesi2[i][j - 1] = pesi[i][j];
        for (i = pos + 1; i < N; i++)
            for (j = pos + 1; j < N; j++)
                pesi2[i - 1][j - 1] = pesi[i][j];
        int ii, jj;
        ii = posConnessa;
        jj = posConnessa;
        if (pos < posConnessa)
        {
            ii--;
            jj--;
        }
        pesi = pesi2;
        numeroNodi--;
        //System.out.println(toString() + "---------------------------------\n");
    }

    public int[] dovePossoAndarePartendoDa(int partendoDa)
    {
        ArrayList<Integer> l = new ArrayList<>();
        int i;
        int N = pesi.length;
        for(i=0;i<N;i++)
            if (partendoDa!=i && pesi[partendoDa][i]!=0)
                l.add(i);
        int[] ris = new int[l.size()];
        for(i=0;i<ris.length;i++)
            ris[i] = l.get(i);
        return ris;
    }
    
    public int cercaNodoComprimibile()
    {
        int i, j, N, trovati;
        N = numeroNodi;
        for (i = 0; i < N; i++)
        {
            trovati = 0;
            for (j = 0; j < N && trovati <= 1; j++)
                if (pesi[i][j] != 0)
                    trovati++;
            if (trovati == 1)
            {
                trovati = 0;
                System.out.println(toString());
                for (int k = 0; k < N && trovati <= 1; k++)
                    if (pesi[k][i] != 0)
                        trovati++;
                if (trovati == 1)
                    return i;
            }
        }
        return -1;
    }
    
public void visualizzaGrafo(Grafo gr)
    {
        Disegni.fps.aggiungiGrafo(gr);
    }

    public void riduciAlGrafoEquivalente()
    {
        int daTogliere;
        int conta = 0;
        do
        {
            visualizzaGrafo(this);
            daTogliere = cercaNodoComprimibile();
            if (daTogliere >= 0 && pesi.length > 1)
                rimuoviNodo(daTogliere);
            conta++;
        }
        while (conta < 99999 && daTogliere >= 0 && pesi.length > 1);
    }

    void impostaPeso(double nuovoPeso, int da, int a)
    {
        if (da >= numeroNodi)
            return;
        if (a >= numeroNodi)
            return;
        pesi[da][a] = nuovoPeso;
    }

    void impostaPercorso(String perc, int da, int a)
    {
        if (da >= numeroNodi)
            return;
        if (a >= numeroNodi)
            return;
        percorsi[da][a] = perc;
    }

    double getPeso(int da, int a)
    {
        if (da >= numeroNodi)
            return 0;
        if (a >= numeroNodi)
            return 0;
        return pesi[da][a];
    }

    String getPercorso(int da, int a)
    {
        if (da >= numeroNodi)
            return "";
        if (a >= numeroNodi)
            return "";
        return percorsi[da][a];
    }

    void impostaPesiCasualmente()
    {
        int i, j;
        for (i = 0; i < numeroNodi; i++)
            for (j = 0; j < numeroNodi; j++)
                if (i != j && Math.random() > 0.6)
                    pesi[i][j] = Math.random() * 10;
                else
                    pesi[i][j] = 0.0d;
    }

    public int numeroNodi()
    {
        return numeroNodi;
    }

    public String charPad(String s, String c, int quanti, boolean aDx)
    {
        while (s.length() < quanti)
            if (aDx)
                s = s + c;
            else
                s = c + s;
        return s;
    }

    @Override
    public String toString()
    {
        int i, j;
        String r = "   "; //-------------------------------------\n" + nodi.toString() + "\n___________________________________________________\n";
        DecimalFormat df = new DecimalFormat("00");
        int maxlunghezza = ("|" + numeroNodi).length() + 3;
        for (i = 0; i < numeroNodi; i++)
            r = r + charPad("| " + i, " ", maxlunghezza,false);
        r = r + "\n";
        
        for (i = 0; i < 10+maxlunghezza*numeroNodi; i++)
            r = r + "_";
        r = r + "\n";
        
        
        for (i = 0; i < numeroNodi; i++)
        {
            r = r + charPad(i + ")", " ", maxlunghezza,true) + "|";
            for (j = 0; j < numeroNodi; j++)
                r = r + " " + charPad(""+pesi[i][j]," ",maxlunghezza-1,true); //if (j != numeroNodi - 1)
            //    r = r + ",";
            r = r + "\n\n\n";
        }
        r = r + "___________________________________________________\n";
        return r;
    }
}