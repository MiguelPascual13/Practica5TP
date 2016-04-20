package es.ucm.fdi.tp.practica5.ListenerSettings;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.lateralpanel.PlayerModesPanel;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController.ErrorListener;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController.MoveListener;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;
import es.ucm.fdi.tp.practica5.view.GUI;

public class ListenerSettings {
	PieceColorMap colorChooser;
	GUI gui;
	Controller c;
	Player random, ai;
	Piece turn;
	List<Piece> randomPlayers, intelligentPlayers;
	Board board;
	MoveController moveController;

	public ListenerSettings(PieceColorMap colorChooser, GUI gui, Controller c,
			Player random, Player ai, Piece turn, List<Piece> randomPlayers,
			List<Piece> intelligentPlayers, Board board,MoveController moveController) {
		this.colorChooser =colorChooser;
		this.gui=gui;
		this.c=c;
		this.random=random;
		this.ai=ai;
		this.turn=turn;
		this.randomPlayers=randomPlayers;
		this.intelligentPlayers=intelligentPlayers;
		this.board=board;
		this.moveController=moveController;
	}
	public void cellWasLeftClicked(int row, int column) {
		if (moveController.manageClicks(board, row, column,
				turn, MouseEvent.BUTTON1,
				new MoveListener() {
			
					public void notifyMove(String message) {
						gui.appendToStatusMessagePanel(message);
					}

				}, new ErrorListener() {

					public void notifyError(String message) {
						gui.appendToStatusMessagePanel(message);
					}

				})) {
			c.makeMove(moveController);
			gui.update();
		}
	}
	public void cellWasRightClicked(int row, int column) {
		moveController.manageClicks(board, row, column, turn,
				MouseEvent.BUTTON3, new MoveListener() {

					@Override
					public void notifyMove(String message) {
						gui.appendToStatusMessagePanel(message);
					}

				}, new ErrorListener() {

					@Override
					public void notifyError(String message) {
						gui.appendToStatusMessagePanel(message);
					}

				});
	}
	public void colorChanged(Piece piece, Color color) {
		colorChooser.setColorFor(piece, color);
		gui.update();
	}

	public void SetButtonClicked(Piece piece, String mode) {
		if (mode == PlayerModesPanel.manualText) {
			if (gui.isRandomPlayer(piece) != null) {
				randomPlayers.remove(piece);
			} else if (gui.isIntelligentPlayer(piece) != null) {
				intelligentPlayers.remove(piece);
			}
		} else if (mode == PlayerModesPanel.randomText) {
			if (gui.isIntelligentPlayer(piece) != null) {
				intelligentPlayers.remove(piece);
			}
			if (gui.isRandomPlayer(piece) == null) {
				randomPlayers.add(piece);
			}
			if (piece == turn) {
				c.makeMove(random);
			}
		} else {
			if (gui.isRandomPlayer(piece) != null) {
				randomPlayers.remove(piece);
			}
			if (gui.isIntelligentPlayer(piece) == null) {
				intelligentPlayers.add(piece);
			}
			if (piece == turn) {
				c.makeMove(ai);
			}
		}
		gui.update();
	}

	public void RandomButtonClicked() {
		c.makeMove(random);
	}

	public void IntelligentButtonClicked() {
		c.makeMove(ai);
	}

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

	public void RestartButtonClicked() {
		gui.dispose();
		c.restart();
	}
	
	public void setTurn(Piece turn) {
		this.turn = turn;
	}

}
