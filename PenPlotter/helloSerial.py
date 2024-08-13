#Importo la libreria di accesso alla seriale
#Se non l'avete dovete fare pip3 install pyserial
import serial

#definico la porta che voglio aprire:
serialPortName = '/dev/cu.usbmodem14101'

#creo l'oggeto della seriale ed apro la porta
s = serial.Serial( serialPortName )

#chiedo un input all'utente
msg = input('cosa vuoi scrivere sulla seriale: ')

#invio il messaggio convertito da stringa in sequenza di byte
n = s.write( msg.encode() )
# la scrittura mi restituisce il numero di byte scritti

#adesso voglio leggere un numero di byte noto (ovvero n)
bytes = s.read(n)

#stampo la conversione a stringa dei byte letti
print( bytes.decode('utf-8') )

#chiudo la seriale
s.close()
