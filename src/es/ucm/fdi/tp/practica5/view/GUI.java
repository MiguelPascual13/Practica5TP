package es.ucm.fdi.tp.practica5.view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.boardpanel.BoardPanel;
import es.ucm.fdi.tp.practica5.boardpanel.Cell.CellClickedListener;
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;
import es.ucm.fdi.tp.practica5.listeners.ListenerSettings;
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

	/*-----COMPONENTES DEPRECATED-----*/
	private ListenerSettings generalListener;

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser,
			Piece turn, MoveController moveController, Player random, Player ai,
			Piece viewPiece, SwingController controller) {

		super();
		this.board = board;
		this.moveController = moveController;
		this.vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.generalListener = new ListenerSettings(colorChooser, this,
				controller, random, ai, turn,
				board, moveController);
		this.actualTurn = turn;

		buildBoard(board, colorChooser, moveController, controller, viewPiece);

		lateralPanel = new LateralPanel(pieces, colorChooser, board,
				generalListener,
				viewPiece, controller, turn, random, ai);

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
