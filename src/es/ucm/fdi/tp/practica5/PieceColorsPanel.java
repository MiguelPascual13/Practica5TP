package es.ucm.fdi.tp.practica5;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class PieceColorsPanel extends JPanel{
	private static final String chooseColorButtonText = "Choose Color";
	private static final String panelNameText = "Piece Colors";
	
	
	JButton chooseColorButton;
	JComboBox<Piece> playerName;
	
	public PieceColorsPanel(){
		super(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder(panelNameText));
		chooseColorButton = new JButton(chooseColorButtonText);
		playerName = new JComboBox<Piece>(new Vector<Piece>());
		//playerName.addItem(xChar);
		//playerName.addItem(oChar);
		
		this.add(playerName);
		this.add(chooseColorButton);
	}

}
