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

	public PlayerModesPanel(List<Piece> pieces, ListenerSettings listener) {
		super(new FlowLayout());

		Piece pieceArray[] = new Piece[pieces.size()];
		for (int i = 0; i < pieces.size(); i++) {
			pieceArray[i] = pieces.get(i);
		}

		String playerModesArray[] = { MANUAL_TEXT, RANDOM_TEXT, INTELLIGENT_TEXT};

		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), PANEL_NAME_TEXT));
		setButton = new JButton(SET_BUTTON_TEXT);
		playerName = new JComboBox<Piece>(pieceArray);
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
}
