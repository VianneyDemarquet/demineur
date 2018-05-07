import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.lang.Object;
import  java.awt.event.*;
import java.io.*;

/**
 * La classe <code>Partie</code> est utilis&eacute;e pour comenc&eacute; et g&eacute;r&eacute; une partie en cour.
 *  
 * @author Vianney Demarquet
 * @version 0.1
 */
public class Partie {
	
	private int[][] grille_bombe;
	private int[][] grille_partie;
	private int lignes;
	private int colonnes;
	private int nbbombes;
	private int case_libre;
	private int drapeaux;

	private Affichage affiche;

	/**
	* Constructeur destin&eacute; &agrave; la cr&eacute;ation de certaine
	* constantes publiques.
	*
	* @param l nombre de lignes souhait&eacute; (entre 4 et 30)
	* @param c nombre de colonnes souhait&eacute; (entre 4 et 30)
	* @param b nombre de bombe souhait&eacute;
	*/

	public Partie(int l, int c, int b) {
		grille_bombe = new int[l][c];
		grille_partie = new int[l][c];
		lignes=l;
		colonnes=c;
		nbbombes=b;
		case_libre=(lignes*colonnes)-nbbombes;
		drapeaux=0;
		this.Zéro();
		this.Place_Bomb();
		affiche = new Affichage(this);
		
	}

	/**
	* Constructeur destin&eacute; &agrave; la cr&eacute;ation de certaine 
	* constantes publiques à partire d'une sauvegarde.
	*/
	public Partie()
	{
<<<<<<< HEAD
		new Save(this);
		this.Init();
	}

	/**
	* Cr&eacute;&eacute; les interfaces graphiques et 
	* certaine constante publique.
	*
	*/
	private void Init()
	{
		grille=this;
		fenetre = new JFrame("Démineur");
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		//Afficher_consol();
		
		fenetre.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				if (!win && !loose) 
				{
					new Save(lignes,colonnes,grille);
				}
				System.exit(0);
			}
		}); 

		menu = new JFrame("Menu");
		menu.setSize(200,500);
		menu.setLocation(600,0);
		menu.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				if (!win && !loose) 
				{
					new Save(lignes,colonnes,grille);
				}
				System.exit(0);
			}
		}); 

		grille_bouton = new JButton[lignes][colonnes];
		win=false;
		loose=false;
		first=false;
		gray = new Color(230, 230, 230);
		white = new Color(255, 255, 255);
		red = new Color(255, 0, 0);
		this.Afficher();
		this.Menu();
		first=true;
=======
		new Load(this);
		affiche = new Affichage(this);
