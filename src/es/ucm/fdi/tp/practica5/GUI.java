package es.ucm.fdi.tp.practica5;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import es.ucm.fdi.tp.practica5.lateralpanel.LateralPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	/**
	 * Generic name for the main frame, it must be completed with the concrete
	 * game description.
	 */
	private static final String jFrameNameText = "Board Games: ";
	
	private LateralPanel lateralPanel;
	private BoardPanel boardPanel;

	public GUI() {
		super(jFrameNameText);
		this.setLayout(new BorderLayout());
		lateralPanel = new LateralPanel();
		boardPanel = new BoardPanel();
		this.add(lateralPanel, BorderLayout.EAST);
		this.add(boardPanel, BorderLayout.CENTER);

		this.setLocation(100, 50);
		this.setResizable(true);
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
