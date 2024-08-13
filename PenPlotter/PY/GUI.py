from tkinter import *
from Protocol import Protocol

class MainWindow(Frame):
    def __init__(self):
        self.root = Tk()
        Frame.__init__(self, self.root)
        self.pack(fill=BOTH, expand=1)
        w=210
        h=293
        self.root.geometry(str(w)+"x"+str(h+50)+"+100+100")
        self.prot = Protocol()
        
        self.r=2
        self.canvas = Canvas(self, width=w, height=h)
        self.p=[]
        
        self.canvas.place(x=0, y=0)
        self.canvas.bind("<Button-1>", self.on_canvas_click)
        self.canvas.bind("<B1-Motion>", self.on_canvas_click)
        self.on_clear_click(None)
        
        self.send_button = Button(self,text="Send")
        self.send_button.place(x=10,y=h+10)
        self.send_button.bind("<Button-1>",self.on_send_click)
        
        self.clear_button = Button(self,text="Clear")
        self.clear_button.place(x=100,y=h+10)
        self.clear_button.bind("<Button-1>",self.on_clear_click)
        
    def on_canvas_click(self, event):
        print(event)
        self.drawPoint(event)
    
    def on_send_click(self, event):
        print("on_send_click")
        if(self.com!=None):
            s = self.prot.encode(self.p)
            print("sending to plotter: "+s)
            self.com.write(s)
        else:
            print("cannot send data")

    def on_clear_click(self, event):
        print("on_clear_click")
        self.canvas.delete("all")
        self.p.clear()
        self.canvas.create_rectangle(0,0,210,293, fill="white")
        

    def drawPoint(self, p):
        self.p.append(p)
        x=p.x-self.r/2
        y=p.y-self.r/2
        self.canvas.create_oval(x, y, x+self.r, y+self.r, fill="black")
        
    def main_loop(self):
        self.root.mainloop()