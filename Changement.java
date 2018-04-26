/**
 * La classe <code>Changemet</code> est utilis&eacute;e pour effectu&eacute; la d&eacute;couverte d'une case.
 *  
 * @author Vianney Demarquet
 * @version 0.1
 */

public class Changement{
	/**
	* D&eacute;couvre la case donn&eacute; &agrave; la position donn&eacute;.
	* Si elle la case en question n'a pas de bombe autour
	* la m&eacute;thode se rappelle r&eacute;cursivement sur les 8 case allentour.
	* @param l le num&eacute;ro ligne de la case
	* @param c le num&eacute;ro colones de la case
	* @param grille la grille de jeux
	*/
	public void Changements(int l, int c, Partie grille)
	{
		grille.setPartie(l,c);
		grille.Cont(l,c);
		if (grille.getBomb(l,c) == 0) 
		{
			for (int i=-1; i<2; i++) 
			{
				if((i+l)>=0 && (i+l)<grille.getLignes())
				{
					for (int k=-1; k<2; k++) 
					{
						if ((k+c)>=0 && (k+c)<grille.getColonnes() && grille.getPartie(l+i,c+k)==0) 
						{
							int x, y;
							x=i+l;
							y=k+c;
							Changements(x,y,grille);
						}
					}
				}
			}
		}
	}
}