import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.lang.Object;
import  java.awt.event.*;
import java.io.*;


public class Partie extends JFrame implements MouseListener{
	private int[][] grille_bombe;
	private int[][] grille_partie;
	private JButton[][] grille_bouton;
	private JButton panel;
	private JButton sauvegarder;
	private JButton quité;
	private int lignes;
	private int colonnes;
	private int nbbombes;
	private boolean win;
	private boolean loose;
	private int case_libre;
	private JFrame fenetre;
	private int drapeaux;
	private boolean first;
	private JFrame menu;
	private Color gray;
	private Color white;
	private Color red;

	public Partie(int l, int c, int b) {
		grille_bombe = new int[l][c];
		grille_partie = new int[l][c];
		grille_bouton = new JButton[l][c];
		lignes=l;
		colonnes=c;
		nbbombes=b;
		this.Zéro();
		this.Place_Bomb();

		fenetre = new JFrame("Démineur");
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new JFrame("Menu");
		menu.setSize(200,500);
		menu.setLocation(600,0);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Init();
		
	}

	private void Init()
	{
		case_libre=(lignes*colonnes)-nbbombes;
		drapeaux=0;
		win=false;
		loose=false;
		first=false;
		gray = new Color(230, 230, 230);
		white = new Color(255, 255, 255);
		red = new Color(255, 0, 0);
		this.Update();
		this.Menu();
		first=true;
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

public int getBomb(int l, int c)
{
	return grille_bombe[l][c];
}

public int getLignes()
{
	return lignes;
}

public int getColonnes()
{
	return colonnes;
}

public int getPartie(int l, int c)
{
	return grille_partie[l][c];
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


	void Afficher_consol(){
		System.out.println("nbcolonnes : "+colonnes);
		System.out.println("nblignes : "+lignes);
		System.out.println("case libre : "+case_libre);
		
		System.out.println("");
		for (int i=0; i<lignes;i++) {
			for (int k=0;k<colonnes ;k++ ) {
				System.out.print(grille_partie[i][k]+" ");
			}
			System.out.println("");
		}
	}
/*
	void Afficher(){
		this.Afficher_consol();
		GridLayout gestionnaire = new GridLayout(lignes, colonnes);
		fenetre.setLayout(gestionnaire);
		Color gray = new Color(230, 230, 230);
		for(int i = 0; i < lignes; i++)
		{
			for (int k = 0 ;k < colonnes ;k++ ) 
			{
				grille_bouton[i][k] = new JButton("");
				grille_bouton[i][k].setName(""+(i*10)+k);
				grille_bouton[i][k].setBackground(gray);
				grille_bouton[i][k].addMouseListener(this);
				fenetre.add(grille_bouton[i][k]);				
			}
		}
		fenetre.setVisible(true);
	}
*/
	void Update(){
		GridLayout gestionnaire = new GridLayout(lignes, colonnes);
		fenetre.setLayout(gestionnaire);
		
		for(int i = 0; i < (lignes); i++)
		{
			for (int k = 0 ;k < colonnes ;k++ ) 
			{
				if (first==true) {
					fenetre.remove(grille_bouton[i][k]);
				}
				if (grille_partie[i][k] == 0) 
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setName(""+(i*10)+k);
					grille_bouton[i][k].setBackground(gray);
					if (!win && !loose) {
						grille_bouton[i][k].addMouseListener(this);
					}
					
				}else if(grille_partie[i][k]==2)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setName(""+(i*10)+k);
					grille_bouton[i][k].setBackground(gray);
					grille_bouton[i][k].setIcon(new ImageIcon("image/étoile.png"));

					if (!win && !loose) {
						grille_bouton[i][k].addMouseListener(this);
					}
				}else if(grille_partie[i][k]==3)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setName(""+(i*10)+k);
					grille_bouton[i][k].setBackground(gray);
					grille_bouton[i][k].setIcon(new ImageIcon("image/intero.png"));

					if (!win && !loose) {
						grille_bouton[i][k].addMouseListener(this);
					}
				}else if (grille_bombe[i][k] == 0) 
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(white);
					
				}else if (grille_bombe[i][k] != 9) 
				{	
					grille_bouton[i][k] = new JButton(""+grille_bombe[i][k]);
					grille_bouton[i][k].setBackground(white);

				}else if(grille_partie[i][k] == 4)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(red);
					grille_bouton[i][k].setIcon(new ImageIcon("image/bomb.png"));
				}else
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(white);
					grille_bouton[i][k].setIcon(new ImageIcon("image/bomb.png"));
				}
				fenetre.add(grille_bouton[i][k]);
			}
		}
		
		fenetre.setVisible(true);
	}
			
	void ChangeVal(int l, int c)
	{
		grille_partie[l][c]=1;
	}

	void Cont(int l, int c)
	{
		if (grille_bombe[l][c]==9) {
			loose = true;
			grille_partie[l][c]=4;
			this.Perdu();
		}else
		{
			case_libre--;
			if (case_libre == 0) {
				win = true;
				this.Gagné();
			}
		}
		
	}

	void Perdu()
	{
		for (int i=0; i<lignes; i++) {
			for (int k=0; k<colonnes; k++) {
				if (grille_partie[i][k]!=4 && grille_bombe[i][k]==9) {
					grille_partie[i][k]=1;
				}
			}
		}
		GridLayout gestionnaire = new GridLayout(3, 1);
		menu.setLayout(gestionnaire);
		menu.remove(panel);
		menu.remove(sauvegarder);

		panel = new JButton("Perdu");
		sauvegarder = new JButton("Nouvelle partie");
		quité = new JButton("Quité");

		panel.setBackground(red);
		quité.setBackground(gray);
		sauvegarder.setBackground(gray);

		menu.add(panel);
		menu.add(sauvegarder);
		menu.add(quité);
		menu.setVisible(true);
	}

	void Gagné()
	{
		GridLayout gestionnaire = new GridLayout(3, 1);
		menu.setLayout(gestionnaire);

		menu.remove(panel);
		menu.remove(sauvegarder);

		panel = new JButton("Gagné");
		sauvegarder = new JButton("Nouvelle partie");
		quité = new JButton("Quité");

		Color vert = new Color(0,200,0);
		panel.setBackground(vert);
		quité.setBackground(gray);
		sauvegarder.setBackground(gray);

		menu.add(panel);
		menu.add(sauvegarder);
		menu.add(quité);

		menu.setVisible(true);
	}

	void Drapeau(int l, int c)
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
		this.Menu();
	}

	void Menu()
	{
		GridLayout gestionnaire = new GridLayout(2, 1);
		menu.setLayout(gestionnaire);
		if (first == true) 
		{
			menu.remove(panel);
			menu.remove(sauvegarder);
		}
		int mine_rest = nbbombes-drapeaux;

		panel = new JButton("Mine \n restante : "+mine_rest);
		sauvegarder = new JButton("sauvegarder et quité");

		panel.setBackground(gray);
		sauvegarder.setBackground(gray);

		menu.add(panel);
		menu.add(sauvegarder);
		
		menu.setVisible(true);
	}

	public void mouseClicked(MouseEvent e){
	}			// un bouton cliqué

	public void mouseEntered(MouseEvent e){
	}			// debut du survol

	public void mouseExited(MouseEvent e){
	}			// fin du survol

	public void mousePressed(MouseEvent e){
	}			// un bouton appuyé

	public void mouseReleased(MouseEvent e)
	{
		int x=Integer.parseInt(e.getComponent().getName());

		for (int i=0; i<lignes; i++) 
		{
			for (int k=0; k<colonnes; k++) 
			{
				int var1 = i*100+k;
				int var2 = i*1000+k;
				
				if(x==var1 || x==var2)
				{
					if(e.getButton() == MouseEvent.BUTTON1)	//Clic Gauche
					{								
						Changement s = new Changement();
						s.Changements(i,k,this);
					}
					else	//Clic droit
					{
						this.Drapeau(i,k);
					}
					this.Update();					
				}
			}
		}

		
	}		// un bouton est relaché
}	