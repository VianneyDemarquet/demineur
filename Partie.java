import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.lang.Object;
import  java.awt.event.*;


public class Partie extends JFrame implements ActionListener{
	private int[][] grille_bombe;
	private int[][] grille_partie;
	private JButton[][] grille_bouton;
	private int lignes;
	private int colonnes;
	private int nbbombes;
	private boolean win= false;
	private boolean loose=false;
	private int case_libre;
	private JFrame fenetre;

	public Partie(int l, int c, int b) {
		grille_bombe = new int[l][c];
		grille_partie = new int[l][c];
		grille_bouton = new JButton[l][c];
		lignes=l;
		colonnes=c;
		nbbombes=b;
		case_libre=(l*c)-b;
		this.Zéro();
		this.Place_Bomb();

		fenetre = new JFrame("Démineur");
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.Afficher();
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
		for (int i=0; i<lignes;i++) {
			for (int k=0;k<colonnes ;k++ ) {
				System.out.print(grille_bombe[i][k]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		for (int i=0; i<lignes;i++) {
			for (int k=0;k<colonnes ;k++ ) {
				System.out.print(grille_partie[i][k]+" ");
			}
			System.out.println("");
		}
	}

	void Afficher(){
		this.Afficher_consol();
		GridLayout gestionnaire = new GridLayout(lignes, colonnes);
		fenetre.setLayout(gestionnaire);
		Color gray = new Color(230, 230, 230);
		for(int i = 0; i < lignes; i++)
		{
			for (int k = 0 ;k < colonnes ;k++ ) 
			{
				grille_bouton[i][k] = new JButton(""+i+k);
				grille_bouton[i][k].setForeground(gray);
				grille_bouton[i][k].setBackground(gray);
				grille_bouton[i][k].addActionListener(this);
				fenetre.add(grille_bouton[i][k]);				
			}
		}
		fenetre.setVisible(true);
	}

	void Update(){
		GridLayout gestionnaire = new GridLayout(lignes, colonnes);
		fenetre.setLayout(gestionnaire);
		Color gray = new Color(230, 230, 230);
		Color white = new Color(255, 255, 255);
		Color red = new Color(255, 0, 0);
		for(int i = 0; i < (lignes); i++)
		{
			for (int k = 0 ;k < colonnes ;k++ ) 
			{
				fenetre.remove(grille_bouton[i][k]);
				if (grille_partie[i][k] == 0) 
				{
					grille_bouton[i][k] = new JButton(""+i+k);
					grille_bouton[i][k].setForeground(gray);
					grille_bouton[i][k].setBackground(gray);
					grille_bouton[i][k].addActionListener(this);
					fenetre.add(grille_bouton[i][k]);
				}else if (grille_bombe[i][k] == 0) 
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(white);
					fenetre.add(grille_bouton[i][k]);	
				}else if (grille_bombe[i][k] != 9) 
				{	
					grille_bouton[i][k] = new JButton(""+grille_bombe[i][k]);
					grille_bouton[i][k].setBackground(white);
					fenetre.add(grille_bouton[i][k]);
				}else if(grille_partie[i][k] == 4)
				{
					grille_bouton[i][k] = new JButton("b");
					grille_bouton[i][k].setBackground(red);
					fenetre.add(grille_bouton[i][k]);
				}else
				{
					grille_bouton[i][k] = new JButton("b");
					grille_bouton[i][k].setBackground(white);
					fenetre.add(grille_bouton[i][k]);
				}
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
			Perdu();
		}else
		{
			case_libre--;
			if (case_libre == 0) {
				win = true;
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
	}

	public void actionPerformed(ActionEvent e)
	{
		for (int i=0; i<lignes; i++) 
		{
			for (int k=0; k<colonnes; k++) 
			{
				int x=Integer.parseInt(e.getActionCommand());
				if(x==(i*10+k) && grille_partie[i][k]==0)
				{
					Changement s = new Changement();
					s.Changements(i,k,this);
    				this.Update();
				}
			}
		}
      
	}
}