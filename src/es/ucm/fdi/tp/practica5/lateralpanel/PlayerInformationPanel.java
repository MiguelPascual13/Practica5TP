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
import es.ucm.fdi.tp.practica5.PieceColorMap;

@SuppressWarnings("serial")
public class PlayerInformationPanel extends JPanel {
	private static final String panelNameText = "Player Information";
	private static final String col1 = "Player";
	private static final String col2 = "Mode";
	private static final String col3 = "#Pieces";
	private JScrollPane scrollPane;
	private JTable table;

	public PlayerInformationPanel(List<Piece> pieces, Board board,
			PieceColorMap colorChooser) {
		// He añadido el colorChooser para cuando intentemos pintar el fondo de
		// las filas.
		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), panelNameText));

		/* That should come from somewhere... */

		String columName[] = { col1, col2, col3 };
		table = new JTable(new MyTableModel(pieces, columName, board));
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

	static class MyTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnName;
		private List<Piece> pieces;
		private Board board;

		public MyTableModel(List<Piece> pieces, String[] columnName, Board board) {
			this.pieces = pieces;
			this.columnName = columnName;
			this.board = board;
		}

		@Override
		public int getRowCount() {
			return pieces.size();
		}

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
				return "MANUAL";// Se que no es esto hay que ver que hay que
								// poner los modes
								// ESTO ESTA MAAAAAAAAL
			default:
				return board.getPieceCount(pieces.get(row)); // TODO
			}
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}
}
