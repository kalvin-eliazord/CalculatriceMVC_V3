package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Modele.CalculatriceBDD;
import Modele.CalculatriceModele;
import Vue.CalculatricePub;
import Vue.CalculatriceVue;
import Vue.SaveEleve;

// le controleur fait le pont 
// entre la vue et le modèle
public class CalculatriceControleur {

	private CalculatriceVue laVue;
	private CalculatriceModele leModele;
	private CalculatriceBDD laBDD;
	private CalculatricePub laPub;
	private SaveEleve leSaveEleve;
	
	private int conteneurQuestions;
	private int score;
	
	public CalculatriceControleur(CalculatriceVue laVue, CalculatriceModele leModele, CalculatricePub laPub, CalculatriceBDD laBDD, SaveEleve leSaveEleve) throws SQLException {
		
		this.laVue = laVue;
		this.leModele = leModele;
		this.laPub = laPub;
		this.laBDD = laBDD;
		this.leSaveEleve = leSaveEleve;
		
		// méthode qui va bloquer le programme pendant 3s pour la publicité 
	    this.leModele.pauseProgramme(3);
				
		// je rend visible la calculatrice et je fais disparaitre la pub 
		this.laVue.setVisible(true);
		this.laPub.setVisible(false);
				
		// Charger le driver jbdc
		this.laBDD.chargerDriver("com.mysql.jdbc.Driver");
				
		// Connexion à la BDD
		this.laBDD.connexionBdd("mysql://localhost/", "calculatricev3", "root", "");
				
		// Creation d'ub statement
		this.laBDD.creerStatement();
		
		// Envoi de la requete select pour filtrer l'eleve au meilleur score
		this.laBDD.executeSelect("SELECT MAX(`score`), nom, prenom from eleves");
		
		// récupère la requête et l'affiche au démarrage de la calculatrice
		this.laVue.affichagePopUp(this.laBDD.recupererResultatsRequete());

		this.laVue.ecouteurBouttonVerification(new ActionBoutton());
		this.leSaveEleve.ecouteurBouttonValider(new ActionBoutValider());
	}
	
	class ActionBoutton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
				
			// déclaration et initialisation des variables qui vont être 
			// utilisés en paramètre de fonction

			int premierNombre = laVue.getPremierNombre();
			int deuxiemeNombre = laVue.getDeuxiemeNombre();  
			
			//condition qui va faire une addition ou une soustraction 
			//en fonction de l'opérateur selectionné
				
			if (laVue.getOperateurs() == "+") { 			
				
				leModele.addition(premierNombre, deuxiemeNombre);
				
				//condition qui va vérifier si le résultat de l'addition  
				// est égal au résultat proposé par l'élève et afficher Bon ou Mauvais
				
				if (leModele.getSommeAddition() == laVue.getResultatPropose() && leModele.getSommeAddition() <= 10){
						
					laVue.setAffichageBonMauvais(" Le résultat choisit est BON! Félicitation!!");		
					conteneurQuestions = conteneurQuestions + 1;
					score = score + 1; // augmentation du score quand la réponse est bonne
					
					if (conteneurQuestions == 7){
						conteneurQuestions = 0; // réinitialisation du conteneur 
						
						laVue.setVisible(false);
						leSaveEleve.setVisible(true);
						leSaveEleve.affichagePopUp(" Tu as répondu à 7 opérations successives! Enregistre ton NOM et PRENOM! ");	
					}
					
				} else if (leModele.getSommeAddition() != laVue.getResultatPropose() && leModele.getSommeAddition() <= 10){
						
						laVue.setAffichageBonMauvais(" Le résultat choisit est MAUVAIS! Ré-essaye!");
						conteneurQuestions = conteneurQuestions + 1;
						
						if (conteneurQuestions == 7){
							conteneurQuestions = 0;	// réinitialisation du conteneur 	
							
							laVue.setVisible(false);
							leSaveEleve.setVisible(true);
							leSaveEleve.affichagePopUp(" Tu as répondu à 7 opérations successives! Enregistre ton NOM et PRENOM!! ");	
						}
					}
				
				} else {			
					
					leModele.soustraction(premierNombre, deuxiemeNombre);
					
					//condition qui va vérifier si le résultat de la soustraction  
					// est égal au résultat proposé par l'élève et afficher Bon ou Mauvais
				
					if (leModele.getSommeSoustraction() == laVue.getResultatPropose() && leModele.getSommeSoustraction() >= 0){
	
							laVue.setAffichageBonMauvais(" Le résultat choisit est BON! Félicitation!!");
							conteneurQuestions = conteneurQuestions + 1;
							score = score + 1; // augmentation du score quand la réponse est bonne
							
							if (conteneurQuestions == 7){	
								conteneurQuestions = 0; // réinitialisation du conteneur 
								
								laVue.setVisible(false);
								leSaveEleve.setVisible(true);
								leSaveEleve.affichagePopUp(" Tu as répondu à 7 opérations successives! Enregistre ton NOM et PRENOM! ");	
							}
							
					} else if (leModele.getSommeSoustraction() != laVue.getResultatPropose() && leModele.getSommeSoustraction() >= 0){
								
							laVue.setAffichageBonMauvais(" Le résultat choisit est MAUVAIS! Ré-essaye!");
							conteneurQuestions = conteneurQuestions + 1;
							
						if (conteneurQuestions == 7){
							conteneurQuestions = 0; 
							
							laVue.setVisible(false);
							leSaveEleve.setVisible(true);
							leSaveEleve.affichagePopUp(" Tu as répondu à 7 opérations successives! Enregistre ton NOM et PRENOM! ");	
						}	
					}
				}
				
			// condition qui va afficher un msg d'erreur si les résultats de l'opération sont > 10 ou < 0
			if (leModele.getSommeAddition() > 10 || leModele.getSommeSoustraction() < -0) {
			
				laVue.affichagePopUp("Le résultat ne doit pas dépasser 10 ou descendre en dessous de 0! Choisis une autre opération!");
				laVue.setAffichageBonMauvais("Choisis une autre opération!!");
				laVue.setAffichageResultatNettoyage();
			}
		}
	}	
	
	// action du bouton valider de l'ihm enregistrement
	class ActionBoutValider implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			laBDD.executeUpdate("INSERT INTO `eleves`(`nom`, `prenom`, `score`) VALUES ('"+ leSaveEleve.getTextNom() +"', '"+ leSaveEleve.getTextPrenom() +"', "+ score +")");
				
			// boite de dialogue qui affichera la pub pendant 5s si l'utilisteur ferme le programme
				int choix = JOptionPane.showConfirmDialog(null,
		               "Vous avez bien été enregistrez! Voulez vous fermer l'application? ",
		               "Cliquez sur YES pour fermer l'application.", JOptionPane.YES_OPTION,
		               JOptionPane.INFORMATION_MESSAGE);
		        if (choix == JOptionPane.YES_OPTION) {
		        	
		        	// si cochez oui la pub se lance et le programme se ferme
		        	laPub.setVisible(true);
		        	leSaveEleve.setVisible(false);
		        	leModele.pauseProgramme(3);
		        	System.exit(0);
		        	
		        } else {
		        	
		        	//sinon le programme reprend
		        	laVue.setVisible(true);
		        	leSaveEleve.setVisible(false);
	        }	
		}
	}
}
