import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.lang.Object;


public class Partie{
	private int[][] grille;
	private int lignes;
	private int colonnes;
	private int nbbombes;

	public Partie(int l, int c, int b) {
		grille = new int[l][c];
		lignes=l;
		colonnes=c;
		nbbombes=b;
		this.Zéro();
		this.Place_Bomb();
	}

	/*
	zéroiphie les valeur du tableau;
	*/
	private void Zéro(){
		for (int i=0; i<lignes; i++) {
			for (int y=0; y<colonnes; y++) {
				grille[i][y]=0;
			}
		}
	}
	
/*
place les bombes dans la grille
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

			if (grille[l][c] == 4)
			{
				i--;
			}else
			{
				grille[l][c] = 4; //code des bombes
			}
		}
	}
}