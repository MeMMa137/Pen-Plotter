package disegni;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author simone
 */
public class FinestraPassiSemplificazione extends javax.swing.JFrame
{

    public FinestraPassiSemplificazione()
    {
        initComponents();
        passi = new ArrayList<>();
    }

    public void azzeraPassi()
    {
        passi = new ArrayList<>();
    }

    public void visualizzaUltimo()
    {
        visualizzaPasso(passi.size()-1);
    }
    
    public void visualizzaPasso(int passo)
    {
        if (passi==null || passi.isEmpty() || passo<0 || passo>=passi.size())
            return;
        Grafo g = passi.get(passo);
        vg.setGrafo(g.clone());
        noditxt.setText(vg.getNodiString());
        grafotxt.setText(vg.getGrafoString());
        this.passo.setValue(passo);
        vg.repaint();
    }

    public void aggiungiGrafo(Grafo g)
    {
        passo.setMinimum(0);
        passi.add(g.clone());
        passo.setMaximum(passi.size());
        visualizzaUltimo();
    }
    
    public Grafo getLast() {
        if(passi.size()<=0)
            return null;
        return passi.get(passi.size()-1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        passo = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        vg = new disegni.VisualizzatoreGrafo();
        jScrollPane1 = new javax.swing.JScrollPane();
        noditxt = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        grafotxt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        passo.setPaintLabels(true);
        passo.setPaintTicks(true);
        passo.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                passoStateChanged(evt);
            }
        });

        jLabel1.setText("Passo");

        javax.swing.GroupLayout vgLayout = new javax.swing.GroupLayout(vg);
        vg.setLayout(vgLayout);
        vgLayout.setHorizontalGroup(
            vgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );
        vgLayout.setVerticalGroup(
            vgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        noditxt.setColumns(20);
        noditxt.setRows(5);
        jScrollPane1.setViewportView(noditxt);

        grafotxt.setEditable(false);
        grafotxt.setBackground(new java.awt.Color(208, 194, 194));
        grafotxt.setColumns(20);
        grafotxt.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        grafotxt.setForeground(new java.awt.Color(212, 23, 23));
        grafotxt.setRows(5);
        jScrollPane3.setViewportView(grafotxt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(passo, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(vg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passoStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_passoStateChanged
    {//GEN-HEADEREND:event_passoStateChanged
        if (passo!=null)
            visualizzaPasso(passo.getValue());
    }//GEN-LAST:event_passoStateChanged

    private ArrayList<Grafo> passi;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea grafotxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea noditxt;
    private javax.swing.JSlider passo;
    private disegni.VisualizzatoreGrafo vg;
    // End of variables declaration//GEN-END:variables
}
