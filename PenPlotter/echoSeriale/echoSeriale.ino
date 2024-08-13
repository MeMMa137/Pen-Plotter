
void setup() {
  Serial.begin(9600);
}

void loop() {
  if(Serial.available()>0) {//Se ho ricevuto almeno un byte 

    int chIn = Serial.read();
    Serial.write(chIn);
  }
}
