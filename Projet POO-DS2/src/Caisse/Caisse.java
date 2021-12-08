package Caisse;

import java.util.ArrayList;
import java.util.Scanner;

import Menu.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Caisse {
private ArrayList <Commande> cmds;

 //AJOUT COMMANDE 
public  void Ajout_commande() {
	cmds.add(new Commande());
}

//Affichage details commande 
public void affichage_details_cmd (int code) {
	for(int i = 0 ; i < cmds.size();i++) {
		if ( cmds.get(i).getCode_inc() == code)
			cmds.get(i).toString();
}
}

//cloturer COMMANDE 
public void cloturer_commande(int code) {
	
	for(int i = 0 ; i < cmds.size();i++) {
		if ( cmds.get(i).getCode_inc() == code)
			cmds.get(i).setEtat_cmd(false);
	}
}


//Afficher recette journaliere 
public void recette_journaliere() {
	double recette = 0;
	for (int i = 0; i<cmds.size();i++) {
		if (cmds.get(i).getDate_cmd().isEqual(LocalDate.now()))
			recette =+ cmds.get(i).montant_total();
	}
}


//Afficher CA + recette durant periode

public void recette_journaliere_periode() {
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	Scanner sc= new Scanner(System.in);
	System.out.println("Donner la date de de debut :(dd-MM-yyyy) ");
	String date_deb = sc.next();
	 LocalDate dte_deb = LocalDate.parse(date_deb, df);  
	
	System.out.println("Donner la date de de fin : (dd-MM-yyyy)");
	String date_fin = sc.next();
	 LocalDate dte_fin = LocalDate.parse(date_fin, df);  
	 
	 double chiffre_a = 0;
	 double recette = 0;
		for (int i = 0; i<cmds.size();i++) {
			if (cmds.get(i).getDate_cmd().isAfter(dte_deb) && cmds.get(i).getDate_cmd().isBefore(dte_fin))
				recette =+ cmds.get(i).montant_total();
			System.out.println("\nRecette Journalière : " + recette);//affiche recette de chaque jour 
			chiffre_a =+ recette;//et stocke recette dans une autre variable pour calculer le Ca
		}
		System.out.println("Chiffre d'affaire de cette periode est : " + chiffre_a);

}


public void plat_plus_commande() {
	Menu menu = new Menu();
	int max1 = 0;
	String ref_max1 = "";//celui de entree
	
	int max2 = 0;
	String ref_max2 = "";//suite
	
	int max3 = 0;
	String ref_max3 = "";//dessert
	
for (int i = 0 ; i<cmds.size();i++) {
	for(int j = 0 ; j<cmds.get(i).getL_Cmds().size();j++)
		//Afficher le plat le plus commande dans la categories entree
	if ( cmds.get(i).getL_Cmds().get(j).getPlat_commandee() instanceof Entree)
	{
		if (cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getQte_commandee_total() > max1 ) 
			max1 = cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getQte_commandee_total();
		ref_max1 = cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getRef();
	}
	//Afficher le plat le plus commande dans la categories suite

	else if ( cmds.get(i).getL_Cmds().get(j).getPlat_commandee() instanceof Suite)
	{
		if (cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getQte_commandee_total() > max2 ) 
			max2 = cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getQte_commandee_total();
		ref_max2 = cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getRef();
	}
	
	//Afficher le plat le plus commande dans la categories dessert

	else if ( cmds.get(i).getL_Cmds().get(j).getPlat_commandee() instanceof Dessert)
	{
		if (cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getQte_commandee_total() > max3 ) 
			max3= cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getQte_commandee_total();
		ref_max3 = cmds.get(i).getL_Cmds().get(j).getPlat_commandee().getRef();
	}

}

System.out.println("L'ENTREE la plus commandée est : "+ menu.Recherche_plat(ref_max1).getNom());
System.out.println("La SUITE la plus commandée est : "+ menu.Recherche_plat(ref_max2).getNom());
System.out.println("Le Dessert le plus commandé est : "+ menu.Recherche_plat(ref_max3).getNom());

//AFFICHAGE PLAT PLUS COMMANDE ENTRE LES 3 CATEGORIES  
if( max1 >= max2 && max1 >= max3)
	System.out.println("Le PLAT le plus commandé est : "+ menu.Recherche_plat(ref_max1).getNom());
else if( max2 >= max1 && max2 >= max3)
	System.out.println("Le PLAT le plus commandé est : "+ menu.Recherche_plat(ref_max1).getNom());
else if( max3 >= max2 && max3 >= max1)
	System.out.println("Le PLAT le plus commandé est : "+ menu.Recherche_plat(ref_max1).getNom());	
}




}