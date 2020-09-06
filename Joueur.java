

import java.util.Arrays;

public class Joueur {

	/* Champs caract�risant le joueur*/
	private String nom;
	private Piece maPiece; // la pi�ce o� il se trouve actuellement
	private int capacitePoids;// le poids qu'il peut transport�s
	private int poids;// le poids qu'il transporte d�j�
	private ObjetZork[] objetPortes; // la liste des objets qu'il porte
	private int nbObjet; // le nombre d'objet qu'il porte



	/*Cr�ation du constructeur avec toutes les caract�ristiques en argument*/
	public Joueur(String nom, Piece maPiece, int capacitePoids, int poids, int nbObjet) {
		this.nom = nom;
		this.maPiece = maPiece;
		this.capacitePoids = capacitePoids;
		this.poids = poids;
		this.objetPortes = new ObjetZork[6];
		this.nbObjet = nbObjet;
	}



	/*Fonction permettant de renvoyer un bool�en testant si un objet peut �tre
	 * transport� ou pas par le joueur.
	 * Si le poids de cet objet, pass� en param�tre, ajout� au poids que le joueur
	 * transporte d�j� ne d�passe pas la capacit�, alors il est transportable : on retourne
	 * vrai.*/

	public Joueur() {}


	public boolean retirerObjet(ObjetZork oz){
		if(this.nbObjet==0){
			//System.out.println("Liste vide");
			return false;
		}
		else
		{
			for(int j = 0; j<this.nbObjet;j++){
				if(objetPortes[j].equals(oz)){
					poids = poids-oz.getPoids();
					objetPortes[j] = null;
					objetPortes[j] = objetPortes[this.nbObjet-1];
					//System.out.println("Mon objet a �t� retir� !");
					nbObjet--;
					
					return true;
				}
				
			}
			return false;
		}
	}

	public boolean ajouterObjet(ObjetZork oz){
		if(oz.getPoids()+this.poids<capacitePoids){
			objetPortes[this.nbObjet] = oz;
			nbObjet++;
			poids = poids+oz.getPoids();
			//System.out.println("Mon objet "+oz.getNom()+" a �t� ajout�, po "+poids);
			return true;
		}
		else{
			System.out.println("Capacit� atteinte !");
			return false;
		}
	}

	public boolean transportable(ObjetZork oz){
		if(this.poids+oz.getPoids()<=capacitePoids){
			System.out.println("Possibilit� de transporter");
			objetPortes[nbObjet]=oz;
			return true;
		}
		else{
			System.out.println("Ca va pas ?!");
			return false;
		}
	}


	/*Fonction permettant de rechercher sin un objet pass� en param�tre est pr�sent
	 * dans les objets port�s par le joueur. Pour cela, on teste l'une apr�s l'autre les
	 * cases du tableau des objet port�s et on observe si l'objet est �gal � l'un des objet
	 * contenu dans ce tableau. Si oui, on retourne vrai, sinon faux.*/

	public ObjetZork contient(String nom){

		for(int i = 0;i<nbObjet;i++){
			if(objetPortes[i].getNom().equals(nom)){
				//System.out.println("Objet pr�sent");
				return objetPortes[i];
			}
		}
		//System.out.println("Objet pas laaaa");
		return null;
	}





	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", maPiece=" + maPiece.descriptionCourte() + ", capacitePoids=" + capacitePoids + ", poids=" + poids
				+ ", objetPortes=" + Arrays.toString(objetPortes) + ", nbObjet=" + nbObjet + "]";
	}





	public String getNom() {
		return nom;
	}





	public void setNom(String nom) {
		this.nom = nom;
	}





	public Piece getMaPiece() {
		return maPiece;
	}





	public void setMaPiece(Piece maPiece) {
		this.maPiece = maPiece;
	}





	public int getCapacitePoids() {
		return capacitePoids;
	}





	public void setCapacitePoids(int capacitePoids) {
		this.capacitePoids = capacitePoids;
	}





	public int getPoids() {
		return poids;
	}





	public void setPoids(int poids) {
		this.poids = poids;
	}





	public ObjetZork[] getObjetPortes() {
		return objetPortes;
	}





	public void setObjetPortes(ObjetZork[] objetPortes) {
		this.objetPortes = objetPortes;
	}





	public int getNbObjet() {
		return nbObjet;
	}





	public void setNbObjet(int nbObjet) {
		this.nbObjet = nbObjet;
	}




}
