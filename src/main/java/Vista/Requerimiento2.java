package Vista;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Modelo.vo.*;
import Controlador.*;

public class Requerimiento2 extends JFrame {

    //ATRIBUTOS DE LA CLASE
    JTable tabla;
    public JButton regresar2;

    //CONSTRUCTOR
    public Requerimiento2()  {
        initUI();
        evento_regreso2();

    }
    
    //METODO Q MUESTRA EL REQUERIMIENTO EN UNA TABLA
    public void initUI()  {

        setLayout(new BorderLayout());

        //TITULOS DE LA TABLA
        String[] nombres = {"Nombre", "Primer_Apellido", "Ciudad_Residencia", "Cargo", "Salario"};

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
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        getContentPane().add(panel2, BorderLayout.NORTH);
        regresar2 = new JButton("Regresar");
        regresar2.setHorizontalAlignment(JButton.CENTER);
        regresar2.setBackground(Color.green);
        regresar2.setFont(new Font("default", Font.ITALIC, 15));
        panel2.add(regresar2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLocationRelativeTo(null);

        setVisible(true);

    }

    /*METODO QUE UNE LOS DATOS DEL REQUERIMIENTO CONECTADOS EN EL CNTROLADOR
    Y LOS MUESTRA, PARA LUEGO UNIRSE CON LA TABLA GENERADA.*/
    public String[][] mostrar() throws SQLException {

        //LISTA DE TIPO OBJ Requerimiento1
        ArrayList<Requerimiento_2> lista = new ArrayList<Requerimiento_2>();

        //Instancia Y Llama el constructor de Elcontroladorderequerimientos
        ElControladorDeRequerimientos controlador = new ElControladorDeRequerimientos();

        //Llama metodo de la clase ElcontroladordeRequerimeientos
        lista = controlador.consultarRequerimiento2();

        //ARREGLO QUE CONTIENE FILAS DEL TAMAÑO DEL OBJETO LISTA Y COLUMNAS SEGUN LAS REQUERIDAS O EL TITULO
        String matris[][] = new String [lista.size()][5];
        for (int i = 0; i< lista.size(); i++) {
            matris[i][0] = lista.get(i).getNombre();
            matris[i][1] = lista.get(i).getPrimer_Apellido();
            matris[i][2] = lista.get(i).getCiudad_Residencia();
            matris[i][3] = lista.get(i).getCargo();
            matris[i][4] = String.valueOf(lista.get(i).getSalario());
        }
        return matris;
    }
    //METODO QUE CONTIENE EVENTO DEL BOTON REGRESAR
    public void evento_regreso2() {
        ActionListener boton_regresar2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gui_inicio gui2 = new Gui_inicio();
                gui2.setVisible(true);
                dispose();
                

                
            }
        };    
        regresar2.addActionListener(boton_regresar2);
    
    }
}
