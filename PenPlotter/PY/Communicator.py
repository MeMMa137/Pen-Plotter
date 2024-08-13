from threading import Thread
import socket

class TcpServer(Thread):
    def __init__(self):
        super(TcpServer,self).__init__()
        self.host="localhost"
        self.port=5000
        self.str_data = ""
        self.conn = None
    
    def run(self):
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.bind((self.host, self.port))
            s.listen()
            conn, addr = s.accept()
            self.conn = conn
            with conn:
                print('Connected by', addr)
                self.str_data = ""
                while True:
                    dd = conn.recv(1024)
                    self.str_data += dd.decode("utf-8") 
                    print(self.str_data)
                    #if not data:
                    #    break
                    #conn.sendall(data.encode("utf-8"))

    def send(self,str_data):
        if self.conn!=None:
            self.conn.sendall(str_data.encode("utf-8"))
        else:
            print("Cannot send "+str_data+" because not connected")

    def get_str_data(self):
        d = self.str_data
        self.str_data = ""
        return d
    
    def available(self):
        return len(self.str_data)

class Communicator:
    def __init__(self, is_fake=True):        
        self._is_fake = is_fake
        if is_fake:
            self.tcp_server = TcpServer()
            self.tcp_server.start()

    def available(self):
        return self.tcp_server.available()
    
    def read(self):
        return self.tcp_server.get_str_data()
    
    def write(self, str_data):
        self.tcp_server.send(str_data)
    
