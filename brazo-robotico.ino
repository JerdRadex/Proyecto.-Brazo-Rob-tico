/*
 Fecha: 17/05/2018

Autores: Ana Evelia Hernandez Aguirre, Karla Paola Gaona Delgado y Luis Daniel Ramirez Bravo.

Nombre de la practica : Brazo Robotico

Descripcion: practica que utiliza servos los cuales seran configurados por medio de arduino,
transmitiendole los datos (grados) desde una interfaz realizada en java la cual pide escojer
los grados ya definidos en varios JComboBox para asi poder realizar el movimeinto de los servos.
Tambien cuenta con un motor a pasos en la cintira del brazo robotico que tambien recibe los datos
(grados) de la interfaz en java mediante un JComboBox y son enviados al arduino para que ejecute 
el codigo necesario y pueda girar de 0 a 360 grados. En un JTextArea en la interfaz se muestra los
datos seleccionados para ejecutar y al apretar el boton para enviar los datos, se prendera un led (verde)
para indicar que  el brazo esta en movimiento y cuando acabe de su ejecucion se apagare el led y
prendera un led rojo y asi indicar que el brazo no esta en ejecicion.
*/

//libreria para controlar los servos.
#include <Servo.h>

int pulsador=0;              //almacena el estado del botón
int estado=0;                //0=led apagado, 1=led encendido
int pulsadorAnt=0;           //almacena el estado anterior del boton

// crear un objetos tipo servo para controlar los servos
Servo myservo;  
Servo myservo2;
Servo myservo3;

//variables para guardar el mensaje que se envo desde java.
String Mensaje="",Mensaje2="",Mensaje3="",Mensaje4="",Mensaje5="",Mensaje6="",Mensaje7="",Mensaje8="",Mensaje9="",Mensaje10="",Mensaje11="",Mensaje12="";
//variable que guarda el primer dato (char) de el mensaje enviado desde java.
char pestana;
//variables para el estado inicial de los servos
int grado=30;
int grado2=30;
int grado3=30;
int grado4=30;

// Tiempo de retardo en milisegundos (Velocidad del Motor)
int retardo=5;   
 // valor recibido en grados       
int dato_rx,dato_rx2;  
// Valor en grados donde se encuentra el motor         
int numero_pasos = 0;  
// Almacena la cadena de datos recibida 
String leeCadena,leeCadena2;       

// Pin para el LED
int led1 = 12;  
// Pin para el LED                       
int led2 = 13; 
// Pin para el Boton                        
int boton= 2;
//contador para el boton;
int contador=0;

int estado1=0;

//lectura de el estado del boton.


//inicializacion de los pines que se ocuparan en el arduino
void setup() {
  //inicializacion del servo1 en el pin 9
   myservo.attach(9);
   //inicializacion del servo 2 en el pin 10
   myservo2.attach(10);
   //inicializacion del servo 3 en el pin 11
   myservo3.attach(11);

   // Pin 6 conectar a IN4, inicializacion como salida.
   pinMode(6, OUTPUT);  
   // Pin 5 conectar a IN3, inicializacion como salida. 
   pinMode(5, OUTPUT);  
   // Pin 4 conectar a IN2, inicializacion como salida.  
   pinMode(4, OUTPUT);     
   // Pin 3 conectar a IN1, inicializacion como salida.
   pinMode(3, OUTPUT);     

   // inicializacion del led1 como salida.
   pinMode(led1, OUTPUT);
   //inicializacion del led2 como salida.
   pinMode(led2, OUTPUT);
   //inicializacion del boton como entrada.
   pinMode(boton, INPUT);

   myservo.write(60);
   myservo2.write(50);
 
   // se inicializa el serial.
   Serial.begin(9600);
}

