package disegni;

import disegni.Punto;

public class Ausiliaria {
    int r;
    int c;
    int n;
    int sr;
    int sc;
    public Ausiliaria(Punto p){
        r = p.r;
        c = p.c;
        n = 0;
        sr = 0;
        sc = 0;
    }
    
    public Ausiliaria su(Punto p){
        r = p.r;
        c = p.c;
        return this;
    }
    
    public Punto puntoDiMezzo(){
        if (n==0)
            return null;
        Punto p = new Punto(sr/n,sc/n);
        return p;
    }
}
