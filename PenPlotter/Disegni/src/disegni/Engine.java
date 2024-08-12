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