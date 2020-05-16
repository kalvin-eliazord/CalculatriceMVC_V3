package Vue;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;

//la seule fonctionnalité de la vue est d'afficher 
//ce que l'utilisateur va voir, aucun calculs ne sera fait
public class CalculatriceVue extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> premierNombre  = new JComboBox<Integer>();
	private JComboBox<String> operateurs = new JComboBox<String>();
	private JComboBox<Integer> deuxiemeNombre = new JComboBox<Integer>();
	private JLabel labelEgal = new JLabel(" = ");
	private JComboBox<Integer> resultatPropose = new JComboBox<Integer>();
	private JButton bouttonVerification = new JButton("Vérifier le Résultat");
	private JLabel affichageBonMauvais = new JLabel(" ");
	private JLabel affichageResultat = new JLabel();
	private JPanel fenetreCalcul = new JPanel();

	//constructeur 
	public CalculatriceVue(){

		// ihm paramètres
		fenetreCalcul.setBackground(Color.red);
		this.setTitle("EditCALC V3 - par Kalvin ELIAZORD");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//fenetre centrer
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // plein écran par défaut 

		// Affectation des valeurs des listes de JCombobox
		Integer[] listeNombre = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String[] listeOperateur = new String[] {"+", "-"};

		// affectation des JCombobox à une liste 
		// pour pouvoir l'ajouter à la fenetreCalcul 
		premierNombre = new JComboBox<Integer>(listeNombre);
		operateurs = new JComboBox<String>(listeOperateur);
		deuxiemeNombre = new JComboBox<Integer>(listeNombre);
		resultatPropose = new JComboBox<Integer>(listeNombre);

		// ajout des objets graphiques de l'ihm calculatrice dans le conteneur
		fenetreCalcul.add(premierNombre);
		fenetreCalcul.add(operateurs);
		fenetreCalcul.add(deuxiemeNombre);
		fenetreCalcul.add(labelEgal);
		fenetreCalcul.add(resultatPropose);
		fenetreCalcul.add(bouttonVerification);
		fenetreCalcul.add(affichageBonMauvais);
		fenetreCalcul.add(affichageResultat);

		this.add(fenetreCalcul);
	}

	// les getters
	public int getPremierNombre(){ 
		return (int) premierNombre.getSelectedItem();
	}

	public int getDeuxiemeNombre(){
		return (int) deuxiemeNombre.getSelectedItem();
	}

	public String getStringOperateurs() {
		return (String) operateurs.getSelectedItem();
	}

	public int getResultatPropose() {
		return (int) resultatPropose.getSelectedItem();
	}

	// affiche le résultat du calcul dans le label Resultat
	public void setAffichageBonMauvais(String bonOuMauvais){
		affichageBonMauvais.setText(bonOuMauvais);
	}

	public void setAffichageResultat(int resultatOperation){
		affichageResultat.setText(Integer.toString(resultatOperation));	
	}

	public void setAffichageResultatNettoyage(){
		affichageResultat.setText(" ");	
	}

	// méthode qui ajoute une action de type actionlistener 
	public void ecouteurBtnVerification(ActionListener event) {
		bouttonVerification.addActionListener(event);
	}
	
	public void ecouteurCombo(ActionListener eventCombo) {
		operateurs.addActionListener(eventCombo);
		premierNombre.addActionListener(eventCombo);
	}

	// Ouvre une fenêtre qui va afficher un msg à l'écran
	public void affichagePopUp(String message){
		JOptionPane.showMessageDialog(this, message);
	}

	public void affichageEleve(Object recupererResultatsRequete) {
		JOptionPane.showMessageDialog(this, recupererResultatsRequete.toString());
	}

	public JComboBox<Integer> getComboUn() {
		return premierNombre;
	}

	public JComboBox<String> getOperateurs() {
		return operateurs;
	}

	public JComboBox<Integer> getComboDeux() {
		return deuxiemeNombre;
	}	
}
