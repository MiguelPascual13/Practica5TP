package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AutomaticMovesPanel extends JPanel {
	public static final String randomButtonText = "Random";
	public static final String intelligentButtonText = "Intelligent";
	private static final String panelNameText = "Automatic Moves";

	private JButton randomButton;
	private JButton intelligentButton;

	public AutomaticMovesPanel() {
		super(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelNameText));
		randomButton = new JButton(randomButtonText);
		intelligentButton = new JButton(intelligentButtonText);
		intelligentButton.setEnabled(false);
		this.add(randomButton);
		this.add(intelligentButton);
		

	}
}
