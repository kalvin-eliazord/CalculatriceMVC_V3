package Modele;

import java.sql.SQLException;

import Controleur.CalculatriceControleur;
import Vue.CalculatricePub;
import Vue.CalculatriceVue;
import Vue.SaveEleve;

// instanciation des classes MVC
public class MainCalculatrice {
    
    public static void main(String[] args) throws SQLException {
    	
    	CalculatriceBDD laBDD = new CalculatriceBDD();
    	
    	SaveEleve lEnregistrement = new SaveEleve();
    	
    	CalculatricePub laPub = new CalculatricePub();
    	
    	CalculatriceVue laVue = new CalculatriceVue();
        
    	CalculatriceModele leModele = new CalculatriceModele();
        
        CalculatriceControleur leControleur = new CalculatriceControleur(laVue, leModele, laPub, laBDD, lEnregistrement); 
        
    }
}
