package es.ucm.fdi.tp.practica5.moveControllers;

import java.awt.event.MouseEvent;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")

public abstract class MoveController extends Player {

	public static final Integer NOTHING_TO_REPAINT = null;
	public static final Integer SOMETHING_TO_REPAINT = -1;
	public static final Integer REPAINT_AND_MOVE = 1;

	public abstract Integer manageClicks(Board board, int row, int column, Piece turn, Piece viewPiece,
			MouseEvent mouseEvent);

	public boolean checkMultiViewCase(Piece turn, Piece viewPiece) {
		if (viewPiece != null) {
			if (turn != viewPiece)
				return false;
			else
				return true;
		} else
			return true;
	}

	public abstract Integer getSelectedRow();

	public abstract Integer getSelectedColumn();
}
