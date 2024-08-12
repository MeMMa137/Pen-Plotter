package disegni;

import disegni.TcpServer;

public class Communicator {
    private boolean isFake;
    private TcpServer tcpServer;
    public Communicator(boolean isFake) {
        this.isFake = isFake;
        tcpServer = new TcpServer();
        tcpServer.start();
    }
    public int available() {
        return tcpServer.getAvailable();
    }
    public String read() {
        return tcpServer.getStrData();
    }
    public void write(String msg) {
        tcpServer.send(msg);
    }
    
}
