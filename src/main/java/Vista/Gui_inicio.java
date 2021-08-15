package Vista;


import java.awt.*;
import javax.swing.*;

import java.awt.event.*;



public class Gui_inicio extends JFrame {


    // ATRIBUTOS TYPE JBUTTON
    public JButton req_1_boton;
    public JButton req_2_boton;
    public JButton req_3_boton;

    //CONSTRUCTOR
    public Gui_inicio() {

        setLayout(new BorderLayout());
        
        setTitle("Reto 5");

        setSize(600, 300);

        setLocationRelativeTo(null);

        
        inicio();
        eventos();

        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        


    }


    //METODO PARA ORGANIZAR PANEL Y ELEMENTOS DE ESTE
    public void inicio() {

        //Panel_norte
        JPanel inicio = new JPanel();
        inicio.setLayout(new BorderLayout());
        getContentPane().add(inicio, BorderLayout.NORTH);

        //titulo
        JLabel titulo = new JLabel("Proyectos Construccion");
        
        titulo.setForeground(Color.RED);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("default", Font.BOLD, 40));
        inicio.add(titulo);

        //panel centro
        JPanel botones = new JPanel();
        getContentPane().add(botones, BorderLayout.CENTER);
       
        //interfaz_centro
        //PREgunta
        JLabel opciones = new JLabel("Elige un requerimiento especial");
        
        opciones.setForeground(Color.DARK_GRAY);
        opciones.setHorizontalAlignment(JLabel.CENTER);
        opciones.setFont(new Font("default", Font.ITALIC, 25));
        botones.add(opciones);

        //requierimiento1_boton
        req_1_boton = new JButton("Condominios del 2021");
        req_1_boton.setHorizontalAlignment(JButton.CENTER);
        req_1_boton.setBackground(Color.MAGENTA);
        req_1_boton.setFont(new Font("default", Font.ITALIC, 15));
        botones.add(req_1_boton);

        //requerimiento2_boton
        req_2_boton = new JButton("Asesor/Coordinador con salario >510.000");
        req_2_boton.setHorizontalAlignment(JButton.CENTER);
        req_2_boton.setBackground(Color.MAGENTA);
        req_2_boton.setFont(new Font("default", Font.ITALIC, 15));
        botones.add(req_2_boton);

        //requerimiento3_boton
        req_3_boton = new JButton("No pagos de JUMBO");
        req_3_boton.setHorizontalAlignment(JButton.CENTER);
        req_3_boton.setBackground(Color.MAGENTA);
        req_3_boton.setFont(new Font("default", Font.ITALIC, 15));
        botones.add(req_3_boton);

        //PANEL SOUTH
        JPanel minombre = new JPanel();
        getContentPane().add(minombre, BorderLayout.SOUTH);

        
        
        //MINOMBRE
        JTextField nombre = new JTextField("Juan Pablo Suarez Ramirez");
        nombre.setHorizontalAlignment(JTextField.CENTER);
        nombre.setBackground(Color.CYAN);
        nombre.setForeground(Color.DARK_GRAY);
        nombre.setFont(new Font("default", Font.BOLD, 20));
        nombre.setEditable(false);
        minombre.add(nombre);

        //logo mintic
        JLabel img_TIC = new JLabel();
        Image imagenTIC = new ImageIcon("mision_tic.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        img_TIC.setIcon(new ImageIcon(imagenTIC));
        minombre.add(img_TIC);

        //Panel West
        JPanel logo = new JPanel();
        logo.setBackground(Color.PINK);
        getContentPane().add(logo, BorderLayout.WEST);

        // //Logo
        JLabel img = new JLabel();
        Image imagenInterna = new ImageIcon("logo.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        img.setIcon(new ImageIcon(imagenInterna));
        logo.add(img);

        //Panel East
        JPanel utp_logo = new JPanel();
        utp_logo.setBackground(Color.PINK);
        getContentPane().add(utp_logo, BorderLayout.EAST);

        //Logo utp_logo
        JLabel img_utp = new JLabel();
        Image imagenutp = new ImageIcon("Logo_U.T.P.png").getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT);
        img_utp.setIcon(new ImageIcon(imagenutp));
        utp_logo.add(img_utp);    
    
    }      

    //METODO PARA LLAMAR EVENTOS
    public void eventos() {

        //EVENTOS DEL 1 BOTON 
        ActionListener boton_1_req = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Requerimiento1 r1 = new Requerimiento1();

            }
        }; req_1_boton.addActionListener(boton_1_req);
    
    
        //EVENTOS DEL 2 BOTON
        ActionListener boton_2_req = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Requerimiento2 r2 = new Requerimiento2();
            }
        }; req_2_boton.addActionListener(boton_2_req);

        //EVENTOS DEL 3 BOTON
        ActionListener boton_3_req = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Requerimiento3 r3 = new Requerimiento3();
                 
            }
        }; req_3_boton.addActionListener(boton_3_req);    

    }
    
}
