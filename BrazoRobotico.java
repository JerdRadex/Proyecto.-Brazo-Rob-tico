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
    JLabel titulo;
    private JLabel texto1,texto2,texto3,texto4/*,texto5*/;
    private JComboBox txtf1,txtf2,txtf3,txtf4/*,txtf5*/;
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
        titulo = new JLabel("Programación del Brazo");
        
        texto1 = new JLabel("Grados de giro de la Base ");
        txtf1 = new JComboBox ();
        txtf1.addItem("0");
        txtf1.addItem("50");
        txtf1.addItem("100");
        txtf1.addItem("200");
        txtf1.addItem("360");
        txtf1.addItem("");
        
        texto2 = new JLabel("Grados de movimiento del Hombro ");        
        txtf2 = new JComboBox ();
        txtf2.addItem("60");
        txtf2.addItem("40");
        txtf2.addItem("20");
        txtf2.addItem("10");
        
        texto3 = new JLabel("Grados de movimiento del Codo ");
        txtf3 = new JComboBox ();
        txtf3.addItem("50");
        txtf3.addItem("30");
        txtf3.addItem("10");
        txtf3.addItem("0");
        
        texto4 = new JLabel("Estado Pinza ");
        txtf4=new JComboBox();
        txtf4.addItem("Abrir");
        txtf4.addItem("cerrar");
        
        ejecutar = new JButton("Ejecutar");
        
        textoF = new JTextArea();
        textoF.setBounds(40,420,400,200);
        textoF.setEditable(false);
        add(textoF);
        
        // donde se inicializa los valores y lo que contendra cada componente.
        prepararGUI();
    }
    
    // Método para preparar la ventana
    public void prepararGUI(){
        setSize(500,700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        // Se añaden los omponentes a la ventana
        add(fondo);
        fondo.add(titulo);
        fondo.add(texto1);
        fondo.add(txtf1);
        fondo.add(texto2); 
        fondo.add(txtf2); 
        fondo.add(texto3);
        fondo.add(txtf3);
        fondo.add(texto4); 
        fondo.add(txtf4); 
        fondo.add(ejecutar);
         
        // Se establecen las características de cada componente
        fondo.setBounds(0,0,500,400);
        titulo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setBounds(100,20,300,50);
        
        texto1.setBounds(70,80,220,30);
        texto1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto1.setForeground(new java.awt.Color(255, 255, 255));
        txtf1.setBounds(290,80,100,30);
        texto2.setBounds(70,130,220,30);
        texto2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto2.setForeground(new java.awt.Color(255, 255, 255));
        txtf2.setBounds(290,130,100,30);
        texto3.setBounds(70,180,220,30);
        texto3.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto3.setForeground(new java.awt.Color(255, 255, 255));
        txtf3.setBounds(290,180,100,30);
        texto4.setBounds(70,230,220,30);
        texto4.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        texto4.setForeground(new java.awt.Color(255, 255, 255));
        txtf4.setBounds(290,230,100,30);
        ejecutar.setBounds(290,280,100,25);
        
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
            String var1 = txtf1.getSelectedItem().toString()+"\n";
            String var2 = txtf2.getSelectedItem().toString()+"\n";
            String var3 = txtf3.getSelectedItem().toString()+"\n";
            String var4 = txtf4.getSelectedItem().toString()+"\n";
            
            // se muestra en el TextArea los valores de las variables.
            textoF.setText("");
                textoF.setText("MODO EJECUCION......"+"\n"+"\n"+
                               "Base: "+var1+"\n"+
                               "Brazo: "+var2+"\n"+
                               "Codo: "+var3+"\n"+
                               "pinza estado: "+var4+"\n");
                
                
            // se envian los datos a el arduiono
            EnviarDatos("2");
                    EnviarDatos(txtf2.getSelectedItem().toString());
                    EnviarDatos(txtf3.getSelectedItem().toString());
                    
                    if("Abrir".equals(txtf4.getSelectedItem().toString())){
                        EnviarDatos("12");
                    }else{
                         EnviarDatos("70");
                    }
                    EnviarDatos(txtf1.getSelectedItem().toString());  
        }
    }
    
}
