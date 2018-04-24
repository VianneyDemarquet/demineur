import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.lang.Object;
import  java.awt.event.*;
import java.io.*;


public class Partie extends JFrame implements MouseListener, ActionListener{
	
	private int[][] grille_bombe;
	private int[][] grille_partie;
	private int lignes;
	private int colonnes;
	private int nbbombes;
	private int case_libre;
	private int drapeaux;

	private JButton[][] grille_bouton;
	private JButton panel;
	private JButton sauvegarder;
	private JButton quitté;

	private boolean first;
	private boolean win;
	private boolean loose;
	
	private JFrame fenetre;
	private JFrame menu;
	
	private Color gray;
	private Color white;
	private Color red;

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
		this.Init();
		
	}

	public Partie()
	{
		new Save(this);
		this.Init();
	}

	private void Init()
	{
		fenetre = new JFrame("Démineur");
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		Partie grille=this;
		
		fenetre.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				new Save(lignes,colonnes,grille);
				System.exit(0);
			}
		}); 

		menu = new JFrame("Menu");
		menu.setSize(200,500);
		menu.setLocation(600,0);
		menu.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				new Save(lignes,colonnes,grille);
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

	public int getLignes()
	{
		return lignes;
	}

	public int getColonnes()
	{
		return colonnes;
	}

	public int getNbbombes()
	{
		return nbbombes;
	}

	public int getBomb(int l, int c)
	{
		return grille_bombe[l][c];
	}

	public int getPartie(int l, int c)
	{
		return grille_partie[l][c];
	}

	public int getCase_libre()
	{
		return case_libre;
	}

	public int getDrapeaux()
	{
		return drapeaux;
	}

	public void setLignes(int l)
	{
		lignes=l;
	}

	public void setColonnes(int c)
	{
		colonnes=c;
	}

	public void setTables()
	{
		grille_bombe = new int[lignes][colonnes];
		grille_partie = new int[lignes][colonnes];
	}

	public void setPartie(int l, int c)
	{
		grille_partie[l][c]=1;
	}

	public void setPartie(int l, int c,int val)
	{
		grille_partie[l][c]=val;
	}

	public void setBombs(int l, int c,int val)
	{
		grille_bombe[l][c]=val;
	}

	public void setCase_libre(int val)
	{
		case_libre=val;
	}

	public void setNbbombes(int val)
	{
		nbbombes=val;
	}

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

	public void Afficher(){
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
				}else if(grille_partie[i][k]==5)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(red);
					grille_bouton[i][k].setIcon(new ImageIcon("image/étoile.png"));

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

	public void Cont(int l, int c)
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
		GridLayout gestionnaire = new GridLayout(3, 1);
		menu.setLayout(gestionnaire);
		menu.remove(panel);
		menu.remove(sauvegarder);

		panel = new JButton("Perdu");
		sauvegarder = new JButton("Nouvelle partie");
		quitté = new JButton("Quitté");

		panel.setBackground(red);
		quitté.setBackground(gray);
		sauvegarder.setBackground(gray);

		quitté.addActionListener(this);
		sauvegarder.addActionListener(this);

		menu.add(panel);
		menu.add(sauvegarder);
		menu.add(quitté);
		menu.setVisible(true);
	}

	private void Gagné()
	{
		GridLayout gestionnaire = new GridLayout(3, 1);
		menu.setLayout(gestionnaire);

		menu.remove(panel);
		menu.remove(sauvegarder);

		panel = new JButton("Gagné");
		sauvegarder = new JButton("Nouvelle partie");
		quitté = new JButton("Quitté");

		Color vert = new Color(0,200,0);
		panel.setBackground(vert);
		quitté.setBackground(gray);
		sauvegarder.setBackground(gray);

		quitté.addActionListener(this);
		sauvegarder.addActionListener(this);

		menu.add(panel);
		menu.add(sauvegarder);
		menu.add(quitté);

		menu.setVisible(true);
	}

	private void Drapeau(int l, int c)
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

	private void Menu()
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
		sauvegarder = new JButton("Sauvegarder et quitté");

		panel.setBackground(gray);
		sauvegarder.setBackground(gray);

		sauvegarder.addActionListener(this);

		menu.add(panel);
		menu.add(sauvegarder);

		menu.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		String chaine = e.getActionCommand();
		if(chaine=="Sauvegarder et quitté")
		{
			new Save(lignes, colonnes, this);
			System.exit(0);

		}else if(chaine=="Nouvelle partie")
		{

			fenetre.dispose();
			menu.dispose();

			new Partie(lignes,colonnes,nbbombes);

		}else if(chaine=="Quitté")
		{
			System.exit(0);
		}
		
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
					this.Afficher();					
				}
			}
		}

	}		// un bouton est relaché
}	