package es.ucm.fdi.tp.practica5;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	/**
	 * Generic name for the main frame, it must be completed with the concrete
	 * game description.
	 */
	private static final String jFrameNameText = "Board Games: ";

	/**
	 * Lateral options panel, to view and modify some game information.
	 */
	private LateralPanel lateralPanel;

	/**
	 * This pane shows the full game board with its pieces and obstacles if
	 * necessary.
	 */
	private BoardPanel boardPanel;

	/* g es el puto juego, el que tiene el puto tablero. */
	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser) {

		super();

		/*
		 * Shows a title or another depending on the game an on the view
		 * options.
		 */
		/*
		 * if (viewPiece != null) this.setTitle(jFrameNameText + gameDescription
		 * + "(" + viewPiece.getId() + ")"); else this.setTitle(jFrameNameText +
		 * gameDescription);
		 */
		/*
		 * We are subscribing as an observer to the game, i don´t really know
		 * what this means. Me suscribo como observador del juego.
		 */

		/**
		 * IMPORTANT: All of this will be eventually changed into a JSplitPane
		 * structure, for the moment it will stay as it is now.
		 */

		this.setLayout(new BorderLayout());
		boardPanel = new BoardPanel(board, colorChooser);
		lateralPanel = new LateralPanel(pieces, colorChooser, boardPanel);
		this.add(lateralPanel, BorderLayout.EAST);
		this.add(boardPanel, BorderLayout.CENTER);

		/* Other stuff */
		this.setLocation(100, 50);
		this.setResizable(true);
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Adds a message at the end of the text area, the messages are supposed to
	 * end with an end line character.
	 * 
	 * @param message
	 *            The message to show.
	 */
	public void appendToStatusMessagePanel(String message) {
		this.lateralPanel.appendToStatusMessagePanel(message);
	}

	public void setBoard(Board board) {
		this.boardPanel.setBoard(board);
	}

	public void update() {
		this.boardPanel.update();
	}
}