//servos segundo movimiento (2.1)
void inicial4(){
  //la variable grado toma el valor de mensaje.
    grado=Mensaje11.toInt();
    //el servo se ejecuta con el valor de la variable grado
    myservo.write(grado);
    //se le da un tiempo de 100 milisegundos.
    delay (1000);

    //la variabe gardo2 toma el valor de mensaje2. 
    grado2=Mensaje12.toInt();
    //el servo se ejecuta con el valor de la variable grado2
    myservo2.write(grado2);
    //se le da un tiempo de 100 milisegundos.
    delay (1000);
}

//motor segunda vez
void inicial3(){
   // Girohacia la izquierda en grados
    while (dato_rx2>numero_pasos){   
       paso_izq();
       numero_pasos = numero_pasos + 1;
   }
    // Giro hacia la derecha en grados
   while (dato_rx2<numero_pasos){ 
        paso_der();
        numero_pasos = numero_pasos -1;
   }  

    // Inicializamos la cadena de caracteres recibidos 
    leeCadena2 = "";  
    //apagamos el motor.
    apagado();


    //la variable grado toma el valor de mensaje.
    grado=Mensaje5.toInt();
    //el servo se ejecuta con el valor de la variable grado
    myservo.write(grado);
    //se le da un tiempo de 100 milisegundos.
    delay (2000);

    //la variabe gardo2 toma el valor de mensaje2. 
    grado2=Mensaje6.toInt();
    //el servo se ejecuta con el valor de la variable grado2
    myservo2.write(grado2);
    //se le da un tiempo de 100 milisegundos.
    delay (2000);
    
    //si la variable Mensaje3 es igual a 12, entonces se ejecutara el codigo detro del if.
    if(Mensaje7 == "12"){
      // se abrira la pinza del brazo.
      abrir();
    // si la variable mensaje3 es igual a 70, entonces se ejecutara el codigo detro del if.
    }else if(Mensaje7 == "70"){
      // se cerrara la pinza del brazo.
      cerrar();
  }
  
}

//servos primer movimiento (1.1)
void inicial2(){  
  //la variable grado toma el valor de mensaje.
    grado=Mensaje9.toInt();
    //el servo se ejecuta con el valor de la variable grado
    myservo.write(grado);
    //se le da un tiempo de 100 milisegundos.
    delay (1000);

    //la variabe gardo2 toma el valor de mensaje2. 
    grado2=Mensaje10.toInt();
    //el servo se ejecuta con el valor de la variable grado2
    myservo2.write(grado2);
    //se le da un tiempo de 100 milisegundos.
    delay (1000);

}

