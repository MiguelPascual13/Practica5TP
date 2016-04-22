package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import es.ucm.fdi.tp.practica5.listeners.ListenerSettings;

@SuppressWarnings("serial")
public class AutomaticMovesPanel extends JPanel {
	public static final String RANDOM_BUTTON_TEXT = "Random";
	public static final String INTELLIGENT_BUTTON_TEXT = "Intelligent";
	private static final String PANEL_NAME_TEXT = "Automatic Moves";

	private JButton randomButton;
	private JButton intelligentButton;
	
	public interface RandomButtonListener {
		void RandomButtonClicked();
	}
	public interface IntelligentButtonListener{
		void IntelligentButtonClicked();
	}

	public AutomaticMovesPanel(ListenerSettings listener) {
		super(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), PANEL_NAME_TEXT));
		randomButton = new JButton(RANDOM_BUTTON_TEXT);
		intelligentButton = new JButton(INTELLIGENT_BUTTON_TEXT);
		this.add(randomButton);
		this.add(intelligentButton);
		
		randomButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				listener.RandomButtonClicked();
			}});
		
		intelligentButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				listener.IntelligentButtonClicked();
			}});
	}
	public void enableRandom(boolean change) {
		randomButton.setEnabled(change);
	}

	public void enableIntelligent(boolean change) {
		intelligentButton.setEnabled(change);
	} 
}
