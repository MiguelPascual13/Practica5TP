package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.lateralpanel.PlayerModesPanel.PlayerModesChangeListener;
import es.ucm.fdi.tp.practica5.listeners.ListenerSettings;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

/*Esto debe conocer qué tipos de jugadores hay disponibles, probablemente sea lo más sensato pasar el controlador, por el momento inexistente*/

@SuppressWarnings("serial")
public class LateralPanel extends JPanel {

	private PlayerModesPanel playerModesPanel;
	private PieceColorsPanel pieceColorsPanel;
	private AutomaticMovesPanel automaticMovesPanel;
	private QuitRestartPanel quitRestartPanel;
	private StatusMessagePanel statusMessagePanel;
	private PlayerInformationPanel playerInformationPanel;

	public LateralPanel(List<Piece> pieces, PieceColorMap colorChooser,
			Board board, ListenerSettings listener, Piece viewPiece,
			SwingController controller) {

		super(new GridLayout(0, 1));
		statusMessagePanel = new StatusMessagePanel();
		playerInformationPanel = new PlayerInformationPanel(pieces, board,
				colorChooser, viewPiece, controller);

		pieceColorsPanel = new PieceColorsPanel(pieces, listener);
		automaticMovesPanel = new AutomaticMovesPanel(listener);
		quitRestartPanel = new QuitRestartPanel(listener, viewPiece);
		this.add(statusMessagePanel);
		this.add(playerInformationPanel);
		this.add(pieceColorsPanel);
		this.buildAndAddPlayerModesPanel(pieces, viewPiece, controller);
		this.add(automaticMovesPanel);
		this.add(quitRestartPanel);

	}

	public void updateTable() {
		playerInformationPanel.updateTableInfo();
	}

	public void appendToStatusMessagePanel(String message) {
		this.statusMessagePanel.append(message);
	}

	public void enableBottons(boolean change, Piece viewPiece) {
		automaticMovesPanel.enableRandom(change);
		automaticMovesPanel.enableIntelligent(change);
		quitRestartPanel.enableQuit(change);
		if (viewPiece == null) {
			quitRestartPanel.enableRestart(change);
		}
	}

	private boolean buildPlayerModesPanel(List<Piece> pieces, Piece viewPiece,
			SwingController controller) {
		Piece piecesArray[] = this.piecesListToArrayOfPieces(pieces);
		if (piecesArray.length == 1) {
			return false;
		} else {
			playerModesPanel = new PlayerModesPanel(piecesArray,
					new PlayerModesChangeListener() {

						@Override
						public void SetButtonClicked(Piece piece, String mode) {
							if(controller.getPlayerType(piece) != mode)
								controller.setPlayerType(piece, mode);
						}

					}, viewPiece, controller.getPlayerModesStringArray());
			return true;
		}
	}

	private Piece[] piecesListToArrayOfPieces(List<Piece> pieces) {
		Piece piecesArray[] = new Piece[pieces.size()];
		for (int i = 0; i < pieces.size(); i++) {
			piecesArray[i] = pieces.get(i);
		}
		return piecesArray;
	}

	private void buildAndAddPlayerModesPanel(List<Piece> pieces,
			Piece viewPiece, SwingController controller) {
		if (this.buildPlayerModesPanel(pieces, viewPiece, controller))
			this.add(playerModesPanel);
	}
}
