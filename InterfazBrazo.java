package interfazbrazo;

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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jssc.SerialPortException;

public class InterfazBrazo extends JFrame implements ActionListener {
    // se crea la instancia para la utilizacion de la libreria para la conexion a arduino.
    PanamaHitek_Arduino ino =new PanamaHitek_Arduino();
    
    // Se declaran componentes de la ventana
    JLabel fondo;
    JButton iniciar;
    JLabel titulo, titulo2;

    // Inicializamos los componentes de la interfaz
    InterfazBrazo() {
        /*a continuacion se muestra la linea de codigo en la cual se envia un mensaje que se mostrara en la parte
         superior de la interfaz.*/
        super("Brazo Robótico de 4 Ejes");
        
        /* a continuacion se inicializan los componentes JLabel, JButton
        y la imagen, con sus respectivos nombres ya colocados anteriormente y su funcionalidad.*/
        
        ImageIcon im = new ImageIcon("fondo2.jpg");
        fondo = new JLabel(im);
        titulo = new JLabel("BIENVENIDO");
        titulo2 = new JLabel("<html><body>Brazo Róbotico de 4 Ejes </body></html>");
        iniciar = new JButton("Iniciar Programación");
        
        // donde se inicializa los valores y lo que contendra cada componente.
        prepararGUI();
    }

    // Método para preparar la ventana
    public void prepararGUI() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Se añaden los omponentes a la ventana
        add(fondo);
        fondo.add(titulo); 
        fondo.add(titulo2);
        fondo.add(iniciar);

        // Se establecen las características de cada componente
        fondo.setBounds(0, 0, 500, 400);
        titulo.setFont(new java.awt.Font("Tahoma", Font.ITALIC, 28));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setBounds(100, 50, 300, 50);
        
        titulo2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setForeground(new java.awt.Color(255, 255, 255));
        titulo2.setBounds(100, 80, 300, 200);
        iniciar.setBounds(150, 300, 200, 30);
        iniciar.addActionListener(this);
        
        /* a continuacion se muestra la conexion para la comunicacion con arduino con su respectivo puerto, utilizando la libreria
        panamaHitek_Arduino*/
        try {
            ino.arduinoTX("COM4",9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(InterfazBrazo.class.getName()).log(Level.SEVERE, null, ex);
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

     /* a continuacion se muestra la clase "Registro_m" que su funcion es verificar que boton o tecla se presiono y ejecutar
    las instrucciones necesarias para cada caso, haciendo todo esto con los metodos implementados.*/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciar) {
            CerrarPuerto();
            new BrazoRobotico();
            dispose();
        }
    }

    public static void main(String[] args) {
        // Despliega la interfaz
        new InterfazBrazo();     
    }
 
}
