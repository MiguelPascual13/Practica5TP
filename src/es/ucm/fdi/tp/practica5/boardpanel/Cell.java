package es.ucm.fdi.tp.practica5.boardpanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")

public class Cell extends JLabel {
	
	/**
	 * PONER EN UNA SOLA INTERFAZ!
	 */
	public interface CellLeftClickedListener {
		public void cellWasLeftClicked(int row, int column);
	}

	public interface CellRightClickedListener {
		public void cellWasRightClicked(int row, int column);
	}

	public Cell(int row, int column, CellLeftClickedListener leftListener, CellRightClickedListener rightListener) {
		this.addMouseListener(new MouseAdapter() {

			/* Someone has to know what is happening here... */
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					leftListener.cellWasLeftClicked(row, column);

					/* Se que debería usar logging pero la vida es dura */
					System.out.println("lc" + row);
					System.out.println("lc" + column);
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			}

		});

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					rightListener.cellWasRightClicked(row, column);

					System.out.println("rc" + row);
					System.out.println("rc" + column);
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			}
		});

	}
}
