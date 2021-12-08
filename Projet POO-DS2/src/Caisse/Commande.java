package Caisse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import Menu.*;
import Cartes.*;



public class Commande {
	
        private int code_inc=0;
        private int num_table;
        private LocalDate Date_cmd;
        private LocalTime Heure_cmd;
        private ArrayList <Ligne_Commande>l_Cmds;
		private double montant_total;
        private String mode_paiment;
        private boolean etat_cmd=false;
        
		public int getCode_inc() {
			return code_inc;
		}
		public void setCode_inc(int code_inc) {
			this.code_inc = code_inc;
		}
		public int getNum_table() {
			return num_table;
		}
		public void setNum_table(int num_table) {
			this.num_table = num_table;
		}
		public LocalDate getDate_cmd() {
			return Date_cmd;
		}
		public void setDate_cmd(LocalDate date_cmd) {
			Date_cmd = date_cmd;
		}
		public LocalTime getHeure_cmd() {
			return Heure_cmd;
		}
		public void setHeure_cmd(LocalTime heure_cmd) {
			Heure_cmd = heure_cmd;
		}

	    public ArrayList<Ligne_Commande> getL_Cmds() {
				return l_Cmds;
			}
			public void setL_Cmds(ArrayList<Ligne_Commande> l_Cmds) {
				this.l_Cmds = l_Cmds;
			}
			
