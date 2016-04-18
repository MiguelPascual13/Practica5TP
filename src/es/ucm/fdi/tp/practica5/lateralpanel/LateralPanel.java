package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.PieceColorMap;
import es.ucm.fdi.tp.practica5.lateralpanel.PieceColorsPanel.ColorChangeListener;

@SuppressWarnings("serial")
public class LateralPanel extends JPanel {

	private PlayerModesPanel playerModesPanel;
	private PieceColorsPanel pieceColorsPanel;
	private AutomaticMovesPanel automaticMovesPanel;
	private QuitRestartPanel quitRestartPanel;
	private StatusMessagePanel statusMessagePanel;
	private PlayerInformationPanel playerInformationPanel;

	public LateralPanel(List<Piece> pieces, PieceColorMap colorChooser,  Board board, ColorChangeListener listener) {
		super(new GridLayout(0, 1));
		statusMessagePanel = new StatusMessagePanel();
		playerInformationPanel = new PlayerInformationPanel(pieces, board, colorChooser);
		playerModesPanel = new PlayerModesPanel(pieces);
		pieceColorsPanel = new PieceColorsPanel(pieces, colorChooser, listener);
		automaticMovesPanel = new AutomaticMovesPanel();
		quitRestartPanel = new QuitRestartPanel();
		this.add(statusMessagePanel);
		this.add(playerInformationPanel);
		this.add(pieceColorsPanel);
		this.add(playerModesPanel);
		this.add(automaticMovesPanel);
		this.add(quitRestartPanel);

	}
	
	/**
	 * Adds a message at the end of the text area, the messages are supposed to
	 * end with an end line character.
	 * 
	 * @param message
	 *            The message to show.
	 */
	public void appendToStatusMessagePanel(String message) {
		this.statusMessagePanel.append(message);
	}
}
