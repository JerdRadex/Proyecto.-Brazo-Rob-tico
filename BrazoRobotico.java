package interfazbrazo;

// librerias que se ocuparan para la creacion y funcionalidad de la interfaz.
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import jssc.SerialPortException;

public class BrazoRobotico extends JFrame implements ActionListener {

    //a continuacion se crea la instancia ino para la utilizacion de la libreria panamaHitek_Arduino.
    PanamaHitek_Arduino ino =new PanamaHitek_Arduino();
    
    /*a continuacion se crean los JLabes, JTextField y JPanel que se utilizaran en la interfaz para que el usuario
    interactue con ellos. Asi como tambien el Container para colocar cada componente*/
    JLabel fondo;
    JLabel titulo,titulo2,titulo3,titulo4;
    private JLabel texto1,texto2,texto3,texto4;
    private JComboBox txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7,txtf8,txtf9,txtf10,txtf11,txtf12;
    JButton ejecutar;
     JTextArea textoF;
            
    // Inicializamos los componentes de la interfaz
    BrazoRobotico(){
         /*a continuacion se muestra la linea de codigo en la cual se envia un mensaje que se mostrara en la parte
         superior de la interfaz.*/
        super("Modo Programación");
        
        /* a continuacion se inicializan los componentes JPanel, JLabel, JtextFiel, JComboBox, 
        con sus respectivos nombres ya colocados anteriormente y su funcionalidad.
         con el ".add" se agregan los componentes */
        ImageIcon im = new ImageIcon("fondo2.jpg");
        fondo= new JLabel(im);  
        titulo = new JLabel("Programación 1 del brazo");
        titulo2 = new JLabel("Pr 1.1 servos");
        titulo3 = new JLabel("Pr 2 brazo");
        titulo4 = new JLabel("Pr 2.1 servos");
        
        texto1 = new JLabel("Grados de giro de la Base ");
        txtf1 = new JComboBox ();
        txtf1.addItem("0");
        txtf1.addItem("50");
        txtf1.addItem("100");
        txtf1.addItem("200");
        txtf1.addItem("360");
        txtf1.addItem("");
        
        txtf5 = new JComboBox ();
        txtf5.addItem("0");
        txtf5.addItem("50");
        txtf5.addItem("100");
        txtf5.addItem("200");
        txtf5.addItem("360");
        txtf5.addItem("");
        
        texto2 = new JLabel("Grados de movimiento del Hombro ");        
        txtf2 = new JComboBox ();
        txtf2.addItem("60");
        txtf2.addItem("40");
        txtf2.addItem("20");
        txtf2.addItem("10");
        
        txtf9 = new JComboBox ();
        txtf9.addItem("60");
        txtf9.addItem("40");
        txtf9.addItem("20");
        txtf9.addItem("10");
        
        txtf6 = new JComboBox ();
        txtf6.addItem("60");
        txtf6.addItem("40");
        txtf6.addItem("20");
        txtf6.addItem("10");
        
        txtf11 = new JComboBox ();
        txtf11.addItem("60");
        txtf11.addItem("40");
        txtf11.addItem("20");
        txtf11.addItem("10");
        
        texto3 = new JLabel("Grados de movimiento del Codo ");
        txtf3 = new JComboBox ();
        txtf3.addItem("50");
        txtf3.addItem("30");
        txtf3.addItem("10");
        txtf3.addItem("0");
        
        txtf10 = new JComboBox ();
        txtf10.addItem("50");
        txtf10.addItem("30");
        txtf10.addItem("10");
        txtf10.addItem("0");
        
        txtf7 = new JComboBox ();
        txtf7.addItem("50");
        txtf7.addItem("30");
        txtf7.addItem("10");
        txtf7.addItem("0");
        
        txtf12 = new JComboBox ();
        txtf12.addItem("50");
        txtf12.addItem("30");
        txtf12.addItem("10");
        txtf12.addItem("0");
        
        texto4 = new JLabel("Estado Pinza ");
        txtf4=new JComboBox();
        txtf4.addItem("Abrir");
        txtf4.addItem("cerrar");
        
        txtf8=new JComboBox();
        txtf8.addItem("Abrir");
        txtf8.addItem("cerrar");
        
        ejecutar = new JButton("Ejecutar");
        
        textoF = new JTextArea();
        textoF.setBounds(10,420,700,200);
        textoF.setEditable(false);
        add(textoF);
        
        // donde se inicializa los valores y lo que contendra cada componente.
        prepararGUI();
    }
    
