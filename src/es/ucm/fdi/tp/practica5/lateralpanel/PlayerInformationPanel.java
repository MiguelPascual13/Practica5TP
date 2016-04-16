package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PlayerInformationPanel extends JPanel {
	private static final String panelNameText = "Player Information";

	private JScrollPane scrollPane;
	private JTable table;
	
	public PlayerInformationPanel() {
		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelNameText));
		
		/*That should come from somewhere...*/
		
		table = new JTable(2,3);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);

	}
}
