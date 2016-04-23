package es.ucm.fdi.tp.practica5.view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.boardpanel.BoardPanel;
import es.ucm.fdi.tp.practica5.boardpanel.Cell.CellClickedListener;
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.IntelligentButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.RandomButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.QuitButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.RestartButtonListener;
import es.ucm.fdi.tp.practica5.moveControllers.MoveController;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	/*-----COMPONENTES GRÁFICOS-----*/
	private LateralPanel lateralPanel;
	private BoardPanel boardPanel;
	private JSplitPane vSplitPane;

	/*-----COMPONENTES CONTROLADORES-----*/
	private Piece actualTurn;
	private MoveController moveController;
	private Board board;

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser,
			Piece turn, MoveController moveController, Piece viewPiece,
			SwingController controller, QuitButtonListener quitButtonListener,
			RestartButtonListener restartButtonListener,
			RandomButtonListener randomButtonListener,
			IntelligentButtonListener intelligentButtonListener) {

		super();
		this.board = board;
		this.moveController = moveController;
		this.vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.actualTurn = turn;

		buildBoard(board, colorChooser, moveController, controller, viewPiece);

		lateralPanel = new LateralPanel(pieces, colorChooser, board, viewPiece,
				controller, turn, quitButtonListener, restartButtonListener,
				randomButtonListener, intelligentButtonListener);

		this.vSplitPane.setLeftComponent(boardPanel);
		this.vSplitPane.setRightComponent(lateralPanel);
		this.getContentPane().add(vSplitPane, BorderLayout.CENTER);
		this.vSplitPane.setDividerLocation(580);

		/* Other stuff */
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

	public void update() {
		this.boardPanel.update(moveController.getSelectedRow(),
				moveController.getSelectedColumn(),
				moveController.getFilterOnCells(board), actualTurn);
		this.lateralPanel.updateTable();
	}

	public void setTurn(Piece turn) {
		this.actualTurn = turn;
	}

	private void buildBoard(Board board, PieceColorMap colorChooser,
			MoveController moveController, Controller controller,
			Piece viewPiece) {
		boardPanel = new BoardPanel(board, colorChooser,
				new CellClickedListener() {

					public void cellWasClicked(int row, int column,
							MouseEvent mouseEvent) {
						Integer answer = moveController.manageClicks(board, row,
								column, actualTurn, viewPiece, mouseEvent);
						if (answer == MoveController.REPAINT_AND_MOVE) {
							controller.makeMove(moveController);
							update();
						} else if (answer == MoveController.SOMETHING_TO_REPAINT) {
							update();
						}
					}

				});
	}
}
