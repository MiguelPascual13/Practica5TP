package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class PlayerInformationPanel extends JPanel {
	private static final String PANEL_NAME_TEXT = "Player Information";
	private static final String COL1 = "Player";
	private static final String COL2 = "Mode";
	private static final String COL3 = "#Pieces";
	private static final String MANUAL = "Manual";
	private static final String RANDOM = "Random";
	private static final String INTELLIGENT = "Intelligent";
	private static final String UNKNOWN = "-";

	private JScrollPane scrollPane;
	private JTable table;

	public PlayerInformationPanel(List<Piece> pieces, Board board,
			PieceColorMap colorChooser, List<Piece> randomPlayers,
			List<Piece> intelligentPlayers, Piece viewPiece) {

		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), PANEL_NAME_TEXT));
		/* That should come from somewhere... */
		String columName[] = { COL1, COL2, COL3 };
		table = new JTable(new MyTableModel(pieces, columName, board,
				randomPlayers, intelligentPlayers, viewPiece));
		for (int i = 0; i < 3; i++) {
			table.getColumnModel().getColumn(i).setHeaderValue(columName[i]);
		}
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JComponent c = (JComponent) super
						.getTableCellRendererComponent(table, value,
								isSelected, hasFocus, row, column);
				c.setBackground(colorChooser.getColorFor(pieces.get(row)));
				return c;
			}

		});
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}

	public void updateTableInfo() {
		scrollPane.repaint();
	}

	static class MyTableModel extends AbstractTableModel {

		private String[] columnName;
		private List<Piece> pieces;
		private Board board;
		private List<Piece> randomPlayers;
		private List<Piece> intelligentPlayers;
		private Piece viewPiece;

		public MyTableModel(List<Piece> pieces, String[] columnName,
				Board board, List<Piece> randomPlayers,
				List<Piece> intelligentPlayers, Piece viewPiece) {
			this.pieces = pieces;
			this.columnName = columnName;
			this.board = board;
			this.randomPlayers = randomPlayers;
			this.intelligentPlayers = intelligentPlayers;
			this.viewPiece = viewPiece;
		}

		@Override
		public int getRowCount() {
			return pieces.size();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int col) {
			return String.class;
		}

		@Override
		public int getColumnCount() {
			return columnName.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0:
				return pieces.get(row);
			case 1:
				return stringMode(row);
			default:
				return stringPieceCount(row);
			}
		}

		public String stringPieceCount(int row) {
			Integer pieceCount = board.getPieceCount(pieces.get(row));
			if (pieceCount != null) {
				return pieceCount.toString();
			} else {
				return UNKNOWN;
			}
		}

		public String stringMode(int row) {
			if (viewPiece == null || viewPiece == pieces.get(row)) {
				if (isAutomaticPlayer(pieces.get(row), randomPlayers) != null) {
					return RANDOM;
				} else if (isAutomaticPlayer(pieces.get(row),
						intelligentPlayers) != null) {
					return INTELLIGENT;
				} else {
					return MANUAL;
				}
			} else {
				return UNKNOWN;
			}

		}

		public Integer isAutomaticPlayer(Piece p, List<Piece> players) {
			for (int i = 0; i < players.size(); i++) {
				if (p == players.get(i))
					return i;
			}
			return null;
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}
}
