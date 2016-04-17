package es.ucm.fdi.tp.practica5;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Cell extends JLabel {
	
	private int row;
	private int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		this.addMouseListener(new MouseAdapter() {

			/* Someone has to know what is happening here... */
			public void mouseClicked(MouseEvent e) {
				System.out.println(row);
				System.out.println(column);
				java.awt.Toolkit.getDefaultToolkit().beep();
			}

		});
	}
}
