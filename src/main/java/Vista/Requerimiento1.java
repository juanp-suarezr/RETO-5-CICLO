package Vista;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Modelo.vo.*;
import Controlador.*;

public class Requerimiento1 extends JFrame {
 
    //ATRIBUTOS DE LA CLASE
    JTable tabla;
    public JButton regresar1;
    private Boolean bool = true;

    //CONSTRUCTOR
    public Requerimiento1() {
        initUI();
        evento_regreso1();

    }

    //METODO Q MUESTRA EL REQUERIMIENTO EN UNA TABLA
    public void initUI() {

        setLayout(new BorderLayout());

        //TITULOS DE LA TABLA
        String[] nombres = {"ID_proyecto", "Ciudad", "Banco_Vinculado", "Constructora", "Clasificacion" };

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
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        getContentPane().add(panel1, BorderLayout.NORTH);
        regresar1 = new JButton("Regresar");
        regresar1.setHorizontalAlignment(JButton.CENTER);
        regresar1.setBackground(Color.green);
        regresar1.setFont(new Font("default", Font.ITALIC, 15));
        panel1.add(regresar1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLocationRelativeTo(null);
        
        
        setVisible(bool);
        
        
    
        

    }
    

    /*METODO QUE UNE LOS DATOS DEL REQUERIMIENTO CONECTADOS EN EL CNTROLADOR
    Y LOS MUESTRA, PARA LUEGO UNIRSE CON LA TABLA GENERADA.*/
    
    public String[][] mostrar() throws SQLException {
 
        //LISTA DE TIPO OBJ Requerimiento1
        ArrayList<Requerimiento_1> lista = new ArrayList<Requerimiento_1>();

        //Instancia Y Llama el constructor de Elcontroladorderequerimientos
        ElControladorDeRequerimientos controlador = new ElControladorDeRequerimientos();

        //Llama metodo de la clase ElcontroladordeRequerimeientos
        lista = controlador.consultarRequerimiento1();

        //ARREGLO QUE CONTIENE FILAS DEL TAMAÑO DEL OBJETO LISTA Y COLUMNAS SEGUN LAS REQUERIDAS O EL TITULO
        String matris[][] = new String [lista.size()][5];
        for (int i = 0; i< lista.size(); i++) {
            matris[i][0] = String.valueOf(lista.get(i).getID_Proyecto());
            matris[i][1] = lista.get(i).getCiudad();
            matris[i][2] = lista.get(i).getBanco_vinculado();
            matris[i][3] = lista.get(i).getConstructora();
            matris[i][4] = lista.get(i).getClasificacion();
        }
        return matris;
    }

    //METODO QUE CONTIENE EVENTO DEL BOTON REGRESAR
    public void evento_regreso1() {
        ActionListener boton_regresar1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gui_inicio gui = new Gui_inicio();
                gui.setVisible(true);
                dispose();
                

                
            }
        };    
        regresar1.addActionListener(boton_regresar1);
    
    }
}

