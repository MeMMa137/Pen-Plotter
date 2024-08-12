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