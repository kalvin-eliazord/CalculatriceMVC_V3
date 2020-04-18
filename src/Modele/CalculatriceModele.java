package Modele;

import java.util.concurrent.TimeUnit;

// Le modèle reçoit toute les valeurs et fait tout les calculs
// il n'intéragit pas avec la vue
public class CalculatriceModele {

	// variables qui permettent d'obtenir la valeur de la somme des calculs
	// entrez dans la vue
	private int sommeAddition, sommeSoustraction;

	// méthodes d'addition et de soustraction
	public void addition(int premierNombre, int deuxiemeNombre){
						
		 sommeAddition = premierNombre + deuxiemeNombre;
	}
	
	public void soustraction(int premierNombre, int deuxiemeNombre) {
		
		sommeSoustraction = premierNombre - deuxiemeNombre;
	}
	
	// getter des sommes des résultats 
	
	public int getSommeAddition() {
		
		return sommeAddition;
	}
	
	public int getSommeSoustraction() {
		
		return sommeSoustraction;
	}
	
	public void pauseProgramme(int secondes) {

		try {
			TimeUnit.SECONDS.sleep(secondes);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
