package es.ucm.fdi.tp.practica5;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.Game;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	/**
	 * Matrix of JLabels, it will our "board" to paint.
	 */
	private JLabel cells[][];

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
	public BoardPanel() {
		super();
		this.board = new FiniteRectBoard(3, 3);
		this.cells = new JLabel[this.board.getRows()][this.board.getCols()];
		this.fillJLabelMatrix();
		this.setLayout(new GridLayout(this.board.getRows(), this.board.getCols()));
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
		cells = new JLabel[this.board.getRows()][this.board.getCols()];

		// changing the JPanel GridLayout (same doubt as before)...
		this.setLayout(new GridLayout(this.board.getRows(), this.board.getCols()));

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
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getCols(); j++) {
				// Why do we create an object here?
				// Piece p = this.board.getPosition(i, j);
				cells[i][j].setOpaque(true);
				if(i %2 == 0 && j%2 == 0)
					cells[i][j].setBackground(Color.RED);
				else
					cells[i][j].setBackground(Color.BLUE);
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
		/**
		 * HELP WANTED: Can we use for each notation?
		 */
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getCols(); j++) {
				this.cells[i][j] = new JLabel();
			}
		}
	}
}
