package es.ucm.fdi.tp.practica5.moveControllers;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxMove;

public class AtaxxMoveController implements MoveController {

	/*Igual deberíamos tener aquí una interfacita para mandar los mensajes
	 * al status...*/
	
	
	private AtaxxMove ataxxMove;
	private boolean somethingSelected = false;

	private int oldRow;
	private int oldColumn;

	@Override
	public boolean generateMove(int row, int column, Board board, Piece turn, List<Piece> pieces, Observable<GameObserver> g) {
		boolean repaint = false;
		if (somethingSelected) {
			/* We are selecting an empty position... */
			if (board.getPosition(row, column) == null) {
				try {
					this.ataxxMove = new AtaxxMove(this.oldRow, this.oldColumn, row, column, turn);
					repaint = true;
				} catch (Exception e) {
					/* Nothing has to be done but stopping the exception... */
				}
			}
		} else {
			if(board.getPosition(row, column) == turn){
				this.oldRow = row;
				this.oldColumn = column;
				somethingSelected = true;
				//repaint keep being false...
			}
		}
		return repaint;
	}
}
