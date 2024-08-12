package disegni;

import javax.swing.JFrame;

public class Disegni 
{
    static FinestraPassiSemplificazione fps;
    
    public static void main(String[] args) {
        Finestra f = new Finestra();
        f.setVisible(true);
        
        FinestraGrafo fg = new FinestraGrafo();
        f.vg = fg.visualizzatoreGrafo;
        fg.setVisible(false);
        
        
        fps = new FinestraPassiSemplificazione();
        fps.setVisible(false);
        
        f.ricaricaUltimaImmagineSalvata();
    }
    
}
