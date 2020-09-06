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
 *  Une "Piece" represente un des lieux dans lesquels se d√©roule l'action du
 *  jeu. Elle est reli√©e a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont √©tiquett√©es "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" poss√®de une r√©f√©rence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      August 2000
 */

public class Piece {
	private String description;

	// m√©morise les sorties de cette piece.
	private Map sorties;
	private ObjetZork[] objetZorkList;
	private int capacite;
	private int somme;


	/**
	 *  Initialise une piece d√©crite par la chaine de caract√®res sp√©cifi√©e.
	 *  Initialement, cette piece ne poss√®de aucune sortie. La description fournie
	 *  est une courte phrase comme "la biblioth√®que" ou "la salle de TP".
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

	/*Constructeur de piËce avec en argument :
	 * - la capacitÈ d'objet qu'elle peut contenir
	 * - le total des objets qu'elle contient dÈj‡, varible qui s'adaptera aux apports
	 *   ou retraits du joueur
	 * - une liste d'objet prÈsents, contenant les noms, les poids... des objets*/
	public Piece(String description,int capacite,int somme, ObjetZork[] list) {
		this.description = description;
		sorties = new HashMap();
		this.capacite = capacite;
		this.objetZorkList = list;
		this.somme = somme;
	}

	/**
	 *  D√©finie les sorties de cette piece. A chaque direction correspond ou bien
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

	/*Fonction permettant d'ajouter l'objet passÈ en paramËtre dans la piËce. Deux cas :
	 * - si la capacitÈ est dÈj‡ atteinte, envoyer un message d'avertissement au joueur
	 *  - sinon, l'inclure dans le tableau d'objet en incrÈmentant la somme*/

	public boolean ajouterObjet(ObjetZork oz){
		if(this.somme<capacite){
			objetZorkList[somme] = oz;
			somme++;
			//System.out.println("Mon objet "+oz.getNom()+" a ÈtÈ ajoutÈ");
			return true;
		}
		else{
			System.out.println("CapacitÈ atteinte !");
			return false;
		}
	}

	/*Fonction permettant de retirer un objet d'une piËce. Deux cas :
	 * - si la liste est vide, envoyer un message d'avertissement au joueur
	 * - sinon, on parcourut le tableau : on teste si une case contient l'objet
	 *   passÈ en paramËtre. Si oui, on supprime l'objet et on dÈcrÈmente la somme.*/

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
					//System.out.println("Mon objet a ÈtÈ retirÈ !");
					return true;
				}
				
			}
			return false;
		}
	}

	/**
	 *  Renvoie la description de cette piece (i.e. la description sp√©cifi√©e lors
	 *  de la cr√©ation de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement format√©e pour affichage, de la forme: <pre>
	 *  Vous etes dans la biblioth√®que.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caract√®res
	 *  renvoy√©e par la m√©thode descriptionSorties pour d√©crire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilis√©e dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pi√®ce.
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
	 *  Renvoie la piece atteinte lorsque l'on se d√©place a partir de cette piece
	 *  dans la direction sp√©cifi√©e. Si cette piece ne poss√®de aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se d√©placer
	 * @return            Piece atteinte lorsque l'on se d√©place dans la direction
	 *      sp√©cifi√©e ou null.
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

