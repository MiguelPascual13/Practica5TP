package es.ucm.fdi.tp.practica5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	/*
	private JPanel botonesSuperiores;
	private JButton boton1;
	private JButton boton2;
	
	public GUI(){
		super("Ventana Grafica");
		this.setSize(1000,  800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		botonesSuperiores = new JPanel(new BorderLayout(30, 40));
		this.add(botonesSuperiores);
		boton1 = new JButton("Jugadores");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
		botonesSuperiores.add(boton1, BorderLayout.NORTH);
		boton2 = new JButton("Colores");
		botonesSuperiores.add(boton2, BorderLayout.EAST);
		botonesSuperiores.setVisible(true);
		
	}*/
	JPanel panelgrid, panelborder, panelflow;
    JButton boton1, boton2, boton3, boton4, boton5, boton6;   
 
    public GUI(){
 
        super(); 
        this.setLayout(new FlowLayout());
 
 
        gridJP(); bordJP(); flowJP(); //invocamos los metodos que contienen los paneles 
 
        boton4 = new JButton("Boton1JFrame1"); boton5 = new JButton("Boton2JFrame1"); boton6 = new JButton("Boton3JFrame1");
 
        panelgrid.setBounds(10, 10, 200, 200); 
        panelborder.setBounds(240, 10, 400, 250); 
        panelflow.setBounds(10, 270, 400, 150);
        boton4.setBounds(10, 430, 90, 20); 
        boton5.setBounds(110, 430, 90, 20); 
        boton6.setBounds(210, 430, 90, 20);
 
        this.add(panelgrid); this.add(panelborder); this.add(panelflow); 
        this.add(boton4); this.add(boton5); this.add(boton6);
 
        
 
        this.setLocation(100, 50);
        this.setResizable(true);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public void gridJP(){
 
        panelgrid = new JPanel(new GridLayout(3, 1, 5, 7));//filas, columnas, espacio entre filas, espacio entre columnas
 
        boton1= new JButton("B1panel1"); boton2= new JButton("B2panel1"); boton3= new JButton("B3panel1");//creamos los objetos para el panel
 
        panelgrid.add(boton1); panelgrid.add(boton2); panelgrid.add(boton3);//añadimos los objetos al jpanel
 

 
        panelgrid.setVisible(true);
    }
 
    public void bordJP(){
        panelborder = new JPanel(new BorderLayout(2, 3));//espacio entre las regiones, horizontal y vertical
 
        boton1= new JButton("B1panel2"); boton2= new JButton("B2panel2"); boton3= new JButton("B3panel2");//añadiendo objetos al jpanel
 
        panelborder.add(boton1, BorderLayout.NORTH);//boton al panel norte
        panelborder.add(boton2, BorderLayout.WEST); //boton a la region oeste
        panelborder.add(boton3, BorderLayout.CENTER); //boton a la region centro    
 
      
        panelborder.setVisible(true);
    }
 
    public void flowJP(){
        panelflow = new JPanel(new FlowLayout());
 
        boton1= new JButton("B1panel3"); boton2= new JButton("B2panel3"); boton3= new JButton("B3panel3");//añadiendo objetos al jpanel
 
        panelflow.add(boton1); panelflow.add(boton2); panelflow.add(boton3);//añadimos los objetos al jpanel
 
        
 
        panelflow.setVisible(true);
    }
 
 
   /* public void actionPerformed(ActionEvent e) {//sobreescribimos el metodo del listener
 
        if(e.getSource() == jbP1){
            if(jp1.isVisible()){
                jp1.setVisible(false);
            }else
                jp1.setVisible(true);
        }else if(e.getSource() == jbP2){
            if(jp2.isVisible()){
                jp2.setVisible(false);
            }else
                jp2.setVisible(true);
 
        }else if(e.getSource() == jbP3){
            if(jp3.isVisible()){
                jp3.setVisible(false);
            }else
                jp3.setVisible(true);
 
        }else{
            JOptionPane.showMessageDialog(null, e.getActionCommand());
        }
    }
}*/
}
