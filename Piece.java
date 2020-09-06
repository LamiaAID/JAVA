import java.util.Set;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      August 2000
 */

public class Piece {
	private String description;

	// mémorise les sorties de cette piece.
	private Map sorties;
	private ObjetZork[] objetZorkList;
	private int capacite;
	private int somme;


	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new HashMap();
		this.capacite = 5;
		this.somme = 0;
		objetZorkList = new ObjetZork[5];
	}

	/*Constructeur de pi�ce avec en argument :
	 * - la capacit� d'objet qu'elle peut contenir
	 * - le total des objets qu'elle contient d�j�, varible qui s'adaptera aux apports
	 *   ou retraits du joueur
	 * - une liste d'objet pr�sents, contenant les noms, les poids... des objets*/
	public Piece(String description,int capacite,int somme, ObjetZork[] list) {
		this.description = description;
		sorties = new HashMap();
		this.capacite = capacite;
		this.objetZorkList = list;
		this.somme = somme;
	}

	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}

	/*Fonction permettant d'ajouter l'objet pass� en param�tre dans la pi�ce. Deux cas :
	 * - si la capacit� est d�j� atteinte, envoyer un message d'avertissement au joueur
	 *  - sinon, l'inclure dans le tableau d'objet en incr�mentant la somme*/

	public boolean ajouterObjet(ObjetZork oz){
		if(this.somme<capacite){
			objetZorkList[somme] = oz;
			somme++;
			//System.out.println("Mon objet "+oz.getNom()+" a �t� ajout�");
			return true;
		}
		else{
			System.out.println("Capacit� atteinte !");
			return false;
		}
	}

	/*Fonction permettant de retirer un objet d'une pi�ce. Deux cas :
	 * - si la liste est vide, envoyer un message d'avertissement au joueur
	 * - sinon, on parcourut le tableau : on teste si une case contient l'objet
	 *   pass� en param�tre. Si oui, on supprime l'objet et on d�cr�mente la somme.*/

	public boolean retirerObjet(ObjetZork oz){
		if(this.somme==0){
			//System.out.println("Liste vide");
			return false;
		}
		else
		{
			for(int j = 0; j<this.somme;j++){
				if(objetZorkList[j].equals(oz)){
					objetZorkList[j] = null;
					objetZorkList[j] = objetZorkList[this.somme-1];
					somme--;
					//System.out.println("Mon objet a �t� retir� !");
					return true;
				}
				
			}
			return false;
		}
	}

	/**
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set keys = sorties.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
			resulString += " " + iter.next();
		}
		return resulString;
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
	
	public ObjetZork contient(String nom){
		  
		  for(int i = 0;i<somme;i++){
			  if(objetZorkList[i].getNom().equals(nom)){
				  return objetZorkList[i];
			  }
		  }
		  return null;
	  }
	
	/*public void afficher(){
		  
		  for(int i = 0;i<somme;i++){
			 System.out.println(objetZorkList[i].getNom());
		  }
	}*/

	public ObjetZork[] getObjetZorkList() {
		return objetZorkList;
	}

	public void setObjetZorkList(ObjetZork[] objetZorkList) {
		this.objetZorkList = objetZorkList;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int somme) {
		this.somme = somme;
	}
}

