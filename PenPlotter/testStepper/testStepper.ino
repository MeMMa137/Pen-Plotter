#include <AccelStepper.h>

const int stepsPerRev = 2048;
int v=stepsPerRev/4;

long goal1 = 0;
long goal2 = 0;

AccelStepper stepper1 = AccelStepper(4, 10, 12, 11, 13);
AccelStepper stepper2 = AccelStepper(4, 6, 8, 7, 9);

const int pinFineCorsaIndietroM1 = 2;
const int pinFineCorsaAvantiM1 = 3;
const int pinFineCorsaIndietroM2 = 4;
const int pinFineCorsaAvantiM2 = 5;

long maxPosX=0;
long maxPosY=0;
void calibra() {
  //andiamo a battura con i motori indietro
  //indietro asse X
  stepper1.setSpeed(-v);
  while( digitalRead(pinFineCorsaIndietroM1)==LOW ) {
    stepper1.runSpeed();
  }
  stepper1.setCurrentPosition(0);
  //indietro asse Y
  stepper2.setSpeed(-v);
  while( digitalRead(pinFineCorsaIndietroM2)==LOW ) {
    stepper2.runSpeed();
  }
  stepper2.setCurrentPosition(0);

  //azzeriamo il conteggio dei giri massimi (che ci dicono la lunghezza delle barre)
  stepper1.setSpeed(v);
  while( digitalRead(pinFineCorsaAvantiM1)==LOW ) {
    stepper1.runSpeed();
    Serial.println(stepper1.currentPosition());
  }
  maxPosX = stepper1.currentPosition();

  stepper2.setSpeed(v);
  while( digitalRead(pinFineCorsaAvantiM2)==LOW ) {
    stepper2.runSpeed();
    Serial.println(stepper2.currentPosition());
  }
  maxPosY = stepper2.currentPosition();
  //salviamo il valore dei contatori come massima escursione

  char s[100];
  sprintf(s,"maxPosX = %li, maxPosY = %li :)",maxPosX,maxPosY);
  Serial.println(s);
}

int x,y;
const int soglia=1;


void setup() {
  stepper1.setMaxSpeed(v);
  stepper2.setMaxSpeed(v);
  stepper1.setCurrentPosition(0);
  stepper2.setCurrentPosition(0);
  
  pinMode(pinFineCorsaIndietroM1,INPUT);
  pinMode(pinFineCorsaIndietroM2,INPUT);
  pinMode(pinFineCorsaAvantiM1,INPUT);
  pinMode(pinFineCorsaAvantiM2,INPUT);
  Serial.begin(9600);
  calibra();


void testButton(int pin) {
  Serial.println(digitalRead(pin));
}

bool actuateStepper(bool isFirst) {
  AccelStepper *stepper = isFirst ? &stepper1 : &stepper2;
  long goal = isFirst ? goal1 : goal2;
  if(stepper->currentPosition() == goal) return true;
  if(stepper->currentPosition() < goal) stepper->setSpeed(+v);
  else                                  stepper->setSpeed(-v);
  stepper->runSpeed();
  return false;
}

int i=0;
long goals[]={+3*stepsPerRev,2*stepsPerRev, 2*stepsPerRev,+3*stepsPerRev, 0, 5*stepsPerRev};

void test() {
    goal1=goals[i++];
    goal2=goals[i++];
    i = i%6;
    delay(1000);  
}


void loop() {
  bool g1=actuateStepper(true);//&stepper1,goal1);
  bool g2=actuateStepper(false);//&stepper2,goal2);
  if(g1 && g2) {
    test();
  }
  
  //test(&s1);
  //test(&s2);
  //testButton(pinFineCorsaIndietroM1);
  //testButton(pinFineCorsaAvantiM1);
  //testButton(pinFineCorsaIndietroM2);
  //testButton(pinFineCorsaAvantiM2);
  return;
  
  if(Serial.available()>3) {
    //xd e yd sono le posizioni in cui voglio andare
    int xd1 = Serial.read();
    int xd2 = Serial.read();
    int xd = xd1<<8+xd2;
    int yd1 = Serial.read();
    int yd2 = Serial.read();
    int yd = yd1<<8+xd2;

    //finche non sono arrivato nel punto dove devo ndare
    while ( abs(x-xd)>soglia || abs(y-yd)>soglia  ) {
      //decido in che direzione girare il motore orizzontale
      if (x>xd) {
        //s1.step(stepPerGiro); //faccio 1 giro in avanti
      } else {
        //s1.step(-stepPerGiro); //faccio 1 giro in avanti      
      }
      // decido in che direzione girar eil motore verticale
      if (y>yd) {
        //s2.step(stepPerGiro); //faccio 1 giro in avanti
      } else {
        //s2.step(-stepPerGiro); //faccio 1 giro in avanti      
      } 
      //aspetto che girino   
      delay(500);
    }
    //dico al computer che mi puo' dare le prossime cooordinate
    Serial.println("ok");    
  }
}
