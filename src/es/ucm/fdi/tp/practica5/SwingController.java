package es.ucm.fdi.tp.practica5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class SwingController extends Controller {

	protected Map<Piece, Player> players;

	/**
	 * Nos proporciona el juego al que queremos jugar y la lista de piezas.
	 */
	public SwingController(Game game, List<Piece> pieces) {
		super(game, pieces);
				
		this.players = new HashMap<Piece, Player>();
		for (int i = 0; i < pieces.size(); i++) {
			this.players.put(pieces.get(i), players.get(i));
		}
	}
}
