package es.ucm.fdi.tp.practica5.factories;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;

/**
 * Class made for making possible to the connectN factory creating a swing view
 * of the game.
 */
@SuppressWarnings("serial")
public class ConnectNFactoryExt extends ConnectNFactory {

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
	}
	
}
