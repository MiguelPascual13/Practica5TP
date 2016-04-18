package es.ucm.fdi.tp.practica5.moveControllers;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public interface MoveController {
	public boolean generateMove(int row, int column, Board board, Piece turn, List<Piece> pieces, Observable<GameObserver> g);
}
