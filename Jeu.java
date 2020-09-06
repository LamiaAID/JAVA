import java.util.ArrayList;
import java.util.List;

/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure tr√®s rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se d√©placer parmi les diff√©rentes pieces.
 *  Ce jeu n√©cessite vraiment d'etre enrichi pour devenir int√©ressant!</p> <p>
 *
 *  Pour jouer a ce jeu, cr√©er une instance de cette classe et appeler sa
 *  m√©thode "jouer". </p> <p>
 *
 *  Cette classe cr√©e et initialise des instances de toutes les autres classes:
 *  elle cr√©e toutes les pieces, cr√©e l'analyseur syntaxique et d√©marre le jeu.
 *  Elle se charge aussi d'ex√©cuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private List<Piece> piecesParcourues;
	Joueur joueur;
	ObjetZork baguetteMagique = new ObjetZork(2,"baguette_magique",true);



	/**
	 *  Cr√©e le jeu et initialise la carte du jeu (i.e. les pi√®ces).
	 */
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();


	}


	/**
	 *  Cr√©e toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		
		piecesParcourues = new ArrayList<Piece>(); 
		

		// cr√©ation des pieces
		Piece Hall = new Piece("hall");
		Piece Vue = new Piece("vue");
		Piece Dortoir = new Piece("dortoir");
		Piece Cuisine = new Piece("cuisine");
		Piece SalleDesEpees = new Piece("salle des ÈpÈes");
		Piece SalleRituels = new Piece("salle des rituels");
		Piece Jardin = new Piece("Jardin");
		Piece SalleDruide = new Piece("Salle du druide");

		// initialise les sorties des pieces
		Hall.setSorties(null,null,Vue,Cuisine);
		Vue.setSorties(Hall, null, SalleDesEpees, Dortoir);
		Dortoir.setSorties(Cuisine, Vue, null, null);
		Cuisine.setSorties(Jardin, Hall, Dortoir, SalleRituels);
		SalleDesEpees.setSorties(Vue, SalleDruide, null, null);
		SalleRituels.setSorties(null, Cuisine, null, null);
		Jardin.setSorties(null, null, Cuisine, null);
		SalleDruide.setSorties(null, null, null, SalleDesEpees);

		// le jeu commence dans le dortoir
		pieceCourante = Dortoir;
		piecesParcourues.add(pieceCourante);

		joueur = new Joueur("garde", pieceCourante, 50, 0, 0);


		/*CrÈation des objets*/

		ObjetZork tapis = new ObjetZork(2,"tapis",false);
		ObjetZork vase = new ObjetZork(2,"vase",true);
		ObjetZork chaise = new ObjetZork(5,"chaise",true);
		ObjetZork torche = new ObjetZork(2,"torche",false);
		ObjetZork lit = new ObjetZork(20,"lit",true);
		ObjetZork table = new ObjetZork(15,"table",true);
		ObjetZork poulet = new ObjetZork(5,"poulet",true);
		ObjetZork epee = new ObjetZork(8,"epee",true);
		ObjetZork casque = new ObjetZork(3,"casque",true);
		ObjetZork armure = new ObjetZork(10,"armure",false);
		ObjetZork encens = new ObjetZork(1,"encens",false);
		ObjetZork craie = new ObjetZork(1,"craie",true);
		ObjetZork rideaux = new ObjetZork(6,"rideaux",false);
		ObjetZork plantes = new ObjetZork(5,"plantes",true);
		ObjetZork herbes = new ObjetZork(2,"herbes",true);
		ObjetZork potion = new ObjetZork(1,"potion",true);
		ObjetZork marmite = new ObjetZork(10,"marmite",false);
		ObjetZork grimoire = new ObjetZork(2,"grimoire",true);


		/*On assigne les objets aux piËces*/

		Hall.ajouterObjet(tapis);
		Hall.ajouterObjet(vase);
		Vue.ajouterObjet(chaise);
		Vue.ajouterObjet(torche);
		Dortoir.ajouterObjet(lit);
		Cuisine.ajouterObjet(table);
		Cuisine.ajouterObjet(poulet);
		Cuisine.ajouterObjet(chaise);
		SalleDesEpees.ajouterObjet(epee);
		SalleDesEpees.ajouterObjet(armure);
		SalleDesEpees.ajouterObjet(casque);
		SalleRituels.ajouterObjet(encens);
		SalleRituels.ajouterObjet(craie);
		SalleRituels.ajouterObjet(rideaux);
		Jardin.ajouterObjet(plantes);
		Jardin.ajouterObjet(herbes);
		SalleDruide.ajouterObjet(potion);
		SalleDruide.ajouterObjet(marmite);
		SalleDruide.ajouterObjet(grimoire);
		joueur.ajouterObjet(armure);
		joueur.ajouterObjet(epee);
		joueur.ajouterObjet(casque);
		Vue.ajouterObjet(baguetteMagique);
	}


	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();

		// Entr√©e dans la boucle principale du jeu. Cette boucle lit
		// et ex√©cute les commandes entr√©es par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		boolean gain = false;
		while (termine == false) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
			gain = gagner();
			if(gain == true){

				System.out.println("Bravo ! Vous Ítes le WIIIIIIINNER !");
				termine = true;
			}
			
		}
		if(termine==true){
			System.out.println("Merci d'avoir jouer.  Au revoir.");
		}
		

	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans le monde de Zork !");
		System.out.println("Zork est un nouveau jeu d'aventure, terriblement enuyeux.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}


	/**
	 *  Ex√©cute la commande sp√©cifi√©e. Si cette commande termine le jeu, la valeur
	 *  true est renvoy√©e, sinon false est renvoy√©e
	 *
	 * @param  commande  La commande a ex√©cuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
		} 
		else if(motCommande.equals("retour")){
			retour();
		}
		else if(motCommande.equals("deposer")){
			deposerObjet(commande);
		}
		else if(motCommande.equals("prendre")){
			prendreObjet(commande);
		}
		else if (motCommande.equals("objetsPresents")) {
			afficheObjetPiece();
		}
		else if (motCommande.equals("mesObjets")) {
			afficheObjetJoueur();
		}
		else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		} else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println("dans le donjon...");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction sp√©cifi√©e par la commande. Si la piece
	 *  courante poss√®de une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot sp√©cifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller o√π ?");
			return;
		}

		String direction = commande.getSecondMot();

		// Tentative d'aller dans la direction indiqu√©e.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			piecesParcourues.add(pieceCourante);
			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
		}
	}

	/*Fonction de retour en arriËre
	 * On teste d'abord la taille de la liste, si elle n'est pas nulle : la nouvelle piËce
	 * est donc la piËce d'avant, un indice - 1 et on supprime la piËce qui Ètait la suivante*/

	public void retour() 
	{
		if(piecesParcourues.size()!= 0)
		{
			int i = piecesParcourues.size();
			pieceCourante = piecesParcourues.get(i-1);
			System.out.println(pieceCourante.descriptionLongue());
			piecesParcourues.remove(i-1);
		}
		else
		{
			System.out.println("Manoeuvre impossible !");
		}	

	}

	public void deposerObjet(Commande commande){

		if(!commande.aSecondMot()){
			System.out.println("dÈposer quoi ?");
			return;
		}

		/*On recherche d'abord si l'objet est bien chez le joueur*/
		ObjetZork oz = new ObjetZork();
		oz = joueur.contient(commande.getSecondMot());
		if(oz!=null){
			//System.out.println("l'objet ");
			// Si l'objet est bien chez le joueur, on l'ajoute ‡ la piËce et seulement si on peut l'ajouter,
			// on le supprime au joueur
			boolean bool = pieceCourante.ajouterObjet(oz);
			if(bool==true){
				boolean bool1 = joueur.retirerObjet(oz);
				//System.out.println(bool1);
				if(bool1==true){
					System.out.println("l'objet "+oz.getNom()+ " a ÈtÈ dÈposÈ dans la piËce "+pieceCourante.descriptionCourte());
					return;
				}
			}
		}

	}

	public void prendreObjet(Commande commande)
	{
		if(!commande.aSecondMot()){
			System.out.println("prendre quoi ?");
			return;
		}

		/*On recherche d'abord si l'objet est bien prÈsent dans la piece*/
		ObjetZork oz = new ObjetZork();
		oz = pieceCourante.contient(commande.getSecondMot());
		if(oz!=null){				
			// Si l'objet est bien dans la piËce, on tente de savoir si on peut ajouter au joeur et seulement
			// si on peut, on le supprime de la piËce
			boolean bool = joueur.ajouterObjet(oz);
			if(bool == true){
				pieceCourante.retirerObjet(oz);
				System.out.println("l'objet "+oz.getNom()+ " a ÈtÈ pris de la piËce "+pieceCourante.descriptionCourte());
			}
		}


	}

	/*Fonction permettant de savoir quand le joueur a gagnÈ, il doit avoir sur lui la baguette
	 * magique et se trouver dans la salle du druide*/

	public boolean gagner()
	{	
		if(pieceCourante.descriptionCourte().equals("Salle du druide")){
			ObjetZork oz = joueur.contient("baguette_magique");
			if(oz!=null){
				return true;
			}
			return false;
		}
		return false;
	}

	public void afficheObjetPiece()
	{
		ObjetZork[] tab = pieceCourante.getObjetZorkList();
		for(int i = 0;i<pieceCourante.getSomme();i++){
			System.out.println(tab[i].getNom());
		}

	}

	public void afficheObjetJoueur(){

		ObjetZork[] tab = joueur.getObjetPortes();
		for(int i = 0;i<joueur.getNbObjet();i++){
			System.out.println(tab[i].getNom());
		}

	}




}

