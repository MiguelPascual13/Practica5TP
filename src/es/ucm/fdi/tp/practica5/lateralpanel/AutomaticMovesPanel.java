package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import es.ucm.fdi.tp.practica5.controller.SwingController;

@SuppressWarnings("serial")
public class AutomaticMovesPanel extends JPanel {
	private static final String PANEL_NAME_TEXT = "Automatic Moves";

	private List<JButton> playerModeButtons;

	public interface RandomButtonListener {
		void RandomButtonClicked();
	}

	public interface IntelligentButtonListener {
		void IntelligentButtonClicked();
	}

	public AutomaticMovesPanel(RandomButtonListener randomListener,
			IntelligentButtonListener intelligentListener,
			String playerModesArray[]) {

		/*
		 * Podriamos rizar el rizo pasando una lista de listeners, pero por el
		 * momento no caer� esa breva.
		 */
		super(new FlowLayout());
		this.playerModeButtons = new ArrayList<JButton>();
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), PANEL_NAME_TEXT));
		this.buildButtons(playerModesArray);
		this.addButtons();
		this.addListeners(randomListener, intelligentListener);
	}

	private void buildButtons(String playerModesArray[]) {
		this.playerModeButtons.add(null);
		for (int i = 1; i < playerModesArray.length; i++) {
			if (playerModesArray[i] != null) {
				this.playerModeButtons.add(new JButton(playerModesArray[i]));
			} else {
				this.playerModeButtons.add(null);
			}
		}
	}

	private void addButtons() {
		for (int i = 0; i < this.playerModeButtons.size(); i++) {
			if (this.playerModeButtons.get(i) != null)
				this.add(this.playerModeButtons.get(i));
		}
	}

	private void addListeners(RandomButtonListener randomListener,
			IntelligentButtonListener intelligentListener) {
		if (this.buttonAvailable(SwingController.RANDOM))
			addRandomButtonListener(randomListener);
		if (this.buttonAvailable(SwingController.INTELLIGENT))
			addIntelligentButtonListener(intelligentListener);
	}

	private void addRandomButtonListener(RandomButtonListener listener) {
		this.playerModeButtons.get(SwingController.RANDOM)
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						listener.RandomButtonClicked();
					}

				});
	}

	private void addIntelligentButtonListener(
			IntelligentButtonListener listener) {
		this.playerModeButtons.get(SwingController.INTELLIGENT)
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						listener.IntelligentButtonClicked();
					}

				});
	}

	private boolean buttonAvailable(int index) {
		return index < this.playerModeButtons.size()
				&& this.playerModeButtons.get(index) != null;
	}
	
	/*
	 * public void enableRandom(boolean change) {
	 * randomButton.setEnabled(change); }
	 * 
	 * public void enableIntelligent(boolean change) {
	 * intelligentButton.setEnabled(change); }
	 */
}
