package es.ucm.fdi.tp.practica5;

import java.awt.EventQueue;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class GenericSwingView implements GameObserver {

	private static final String startingMessage = "Starting ";
	private static final String changeTurnMessage = "Turn for ";
	private static final String gameOverMessage = "Game Over!!\n";
	private static final String gameStatusMessage = "Game Status: ";
	private static final String winnerMessage = "Winner: ";
	private static final String titleMessage = "Board Games: ";
	
	private PieceColorMap colorChooser;
	
	/*
	 * Esta clase debería tener una GUI como atributo privado sobre el que
	 * trabajar.
	 */
	private GUI gui;

	public GenericSwingView(Observable<GameObserver> g, Controller c) {
		/*
		 * Me suscribo como observador de la clase. (No se que significa
		 * exactamente, pero bueno).
		 * 
		 * Deberíamos iniciar la gui desde aquí, pero nos genera el problema del
		 * puto tablero...
		 */
		g.addObserver(this);
		
		colorChooser = new PieceColorMap();
	}

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		// Debería iniciar la GUI...
		// GUI view = new GUI(...);
		// Nos vuelve a generar el problema del puto titulo...

		/*
		 * De hecho no solo deberíamos iniciar la GUI sino también hacer cosas
		 * con ella, por ejemplo, pintar cosas en el status,... A ver como
		 * hostias hacemos eso...
		 */
		gui = new GUI(board, pieces, colorChooser);
		gui.setTitle(titleMessage + gameDesc);
		gui.update();
		gui.appendToStatusMessagePanel(startingMessage + "'" + gameDesc + "'\n");
		gui.appendToStatusMessagePanel(changeTurnMessage + turn + "\n");
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
		gui.appendToStatusMessagePanel(winnerMessage + winner + "\n");
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
		gui.update();
		gui.appendToStatusMessagePanel(changeTurnMessage + turn + "\n");
	}

	@Override
	public void onError(String msg) {
		gui.update();
		gui.appendToStatusMessagePanel(msg + "\n");
	}

}
