package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import es.ucm.fdi.tp.practica5.listeners.ListenerSettings;

@SuppressWarnings("serial")
public class QuitRestartPanel extends JPanel{
	public static final String quitButtonText = "Quit";
	public static final String restartButtonText = "Restart";
	
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
		
		quitButton = new JButton(quitButtonText);
		restartButton = new JButton(restartButtonText);
		
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
