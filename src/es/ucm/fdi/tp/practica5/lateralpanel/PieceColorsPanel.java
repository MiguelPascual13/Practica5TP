package es.ucm.fdi.tp.practica5.lateralpanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.BoardPanel;
import es.ucm.fdi.tp.practica5.PieceColorMap;

@SuppressWarnings("serial")
public class PieceColorsPanel extends JPanel {
	private static final String chooseColorButtonText = "Choose Color";
	private static final String panelNameText = "Piece Colors";

	private JButton chooseColorButton;
	private JComboBox<Piece> playerName;

	public PieceColorsPanel(List<Piece> pieces, PieceColorMap colorChooser, BoardPanel b) {
		super(new FlowLayout());

		Piece pieceArray[] = new Piece[pieces.size()];
		for (int i = 0; i < pieces.size(); i++) {
			pieceArray[i] = pieces.get(i);
		}

		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), panelNameText));
		chooseColorButton = new JButton(chooseColorButtonText);
		playerName = new JComboBox<Piece>(pieceArray);
		this.add(playerName);
		this.add(chooseColorButton);
		chooseColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(getParent(),
						"Elige el color al que deseas cambiar", Color.BLUE);
				colorChooser.setColorFor(
						pieces.get(playerName.getSelectedIndex()), c);
				b.update();
			}
		});
	}

}
