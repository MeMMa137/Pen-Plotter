
package disegni;

public class Elem<T> {

    T inf;
    Elem<T> next;

    public Elem(T p) {
        inf = p;
        next = null;
    }

    public Elem(T p, Elem next) {
        inf = p;
        this.next = next;
    }
}
