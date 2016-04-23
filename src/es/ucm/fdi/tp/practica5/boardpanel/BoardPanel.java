package es.ucm.fdi.tp.practica5.boardpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.boardpanel.Cell.CellClickedListener;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private static final int SEPARATION = 4;
	private CellClickedListener listener;
	private PieceColorMap colorChooser;
	private Cell cells[][];
	private Board board;

	public BoardPanel(Board board, PieceColorMap colorChooser,
			CellClickedListener listener) {
		super();
		this.listener = listener;
		this.colorChooser = colorChooser;
		this.setBoard(board);
		this.update(null, null, null, null);
	}

	public void setGame(Game game) {

	}

	public void setColors(PieceColorMap colorMap) {

	}

	public void setBoard(Board board) {
		removeAll();
		this.board = board;
		cells = new Cell[this.board.getRows()][this.board.getCols()];
		this.setLayout(new GridLayout(this.board.getRows(),
				this.board.getCols(), SEPARATION, SEPARATION));
		this.fillJLabelMatrix();
	}

	public void update(Integer row, Integer column,
			List<Pair<Integer, Integer>> filterOnCellsList, Piece turn) {
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getCols(); j++) {
				cells[i][j].setOpaque(true);
				if (row != null && column != null) {
					applyFilter(row, column, filterOnCellsList, turn);
				} else {
					cells[i][j].setBorder(null);
				}
				cells[i][j].setBackground(
						this.colorChooser.getColorFor(board.getPosition(i, j)));

				this.add(cells[i][j]);
			}
		}
		this.repaint();
	}

	private void fillJLabelMatrix() {
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getCols(); j++) {
				this.cells[i][j] = new Cell(i, j, listener);
			}
		}
	}

	private void applyFilter(int row, int column,
			List<Pair<Integer, Integer>> filterOnCellsList, Piece turn) {
		if (turn != null) {
			Color c = this.colorChooser.getColorFor(turn);
			for (int i = 0; i < filterOnCellsList.size(); i++) {
				cells[filterOnCellsList.get(i).getFirst()][filterOnCellsList
						.get(i).getSecond()].setBorder(
								BorderFactory.createLineBorder(c, SEPARATION));
			}
		}
	}

	public void paintComponent(Graphics g, Color color) {
		super.paintComponent(g);
		g.setColor(color);
		if (color == PieceColorMap.OBSTACLE_COLOR) {
			g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
			// g.setColor(Color.WHITE);
			g.drawRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
		} else {
			g.fillOval(3, 3, this.getWidth() - 9, this.getHeight() - 9);
			// g.setColor(Color.BLACK);
			g.drawOval(3, 3, this.getWidth() - 9, this.getHeight() - 9);
		}
	}

	public void disableFilters() {
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				cells[i][j].setBorder(null);
			}
		}
	}
}
