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
	private List<Piece> actualRandomPlayers;
	private List<Piece> actualIntelligentPlayers;
	private Piece actualTurn;
	private MoveController moveController;
	private Board board;

	/*-----COMPONENTES DEPRECATED-----*/
	private ListenerSettings generalListener;

	public GUI(Board board, List<Piece> pieces, PieceColorMap colorChooser, Piece turn, Controller controller,
			MoveController moveController, Player random, Player ai, List<Piece> randomPlayers,
			List<Piece> intelligentPlayers, Piece viewPiece) {

		super();
		this.board = board;
		this.moveController = moveController;
		this.actualRandomPlayers = randomPlayers;
		this.actualIntelligentPlayers = intelligentPlayers;
		this.vSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.generalListener = new ListenerSettings(colorChooser, this, controller, random, ai, turn, randomPlayers,
				intelligentPlayers, board, moveController);
		this.actualTurn = turn;

		buildBoard(board, colorChooser, moveController, controller, viewPiece);

		lateralPanel = new LateralPanel(pieces, colorChooser, board, generalListener, actualRandomPlayers,
				actualIntelligentPlayers, viewPiece);

		this.vSplitPane.setLeftComponent(boardPanel);
		this.vSplitPane.setRightComponent(lateralPanel);
		this.getContentPane().add(vSplitPane, BorderLayout.CENTER);
		this.vSplitPane.setDividerLocation(580);

		/* Other stuff */
		this.setLocation(100, 50);
		this.setResizable(false);
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
		this.boardPanel.update(moveController.getSelectedRow(), moveController.getSelectedColumn(), moveController.getFilterOnCells(board), actualTurn);
		this.lateralPanel.updateTable();
	}

	/**
	 * Updates the the turn so the interface know what´s going on to tell new
	 * things to the controller.
	 * 
	 * @param turn
	 *            The new turn.
	 */
	public void setTurn(Piece turn) {
		this.actualTurn = turn;
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

	private void buildBoard(Board board, PieceColorMap colorChooser, MoveController moveController,
			Controller controller, Piece viewPiece) {
		boardPanel = new BoardPanel(board, colorChooser, new CellClickedListener() {

			public void cellWasClicked(int row, int column, MouseEvent mouseEvent) {
				Integer answer = moveController.manageClicks(board, row, column, actualTurn, viewPiece, mouseEvent);
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
