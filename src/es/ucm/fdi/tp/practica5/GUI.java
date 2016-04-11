package es.ucm.fdi.tp.practica5;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private static final String jFrameNameText = "Board Games";
	private LateralPanel lateralPanel;
 
    public GUI(){
 
        super(jFrameNameText);
        this.setLayout(new BorderLayout());        
        lateralPanel = new LateralPanel();
        this.add(lateralPanel, BorderLayout.EAST);
        
        this.setLocation(100, 50);
        this.setResizable(true);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
