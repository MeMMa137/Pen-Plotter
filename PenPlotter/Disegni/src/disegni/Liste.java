package disegni;

import java.util.ArrayList;

public class Liste {

    ArrayList<Lista> l;

    public Liste() {
        l = new ArrayList<>();
    }

    public boolean provaAdAggiungere(Punto da, Punto a) {
        for (Lista e : l)
            if (e.provaAdInserire(da, a))
                return true;
        return false;
    }

    public void inserisciInizio(Punto c) {
        Lista ll = new Lista();
        ll.inserisciInTesta(c);
        l.add(ll);
    }

    public int size(){
        return l.size();
    }
    
    public Lista get(int i){
        return l.get(i);
    }
    
    public void stampati(){
        for (Lista e : l)
           System.out.println(e.toString());
        System.out.println("-----------------------------");
    }
    
    public Lista trovaListaDaA(int da, int a){
        System.out.println("Cerco lista da:"+da+ " a "+a);
        for(Lista e : l){
            if ((e.idPrimo == da && e.idUltimo == a) || (e.idPrimo == a && e.idUltimo == da))
                return e;
            System.out.println("No match per la lista "+e.idPrimo+","+e.idUltimo+" cioè "+e.testa.inf+" "+e.penultimo.next.inf.toString());
        }
        return null;
    }
    
    @Override
    public Liste clone(){
        Liste ll = new Liste();
        for(int i=0;i<l.size();i++)
            ll.l.add(l.get(i).clone());
        return ll;
    }
}
