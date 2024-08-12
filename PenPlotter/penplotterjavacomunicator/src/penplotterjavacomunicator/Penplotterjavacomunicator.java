package penplotterjavacomunicator;

public class PenPlotterJavaComunicator {
    public static void main(String[] args) {
        Comunicator c = new Comunicator(true);
        GUI gui = new GUI(c);        
    }
}