>>>>>>> c5565e15800dede26549ec0dfdf0e8d84f092a11
	}

	/*
	zéroiphie les valeur du tableau;
	*/
	private void Zéro(){
		for (int i=0; i<lignes; i++) {
			for (int y=0; y<colonnes; y++) {
				grille_bombe[i][y]=0;
				grille_partie[i][y]=0;
			}
		}
	}

	/**
	* Renvoie le nombre de lignes.
	*
	* @return le nombre de lignes (entre 4 et 30)
	*/
	public int getLignes()
	{
		return lignes;
	}

	/**
	* Renvoie le nombre de colonnes.
	*
	* @return le nombre de colonnes (entre 4 et 30)
	*/
	public int getColonnes()
	{
		return colonnes;
	}

	/**
	* Renvoie le nombre de bombes.
	*
	* @return le nombre de bombes
	*/
	public int getNbbombes()
	{
		return nbbombes;
	}

	/**
	* Renvoie le nombre de bombes autour de cette case
	*
	* @return le nombre de bombes
	* @param l le num&eacute;ro ligne de la case
	* @param c le num&eacute;ro colonnes de la case
	*/
	public int getBomb(int l, int c)
	{
		return grille_bombe[l][c];
	}

	/**
	* Renvoie renvoie si la case et cach&eacute;, r&eacute;v&eacute;l&eacute;
	* ou avec un marqueur.
	*
	* @return &eacute;tat de la case
	* @param l le num&eacute;ro ligne de la case
	* @param c le num&eacute;ro colonnes de la case
	*/
	public int getPartie(int l, int c)
	{
		return grille_partie[l][c];
	}

	/**
	* Renvoie le nombre de case libre.
	*
	* @return le nombre de case libre
	*/
	public int getCase_libre()
	{
		return case_libre;
	}

	/**
	* Renvoie le nombre de drapeaux.
	*
	* @return le nombre de drapeaux
	*/
	public int getDrapeaux()
	{
		return drapeaux;
	}

	/**
	* Initilise le nombre de lignes.
	*
	* @param l le nombre de lignes
	*/
	public void setLignes(int l)
	{
		lignes=l;
	}

	/**
	* Initilise le nombre de colonnes.
	*
	* @param c le nombre de colonnes
	*/
	public void setColonnes(int c)
	{
		colonnes=c;
	}

	/**
	* Initilise la taille des tableaux.
	*
	*/
	public void setTables()
	{
		grille_bombe = new int[lignes][colonnes];
		grille_partie = new int[lignes][colonnes];
	}

	/**
	* Change la valeur de la case de cach&eacute; &agrave; r&eacute;v&eacute;l&eacute;.
	*
	* @param l le num&eacute;ro de lignes
	* @param c le num&eacute;ro de colonnes
	*/
	public void setPartie(int l, int c)
	{
		grille_partie[l][c]=1;
	}

	/**
	* Change la valeur de la case de cach&eacute;, r&eacute;v&eacute;l&eacute;
	* ou avec un marqueur.
	*
	* @param l le num&eacute;ro de lignes
	* @param c le num&eacute;ro de colonnes
	* @param val &eacute;tat de la case
	*/
	public void setPartie(int l, int c,int val)
	{
		grille_partie[l][c]=val;
	}

	/**
	* Change le nombre de bombe des case alentour.
	*
	* @param l le num&eacute;ro de lignes
	* @param c le num&eacute;ro de colonnes
	* @param val le nombre de bombe souhait&eacute;
	*/
	public void setBombs(int l, int c,int val)
	{
		grille_bombe[l][c]=val;
	}

	/**
	* Change le nombre de case libre
	*
	* @param val le nombre de case libre
	*/
	public void setCase_libre(int val)
	{
		case_libre=val;
	}

	/**
	* change le nombre de bombes
	*
	* @param val le nombre de bombes
	*/
	public void setNbbombes(int val)
	{
		nbbombes=val;
	}

	/**
	* Change le nombre de drapeaux
	*
	* @param val le nombre de drapeaux
	*/
	public void setDrapeaux(int val)
	{
		drapeaux=val;
	}

		
	/*
	place les bombes dans la grille_bombe
	*/
	private void Place_Bomb(){
		double y;
		int l,c;
		java.util.Random x = new java.util.Random();
		for (int i=0; i<nbbombes; i++) {
			y = x.nextDouble(); //génération du nombre aléatoire
			y = y*lignes;
			l = (int)y;
			y = x.nextDouble();
			y = y*colonnes;
			c = (int)y;

			if (grille_bombe[l][c] == 9)
			{
				i--;
			}else
			{
				grille_bombe[l][c] = 9; //code des bombes
				for (int k=-1;k<2; k++) 
				{
					if ((l+k)>=0 && (l+k)<lignes) 
					{
						for (int m=-1;m<2; m++) 
						{
							if ((c+m)>=0 && (c+m)<colonnes) 
							{
								if (grille_bombe[l+k][c+m]!=9) 
								{
									grille_bombe[l+k][c+m]++;		//incrémente le nombre de bombe au alentoure
								}
							}
						}
					}
				}
			}
		}
	}


	private void Afficher_consol(){
		System.out.println("nbcolonnes : "+colonnes);
		System.out.println("nblignes : "+lignes);
		System.out.println("case libre : "+case_libre);
		
		System.out.println("");
		for (int i=0; i<lignes;i++) {
			for (int k=0;k<colonnes ;k++ ) {
				System.out.print(grille_bombe[i][k]+" ");
			}
			System.out.println("");
		}
		System.out.println("\n");
		for (int i=0; i<lignes;i++) {
			for (int k=0;k<colonnes ;k++ ) {
				System.out.print(grille_partie[i][k]+" ");
			}
			System.out.println("");
		}
	}
	

	/**
	* V&eacute;rifie si on &agrave; gagn&eacute; ou perdu.
	*
	* @param l le num&eacute;ro de lignes
	* @param c le num&eacute;ro de colonnes
	*/
	public void Cont(int l, int c)
	{
		if (grille_bombe[l][c]==9) 
		{
			grille_partie[l][c]=4;
			this.Perdu();
			affiche.setFin();
			affiche.MenuFin(true);
		}else
		{
			case_libre--;
			if (case_libre == 0) 
			{
				affiche.setFin();
				affiche.MenuFin(false);
			}
		}
		
	}


	private void Perdu()
	{
		for (int i=0; i<lignes; i++) {
			for (int k=0; k<colonnes; k++) {
				if (grille_partie[i][k]!=4 && grille_bombe[i][k]==9 && grille_partie[i][k]!=2) {
					grille_partie[i][k]=1;
				}else if (grille_partie[i][k]==2 && grille_bombe[i][k]!=9) {
					grille_partie[i][k]=5;
				}
			}
		}
	}

	public void Drapeau(int l, int c)
	{
		if (grille_partie[l][c]==0) {
			grille_partie[l][c]=2;
			drapeaux++;
		}else if (grille_partie[l][c]==2) {
			grille_partie[l][c]=3;
			drapeaux--;
		}else
		{
			grille_partie[l][c]=0;
		}
		affiche.Menu(nbbombes-drapeaux);
	}


}

	