package Menu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
public class Menu {
private ArrayList<Plat> menu;

public Menu() {
	
	menu=new ArrayList<Plat>(); // Liste des plats disponible dans le restaurant
	
}

//Recherche Plat par reference 
public Plat Recherche_plat(String ref) {
for (int i=0;i<menu.size();i++) {
	if (menu.get(i).getRef().equals(ref))
		return menu.get(i);
}
return null;	
}


//Ajouter un plat au menu du restaurant 
public void Ajout_plat(String ref) {
	Scanner sc  = new Scanner(System.in);
	String type;
	do {
	System.out.println("Quel type du plat :\nEntree\tSuite\tDessert\n");
	type=sc.next();
	}while (!type.equals("Dessert")&& !type.equals("Entree")&&!type.equals("Suite"));
	
	if (type.equals("Dessert"))
		menu.add(new Dessert(ref));
	
	if (type.equals("Entree"))
		menu.add(new Entree(ref));
	
	if (type.equals("Suite"))
		menu.add(new Suite(ref));
	
	
}

//Modifier details plats
public void Modifier_Plat(String ref) {
	Scanner sc = new Scanner(System.in);
	int choix;
	Recherche_plat(ref).toString();
	
	System.out.println("1.Nom\n2.Prix");
	do {
	System.out.println("Quel donnée de ce plat, voudrez vous modifier ? ");
	choix=sc.nextInt();
	}while (choix!=1 && choix!=2);
	
	if (choix==1)
	{
		System.out.println("Donner le nouveau nom du Plat\n");
		Recherche_plat(ref).setNom(sc.next());
	}
	else {
	System.out.println("Donner le nouveau prix du Plat\n");
	Recherche_plat(ref).setPrix(sc.nextDouble()); 
	}

}

public  void Affichage_plats() {
	Scanner sc = new Scanner(System.in);
	String choix;
	do {
	System.out.println("Donner quel Type de plats voudrez consulter:\nEntree\\tSuite\\tDessert\\n  ");
	choix=sc.next();
	}while (!choix.equals("Dessert")&& !choix.equals("Entree")&&!choix.equals("Suite"));
	ArrayList <String> refs =new ArrayList<String>();
	for(int i=0;i<menu.size();i++) {
	if (choix.equals("Entree") && menu.get(i) instanceof Entree);
	{ArrayList <Plat> pl = new ArrayList<Plat>();
	 refs =  ordonner(pl);
	}
	if (choix.equals("Suite") && menu.get(i) instanceof Suite);
	{ArrayList <Plat> pl = new ArrayList<Plat>();
	 refs =  ordonner(pl);
	}
	if (choix.equals("Dessert") && menu.get(i) instanceof Entree);
	{ArrayList <Plat> pl = new ArrayList<Plat>();
	 refs =  ordonner(pl);
	}
	
	}
	//On stocker les refs dun type bine defini selon choix du user ( dessert suite entre ) dans une liste 
	//puis on a trier la liste des refs 
	// puis on afficher les plat selon la liste des refs ordonnes en utilisant methode recherche 
	for(int i=0;i<menu.size();i++) {
	 System.out.println(Recherche_plat(refs.get(i)).toString());
	}
	
	
		          	
}

public ArrayList<String> ordonner (ArrayList<Plat> pl){
	ArrayList<String> references=new ArrayList<String>();
	for(int i = 0 ; i<pl.size();i++) {
 references.add(pl.get(i).getRef());
	}
	Collections.sort(references);
	return references;
}
}