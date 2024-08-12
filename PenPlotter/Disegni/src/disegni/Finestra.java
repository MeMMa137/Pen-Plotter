/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disegni;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Finestra extends javax.swing.JFrame implements ElaboraInterface
{

    static double scala = 3;
    int w, h;

    public Finestra()
    {
        w = 100;
        h = 100;
        initComponents();
        visualizzatore1.label = "Area di disegno";
        visualizzatore2.label = "Punti da unire";
        visualizzatore3.label = "Punti da rimettere";
        visualizzatore4.label = "Percorsi trovati";
        visualizzatore5.label = "Punti ripresi";

        visualizzatore1.img = generaImmagine(w, h);
        visualizzatore2.cambiaImmagine(generaImmagine(w, h));
        visualizzatore3.cambiaImmagine(generaImmagine(w, h));
        visualizzatore4.cambiaImmagine(generaImmagine(w, h));
        visualizzatore5.cambiaImmagine(generaImmagine(w, h));
        visualizzatore6.cambiaImmagine(generaImmagine(w, h));
        visualizzatore1.scala = scala;
        sistemaComponenti();
        visualizzatore1.addListener(this);

        communicator = new Communicator(true);
    }

    private void sistemaComponenti()
    {
        if (visualizzatore1 == null || visualizzatore1.img == null)
            return;
        Rectangle r = new Rectangle();
        Insets inset = getInsets();
        r.y = 30; //inset.top;
        r.x = 10;
        int ww = (int) (visualizzatore1.img.getWidth() * scala);
        r.width = ww;
        int hh = (int) (visualizzatore1.img.getHeight() * scala);
        r.height = hh;
        visualizzatore1.setBounds(r);
        r.x = r.x + ww + 10;
        visualizzatore2.setBounds(r);
        r.y = r.y + hh + 10;
        visualizzatore3.setBounds(r);
        r.y = r.y - hh - 10;
        r.x = r.x + ww + 10;
        visualizzatore4.setBounds(r);
        r.y = r.y + hh + 10;
        visualizzatore5.setBounds(r);
        r.y = r.y - hh - 10;
        r.x = r.x + ww + 10;
        visualizzatore6.setBounds(r);
        r.x = getX();
        r.y = getY();
        r.width = 30 + ww * 4 + 10;
        r.height = 30 + hh * 2 + 10;
        setBounds(r);

    }

    private BufferedImage generaImmagine(int w, int h)
    {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        return img;
    }

    @Override
    public void elabora()
    {
        Engine e = new Engine(visualizzatore1.img);
        e.elabora();
        visualizzatore2.cambiaImmagine(e.dst);
        visualizzatore3.cambiaImmagine(e.dstDeiTolti);
        visualizzatore4.cambiaImmagine(e.immagineDalleListe);
        visualizzatore5.cambiaImmagine(e.immagineDeiPuntiRipresi);
        visualizzatore6.cambiaImmagine(e.immagineDalleListe2);
        vg.setGrafo(e.grafo);
        vg.repaint();
        salvaImmagine("last.png");
        engine=e;
    }

    private BufferedImage caricaImmagine(String imgPath)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(imgPath));
        }
        catch (IOException e)
        {
        }
        return img;
    }

    private void salvaImmagine(String imgPath)
    {
        BufferedImage img = null;
        try
        {
            ImageIO.write(visualizzatore1.img, "png", new File(imgPath));
        }
        catch (IOException e)
        {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        ricaricaLast = new javax.swing.JButton();
        cancellaTutto = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        puntiIntermediCheckBox = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jToolBar1.setRollover(true);

        ricaricaLast.setText("Ricarica ultima immagine");
        ricaricaLast.setFocusable(false);
        ricaricaLast.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ricaricaLast.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ricaricaLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ricaricaLastActionPerformed(evt);
            }
        });
        jToolBar1.add(ricaricaLast);

        cancellaTutto.setText("Cancella Bitmap");
        cancellaTutto.setFocusable(false);
        cancellaTutto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancellaTutto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancellaTutto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaTuttoActionPerformed(evt);
            }
        });
        jToolBar1.add(cancellaTutto);

        sendButton.setText("Invia dati");
        sendButton.setFocusable(false);
        sendButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sendButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(sendButton);

        puntiIntermediCheckBox.setText("aggiungi punti intermedi");
        puntiIntermediCheckBox.setFocusable(false);
        puntiIntermediCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        puntiIntermediCheckBox.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        puntiIntermediCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntiIntermediCheckBoxActionPerformed(evt);
            }
        });
        jToolBar1.add(puntiIntermediCheckBox);

        getContentPane().add(jToolBar1);
        jToolBar1.setBounds(0, 0, 730, 40);
        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ricaricaUltimaImmagineSalvata(){
        visualizzatore1.img = caricaImmagine("last.png");
        repaint();
        elabora();
    }
    
    private void ricaricaLastActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ricaricaLastActionPerformed
    {//GEN-HEADEREND:event_ricaricaLastActionPerformed
        ricaricaUltimaImmagineSalvata();
    }//GEN-LAST:event_ricaricaLastActionPerformed

    private void cancellaTuttoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancellaTuttoActionPerformed
    {//GEN-HEADEREND:event_cancellaTuttoActionPerformed
        visualizzatore1.img = generaImmagine(w, h);
        visualizzatore2.cambiaImmagine(generaImmagine(w, h));
        visualizzatore3.cambiaImmagine(generaImmagine(w, h));
        visualizzatore4.cambiaImmagine(generaImmagine(w, h));
        visualizzatore5.cambiaImmagine(generaImmagine(w, h));
        visualizzatore6.cambiaImmagine(generaImmagine(w, h));
        repaint();
    }//GEN-LAST:event_cancellaTuttoActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        //elabora();
        Grafo g = Disegni.fps.getLast();
        ArrayList<Point> nl =engine.navigaGrafoArray(g,puntiIntermediCheckBox.isSelected()); 
        for(Point n: nl) {
            System.out.println(n.toString());
        }
        communicator.write(Protocol.encode(nl));
    }//GEN-LAST:event_sendButtonActionPerformed

    private void puntiIntermediCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntiIntermediCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puntiIntermediCheckBoxActionPerformed

    public VisualizzatoreGrafo vg;
    private Engine engine;
    private Communicator communicator;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancellaTutto;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JCheckBox puntiIntermediCheckBox;
    public javax.swing.JButton ricaricaLast;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
