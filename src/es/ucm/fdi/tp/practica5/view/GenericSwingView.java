package es.ucm.fdi.tp.practica5.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

public class GenericSwingView implements GameObserver {

	/*
	 * COLOR CHOOSERS CON INTERFACES VISTAS ESPECIFICAS DE CADA JUEGO
	 */

	private static final String startingMessage = "Starting ";
	private static final String changeTurnMessage = "Turn for ";
	private static final String gameOverMessage = "Game Over!!\n";
	private static final String gameStatusMessage = "Game Status: ";
	private static final String winnerMessage = "Winner: ";
	private static final String titleMessage = "Board Games: ";
	private static final String youMessage = " You ";
	private static final String moveMessage = "left-click origin piece...\nrigh-click anywhere to deselect origin...\n";

	private PieceColorMap colorChooser;

	/*
	 * Esta clase debería tener una GUI como atributo privado sobre el que
	 * trabajar.
	 */
	private Piece viewPiece;
	private GUI gui;
	private Controller c;
	private MoveController moveController;
	private Player random;
	private Player ai;
	private List<Piece> randomPlayers;
	private List<Piece> intelligentPlayers;

	public GenericSwingView(Observable<GameObserver> g, Controller c, final Piece viewPiece,
			MoveController moveController, Player random, Player ai) {
		/*
		 * Me suscribo como observador de la clase. (No se que significa
		 * exactamente, pero bueno).
		 * 
		 * Deberíamos iniciar la gui desde aquí, pero nos genera el problema del
		 * puto tablero...
		 */
		this.random = random;
		this.ai = ai;
		this.moveController = moveController;
		this.c = c;
		this.viewPiece = viewPiece;
		g.addObserver(this);
		colorChooser = new PieceColorMap();
		this.randomPlayers = new ArrayList<Piece>();
		this.intelligentPlayers = new ArrayList<Piece>();
	}

	@Override
	/* Nos pasan un read only board, lo cual no nos sirve de mucho... */
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		// Debería iniciar la GUI...
		// GUI view = new GUI(...);
		// Nos vuelve a generar el problema del puto titulo...

		/*
		 * De hecho no solo deberíamos iniciar la GUI sino también hacer cosas
		 * con ella, por ejemplo, pintar cosas en el status,... A ver como
		 * hostias hacemos eso...
		 */
		gui = new GUI(board, pieces, colorChooser, turn, c, moveController, random, ai, randomPlayers, intelligentPlayers);

		if (viewPiece == null) {
			gui.setTitle(titleMessage + gameDesc);
		} else {
			gui.setTitle(titleMessage + gameDesc + " (" + viewPiece + ")");
		}

		gui.update();
		gui.appendToStatusMessagePanel(startingMessage + "'" + gameDesc + "'\n");
		if (this.viewPiece == turn) {
			gui.appendToStatusMessagePanel(changeTurnMessage + youMessage + turn + "\n");
			gui.appendToStatusMessagePanel(moveMessage);
		} else {
			gui.appendToStatusMessagePanel(changeTurnMessage + turn + "\n");
			gui.appendToStatusMessagePanel(moveMessage);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gui.setVisible(true);
			}
		});
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		gui.update();
		gui.appendToStatusMessagePanel(gameOverMessage);
		gui.appendToStatusMessagePanel(gameStatusMessage + state + "\n");
		if (winner != null) {
			gui.appendToStatusMessagePanel(winnerMessage + winner + "\n");
		}
	}

	// Estos dos metodos están en blanco en el generic console view, me da a mi
	// que vamos a tener que meter lógica en ellos...

	@Override
	public void onMoveStart(Board board, Piece turn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		gui.setTurn(turn);
		gui.update();
		if (this.viewPiece == turn) {
			gui.appendToStatusMessagePanel(moveMessage);
			gui.appendToStatusMessagePanel(changeTurnMessage + youMessage + turn + "\n");
		} else {
			gui.appendToStatusMessagePanel(moveMessage);
			gui.appendToStatusMessagePanel(changeTurnMessage + turn + "\n");
		}
		if(isRandomPlayer(turn))
			c.makeMove(random);
	}

	@Override
	public void onError(String msg) {
		gui.update();
		gui.appendToStatusMessagePanel(msg + "\n");
	}

	
	private boolean isRandomPlayer(Piece p){
		for(int i = 0; i < this.randomPlayers.size(); i++){
			if(p == this.randomPlayers.get(i))
				return true;
		}
		return false;
	}
	
}
