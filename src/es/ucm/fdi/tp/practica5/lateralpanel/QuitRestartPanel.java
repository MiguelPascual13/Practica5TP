package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
	
	public QuitRestartPanel(QuitButtonListener quitListener, RestartButtonListener restartListener){
		super(new FlowLayout());
		
		quitButton = new JButton(quitButtonText);
		restartButton = new JButton(restartButtonText);
		
		this.add(quitButton);
		this.add(restartButton);
		
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				quitListener.QuitButtonClicked();
			}});
		
		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				restartListener.RestartButtonClicked();
			}});
		
	}
}
