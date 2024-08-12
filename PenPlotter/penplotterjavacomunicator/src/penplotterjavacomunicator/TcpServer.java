package penplotterjavacomunicator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TcpServer extends Thread{
    private final int PORT = 5000;
    private String strData;
    private Socket socket;
    private DataOutputStream dos;
    public TcpServer() {
        strData="";
        socket=null;
        dos=null;
    }
    @Override
    public void run() {
        while(true) {
            ServerSocket ss=null;
            try {
                ss = new ServerSocket(PORT);
                socket = ss.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                byte[] buffer = new byte[1024];
                while(true) {
                    dis.read(buffer);
                    strData += new String(buffer);
                }
                
            }catch(Exception e) {
                System.out.println(e);
                try{
                    if(socket!=null)
                        socket.close();
                    if(ss!=null)
                        ss.close();
                }catch(Exception ee){}
            }
        }
    }
    public int getAvailable() {
        return strData.length();
    }
    public String getStrData() {
        String s = strData;
        strData="";
        return s;
    }
    public void send(String msg) {
        if(dos==null) return;
        try {
            dos.write(msg.getBytes());
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
