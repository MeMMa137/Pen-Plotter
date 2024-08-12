package disegni;

import java.awt.Point;
import java.util.ArrayList;

class Nodo
{

    private int x;
    private int y;
    private int ox;
    private int oy;
    int raggio;
    String nome;
    int indice;
    Punto punto;
    String infoAccorpamento;
    ArrayList<Nodo> nodiAccorpati;
    private int letture;
    int visite;

    public Nodo()
    {
        x = 100;
        y = 100;
        punto = new Punto(10, 100);
        raggio = 10;
        nome = "senza nome";
        indice = 0;
        ox = 0;
        oy = 0;
        infoAccorpamento = "";
        letture = 0;
        visite = 0;
        nodiAccorpati = new ArrayList<>();
    }

    public Point toPoint() {
        return new Point(x,y);
    }

    public Nodo(int x, int y, int raggio, String nome, int indice)
    {
        this.x = x;
        this.y = y;
        punto = new Punto(y, x);
        this.raggio = raggio;
        this.nome = nome;
        this.indice = indice;
        ox = 0;
        oy = 0;
        infoAccorpamento = "";
        letture = 0;
        visite = 0;
        nodiAccorpati = new ArrayList<>();
    }

    String getInfo()
    {
       if (letture>0)
       {
           letture++;
           return punto.toStringForMachine();
       }
       else
       {
           letture++;
           return infoAccorpamento;
       }
    }
    
    public Nodo clone()
    {
        Nodo n = new Nodo();
        n.x = x;
        n.y = y;
        n.ox = ox;
        n.oy = oy;
        n.raggio = raggio;
        n.nome = nome;
        n.indice = indice;
        n.punto = punto.clone();
        n.infoAccorpamento = infoAccorpamento;
        return n;
}

public boolean interno( int x, int y)
    {
        int dx = this.x - x;
        int dy = this.y - y;
        double r = Math.sqrt(dx*dx+dy*dy);
        return r<raggio;
    }
    
    public void aggiornaOffset(int ox, int oy)
    {
        this.ox = ox;
        this.oy = oy;
    }
    
    public void fissaOffset(){
        x = x + ox;
        y = y + oy;
        ox = 0;
        oy = 0;
    }
    
    public int x(){
        return (int)((x+ox)*Finestra.scala);
    }
    
    public int y(){
        return (int)((y+oy)*Finestra.scala);
    }
    
    @Override
    public String toString() {
        return punto.toStringForMachine();
    }
}
