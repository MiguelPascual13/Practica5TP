package es.ucm.fdi.tp.practica5.moveControllers;

import java.awt.event.MouseEvent;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxMove;
import es.ucm.fdi.tp.practica5.utils.Utils;

@SuppressWarnings("serial")
public class AtaxxMoveController extends MoveController {

	/*
	 * Seguramente tengamos que llevar todas estas interfaces a la clase
	 * abstracta común a todas...
	 */

	/*
	 * Igual deberíamos tener aquí una interfacita para mandar los mensajes al
	 * status...
	 */
	private boolean somethingSelected = false;

	private int oldRow;
	private int oldColumn;
	private int row;
	private int column;

	private static final String moveMessage = "left-click origin piece...\nrigh-click anywhere to deselect origin...\n";
	private static final String destinationMessage = "left-click destination empty valid piece...\nright-click anywhere to deselect origin...\n";
	private static final String invalidDestination = "choose a valid destination...\n";
	private static final String invalidOrigin = "left-click a piece of yours as an origin...\n";

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return new AtaxxMove(oldRow, oldColumn, row, column, p);
	}

	public boolean manageClicks(Board board, int row, int column, Piece turn, int buttonNumber,
			MoveListener moveListener, ErrorListener errorListener) {
		if (buttonNumber == MouseEvent.BUTTON1) {
			if (somethingSelected) {
				moveListener.notifyMove(destinationMessage);
				if (board.getPosition(row, column) == null) {
					this.row = row;
					this.column = column;
					if (Utils.InfiniteDistanceExceeded(oldRow, oldColumn, row, column)) {
						errorListener.notifyError(invalidDestination);
						return false;
					}
					somethingSelected = false;
				}
				errorListener.notifyError(invalidDestination);
			} else {
				if (board.getPosition(row, column) == turn) {
					this.oldRow = row;
					this.oldColumn = column;
					somethingSelected = true;
				} else{
					moveListener.notifyMove(invalidOrigin);
					return false;
				}
			}
			return !somethingSelected;
		} else {
			somethingSelected = false;
			moveListener.notifyMove(moveMessage);
			return false;
		}
	}
}
