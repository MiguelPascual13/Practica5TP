package es.ucm.fdi.tp.practica5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.boardpanel.BoardPanel;
import es.ucm.fdi.tp.practica5.boardpanel.Cell.CellLeftClickedListener;
import es.ucm.fdi.tp.practica5.boardpanel.Cell.CellRightClickedListener;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.IntelligentButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.RandomButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;
import es.ucm.fdi.tp.practica5.lateralpanel.PieceColorsPanel.ColorChangeListener;
import es.ucm.fdi.tp.practica5.lateralpanel.PlayerModesPanel;
import es.ucm.fdi.tp.practica5.lateralpanel.PlayerModesPanel.SetButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.QuitButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.RestartButtonListener;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController.ErrorListener;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController.MoveListener;
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

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser,
			Piece turn, Controller c, MoveController moveController,
			Player random, Player ai, List<Piece> randomPlayers,
			List<Piece> intelligentPlayers) {

		super();
		this.actualTurn = turn;
		this.actualRandomPlayers = randomPlayers;
		this.actualIntelligentPlayers = intelligentPlayers;
		this.vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
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

		boardPanel = new BoardPanel(board, colorChooser,
				new CellLeftClickedListener() {

					@Override
					public void cellWasLeftClicked(int row, int column) {
						if (moveController.manageClicks(board, row, column,
								actualTurn, MouseEvent.BUTTON1,
								new MoveListener() {

									@Override
									public void notifyMove(String message) {
										appendToStatusMessagePanel(message);
									}

								}, new ErrorListener() {

									@Override
									public void notifyError(String message) {
										appendToStatusMessagePanel(message);
									}

								})) {
							c.makeMove(moveController);
							update();
						}
					}
				}, new CellRightClickedListener() {

					@Override
					public void cellWasRightClicked(int row, int column) {
						moveController.manageClicks(board, row, column, turn,
								MouseEvent.BUTTON3, new MoveListener() {

									@Override
									public void notifyMove(String message) {
										appendToStatusMessagePanel(message);
									}

								}, new ErrorListener() {

									@Override
									public void notifyError(String message) {
										appendToStatusMessagePanel(message);
									}

								});
					}

				});

		// This constructor is seems to be really heavy to read, because it
		// contains all the listener of the lateral panel from any game.

		lateralPanel = new LateralPanel(pieces, colorChooser, board,
				new ColorChangeListener() {

					public void colorChanged(Piece piece, Color color) {
						colorChooser.setColorFor(piece, color);
						update();
					}

				}, new QuitButtonListener() {

					public void QuitButtonClicked() {
						JFrame ventanaQuit = new JFrame();
						int n = JOptionPane.showConfirmDialog(ventanaQuit,
								"Are you sure you want to quit?", "Quit",
								JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							System.exit(0);
						} else {
							ventanaQuit.dispose();
						}
					}

				}, new RestartButtonListener() {

					public void RestartButtonClicked() {
						dispose();
						c.restart();
					}

				}, new RandomButtonListener() {

					public void RandomButtonClicked() { // Mete aqui el
														// randomMove
						c.makeMove(random);
					}

				}, new IntelligentButtonListener() {

					public void IntelligentButtonClicked() {
						c.makeMove(ai);
					}

				}, new SetButtonListener() {

					public void SetButtonClicked(Piece piece, String mode) {
						if (mode == PlayerModesPanel.manualText) {
							if (isRandomPlayer(piece) != null) {
								actualRandomPlayers.remove(piece);
							} else if (isIntelligentPlayer(piece)!= null) {
								actualIntelligentPlayers
										.remove(piece);
							}
						} else if (mode == PlayerModesPanel.randomText) {
							if (isIntelligentPlayer(piece) != null) {
								actualIntelligentPlayers
										.remove(piece);
							}
							if (isRandomPlayer(piece) == null) {
								actualRandomPlayers.add(piece);
							}
							if (piece == actualTurn){
								c.makeMove(random);
								update();
							}
						} else {
							if (isRandomPlayer(piece) != null) {
								actualRandomPlayers.remove(piece);
							}
							if (isIntelligentPlayer(piece) == null) {
								actualIntelligentPlayers.add(piece);
							}
							if (piece == actualTurn){
								c.makeMove(ai);
								update();
							}
						}
					}
				});

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
