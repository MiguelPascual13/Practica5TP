
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
	private boolean somethingSelected = false;
	
	//Departure piece.
	private Integer oldRow = null;
	private Integer oldColumn = null;
	
	/*Piece*/
	private Integer selectedRow = null;
	private Integer selectedColumn = null;
	
	private Integer newRow = null;
	private Integer newColumn = null;

	private static final String moveMessage = "left-click origin piece...\nrigh-click anywhere to deselect origin...\n";
	private static final String destinationMessage = "left-click destination empty valid piece...\nright-click anywhere to deselect origin...\n";
	private static final String invalidDestination = "choose a valid destination...\n";
	private static final String invalidOrigin = "left-click a piece of yours as an origin...\n";

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return new AtaxxMove(oldRow, oldColumn, newRow, newColumn, p);
	}

	public Integer manageClicks(Board board, int row, int column, Piece turn, Piece viewPiece, MouseEvent mouseEvent) {

		if (!checkMultiViewCase(turn, viewPiece))
			return NOTHING_TO_REPAINT;

		if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
			if (somethingSelected) {
				if (board.getPosition(row, column) == null) {
					setDestinationCell(row, column);
					if (Utils.InfiniteDistanceExceeded(oldRow, oldColumn, row, column)) {
						return NOTHING_TO_REPAINT;
					}
					somethingSelected = false;
					resetSelectedCell();
					return REPAINT_AND_MOVE;
				} else
					return NOTHING_TO_REPAINT;
			} else {
				if (board.getPosition(row, column) == turn) {
					setSelectedCell(row, column);
					somethingSelected = true;
					return SOMETHING_TO_REPAINT;
				} else {
					return NOTHING_TO_REPAINT;
				}
			}
		} else {
			somethingSelected = false;
			resetSelectedCell();
			return SOMETHING_TO_REPAINT;
		}
	}
	
	/*La vista trabaja con selected, el movimiento con old*/
	//GET
	public Integer getSelectedRow() {
		return this.selectedRow;
	}

	public Integer getSelectedColumn() {
		return this.selectedColumn;
	}
	
	//RESET
	private void resetSelectedCell() {
		this.selectedRow = null;
		this.selectedColumn = null;
	}
	
	//SET
	private void setSelectedCell(Integer row, Integer column) {
		this.oldRow = row;
		this.oldColumn = column;
		this.selectedRow = row;
		this.selectedColumn = column;
	}
	
	private void setDestinationCell(Integer row, Integer column) {
		this.newRow = row;
		this.newColumn = column;
	}
}
