package es.ucm.fdi.tp.practica5;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.moveControllers.AtaxxMoveController;

public class AtaxxSwingView extends GenericSwingView {

	public AtaxxSwingView(Observable<GameObserver> g, Controller c, final Piece viewPiece) {
		super(g, c, viewPiece);
		this.moveController = new AtaxxMoveController();
	}

}