//loop que repetira las instrucciones.
void loop() {
  
  //apagar el led1.
  digitalWrite(led1,LOW);
  //encender el led2.
  digitalWrite(led2,HIGH);
  //a continuacion se guarda en la variable pestana el primer caracter del mensaje de java para saber que se ejecutara.
  pestana=Decimal_to_ASCII(Serial.read());
  
  // a continuacion se guardara en la variables mensaje cada caracter para formar el mensaje completo.
  Mensaje=Mensaje+Decimal_to_ASCII(Serial.read());
  Mensaje=Mensaje+Decimal_to_ASCII(Serial.read());

  Mensaje2=Mensaje2+Decimal_to_ASCII(Serial.read());
  Mensaje2=Mensaje2+Decimal_to_ASCII(Serial.read());

  Mensaje3=Mensaje3+Decimal_to_ASCII(Serial.read());
  Mensaje3=Mensaje3+Decimal_to_ASCII(Serial.read());
 
  Mensaje5=Mensaje5+Decimal_to_ASCII(Serial.read());
  Mensaje5=Mensaje5+Decimal_to_ASCII(Serial.read());

  Mensaje6=Mensaje6+Decimal_to_ASCII(Serial.read());
  Mensaje6=Mensaje6+Decimal_to_ASCII(Serial.read());

  Mensaje7=Mensaje7+Decimal_to_ASCII(Serial.read());
  Mensaje7=Mensaje7+Decimal_to_ASCII(Serial.read());
  //servos
  Mensaje9=Mensaje9+Decimal_to_ASCII(Serial.read());
  Mensaje9=Mensaje9+Decimal_to_ASCII(Serial.read());

  Mensaje10=Mensaje10+Decimal_to_ASCII(Serial.read());
  Mensaje10=Mensaje10+Decimal_to_ASCII(Serial.read());

  Mensaje11=Mensaje11+Decimal_to_ASCII(Serial.read());
  Mensaje11=Mensaje11+Decimal_to_ASCII(Serial.read());

  Mensaje12=Mensaje12+Decimal_to_ASCII(Serial.read());
  Mensaje12=Mensaje12+Decimal_to_ASCII(Serial.read());

    //a continuacion en el while se realizara mientras el serial contenga algo.
  while (Serial.available()>0 && contador<3) {   
    contador=contador+1;
    delay(retardo);
    // Lee los caracteres.
    char c  = Serial.read();   
    // Convierte Caracteres a cadena de caracteres.  
    leeCadena += c;              
  }
 
  if(leeCadena=="000"){
    leeCadena="0";
  }else if(leeCadena=="050"){
    leeCadena="50";
  }

  //a continuacion en el while se realizara mientras el serial contenga algo.
  while (Serial.available()) {   
    delay(retardo);
    // Lee los caracteres.
    char c2  = Serial.read();   
    // Convierte Caracteres a cadena de caracteres.  
    leeCadena2 += c2;              
  }
 
  //compara si la variable leeCadena contiene algo
  if (leeCadena.length()>0){  
    // Convierte Cadena de caracteres a Enteros     
    dato_rx = leeCadena.toInt();   
    // Envia valor en Grados
    Serial.print(dato_rx);  
    delay(retardo);
    // Ajuste de 512 vueltas a los 360 grados.
    dato_rx = (dato_rx * 1.4222222222); 
  } 
  
  if (leeCadena2.length()>0){  
    // Convierte Cadena de caracteres a Enteros     
    dato_rx2 = leeCadena2.toInt();   
    // Envia valor en Grados
    Serial.print(dato_rx2);  
    delay(retardo);
    // Ajuste de 512 vueltas a los 360 grados.
    dato_rx2 = (dato_rx2 * 1.4222222222); 
  } 

  // si el valor de la variable pestana es igual a 2 ejecutara el codigo.
  if(pestana == '2'){
    int pulsador = digitalRead(2);
    while(pulsador==0){

      //enciende el led 1.
      digitalWrite(led1,HIGH);
      //apaga el led 2.
      digitalWrite(led2,LOW);
     
      // Girohacia la izquierda en grados
      while (dato_rx>numero_pasos){   
        paso_izq();
        numero_pasos = numero_pasos + 1;
      }
     
      // Giro hacia la derecha en grados
      while (dato_rx<numero_pasos){ 
        paso_der();
        numero_pasos = numero_pasos -1;
      }
     
      // Inicializamos la cadena de caracteres recibidos 
      leeCadena = "";
      contador=0;
      //apagamos el motor.
      apagado();

      //la variable grado toma el valor de mensaje.
      grado=Mensaje.toInt();
      //el servo se ejecuta con el valor de la variable grado
      myservo.write(grado);
      //se le da un tiempo de 100 milisegundos.
      delay (1000);

      //la variabe gardo2 toma el valor de mensaje2. 
      grado2=Mensaje2.toInt();
      //el servo se ejecuta con el valor de la variable grado2
      myservo2.write(grado2);
      //se le da un tiempo de 100 milisegundos.
      delay (100);
    
      //si la variable Mensaje3 es igual a 12, entonces se ejecutara el codigo detro del if.
      if(Mensaje3 == "12"){
        // se abrira la pinza del brazo.
        abrir();
        // si la variable mensaje3 es igual a 70, entonces se ejecutara el codigo detro del if.
      }else if(Mensaje3 == "70"){
        // se cerrara la pinza del brazo.
        cerrar();
      }

      inicial2();
      inicial3();
      inicial4();
   
      pulsador = digitalRead(2); //refresh value of variable
    }
  }
 
 // se vacian las variables de mensaje antes de acabar el if.
   Mensaje="";
   Mensaje2="";
   Mensaje3="";
   Mensaje4="";
   Mensaje5="";
   Mensaje6="";
   Mensaje7="";
   Mensaje8="";
   Mensaje9="";
   Mensaje10="";
   Mensaje11="";
   Mensaje12="";
    delay(2000); 
}

