package disegni;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Engine
{

    BufferedImage img;
    BufferedImage dst;
    BufferedImage dstDeiTolti;
    BufferedImage immagineDalleListe;
    BufferedImage immagineDeiPuntiRipresi;
    BufferedImage immagineDalleListe2;
    int[][] m;
    int[][] matriceDeiTolti;
    ArrayList<Punto> puntiDiMezzo;
    Liste liste;
    Nodi nodi;
    Grafo grafo;

    public Engine(BufferedImage img)
    {
        this.img = img;
        m = null;
        liste = new Liste();
    }

    public static int[][] cloneArray(int[][] src)
    {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++)
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        return target;
    }
    
    public static double[][] cloneArrayDouble(double[][] src)
    {
        int length = src.length;
        double[][] target = new double[length][src[0].length];
        for (int i = 0; i < length; i++)
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        return target;
    }
    
private void creaRappresentazioneMatrice()
    {
        m = new int[img.getHeight() + 2][img.getWidth() + 2]; // lascio un bordo di 0 in ogni lato
        int i, j;
        int col;
        Color nero = new Color(0, 0, 0);
        for (i = 1; i < m.length - 1; i++)
            for (j = 1; j < m[i].length - 1; j++)
            {
                col = img.getRGB(j - 1, i - 1);
                Color c = new Color(col, true);
                if (c.equals(nero))
                    m[i][j] = 1;
                else
                    m[i][j] = 0;
            }
    }


public static String[][] cloneArrayString(String[][] src)
    {
        int length = src.length;
        String[][] target = new String[length][src[0].length];
        for (int i = 0; i < length; i++)
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        return target;
    }

    

    private BufferedImage ricreaRappresentazioneImmagine(int[][] mat)
    {
        BufferedImage newImg = new BufferedImage(mat[0].length - 2, mat.length - 2, BufferedImage.TYPE_INT_RGB); // rimuovo un bordo di 0 in ogni lato
        int i, j;
        int nero = new Color(0, 0, 0).getRGB();
        int bianco = new Color(255, 255, 255).getRGB();
        for (i = 1; i < mat.length - 1; i++)
            for (j = 1; j < mat[i].length - 1; j++)
                if (mat[i][j] == 1)
                    newImg.setRGB(j - 1, i - 1, nero);
                else
                    newImg.setRGB(j - 1, i - 1, bianco);
        return newImg;
    }

    private BufferedImage ricreaImmagineDalleListe(Liste ll, int w, int h)
    {

        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        for (int i = 0; i < ll.size(); i++)
        {
            Lista l = ll.get(i);
            l.disegnati(g);
        }
        return newImg;
    }

    public static final BufferedImage clone(BufferedImage image)
    {
        BufferedImage clone = new BufferedImage(image.getWidth(),
                image.getHeight(), image.getType());
        Graphics g = clone.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return clone;
    }

    public BufferedImage ricreaImmagineDeiPuntiRipresi(int w, int h)
    {
        BufferedImage newImg = clone(immagineDalleListe);
        Graphics g = newImg.getGraphics();
        g.setColor(Color.RED);
        int raggio = Lista.RAGGIO_PER_PUNTI_DI_MEZZO;
        for (int i = 0; i < puntiDiMezzo.size(); i++)
        {
            Punto p = puntiDiMezzo.get(i);
            g.drawOval(p.c - raggio, p.r - raggio, 2 * raggio + 1, 2 * raggio + 1);
        }
        return newImg;
    }

    private int numeroUnoAdiacenti(int r, int c)
    {
        int n = 0;
        if (m[r - 1][c] == 1)  // sopra
            n++;
        if (m[r + 1][c] == 1)  // sotto
            n++;
        if (m[r][c - 1] == 1)  // sx
            n++;
        if (m[r][c + 1] == 1)  // dx
            n++;
        if (m[r - 1][c - 1] == 1) // sopra sx
            n++;
        if (m[r + 1][c - 1] == 1) // sotto sx
            n++;
        if (m[r - 1][c + 1] == 1) // sopra dx
            n++;
        if (m[r + 1][c + 1] == 1) // sotto dx
            n++;
        return n;
    }

    private void rimuoviNeriNonCollegatiA2Pixel()
    {
        int i, j;
        int[][] m2 = cloneArray(m);
        matriceDeiTolti = new int[m.length][m[0].length];
        for (i = 0; i < m.length; i++)
            for (j = 0; j < m[i].length; j++)
                if (m[i][j] == 1 && numeroUnoAdiacenti(i, j) > 2)
                {
                    m2[i][j] = 0;
                    matriceDeiTolti[i][j] = 1;
                }
        m = cloneArray(m2);
    }
    