    // Método para preparar la ventana
    public void prepararGUI(){
        setSize(760,700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        // Se añaden los omponentes a la ventana
        add(fondo);
        fondo.add(titulo);
        fondo.add(titulo2);
        fondo.add(titulo3);
        fondo.add(titulo4);
        fondo.add(texto1);
        fondo.add(txtf1);
        fondo.add(txtf5);
        fondo.add(texto2); 
        fondo.add(txtf2);
        fondo.add(txtf9);
        fondo.add(txtf6);
        fondo.add(txtf11);
        fondo.add(texto3);
        fondo.add(txtf3);
        fondo.add(txtf10);
        fondo.add(txtf7);
        fondo.add(txtf12);
        fondo.add(texto4); 
        fondo.add(txtf4);
        fondo.add(txtf8);
        fondo.add(ejecutar);
         
        // Se establecen las características de cada componente
        fondo.setBounds(0,0,500,400);
        titulo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setBounds(100,20,300,50);
        
        fondo.setBounds(0,0,750,400);
        titulo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setBounds(40,20,300,50);
        
        titulo2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setForeground(new java.awt.Color(255, 255, 255));
        titulo2.setBounds(265,20,300,50);
        
        titulo3.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        titulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo3.setForeground(new java.awt.Color(255, 255, 255));
        titulo3.setBounds(390,20,300,50);
        
        titulo4.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        titulo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo4.setForeground(new java.awt.Color(255, 255, 255));
        titulo4.setBounds(530,20,300,50);
        
        texto1.setBounds(20,80,220,30);
        texto1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto1.setForeground(new java.awt.Color(255, 255, 255));
        txtf1.setBounds(240,80,100,30);
        txtf5.setBounds(500,80,100,30);
        texto2.setBounds(20,130,220,30);
        texto2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto2.setForeground(new java.awt.Color(255, 255, 255));
        txtf2.setBounds(240,130,100,30);
        txtf9.setBounds(380,130,100,30);
        txtf6.setBounds(500,130,100,30);
        txtf10.setBounds(380,180,100,30);
        texto3.setBounds(20,180,220,30);
        texto3.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto3.setForeground(new java.awt.Color(255, 255, 255));
        txtf3.setBounds(240,180,100,30);
        txtf11.setBounds(640,130,100,30);
        txtf7.setBounds(500,180,100,30);
        txtf12.setBounds(640,180,100,30);
        texto4.setBounds(20,230,220,30);
        texto4.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto4.setForeground(new java.awt.Color(255, 255, 255));
        txtf4.setBounds(240,230,100,30);
        txtf8.setBounds(500,230,100,30);
        ejecutar.setBounds(340,320,100,25);
        
        ejecutar.addActionListener(this);
        
        /* a continuacion se muestra la conexion para la comunicacion con arduino con su respectivo puerto, utilizando la libreria
        panamaHitek_Arduino*/
        try {
            ino.arduinoTX("COM4",9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(BrazoRobotico.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"No se encontro ningun puerto de arduino conectado");
        }

    }
    
    private void EnviarDatos(String data) {
        
        // a continuacion utiliazando  la instancia de la libreria de conexion java-arduino se envian los obtenidos.
        try {
            ino.sendData(data);
        } catch (ArduinoException ex) {
            Logger.getLogger(BrazoRobotico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(BrazoRobotico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //a continuacion se muestra el metodo "CerrarPuerto" que su funcion es cerrara la conexion de arduino que se esta utilizando.
    private void CerrarPuerto(){
        /* a continuacion utiliazando  la instancia de la libreria de conexion java-arduino se cerrara la conexion de
        arduino que se esta utilizando*/
        try {
            ino.killArduinoConnection();
        } catch (ArduinoException ex) {
            Logger.getLogger(BrazoRobotico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /* 
         se hara la funcion del ActionListener para el funcionamiento de la interfaz*/
    public void actionPerformed(ActionEvent e) {
        // si se selecciona el boton se ejecutara el codigo dentro del if.
        if(e.getSource() == ejecutar){
            //las variables toman el lvalor de los JComboBox.
            String var1 = txtf1.getSelectedItem().toString();
            String var2 = txtf2.getSelectedItem().toString();
            String var3 = txtf3.getSelectedItem().toString();
            String var4 = txtf4.getSelectedItem().toString();
            String var5 = txtf5.getSelectedItem().toString();
            String var6 = txtf6.getSelectedItem().toString();
            String var7 = txtf7.getSelectedItem().toString();
            String var8 = txtf8.getSelectedItem().toString();
            String var9 = txtf9.getSelectedItem().toString();
            String var10 = txtf10.getSelectedItem().toString();
            String var11 = txtf11.getSelectedItem().toString();
            String var12 = txtf12.getSelectedItem().toString();
            
            // se muestra en el TextArea los valores de las variables.
           textoF.setText("");
                textoF.setText("MODO EJECUCION......"+"\n"+"\n"+
                               "Brazo Completo..."+"                     "
                               +"Servos..."+"                            "
                               +"Brazo Completo..."+"                         "
                               +"Servos..."+"\n"+"\n"+
                               "Base: "+var1+"                                                                                      "
                               +"Base: "+var5+"\n"+
                               "Hombro: "+var2+"                                 "
                               +"Hombro: "+var9+"                        "
                               +"Hombro: "+var6+"                                  "                  
                               +"Hombro: "+var11+"\n"+
                               "Codo: "+var3+"                                      "
                               +"Codo: "+var10+"                             "
                               +"Codo: "+var7+"                                       "
                               +"Codo: "+var12+"\n"+
                               "pinza estado: "+var4+"                                                                   "
                               +"pinza estado: "+var8+"\n");
                
                
                
            // se envian los datos a el arduiono
            EnviarDatos("2");
                    EnviarDatos(txtf2.getSelectedItem().toString());
                    EnviarDatos(txtf3.getSelectedItem().toString());
                    
                    if("Abrir".equals(txtf4.getSelectedItem().toString())){
                        EnviarDatos("12");
                    }else{
                         EnviarDatos("70");
                    }
                    
                    
                    
                    
                    EnviarDatos(txtf6.getSelectedItem().toString());
                    EnviarDatos(txtf7.getSelectedItem().toString());
                    
                    if("Abrir".equals(txtf8.getSelectedItem().toString())){
                        EnviarDatos("12");
                    }else{
                         EnviarDatos("70");
                    }
                    
                    EnviarDatos(txtf9.getSelectedItem().toString());
                    EnviarDatos(txtf10.getSelectedItem().toString());
                    EnviarDatos(txtf11.getSelectedItem().toString());
                    EnviarDatos(txtf12.getSelectedItem().toString());
                    
                    if(null != var1)switch (var1) {
                case "0":
                    EnviarDatos("000");
                    break;
                case "50":
                    EnviarDatos("050");
                    break;
                default:
                    EnviarDatos(txtf1.getSelectedItem().toString());
                    break;
            }
                  
                    EnviarDatos(txtf5.getSelectedItem().toString());  
        }
    }
    
}