// metodo de abrir pinza del brazo.
void abrir(){
  myservo3.write(120);
}

//metodo de cerrar pinza del brazo.
void cerrar(){
  myservo3.write(80);
}

// Pasos a la derecha
void paso_der(){      
 digitalWrite(6, LOW); 
 digitalWrite(5, LOW);  
 digitalWrite(4, HIGH);  
 digitalWrite(3, HIGH);  
   delay(retardo); 
 digitalWrite(6, LOW); 
 digitalWrite(5, HIGH);  
 digitalWrite(4, HIGH);  
 digitalWrite(3, LOW);  
   delay(retardo); 
 digitalWrite(6, HIGH); 
 digitalWrite(5, HIGH);  
 digitalWrite(4, LOW);  
 digitalWrite(3, LOW);  
  delay(retardo); 
 digitalWrite(6, HIGH); 
 digitalWrite(5, LOW);  
 digitalWrite(4, LOW);  
 digitalWrite(3, HIGH);  
  delay(retardo);  
}

// Pasos a la izquierda
void paso_izq() {        
 digitalWrite(6, HIGH); 
 digitalWrite(5, HIGH);  
 digitalWrite(4, LOW);  
 digitalWrite(3, LOW);  
  delay(retardo); 
 digitalWrite(6, LOW); 
 digitalWrite(5, HIGH);  
 digitalWrite(4, HIGH);  
 digitalWrite(3, LOW);  
  delay(retardo); 
 digitalWrite(6, LOW); 
 digitalWrite(5, LOW);  
 digitalWrite(4, HIGH);  
 digitalWrite(3, HIGH);  
  delay(retardo); 
 digitalWrite(6, HIGH); 
 digitalWrite(5, LOW);  
 digitalWrite(4, LOW);  
 digitalWrite(3, HIGH);  
  delay(retardo); 
}

// Apagado del Motor
void apagado() {        
 digitalWrite(6, LOW); 
 digitalWrite(5, LOW);  
 digitalWrite(4, LOW);  
 digitalWrite(3, LOW);  
 }


