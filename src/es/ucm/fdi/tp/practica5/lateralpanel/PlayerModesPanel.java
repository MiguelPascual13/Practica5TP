package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.listeners.ListenerSettings;

/*NO ME GUSTA EL USO DE VIEWPIECE Y PROBABLEMENTE HAYA QUE CAMBIARLO
 * TAMBIÉN HAY PROBLEMAS CON LOS PUTOS PLAYERS (NO ME GUSTAN)*/

@SuppressWarnings("serial")
public class PlayerModesPanel extends JPanel {
	private static final String SET_BUTTON_TEXT = "Set";
	private static final String PANEL_NAME_TEXT = "Player Modes";
	public static final String MANUAL_TEXT = "Manual";
	public static final String RANDOM_TEXT = "Random";
	private static final String INTELLIGENT_TEXT = "Intelligent";

	private JButton setButton;
	private JComboBox<Piece> playerName;
	private JComboBox<String> playerGameModes;

	public interface SetButtonListener {
		void SetButtonClicked(Piece piece, String mode);
	}
	
	/*Esta ventana debe conocer qué playermodes hay disponibles.*/
	
	public PlayerModesPanel(List<Piece> pieces, ListenerSettings listener, Piece viewPiece) {
		super(new FlowLayout());
		
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), PANEL_NAME_TEXT));
		this.fillJComboBox(pieces, viewPiece);
		
		String playerModesArray[] = { MANUAL_TEXT, RANDOM_TEXT, INTELLIGENT_TEXT };
		
		setButton = new JButton(SET_BUTTON_TEXT);
		playerGameModes = new JComboBox<String>(playerModesArray);
		this.add(playerName);
		this.add(playerGameModes);
		this.add(setButton);
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.SetButtonClicked(pieces.get(playerName.getSelectedIndex()),
						playerModesArray[playerGameModes.getSelectedIndex()]);
			}
		});

	}

	private void fillJComboBox(List<Piece> pieces, Piece viewPiece) {
		if (viewPiece == null) {
			Piece pieceArray[] = new Piece[pieces.size()];
			for (int i = 0; i < pieces.size(); i++) {
				pieceArray[i] = pieces.get(i);
			}
			playerName = new JComboBox<Piece>(pieceArray);
		} else {
			playerName = new JComboBox<Piece>();
			playerName.addItem(viewPiece);
		}
	}
}
