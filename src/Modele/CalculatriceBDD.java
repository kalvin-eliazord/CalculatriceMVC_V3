package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CalculatriceBDD {
	
	private Connection cnx;
	private Statement stmt;
	private ResultSet rs;
	private String resultString;
	
	// Methodes
	
	// // Recherche et chargement du driver approprié à la BDD
	public void chargerDriver(String pilote) {
		
		// Chargement du Driver (pilote)
		try {
			Class.forName(pilote);
			System.out.println("Driver trouvé!!!");
		}
		catch (ClassNotFoundException e) {

			System.out.println("Driver non trouvé!!!");
			e.printStackTrace();
		}
	}
	
	// Etablissement de la connexion à la base de données
	public void connexionBdd(String localisationBdd, String bddName, String user, String password) {
		
		try {
			cnx = DriverManager.getConnection("jdbc:"+localisationBdd+bddName, user, password);
			System.out.println("Connexion à la BDD "+ bddName +" OK!!");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problème Connexion BDD "+ bddName + "  !!");

			e.printStackTrace();
		}
	}
	
	// Creation d'un objet Statement
	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			System.out.println("Problème création statement!!");
			e.printStackTrace();
		}
	}
	
	public void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);
			
		} catch (SQLException e) {
			
			System.out.println("Probleme requete SELECT non executée !!");
			e.printStackTrace();
		}
	}
	
	public void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			System.out.println("Requete UPDATE éxécutée !!");
		} catch (SQLException e) {
			
			System.out.println("Problème requete UPDATE non executée !!");
			e.printStackTrace();
		}
	}
	
	public String recupererResultatsRequete() throws SQLException {		
		// Traitement de la reponse
		while (rs.next()) {
			resultString = ("Le meilleur score est: " + rs.getObject(1).toString() + " et il a été obtenu par: "+ rs.getObject(2).toString()+" "+ rs.getObject(3).toString()+"!");	
		}
		return resultString;
	}
}