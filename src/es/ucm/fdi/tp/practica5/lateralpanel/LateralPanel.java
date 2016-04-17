package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LateralPanel extends JPanel {

	private PlayerModesPanel playerModesPanel;
	private PieceColorsPanel pieceColorsPanel;
	private AutomaticMovesPanel automaticMovesPanel;
	private QuitRestartPanel quitRestartPanel;
	private StatusMessagePanel statusMessagePanel;
	private PlayerInformationPanel playerInformationPanel;

	public LateralPanel() {
		super(new GridLayout(0, 1));
		statusMessagePanel = new StatusMessagePanel();
		playerInformationPanel = new PlayerInformationPanel();
		playerModesPanel = new PlayerModesPanel();
		pieceColorsPanel = new PieceColorsPanel();
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