// a continuacion el siguente metodo recibira un parametro de tipo int el cual convertira  a ASCII para si cpoder obtener el mensaje caracter por caracter.
char Decimal_to_ASCII(int entrada){
  char salida=' ';
  switch(entrada){
  case 32: 
  salida=' '; 
  break; 
  case 33: 
  salida='!'; 
  break; 
  case 34: 
  salida='"'; 
  break; 
  case 35: 
  salida='#'; 
  break; 
  case 36: 
  salida='$'; 
  break; 
  case 37: 
  salida='%'; 
  break; 
  case 38: 
  salida='&'; 
  break; 
  case 39: 
  salida=' '; 
  break; 
  case 40: 
  salida='('; 
  break; 
  case 41: 
  salida=')'; 
  break; 
  case 42: 
  salida='*'; 
  break; 
  case 43: 
  salida='+'; 
  break; 
  case 44: 
  salida=','; 
  break; 
  case 45: 
  salida='-'; 
  break; 
  case 46: 
  salida='.'; 
  break; 
  case 47: 
  salida='/'; 
  break; 
  case 48: 
  salida='0'; 
  break; 
  case 49: 
  salida='1'; 
  break; 
  case 50: 
  salida='2'; 
  break; 
  case 51: 
  salida='3'; 
  break; 
  case 52: 
  salida='4'; 
  break; 
  case 53: 
  salida='5'; 
  break; 
  case 54: 
  salida='6'; 
  break; 
  case 55: 
  salida='7'; 
  break; 
  case 56: 
  salida='8'; 
  break; 
  case 57: 
  salida='9'; 
  break; 
  case 58: 
  salida=':'; 
  break; 
  case 59: 
  salida=';'; 
  break; 
  case 60: 
  salida='<'; 
  break; 
  case 61: 
  salida='='; 
  break; 
  case 62: 
  salida='>'; 
  break; 
  case 63: 
  salida='?'; 
  break; 
  case 64: 
  salida='@'; 
  break; 
  case 65: 
  salida='A'; 
  break; 
  case 66: 
  salida='B'; 
  break; 
  case 67: 
  salida='C'; 
  break; 
  case 68: 
  salida='D'; 
  break; 
  case 69: 
  salida='E'; 
  break; 
  case 70: 
  salida='F'; 
  break; 
  case 71: 
  salida='G'; 
  break; 
  case 72: 
  salida='H'; 
  break; 
  case 73: 
  salida='I'; 
  break; 
  case 74: 
  salida='J'; 
  break; 
  case 75: 
  salida='K'; 
  break; 
  case 76: 
  salida='L'; 
  break; 
  case 77: 
  salida='M'; 
  break; 
  case 78: 
  salida='N'; 
  break; 
  case 79: 
  salida='O'; 
  break; 
  case 80: 
  salida='P'; 
  break; 
  case 81: 
  salida='Q'; 
  break; 
  case 82: 
  salida='R'; 
  break; 
  case 83: 
  salida='S'; 
  break; 
  case 84: 
  salida='T'; 
  break; 
  case 85: 
  salida='U'; 
  break; 
  case 86: 
  salida='V'; 
  break; 
  case 87: 
  salida='W'; 
  break; 
  case 88: 
  salida='X'; 
  break; 
  case 89: 
  salida='Y'; 
  break; 
  case 90: 
  salida='Z'; 
  break; 
  case 91: 
  salida='['; 
  break; 
  case 92: 
  salida=' '; 
  break; 
  case 93: 
  salida=']'; 
  break; 
  case 94: 
  salida='^'; 
  break; 
  case 95: 
  salida='_'; 
  break; 
  case 96: 
  salida='`'; 
  break; 
  case 97: 
  salida='a'; 
  break; 
  case 98: 
  salida='b'; 
  break; 
  case 99: 
  salida='c'; 
  break; 
  case 100: 
  salida='d'; 
  break; 
  case 101: 
  salida='e'; 
  break; 
  case 102: 
  salida='f'; 
  break; 
  case 103: 
  salida='g'; 
  break; 
  case 104: 
  salida='h'; 
  break; 
  case 105: 
  salida='i'; 
  break; 
  case 106: 
  salida='j'; 
  break; 
  case 107: 
  salida='k'; 
  break; 
  case 108: 
  salida='l'; 
  break; 
  case 109: 
  salida='m'; 
  break; 
  case 110: 
  salida='n'; 
  break; 
  case 111: 
  salida='o'; 
  break; 
  case 112: 
  salida='p'; 
  break; 
  case 113: 
  salida='q'; 
  break; 
  case 114: 
  salida='r'; 
  break; 
  case 115: 
  salida='s'; 
  break; 
  case 116: 
  salida='t'; 
  break; 
  case 117: 
  salida='u'; 
  break; 
  case 118: 
  salida='v'; 
  break; 
  case 119: 
  salida='w'; 
  break; 
  case 120: 
  salida='x'; 
  break; 
  case 121: 
  salida='y'; 
  break; 
  case 122: 
  salida='z'; 
  break; 
  case 123: 
  salida='{'; 
  break; 
  case 124: 
  salida='|'; 
  break; 
  case 125: 
  salida='}'; 
  break; 
  case 126: 
  salida='~'; 
  break; 
  }
  return salida;
}
