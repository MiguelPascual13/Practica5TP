package es.ucm.fdi.tp.practica5.factories;

import java.awt.EventQueue;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.GUI;
import es.ucm.fdi.tp.practica5.GenericSwingView;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxFactory;

/**
 * This class was made following the advice of the project statement.
 */

@SuppressWarnings("serial")
public class AtaxxFactoryExt extends AtaxxFactory {

	private Integer dimRows;
	private Integer obstacles;

	public AtaxxFactoryExt(Integer dimRows, Integer obstacles) {
		super(dimRows, obstacles);
		this.dimRows = dimRows;
		this.obstacles = obstacles;
	}

	public AtaxxFactoryExt() {
		super();
		this.dimRows = 5;
		this.obstacles = 4;
	}
	
	/**
	 * Tenemos un game observer lo cual siempre es bueno.
	 * 
	 * Dónde cojones se meten las piezas en el array de los cojones?
	 */
	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		new GenericSwingView(g, c);
	}

}
