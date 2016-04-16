package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class PlayerModesPanel extends JPanel {
	private static final String setButtonText = "Set";
	private static final String panelNameText = "Player Modes";
	private static final String manualText = "Manual";
	private static final String randomText = "Random";
	private static final String intelligentText = "Intelligent";

	private JButton setButton;
	private JComboBox<Piece> playerName;
	private JComboBox<String> playerGameModes;

	public PlayerModesPanel() {
		super(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelNameText));
		setButton = new JButton(setButtonText);
		playerName = new JComboBox<Piece>(new Vector<Piece>());
		// playerName.addItem();
		// playerName.addItem();
		playerGameModes = new JComboBox<String>(new Vector<String>());
		playerGameModes.addItem(manualText);
		playerGameModes.addItem(randomText);
		playerGameModes.addItem(intelligentText);

		this.add(playerName);
		this.add(playerGameModes);
		this.add(setButton);

	}

}
