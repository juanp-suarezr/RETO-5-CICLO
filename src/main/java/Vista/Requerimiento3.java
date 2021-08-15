package Vista;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Modelo.vo.*;
import Controlador.*;

public class Requerimiento3 extends JFrame {

     //ATRIBUTOS DE LA CLASE
    JTable tabla;
    public JButton regresar3;
    
    //CONSTRUCTOR
    public Requerimiento3()  {
        initUI();
        evento_regreso3();
    }

    //METODO Q MUESTRA EL REQUERIMIENTO EN UNA TABLA
    public void initUI()  {

        setLayout(new BorderLayout());

         //TITULOS DE LA TABLA
        String[] nombres = {"Proveedor", "Pagado", "Constructora"};

        //CREACION DE LA TABLA
        try {
            tabla = new JTable (mostrar(), nombres);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //VENTANA DE NAVEGACION
        JScrollPane panel = new JScrollPane(tabla);
        add(panel, BorderLayout.CENTER);

        //CREACION DE BOTON REGRESAR
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        getContentPane().add(panel3, BorderLayout.NORTH);
        regresar3 = new JButton("Regresar");
        regresar3.setHorizontalAlignment(JButton.CENTER);
        regresar3.setBackground(Color.green);
        regresar3.setFont(new Font("default", Font.ITALIC, 15));
        panel3.add(regresar3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLocationRelativeTo(null);

        setVisible(true);

        
    }

    /*METODO QUE UNE LOS DATOS DEL REQUERIMIENTO CONECTADOS EN EL CNTROLADOR
    Y LOS MUESTRA, PARA LUEGO UNIRSE CON LA TABLA GENERADA.*/
    public String[][] mostrar() throws SQLException {

        //LISTA DE TIPO OBJ Requerimiento1
        ArrayList<Requerimiento_3> lista = new ArrayList<Requerimiento_3>();

        //Instancia Y Llama el constructor de Elcontroladorderequerimientos
        ElControladorDeRequerimientos controlador = new ElControladorDeRequerimientos();

        //Llama metodo de la clase ElcontroladordeRequerimeientos
        lista = controlador.consultarRequerimiento3();

         //ARREGLO QUE CONTIENE FILAS DEL TAMAÑO DEL OBJETO LISTA Y COLUMNAS SEGUN LAS REQUERIDAS O EL TITULO
        String matris[][] = new String [lista.size()][3];
        for (int i = 0; i< lista.size(); i++) {
            matris[i][0] = lista.get(i).getProveedor();
            matris[i][1] = lista.get(i).getPagado();
            matris[i][2] = lista.get(i).getConstructora();
            
        }
        return matris;
    }
    
    //METODO QUE CONTIENE EVENTO DEL BOTON REGRESAR
    public void evento_regreso3() {
        ActionListener boton_regresar3 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gui_inicio gui = new Gui_inicio();
                gui.setVisible(true);
                dispose();
                

                
            }
        };    
        regresar3.addActionListener(boton_regresar3);
    }
}