public Punto cercaPuntoSuccessivo(int[][] m, Punto da, Punto a)
    {
        int r = a.r;
        int c = a.c;
        int prevr = da.r;
        int prevc = da.c;
        if (m[r + 1][c] == 1 && !(prevr == r + 1 && prevc == c))
            //m[r+1][c]=0;
            return new Punto(r + 1, c);
        if (m[r - 1][c] == 1 && !(prevr == r - 1 && prevc == c))
            //m[r-1][c]=0;
            return new Punto(r - 1, c);
        if (m[r][c + 1] == 1 && !(prevr == r && prevc == c + 1))
            //m[r][c+1]=0;
            return new Punto(r, c + 1);
        if (m[r][c - 1] == 1 && !(prevr == r && prevc == c - 1))
            //m[r][c-1]=0;
            return new Punto(r, c - 1);
        if (m[r + 1][c + 1] == 1 && !(prevr == r + 1 && prevc == c + 1))
            //m[r+1][c+1]=0;
            return new Punto(r + 1, c + 1);
        if (m[r + 1][c - 1] == 1 && !(prevr == r + 1 && prevc == c - 1))
            //m[r+1][c-1]=0;
            return new Punto(r + 1, c - 1);
        if (m[r - 1][c + 1] == 1 && !(prevr == r - 1 && prevc == c + 1))
            //m[r-1][c+1]=0;
            return new Punto(r - 1, c + 1);
        if (m[r - 1][c - 1] == 1 && !(prevr == r - 1 && prevc == c - 1))
            //m[r-1][c-1]=0;
            return new Punto(r - 1, c - 1);
        return null;
    }

    public void cercaSequenzaPartendoDa(int[][] m, int r, int c)
    {
        Punto da = new Punto(r, c);
        liste.inserisciInizio(da);
        m[r][c] = 0;
        Punto a = new Punto(da);
        Punto prossimo;
        boolean esito = false;
        do
        {
            prossimo = cercaPuntoSuccessivo(m, da, a);
            m[a.r][a.c] = 0;
            if (prossimo != null)
                esito = liste.provaAdAggiungere(a, prossimo);
            da = a;
            a = prossimo;
        }
        while (prossimo != null && esito);
    }

    private void creaListe(int[][] m)
    {
        int i, j;
        for (i = 0; i < m.length; i++)
            for (j = 0; j < m[i].length; j++)
                if (m[i][j] == 1 && numeroUnoAdiacenti(i, j) == 1)
                    cercaSequenzaPartendoDa(m, i, j);
    }

    private Punto trovaPunto(int[][] m)
    {
        int i, j;
        for (i = 0; i < m.length; i++)
            for (j = 0; j < m[0].length; j++)
                if (m[i][j] == 1)
                    return new Punto(i, j);
        return null;
    }

    private void cercaECancella(int[][] m, Ausiliaria a)
    {
        if (m[a.r][a.c] == 0)
            return;
        a.sr = a.sr + a.r;
        a.sc = a.sc + a.c;
        a.n = a.n + 1;
        m[a.r][a.c] = 0;
        Punto p = new Punto(a.r, a.c);
        cercaECancella(m, a.su(p.to(+1, +1)));
        cercaECancella(m, a.su(p.to(+1, +0)));
        cercaECancella(m, a.su(p.to(+1, -1)));
        cercaECancella(m, a.su(p.to(+0, +1)));
        cercaECancella(m, a.su(p.to(+0, -1)));
        cercaECancella(m, a.su(p.to(-1, +1)));
        cercaECancella(m, a.su(p.to(-1, +0)));
        cercaECancella(m, a.su(p.to(-1, -1)));
    }

    private void creaPuntiDaRimettere()
    {
        puntiDiMezzo = new ArrayList<>();
        Punto p;
        do
        {
            p = trovaPunto(matriceDeiTolti);
            if (p != null)
            {
                Ausiliaria a = new Ausiliaria(p);
                cercaECancella(matriceDeiTolti, a);
                Punto mezzo = a.puntoDiMezzo();
                if (mezzo != null)
                    puntiDiMezzo.add(mezzo);
            }
        }
        while (p != null);
    }
    
 public void sistemaListeConPuntiDiMezzo()
    {
        for (Punto p : puntiDiMezzo)
            //System.out.println("p=" + p.toString());
            for (Lista l : liste.l)
                l.sistematiConPuntoDiMezzo(p);
    }
 
