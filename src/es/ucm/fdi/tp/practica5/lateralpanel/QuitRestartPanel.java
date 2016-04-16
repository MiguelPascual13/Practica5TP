package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class QuitRestartPanel extends JPanel{
	public static final String quitButtonText = "Quit";
	public static final String restartButtonText = "Restart";
	
	private JButton quitButton;
	private JButton restartButton;
	
	public QuitRestartPanel(){
		super(new FlowLayout());
		
		quitButton = new JButton(quitButtonText);
		restartButton = new JButton(restartButtonText);
		
		this.add(quitButton);
		this.add(restartButton);
		
	}
}
