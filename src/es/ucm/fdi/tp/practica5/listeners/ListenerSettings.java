package es.ucm.fdi.tp.practica5.listeners;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;
import es.ucm.fdi.tp.practica5.view.GUI;

/*QUE PUTA MIERDA ES ESTA!!!!!!!!!!!!!!!!!!!!!!*/

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
			Player random, Player ai, Piece turn, Board board,MoveController moveController) {
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

	public void colorChanged(Piece piece, Color color) {
		colorChooser.setColorFor(piece, color);
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
		gui = null;
		c.restart();
	}
	
	public void setTurn(Piece turn) {
		this.turn = turn;
	}

}
