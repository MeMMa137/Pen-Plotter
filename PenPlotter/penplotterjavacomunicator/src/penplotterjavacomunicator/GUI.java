package penplotterjavacomunicator;

import javax.swing.JFrame;

class GUI {
    GUI(Communicator c) {
        JFrame f = new JFrame();
        Pannello p = new Pannello(c);
        f.add(p);
        int w=210;
        int h=293;
        f.setBounds(0, 0, w, h+50);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