		public double getMontant_total() {
			return montant_total;
		}
		public void setMontant_total(double montant_total) {
			this.montant_total = montant_total;
		}
		public String getMode_paiment() {
			return mode_paiment;
		}
		public void setMode_paiment(String mode_paiment) {
			this.mode_paiment = mode_paiment;
		}
		public boolean getEtat_cmd() {
			return etat_cmd;
		}
		public void setEtat_cmd(boolean etat_cmd) {
			this.etat_cmd = etat_cmd;
		}

		
		// ajout commande dans la classe caisse 
		public Commande() { 
			Menu menu = new Menu();

			Scanner sc  = new Scanner (System.in);
			int choix;
			String ref;
			this.Date_cmd = LocalDate.now();
			this.Heure_cmd = LocalTime.now();
		System.out.println("Donner le numero de table de la commande Numero : "+this.code_inc); // Le serveur precise le numero table des clients concernés tout en respectant les nums tables existant 
		this.num_table=sc.nextInt();
		
		System.out.println("Veuillez entrer les lignes commandes de la table Numero : "+this.num_table);
		System.out.println("Entrer le nombre de lignes commandes à passer dans cette commande : ");
	

		do {
			do {
				System.out.println("Donner la reference du plat: ");
				 ref= sc.next();
			}while(menu.Recherche_plat(ref)!=null);
			
			
			System.out.println("Donner la quantite que vous desirez commande du plat"+menu.Recherche_plat(ref).getNom());
			int qte = sc.nextInt();
			l_Cmds.add(new Ligne_Commande(qte,menu.Recherche_plat(ref)));
			menu.Recherche_plat(ref).setQte_commandee_total(menu.Recherche_plat(ref).getQte_commandee_total()+qte); // on incremente la quantite total commande de ce plat
			
			//le principe est de calcul le nombre de fois a ete commande le plat dun ref bien precis pour faciliter la recherche du plat
			//le plus commande 
		menu.Recherche_plat(ref).setQte_commandee_total(menu.Recherche_plat(ref).getQte_commandee_total()+1); 
			
		do{System.out.println("Veuillez vous ajouter une autre ligne Commande(Choisir 1 ou 2 )?\1.Oui\n2.Non");
		choix=sc.nextInt();}while(choix!=2 && choix!=1);
		
		}while(choix!=2);
		
		this.montant_total=montant_total() ;// on va le calculer dans la methode 
		int md;
		do{System.out.println("Donner le mode de paiment : \n1-espèce\n2-chèque\n3-Carte");
		md=sc.nextInt();
		}while(md!=1 && md!=2 && md!=3);
		switch (md) {
		case 1 : {this.mode_paiment="espece"; break;}
		case 2 : { this.mode_paiment="cheque"; break;}
		default : this.mode_paiment="carte";
		}
	
		this.etat_cmd = true;

		code_inc ++;
	
		}
		
		
		public double montant_total() {
			Scanner sc  = new Scanner(System.in);
			Cartes_Client cr = new Cartes_Client();

			double Sum=0;
			int choix =0;
			int choix2=0;
			String num ;
			double Sum_apres_reduction=0;

			for(int i = 0 ; i<l_Cmds.size();i++) {
			
			Sum =+   this.l_Cmds.get(i).getQte_commandee()*this.l_Cmds.get(i).getPlat_commandee().getPrix();
			  }
		do {	System.out.println("Avez_vous une carte Client ?\n1.Oui\n2.Non");
			 choix = sc.nextInt();
		} while(choix!=1 && choix !=2);
		if (choix == 1 ) {
System.out.println("Donner votre numero Carte : ");
		num=sc.next();
	if(2000>cr.Recherche_carte(num).getPoints()&& cr.Recherche_carte(num).getPoints()>=1000)
	{
		do{ System.out.println("Voulez vous se profiter d'une reduction (10%) ?\n1.Oui\n2.Non ");
		 choix = sc.nextInt();
			} while(choix!=1 && choix !=2);
		if (choix == 1)
		{
			 cr.Recherche_carte(num).setPoints(0);
			return Sum * 0.9;//S IL VEUT SE PROFITER DUNE REDUCTION ON INITILISE SES POINTS à 0 + on le donne reduction 10%
	
		}
		else  {
			 cr.Recherche_carte(num).setPoints(cr.Recherche_carte(num).getPoints() + Integer.valueOf((int) (Sum*0.1))) ;
			//S IL NE VEUT PAS se PROFITER DUNE REDUCTION ON AJOUTE a SON SOLDE DES POINTS MONTANT TOTAL / 10 
			 return Sum;
		}
		
	}
	else if (2000<=cr.Recherche_carte(num).getPoints()) {
		do{ System.out.println("Voulez vous se profiter d'une reduction (10%) ?\n1.Oui\n2.Non ");
		 choix = sc.nextInt();
			} while(choix!=1 && choix !=2);
		if (choix == 1)
		{
			 cr.Recherche_carte(num).setPoints(0);
			return Sum * 0.8;//S IL VEUT SE PROFITER DUNE REDUCTION ON INITILISE SES POINTS à 0 + on le donne reduction 20%
	
		}
		else  {
			 cr.Recherche_carte(num).setPoints(cr.Recherche_carte(num).getPoints() + Integer.valueOf((int) (Sum*0.2))) ;
			//S IL NE VEUT PAS se PROFITER DUNE REDUCTION ON AJOUTE a SON SOLDE DES POINTS MONTANT TOTAL / 20 
			 return Sum;
		}
	}
	else {//<1000 càad le num point
		cr.Recherche_carte(num).setPoints(cr.Recherche_carte(num).getPoints() + Integer.valueOf((int) (Sum*0.1))) ;
		 return Sum;
	}
		}
		else { //else sil n est pas de carte 
			return Sum;
			
		}
		
		
		
		}
		
		
		public String affichage_lignes_cmd() {//methode pour stocker les lignes commandes dune chaine pour faciliter son affichage dans toString commande
			String res="" ;
			for(int i = 0 ; i<l_Cmds.size();i++) {
				
				res= res + l_Cmds.get(i).toString() + "\n" ;
				  }
			return res;
		}
		
		@Override
		public String toString() {
			return "Commande [code_inc=" + code_inc + "\nnum_table=" + num_table + "\nDate_cmd=" + Date_cmd
					+ "\nHeure_cmd=" + Heure_cmd + "\nLignes Commandes = " + affichage_lignes_cmd() + "\nmontant_total = " + montant_total
					+ "\nmode_paiment = " + mode_paiment + "\netat_cmd = " + etat_cmd + "]";
		}
		
	
		
	
		
		
}
