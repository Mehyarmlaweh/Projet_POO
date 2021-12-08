package Caisse;
import Menu.*;

public class Ligne_Commande {
private int Qte_commandee;
private Plat Plat_commandee;

public int getQte_commandee() {
	return Qte_commandee;
}
public void setQte_commandee(int qte_commandee) {
	Qte_commandee = qte_commandee;
}
public Plat getPlat_commandee() {
	return Plat_commandee;
}
public void setPlat_commandee(Plat plat_commandee) {
	Plat_commandee = plat_commandee;
}


public Ligne_Commande(int Qte,Plat pl) {
	this.Qte_commandee=Qte;
	this.Plat_commandee=pl;
}
public String toString() {
	return "Nom Plat : " + this.Plat_commandee.getNom() + "x" + this.Qte_commandee;
}

}
