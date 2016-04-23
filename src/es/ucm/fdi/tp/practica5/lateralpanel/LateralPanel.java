package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.controller.SwingController;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.IntelligentButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.AutomaticMovesPanel.RandomButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.PieceColorsPanel.ColorChangeListener;
import es.ucm.fdi.tp.practica5.lateralpanel.PlayerModesPanel.PlayerModesChangeListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.QuitButtonListener;
import es.ucm.fdi.tp.practica5.lateralpanel.QuitRestartPanel.RestartButtonListener;
import es.ucm.fdi.tp.practica5.utils.PieceColorMap;

@SuppressWarnings("serial")
public class LateralPanel extends JPanel {

	private PlayerModesPanel playerModesPanel;
	private PieceColorsPanel pieceColorsPanel;
	private AutomaticMovesPanel automaticMovesPanel;
	private QuitRestartPanel quitRestartPanel;
	private StatusMessagePanel statusMessagePanel;
	private PlayerInformationPanel playerInformationPanel;
	private Piece piecesArray[];

	public LateralPanel(List<Piece> pieces, PieceColorMap colorChooser,
			Board board, Piece viewPiece, SwingController controller,
			Piece turn, Player randomPlayer, Player aiPlayer,
			QuitButtonListener quitButtonListener,
			RestartButtonListener restartButtonListener) {
		super(new GridLayout(0, 1));

		this.piecesArray = this.piecesListToArrayOfPieces(pieces);

		statusMessagePanel = new StatusMessagePanel();
		playerInformationPanel = new PlayerInformationPanel(pieces, board,
				colorChooser, viewPiece, controller);
		this.buildPieceColorPanel(pieces, colorChooser);
		this.buildQuitRestartPanel(viewPiece, quitButtonListener,
				restartButtonListener);

		this.add(statusMessagePanel);
		this.add(playerInformationPanel);
		this.add(pieceColorsPanel);
		this.buildAndAddPlayerModesPanel(piecesArray, viewPiece, controller);
		this.buildAndAddAutomaticMovesPanel(controller, randomPlayer, aiPlayer);
		this.add(quitRestartPanel);

	}

	public void updateTable() {
		playerInformationPanel.updateTableInfo();
	}

	public void appendToStatusMessagePanel(String message) {
		this.statusMessagePanel.append(message);
	}

	private boolean buildPlayerModesPanel(Piece pieces[], Piece viewPiece,
			SwingController controller) {
		if (controller.getAvailablePlayerModes() == 1) {
			return false;
		} else {
			playerModesPanel = new PlayerModesPanel(pieces,
					new PlayerModesChangeListener() {

						@Override
						public void SetButtonClicked(Piece piece, String mode) {
							if (controller.getPlayerType(piece) != mode)
								controller.setPlayerType(piece, mode);
							updateTable();
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

	private void buildAndAddPlayerModesPanel(Piece pieces[], Piece viewPiece,
			SwingController controller) {
		if (this.buildPlayerModesPanel(pieces, viewPiece, controller))
			this.add(playerModesPanel);
	}

	private void buildPieceColorPanel(List<Piece> pieces,
			PieceColorMap colorChooser) {
		Piece piecesArray[] = this.piecesListToArrayOfPieces(pieces);
		pieceColorsPanel = new PieceColorsPanel(piecesArray,
				new ColorChangeListener() {

					@Override
					public void colorChanged(Piece piece, Color color) {
						colorChooser.setColorFor(piece, color);
					}

				});
	}

	private boolean buildAutomaticMovesPanel(SwingController controller,
			Player randomPlayer, Player aiPlayer) {
		if (controller.getAvailablePlayerModes() == 1) {
			return false;
		} else {
			RandomButtonListener randomListener = this
					.getRandomButtonListener(controller, randomPlayer);
			IntelligentButtonListener intelligentListener = this
					.getIntelligentButtonListener(controller, aiPlayer);
			automaticMovesPanel = new AutomaticMovesPanel(randomListener,
					intelligentListener,
					controller.getPlayerModesStringArray());
			return true;
		}
	}

	private void buildAndAddAutomaticMovesPanel(SwingController controller,
			Player randomPlayer, Player aiPlayer) {
		if (this.buildAutomaticMovesPanel(controller, randomPlayer, aiPlayer))
			this.add(automaticMovesPanel);
	}

	private RandomButtonListener getRandomButtonListener(
			SwingController controller, Player randomPlayer) {
		return new RandomButtonListener() {

			@Override
			public void RandomButtonClicked() {
				controller.makeMove(randomPlayer);
			}

		};
	}

	private IntelligentButtonListener getIntelligentButtonListener(
			SwingController controller, Player aiPlayer) {
		return new IntelligentButtonListener() {

			@Override
			public void IntelligentButtonClicked() {
				controller.makeMove(aiPlayer);
			}

		};
	}

	private void buildQuitRestartPanel(Piece viewPiece,
			QuitButtonListener quitButtonListener,
			RestartButtonListener restartButtonListener) {
		quitRestartPanel = new QuitRestartPanel(quitButtonListener,
				restartButtonListener, viewPiece);
	}
}
