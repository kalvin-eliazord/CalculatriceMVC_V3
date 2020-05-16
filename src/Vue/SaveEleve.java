package Vue;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveEleve extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelEnregistrement = new JPanel();
	private JLabel labelNom = new JLabel("Ins�rez votre nom: ");
	private JTextField textNom = new JTextField(10);
	private JLabel labelPrenom = new JLabel(" Ins�rez votre pr�nom: ");
	private JTextField textPrenom = new JTextField(10);
	private JButton bouttonValider = new JButton("Valider");

	public SaveEleve() {
		
		// param�trage de l'ihm
		
		this.setTitle("Enregistrement du Nom et Pr�nom");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // plein �cran par d�faut
		panelEnregistrement.setBackground(Color.ORANGE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
		// ajout des objets graphiques au jpanel
				
		this.add(panelEnregistrement);
		panelEnregistrement.add(labelNom);
		panelEnregistrement.add(textNom);
		panelEnregistrement.add(labelPrenom);
		panelEnregistrement.add(textPrenom);
		panelEnregistrement.add(bouttonValider);
	}
	
	public String getTextNom() {
		return textNom.getText();
	}

	public String getTextPrenom() {
		
		return textPrenom.getText();
	}
	
	public void ecouteurBouttonValider (ActionListener ActionBoutValider) {
		bouttonValider.addActionListener(ActionBoutValider);
	}

	public void affichagePopUp(String message){
		JOptionPane.showMessageDialog(this, message);
	}	
}

