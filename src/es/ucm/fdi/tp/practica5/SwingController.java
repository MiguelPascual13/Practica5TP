package es.ucm.fdi.tp.practica5;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class SwingController extends Controller {

	/**
	 * Nos proporciona el juego al que queremos jugar y la lista de piezas.
	 */
	public SwingController(Game game, List<Piece> pieces) {
		super(game, pieces);
	}

}
