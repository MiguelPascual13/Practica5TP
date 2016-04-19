package es.ucm.fdi.tp.practica5;

import java.awt.GridLayout;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.practica5.Cell.CellClickedListener;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private static final int SEPARATION = 4;
	private CellClickedListener listener;
	private PieceColorMap colorChooser;

	/**
	 * Matrix of JLabels, it will our "board" to paint.
	 */
	private Cell cells[][];

	/**
	 * Board with all of its functionality.
	 */
	private Board board;

	/**
	 * ¿How the board is painted? We will make it easy, with a bunch of JLabels
	 * embed in a GridLayout.
	 * 
	 * Each cell is a JLabel. Each cell knows its position. (We need it for the
	 * movement generation).
	 * 
	 * 
	 * 
	 * If time is not a problem we will include a parameter to make it in the
	 * difficult way, using weird and unknown things and so on.
	 * 
	 * @param rows
	 * @param columns
	 */
	public BoardPanel(Board board, PieceColorMap colorChooser, CellClickedListener listener) {
		super();
		this.listener = listener;
		this.colorChooser = colorChooser;
		this.setBoard(board);
		this.update();
	}

	public void setGame(Game game) {

	}

	public void setColors(PieceColorMap colorMap) {

	}

	/**
	 * This are private methods, thats fun, but we will have to do extrange
	 * things with them, we will see...
	 */

	/**
	 * Totally replaces the board.
	 * 
	 * IMPORTANT: It does not repaint the board.
	 * 
	 * NOTE: We does not care about if the new board is totally different from
	 * the old one. It can have different number of rows and columns,...
	 * 
	 * HELP WANTED: In which circumstances is this going to be used???
	 * 
	 * @param board
	 *            New board to paint.
	 */
	public void setBoard(Board board) {

		/**
		 * HIGHLIGHT: The removeAll method from the Container class, removes
		 * every component in the container (but not in the memory), in this
		 * case, every single JLabel in our GridLayout board will be suppressed.
		 */

		// discarding the old board...
		removeAll();

		// changing the reference to another board...
		this.board = board;

		/**
		 * HELP WANTED: This JLabel matrix is resized even if it is not really
		 * necessary, could we change it?
		 */

		/**
		 * I only put this to emphasize that the board we have is the new board.
		 */

		// creating a new JLabel matrix...
		cells = new Cell[this.board.getRows()][this.board.getCols()];

		// changing the JPanel GridLayout (same doubt as before)...
		this.setLayout(new GridLayout(this.board.getRows(), this.board.getCols(), SEPARATION, SEPARATION));

		// filling the JLabel matrix...
		this.fillJLabelMatrix();
	}

	/**
	 * Repaint the board, we assume that rows and columns has not changed.
	 * 
	 * HIGHLIGHT1: I think this observation is redundant, we can change
	 * everything we want in the setBoard method, and there will be no problem.
	 * 
	 * HIGHLIGHT2: It does not add again to the container all the pieces (or
	 * yes), what´s happening?
	 * 
	 * HIGHLIGHT3: Revise all the highlights.
	 */
	public void update() {
		if (colorChooser == null) {
			this.colorChooser = new PieceColorMap();
		}
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getCols(); j++) {
				// Why do we create an object here?
				// Piece p = this.board.getPosition(i, j);
				cells[i][j].setOpaque(true);
				cells[i][j].setBackground(this.colorChooser.getColorFor(board.getPosition(i, j)));
				/*
				 * if (i % 2 == 0 && j % 2 == 0)
				 * cells[i][j].setBackground(Color.GREEN); else if (i % 2 == 0
				 * && j % 2 != 0) cells[i][j].setBackground(Color.BLUE); else if
				 * (i % 2 != 0 && j % 2 == 0)
				 * cells[i][j].setBackground(Color.CYAN); else
				 * cells[i][j].setBackground(Color.PINK);
				 */
				// cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.add(cells[i][j]);
			}
		}

		/**
		 * HIGHLIGHT: The repaint method repaint component, in this cases the
		 * component is ourselves, the BoardPanel, for this reason I put "this",
		 * to emphasize.
		 */
		this.repaint();
	}

	private void fillJLabelMatrix() {
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getCols(); j++) {
				this.cells[i][j] = new Cell(i, j, listener);
			}
		}
	}
}
