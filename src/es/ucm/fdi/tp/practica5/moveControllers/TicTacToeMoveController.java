package es.ucm.fdi.tp.practica5.moveControllers;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class TicTacToeMoveController extends MoveController {

	@Override
	public boolean manageClicks(Board board, int row, int column, Piece actualTurn, int buttonNumber, MoveListener moveListener, ErrorListener errorListener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		// TODO Auto-generated method stub
		return null;
	}

}
