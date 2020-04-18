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
	
	// // Recherche et chargement du driver appropri� � la BDD
	public void chargerDriver(String pilote) {
		
		// Chargement du Driver (pilote)
		try {
			Class.forName(pilote);
			System.out.println("Driver trouv�!!!");
		}
		catch (ClassNotFoundException e) {

			System.out.println("Driver non trouv�!!!");
			e.printStackTrace();
		}
	}
	
	// Etablissement de la connexion � la base de donn�es
	public void connexionBdd(String localisationBdd, String bddName, String user, String password) {
		
		try {
			cnx = DriverManager.getConnection("jdbc:"+localisationBdd+bddName, user, password);
			System.out.println("Connexion � la BDD "+ bddName +" OK!!");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Probl�me Connexion BDD "+ bddName + "  !!");

			e.printStackTrace();
		}
	}
	
	// Creation d'un objet Statement
	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			System.out.println("Probl�me cr�ation statement!!");
			e.printStackTrace();
		}
	}
	
	public void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);
			
		} catch (SQLException e) {
			
			System.out.println("Probleme requete SELECT non execut�e !!");
			e.printStackTrace();
		}
	}
	
	public void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			System.out.println("Requete UPDATE �x�cut�e !!");
		} catch (SQLException e) {
			
			System.out.println("Probl�me requete UPDATE non execut�e !!");
			e.printStackTrace();
		}
	}
	
	public String recupererResultatsRequete() throws SQLException {		
		// Traitement de la reponse
		while (rs.next()) {
			resultString = ("Le meilleur score est: " + rs.getObject(1).toString() + " et il a �t� obtenu par: "+ rs.getObject(2).toString()+" "+ rs.getObject(3).toString()+"!");	
		}
		return resultString;
	}
}