package Menu;
import java.util.Scanner;

public class Plat {
    protected String ref;
    protected String nom;
    protected double prix;
    protected int qte_commandee_total; // quantite de ce plat commande dune façon generale
    
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQte_commandee_total() {
		return qte_commandee_total;
	}
	public void setQte_commandee_total(int qte_commandee) {
		this.qte_commandee_total = qte_commandee;
	}
    
	public Plat (String ref) {
		Scanner sc  = new Scanner(System.in);
		
		this.ref=ref;
		
		System.out.println("Donner le nom du plat : ");
		this.nom=sc.next();
		
		System.out.println("Donner le prix du plat : ");
		this.prix=sc.nextDouble();
		
        this.qte_commandee_total=0;
		
		
		
	}
	@Override
	public String toString() {
		return "Plat [\nref = " + ref + "\nnom = " + nom + "\nprix = " + prix + "\nqte_commandee = " + qte_commandee_total + "\n";
	}
    

}
