package es.ucm.fdi.tp.practica5;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StatusMessagePanel extends JPanel{
	private static final String panelNameText = "Status Message";
	
	JScrollPane scrollPane;
	JTextArea textArea;
	
	public StatusMessagePanel(){
		super(new FlowLayout());
	
		this.setBorder(BorderFactory.createTitledBorder(panelNameText));
		textArea = new JTextArea(5,5);
		textArea.setEditable(true);
		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
	}
	
}
