from Communicator import Communicator
from GUI import MainWindow
from time import sleep

gui = MainWindow()
com = Communicator()
gui.com = com
gui.main_loop()


while True:
    if com.available()>0:
        s = com.read()
        com.write(s)
    sleep(1)