public void creaNodiDelGrafo(Liste liste)
    {
        nodi = new Nodi(liste);
        int tot = 0;
        for (Lista l : liste.l)
            l.aggiungitiA(nodi);
        liste.stampati();
    }

    public Grafo creaGrafo()
    {
        Grafo grafo = new Grafo(nodi);
        for (Lista l : liste.l)
            if (l.idPrimo == -1 || l.idUltimo == -1)
                System.out.println("Esiste almeno una lista che non ha id del primo o del secondo:" + l.toString());
            else
            {
                int da, a;
                da = l.idPrimo;
                a = l.idUltimo;
                int peso = l.lunghezza;
                peso = 1;
                grafo.impostaPeso(peso, da, a);
                grafo.impostaPeso(peso, a, da);
                String percorso = l.toString();
                grafo.impostaPercorso(percorso, da, a);
                grafo.impostaPercorso(percorso, a, da);
            }
        //System.out.println(grafo.toString());
        grafo = semplifica(grafo);
        //System.out.println(grafo.toString());
        return grafo;
    }
    
    public Grafo semplifica(Grafo g){
        g.riduciAlGrafoEquivalente();
        return g;
    }
    
    public String navigaR(Grafo g, int nodoAttuale, int nodiNavigati){
        if (nodiNavigati==g.numeroNodi()-1)
            return "";
        Nodo n = nodi.get(nodoAttuale);
        n.visite++;
        int[] destinazioni = g.dovePossoAndarePartendoDa(nodoAttuale);
        int i;
        for(i=0;i<destinazioni.length;i++)
        {
            Nodo nn = nodi.get(destinazioni[i]);
            if (nn.visite==0){
                nn.visite++;
                String rr = navigaR(g, destinazioni[i], nodiNavigati+1);
                if (rr!="!")
                    return n.getInfo()+";"+rr;
            }
        }
        return "!";
    }
    
    
    
    private void resetVisite(Grafo g, int na, int nn) {
        if(nn>=g.numeroNodi()) return;
        Nodo n = g.nodi.get(na);
        n.visite = 0;
        int[] d = g.dovePossoAndarePartendoDa(na);
        for(int i=0;i<d.length; i++)
            resetVisite(g, d[i], nn+1);
    }
    
    private void aggiungiNodiAccorpati(ArrayList<Nodo> p, Nodo n) {
        for(Nodo na: n.nodiAccorpati) {
            p.add(na);
            aggiungiNodiAccorpati(p, na);
            p.add(n);
        }        
    }
    
    public ArrayList<Nodo> navigaRarray(Grafo g, int nodoAttuale, int nodiNavigati){
        ArrayList<Nodo> p = new ArrayList<>();
        if (nodiNavigati==g.numeroNodi())
            return p;
        Nodo n = g.nodi.get(nodoAttuale);        
        n.visite++;
        p.add(n);
        aggiungiNodiAccorpati(p, n);
        int[] destinazioni = g.dovePossoAndarePartendoDa(nodoAttuale);
        for(int i=0;i<destinazioni.length;i++)
        {
            Nodo nn = g.nodi.get(destinazioni[i]);
            if (nn.visite==0){
                p.addAll(navigaRarray(g, destinazioni[i], nodiNavigati+1));
                return p;
            }
        }
        return p;
    }
    
    public String navigaGrafo(Grafo gg){
        return navigaR(gg.clone(),0,0);
    }

    private ArrayList<Point> agguingiPuntiIntermedi(ArrayList<Point> p) {
        int n = p.size();
        ArrayList<Point> pa = new ArrayList<>();
        if(n==1) {
            pa.add(p.get(0));
            return pa;
        }
        for(int i=0;i<n;i++) {
            Point n2 = p.get(i);
            Point n1 = i<n-1 ? p.get(i+1) : p.get(0);
            int dx = abs(n1.x - n2.x);
            int dy = abs(n1.y - n2.y);
            int ns = dx>dy ? dx : dy;
            for(int k=0;k<ns;k+=1) {
                int nk=ns-k;
                pa.add( new Point( (n1.x*k+n2.x*nk)/ns, (n1.y*k+n2.y*nk)/ns) );
            }            
        }
        return pa;
    }