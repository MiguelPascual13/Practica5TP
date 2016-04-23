package es.ucm.fdi.tp.practica5.view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.QuitButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.RestartButtonListener;
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
	private PieceColorMap colorChooser;

	/*
	 * Esta clase debería tener una GUI como atributo privado sobre el que
	 * trabajar.
	 */
	private SwingController controller;
	private Piece viewPiece;
	private GUI gui;
	private MoveController moveController;
	private Player random;
	private Player ai;

	public GenericSwingView(Observable<GameObserver> g, SwingController c,
			final Piece viewPiece, MoveController moveController, Player random,
			Player ai) {
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
		this.controller = c;
		this.viewPiece = viewPiece;
		g.addObserver(this);
		colorChooser = new PieceColorMap();
	}

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {

		if (gui != null)
			gui.dispose();

		gui = new GUI(board, pieces, colorChooser, turn, moveController, random,
				ai, this.viewPiece, controller,
				this.getQuitButtonListener(controller),
				this.getRestartButtonListener(controller));

		if (viewPiece == null) {
			gui.setTitle(titleMessage + gameDesc);
		} else {
			gui.setTitle(titleMessage + gameDesc + " (" + viewPiece + ")");
		}

		gui.update();
		gui.appendToStatusMessagePanel(
				startingMessage + "'" + gameDesc + "'\n");
		if (this.viewPiece == turn) {
			gui.appendToStatusMessagePanel(
					changeTurnMessage + youMessage + turn + "\n");
		} else {
			gui.appendToStatusMessagePanel(changeTurnMessage + turn + "\n");
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
			JOptionPane.showMessageDialog(new JFrame(), winnerMessage + winner,
					gameOverMessage, JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), gameStatusMessage,
					gameOverMessage, JOptionPane.PLAIN_MESSAGE);
		}
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		gui.setTurn(turn);
		gui.update();
		if (this.viewPiece == turn) {
			gui.appendToStatusMessagePanel(
					changeTurnMessage + youMessage + turn + "\n");
		} else {
			gui.appendToStatusMessagePanel(changeTurnMessage + turn + "\n");
		}
		if (controller.isPlayerOfType(turn,
				controller.getPlayerModeString(SwingController.RANDOM))) {
			randomMakeMove();
		} else if (controller.isPlayerOfType(turn,
				controller.getPlayerModeString(SwingController.INTELLIGENT))) {
			intelligentMakeMove();
		}
	}

	@Override
	public void onError(String msg) {
		JOptionPane.showMessageDialog(new JFrame(), msg, "Game error",
				JOptionPane.ERROR_MESSAGE);
		gui.appendToStatusMessagePanel(msg + "\n");
	}

	private void randomMakeMove() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				controller.makeMove(random);
				gui.update();
			}
		});
	}

	private void intelligentMakeMove() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				controller.makeMove(ai);
				gui.update();
			}
		});
	}

	private QuitButtonListener getQuitButtonListener(
			SwingController controller) {
		return new QuitButtonListener() {

			@Override
			public void QuitButtonClicked() {
				/*
				 * We just put it because the statement said, but we think is
				 * nicer without it.
				 */
				controller.stop();

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

		};
	}

	private RestartButtonListener getRestartButtonListener(
			SwingController controller) {
		return new RestartButtonListener() {

			@Override
			public void RestartButtonClicked() {
				gui.dispose();
				gui = null;
				controller.restart();
			}

		};
	}
}
