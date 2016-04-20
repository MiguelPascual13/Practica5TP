package es.ucm.fdi.tp.practica5.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.ListenerSettings.ListenerSettings;
import es.ucm.fdi.tp.practica5.boardpanel.BoardPanel;
import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	/**
	 * Lateral options panel, to view and modify some game information.
	 */
	private LateralPanel lateralPanel;

	/**
	 * This pane shows the full game board with its pieces and obstacles if
	 * necessary.
	 */
	private BoardPanel boardPanel;

	private Piece actualTurn;
	private List<Piece> actualRandomPlayers;
	private List<Piece> actualIntelligentPlayers;
	private JSplitPane vSplitPane;
	private ListenerSettings generalListener;

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser,
			Piece turn, Controller c, MoveController moveController,
			Player random, Player ai, List<Piece> randomPlayers,
			List<Piece> intelligentPlayers) {

		super();
		this.actualTurn = turn;
		this.actualRandomPlayers = randomPlayers;
		this.actualIntelligentPlayers = intelligentPlayers;
		this.vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.generalListener = new ListenerSettings(colorChooser, this, c,
				random, ai, turn, randomPlayers, intelligentPlayers, board,
				moveController);

		/*
		 * Shows a title or another depending on the game an on the view
		 * options.
		 */
		/*
		 * We are subscribing as an observer to the game, i don´t really know
		 * what this means. Me suscribo como observador del juego.
		 */

		/**
		 * IMPORTANT: All of this will be eventually changed into a JSplitPane
		 * structure, for the moment it will stay as it is now.
		 */

		boardPanel = new BoardPanel(board, colorChooser, generalListener);

		// This constructor is seems to be really heavy to read, because it
		// contains all the listener of the lateral panel from any game.

		lateralPanel = new LateralPanel(pieces, colorChooser, board,
				generalListener, actualRandomPlayers, actualIntelligentPlayers);

		// Creo que se podria hacer asi el JSplitPane, tu veras si te gusta como
		// queda o no.
		vSplitPane.setLeftComponent(boardPanel);
		vSplitPane.setRightComponent(lateralPanel);
		this.getContentPane().add(vSplitPane, BorderLayout.CENTER);

		/* Other stuff */
		this.setLocation(100, 50);
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(850, 600);
		vSplitPane.setDividerLocation(570);
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
		this.lateralPanel.repaint();
	}

	public void setBoard(Board board) {
		this.boardPanel.setBoard(board);
	}

	public void update() {
		this.boardPanel.update();
		this.lateralPanel.updateTable();
	}

	public void setTurn(Piece turn) {
		actualTurn = turn;
		this.generalListener.setTurn(turn);
	}

	public Integer isRandomPlayer(Piece p) {
		for (int i = 0; i < actualRandomPlayers.size(); i++) {
			if (p == actualRandomPlayers.get(i))
				return i;
		}
		return null;
	}

	public Integer isIntelligentPlayer(Piece p) {
		for (int i = 0; i < actualIntelligentPlayers.size(); i++) {
			if (p == actualIntelligentPlayers.get(i))
				return i;
		}
		return null;
	}
}
