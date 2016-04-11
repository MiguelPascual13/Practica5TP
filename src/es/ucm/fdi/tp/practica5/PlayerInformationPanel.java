package es.ucm.fdi.tp.practica5;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PlayerInformationPanel extends JPanel{
	private static final String panelNameText = "Player Information";
	
	JScrollPane scrollPane;
	JTextArea textArea;
		
	public PlayerInformationPanel(){
		super(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder(panelNameText));
		textArea = new JTextArea(10,10);
		textArea.setEditable(true);
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		
		
		
		
	}
}
