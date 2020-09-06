/*Cr�ation de la classe ObjetZork */

public class ObjetZork {

	/*Un objet a un poids, un nom et un boolean qui nous permet de savoir s'il est 
	 * transportable ou pas.*/
	private int poids;
	private String nom;
	private boolean transportable;

	/* Cr�ation du constructeur avec en param�tre les trois champs d'un objet*/
	public ObjetZork(int poids, String nom, boolean transportable) {
		this.poids = poids;
		this.nom = nom;
		this.transportable = transportable;
	}

	/*Cr�ation du constructeur avec un nom donn� qui initialise imm�diatement le poids � 0 et
	 * l'objet n'est pas transportable.*/
	public ObjetZork(String nom) {
		this.nom = nom;
		this.poids = 0;
		this.transportable = false;
		
	}

	public ObjetZork() {
		
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/*Fonction retournant si l'objet est transportable ou pas.*/

	public boolean isTransportable() {
		return transportable;
	}

	public void setTransportable(boolean transportable) {
		this.transportable = transportable;
	}

	@Override
	public String toString() {
		return "ObjetZork [poids=" + poids + ", nom=" + nom + ", transportable=" + transportable + "]";
	}
	
	
	
	
}
