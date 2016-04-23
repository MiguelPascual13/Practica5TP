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
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class PlayerInformationPanel extends JPanel {
	private static final String PANEL_NAME_TEXT = "Player Information";
	private static final String COL1 = "Player";
	private static final String COL2 = "Mode";
	private static final String COL3 = "#Pieces";
	private static final String UNKNOWN = "-";

	private JScrollPane scrollPane;
	private JTable table;

	public PlayerInformationPanel(List<Piece> pieces, Board board,
			PieceColorMap colorChooser, Piece viewPiece,
			SwingController controller) {

		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), PANEL_NAME_TEXT));

		String columName[] = { COL1, COL2, COL3 };
		table = new JTable(new MyTableModel(pieces, columName, board, viewPiece,
				controller));
		for (int i = 0; i < 3; i++) {
			table.getColumnModel().getColumn(i).setHeaderValue(columName[i]);
			// table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		// table.setPreferredSize(new
		// Dimension(table.getPreferredSize().width,20*pieces.size()));
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				JComponent c = (JComponent) super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
				c.setBackground(colorChooser.getColorFor(pieces.get(row)));
				return c;
			}

		});
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}

	public void updateTableInfo() {
		//scrollPane.repaint();
		this.repaint();
	}

	static class MyTableModel extends AbstractTableModel {

		private String[] columnName;
		private List<Piece> pieces;
		private Board board;
		private Piece viewPiece;
		private SwingController controller;

		public MyTableModel(List<Piece> pieces, String[] columnName,
				Board board, Piece viewPiece, SwingController controller) {
			this.pieces = pieces;
			this.columnName = columnName;
			this.board = board;
			this.viewPiece = viewPiece;
			this.controller = controller;
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
				if (this.controller.isPlayerOfType(pieces.get(row), controller
						.getPlayerModeString(SwingController.RANDOM))) {
					return controller
							.getPlayerModeString(SwingController.RANDOM);
				} else if (this.controller.isPlayerOfType(pieces.get(row),
						controller.getPlayerModeString(
								SwingController.INTELLIGENT))) {
					return controller
							.getPlayerModeString(SwingController.INTELLIGENT);
				} else {
					return controller
							.getPlayerModeString(SwingController.MANUAL);
				}
			} else {
				return UNKNOWN;
			}

		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}
}
