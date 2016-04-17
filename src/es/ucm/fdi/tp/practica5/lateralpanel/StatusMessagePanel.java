package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StatusMessagePanel extends JPanel {
	private static final String panelNameText = "Status Message";

	private JScrollPane scrollPane;
	private JTextArea textArea;

	public StatusMessagePanel() {
		super(new BorderLayout());

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);

		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelNameText));

		this.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * Adds a message at the end of the text area, the messages are supposed to
	 * end with an end line character.
	 * 
	 * @param message
	 *            The message to show.
	 */
	public void append(String message) {
		this.textArea.append(message);
	}
}
