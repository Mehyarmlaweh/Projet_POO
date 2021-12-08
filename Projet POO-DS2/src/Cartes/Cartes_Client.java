package Cartes;

import java.util.ArrayList;

public class Cartes_Client {
ArrayList <Carte_Client>  cartes ;


public Cartes_Client() {
	cartes=new ArrayList<Carte_Client>();
}


public Carte_Client Recherche_carte(String num) {
	for(int i = 0 ; i<cartes.size(); i++)
		if (cartes.get(i).getNumero_carte().equalsIgnoreCase(num))
			return cartes.get(i);
		return null;
		
	
}

}