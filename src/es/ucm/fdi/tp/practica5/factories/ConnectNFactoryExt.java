package es.ucm.fdi.tp.practica5.factories;

import java.awt.EventQueue;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;
import es.ucm.fdi.tp.practica5.GUI;
import es.ucm.fdi.tp.practica5.GenericSwingView;

/**
 * Class made for making possible to the connectN factory creating a swing view
 * of the game.
 */
@SuppressWarnings("serial")
public class ConnectNFactoryExt extends ConnectNFactory implements SwingPlayable{
	
	private Integer dimRows;
	private static final Integer obstacles = 0;
	
	public ConnectNFactoryExt() {
		super();
		this.dimRows = 5;
	}

	public ConnectNFactoryExt(Integer dimRows) {
		super(dimRows);
		this.dimRows = dimRows;
	}

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		new GenericSwingView(g, c);
	}

	@Override
	public Player createSwingManualPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
