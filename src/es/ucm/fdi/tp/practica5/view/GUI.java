package es.ucm.fdi.tp.practica5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
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

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser, Piece turn, Controller c,
			MoveController moveController, Player random, Player ai, List<Piece> randomPlayers,
			List<Piece> intelligentPlayers) {

		super();
		this.actualTurn = turn;
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

		boardPanel = new BoardPanel(board, colorChooser, new CellLeftClickedListener() {

			@Override
			public void cellWasLeftClicked(int row, int column) {
				if (moveController.manageClicks(board, row, column, actualTurn, MouseEvent.BUTTON1, new MoveListener() {

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
					lateralPanel.updateTable();
				}
			}
		}, new CellRightClickedListener() {

			@Override
			public void cellWasRightClicked(int row, int column) {
				moveController.manageClicks(board, row, column, turn, MouseEvent.BUTTON3, new MoveListener() {

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

		lateralPanel = new LateralPanel(pieces, colorChooser, board, new ColorChangeListener() {

			public void colorChanged(Piece piece, Color color) {
				colorChooser.setColorFor(piece, color);
				boardPanel.update();
				lateralPanel.updateTable();
			}

		}, new QuitButtonListener() {

			public void QuitButtonClicked() {
				JButton yesButton = new JButton("Yes");
				JButton noButton = new JButton("No");
				JButton options[] = { yesButton, noButton };
				JFrame ventanaQuit = new JFrame();
				ventanaQuit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				yesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.stop();
						ventanaQuit.dispose();
						dispose();
					}
				});
				noButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ventanaQuit.dispose();
					}
				});
				JOptionPane.showOptionDialog(ventanaQuit, "Are you sure you want to quit?", "Quit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			}

		}, new RestartButtonListener() {

			public void RestartButtonClicked() {
				dispose();
				c.restart();
			}

		}, new RandomButtonListener() {

			public void RandomButtonClicked() { // Mete aqui el randomMove
				c.makeMove(random);
			}

		}, new IntelligentButtonListener() {

			public void IntelligentButtonClicked() {
				c.makeMove(ai);
			}

		}, new SetButtonListener() {

			public void SetButtonClicked() {
				/*
				 * Hay que coger la opción del JComboBox e ingeniarnoslas para
				 * que de alguna forma recuerde que siempre tendrá que hacer
				 * movimientos random...
				 * 
				 * NO ESTOY SEGURO DE QUE ESTO SEA EL LUGAR INDICADO
				 * 
				 * HAY QUE COMPROBAR MAS COSAS
				 * ¿ES SU TURNO???
				 * ¿PASO DE RANDOM A MANUAL? ---> QUITANDO LA PIEZA DEL ARRAY
				 */
				randomPlayers.add(actualTurn);
				
				c.makeMove(random);
			}
		});

		// Creo que se podria hacer asi el JSplitPane, tu veras si te gusta como
		// queda o no.
		JSplitPane vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, lateralPanel);
		this.getContentPane().add(vSplitPane, BorderLayout.CENTER);
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
		this.lateralPanel.repaint();
	}

	public void setBoard(Board board) {
		this.boardPanel.setBoard(board);
	}

	public void update() {
		this.boardPanel.update();
	}

	public void setTurn(Piece turn) {
		actualTurn = turn;
	}
}
