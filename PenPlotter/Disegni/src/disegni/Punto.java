package disegni;

public class Punto {

    int r;
    int c;

    public Punto() {
        r = 0;
        c = 0;
    }

    public Punto(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    public Punto(Punto p){
        this.r = p.r;
        this.c = p.c;
    }
    
    public Punto clone(){
        return new Punto(this);
    }
    
    public Punto to(int dr, int dc){
        return new Punto(r+dr,c+dc);
    }
    
    public boolean equals(Punto p){
        return (p.c==c && p.r==r);
    }
    
    @Override
    public String toString(){
        String s = "("+c+","+r+")";
        return s;
    }
    
    public String toStringForMachine(){
        String s = c+","+r;
        return s;
    }
    
    int distaDa(Punto p){
        return (int)Math.sqrt((p.r-r)*(p.r-r)+(p.c-c)*(p.c-c));
    }
}
