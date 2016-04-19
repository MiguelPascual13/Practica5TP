package es.ucm.fdi.tp.practica5.moveControllers;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")

public abstract class MoveController extends Player {
	
	/*PONER EN UNA SOLA INTERFAZ*/
	public interface MoveListener {
		public void notifyMove(String message);
	}

	public interface ErrorListener {
		public void notifyError(String message);
	}

	public abstract boolean manageClicks(Board board, int row, int column, Piece actualTurn, int buttonNumber,
			MoveListener moveListener, ErrorListener errorListener);
}
