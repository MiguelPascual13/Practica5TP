package es.ucm.fdi.tp.practica5;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		
		GUI ventana = new GUI();
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				ventana.setVisible(true);
			}
		});
	}
}