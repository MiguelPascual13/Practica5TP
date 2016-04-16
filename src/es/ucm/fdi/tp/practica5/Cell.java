package es.ucm.fdi.tp.practica5;

import java.awt.event.MouseAdapter;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Cell extends JLabel {
	private int row;
	private int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(){
				
			}
		});
	}
}
