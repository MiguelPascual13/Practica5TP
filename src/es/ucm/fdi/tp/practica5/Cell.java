package es.ucm.fdi.tp.practica5;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;

@SuppressWarnings("serial")

public class Cell extends JLabel {

	public interface CellClickedListener {
		public void cellWasClicked(int row, int column);
	}


	public Cell(int row, int column, CellClickedListener listener) {
		this.addMouseListener(new MouseAdapter() {

			/* Someone has to know what is happening here... */
			public void mouseClicked(MouseEvent e) {

				listener.cellWasClicked(row, column);

				/* Se que debería usar logging pero la vida es dura */
				System.out.println(row);
				System.out.println(column);
				java.awt.Toolkit.getDefaultToolkit().beep();
			}

		});
	}
}
