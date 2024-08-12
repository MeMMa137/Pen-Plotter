package disegni;

import java.awt.Point;
import java.util.ArrayList;

public class Protocol {
    public static final String POINTS_SEPARATOR = ";";
    public static final String COORDS_SEPARATOR = ",";
    public static final String ARGS_SEPARATOR = "_";
    public static final int N_DIGIT_LENGTH = 4;
    private static String toFixedLength(String s) {
        for(int i=s.length();i<N_DIGIT_LENGTH;i++)
            s="0"+s;
        return s;
    }
    public static String encode(ArrayList<Point> p) {
        String s = toFixedLength(p.size()+"");
        s += ARGS_SEPARATOR;
        int i;
        for(i=0;i<p.size()-1;i++)
            s += p.get(i).x+COORDS_SEPARATOR+p.get(i).y+POINTS_SEPARATOR;
        s += p.get(i).x+COORDS_SEPARATOR+p.get(i).y;
        return s;
    }
}
