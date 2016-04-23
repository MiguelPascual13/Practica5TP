package es.ucm.fdi.tp.practica5.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.boardpanel.BoardPanel;
import es.ucm.fdi.tp.practica5.boardpanel.Cell.CellClickedListener;
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.IntelligentButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.RandomButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;
import es.ucm.fdi.tp.practica5.lateralpanel.PieceColorsPanel.ColorChangeListener;
import es.ucm.fdi.tp.practica5.lateralpanel.PlayerModesPanel.PlayerModesChangeListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.QuitButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.RestartButtonListener;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private LateralPanel lateralPanel;
	private BoardPanel boardPanel;
	private JSplitPane vSplitPane;

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser,
			Piece turn, MoveController moveController, Piece viewPiece,
			SwingController controller, QuitButtonListener quitButtonListener,
			RestartButtonListener restartButtonListener,
			RandomButtonListener randomButtonListener,
			IntelligentButtonListener intelligentButtonListener,
			ColorChangeListener colorChangeListener,
			PlayerModesChangeListener playerModesChangeListener,
			CellClickedListener cellClickedListener) {

		super();
		this.vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		buildBoard(board, colorChooser, moveController, controller, viewPiece,
				cellClickedListener);

		lateralPanel = new LateralPanel(pieces, colorChooser, board, viewPiece,
				controller, turn, quitButtonListener, restartButtonListener,
				randomButtonListener, intelligentButtonListener,
				colorChangeListener, playerModesChangeListener);

		this.vSplitPane.setLeftComponent(boardPanel);
		this.vSplitPane.setRightComponent(lateralPanel);
		this.getContentPane().add(vSplitPane, BorderLayout.CENTER);
		this.vSplitPane.setDividerLocation(580);

		this.setLocation(100, 50);
		this.setResizable(true);
		this.setVisible(true);
		this.setSize(850, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void appendToStatusMessagePanel(String message) {
		this.lateralPanel.appendToStatusMessagePanel(message);
		this.lateralPanel.repaint();
	}

	public void setBoard(Board board) {
		this.boardPanel.setBoard(board);
	}

	public void update(Integer selectedRow, Integer selectedColumn,
			List<Pair<Integer, Integer>> filter, Piece turn) {
		this.boardPanel.update(selectedRow, selectedColumn, filter, turn);
		this.lateralPanel.updateTable();
	}

	private void buildBoard(Board board, PieceColorMap colorChooser,
			MoveController moveController, Controller controller,
			Piece viewPiece, CellClickedListener cellClickedListener) {
		boardPanel = new BoardPanel(board, colorChooser, cellClickedListener);
	}

	public void disableAutomaticMoves(boolean disable) {
		this.lateralPanel.disableAutomaticMoves(disable);
	}
}
