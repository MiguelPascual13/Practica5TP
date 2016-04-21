package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import es.ucm.fdi.tp.practica5.listeners.ListenerSettings;

@SuppressWarnings("serial")
public class QuitRestartPanel extends JPanel{
	public static final String QUIT_BUTTON_TEXT = "Quit";
	public static final String RESTART_BUTTON_TEXT = "Restart";
	
	private JButton quitButton;
	private JButton restartButton;
	
	public interface QuitButtonListener {
		void QuitButtonClicked();
	}
	public interface RestartButtonListener{
		void RestartButtonClicked();
	}
	
	public QuitRestartPanel(ListenerSettings listener){
		super(new FlowLayout());
		
		quitButton = new JButton(QUIT_BUTTON_TEXT);
		restartButton = new JButton(RESTART_BUTTON_TEXT);
		
		this.add(quitButton);
		this.add(restartButton);
		
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				listener.QuitButtonClicked();
			}});
		
		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				listener.RestartButtonClicked();
			}});
		
	}
}
