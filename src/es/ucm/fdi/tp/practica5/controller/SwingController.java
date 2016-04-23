package es.ucm.fdi.tp.practica5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.Main;

public class SwingController extends Controller {

	private int availablePlayerModes = 1;

	// cambiar por un enum.
	public static final int MANUAL = 0;
	public static final int RANDOM = 1;
	public static final int INTELLIGENT = 2;

	protected Map<Piece, String> players;
	protected String playerModesStringArray[];

	public SwingController(Game game, List<Piece> pieces, Player randomPlayer,
			Player aiPlayer) {
		super(game, pieces);
		this.initializePlayerModesStringArray(randomPlayer, aiPlayer);
		this.initializePiecePlayersMap(pieces);
	}

	private void initializePiecePlayersMap(List<Piece> pieces) {
		players = new HashMap<Piece, String>();
		for (int i = 0; i < pieces.size(); i++) {
			players.put(pieces.get(i), this.playerModesStringArray[MANUAL]);
		}
	}

	private void initializePlayerModesStringArray(Player randomPlayer,
			Player aiPlayer) {
		String[] provisionalPlayerModesStringArray = Main
				.getPlayerModesDescriptions();
		int length = provisionalPlayerModesStringArray.length;
		this.playerModesStringArray = new String[length];
		this.playerModesStringArray[MANUAL] = provisionalPlayerModesStringArray[MANUAL];
		if (randomPlayer != null) {
			this.playerModesStringArray[RANDOM] = provisionalPlayerModesStringArray[RANDOM];
			this.availablePlayerModes++;
		} else {
			this.playerModesStringArray[RANDOM] = null;
		}
		if (aiPlayer != null) {
			this.playerModesStringArray[INTELLIGENT] = provisionalPlayerModesStringArray[INTELLIGENT];
			this.availablePlayerModes++;
		} else {
			this.playerModesStringArray[INTELLIGENT] = null;
		}
	}

	public String[] getPlayerModesStringArray() {
		return this.playerModesStringArray;
	}

	public boolean isPlayerOfType(Piece piece, String type) {
		return this.players.get(piece) == type;
	}

	public String getPlayerType(Piece piece) {
		return this.players.get(piece);
	}

	public void setPlayerType(Piece piece, String type) {
		this.players.put(piece, type);
	}

	public String getPlayerModeString(int index) {
		return this.playerModesStringArray[index];
	}

	public int getAvailablePlayerModes() {
		return this.availablePlayerModes;
	}
}
