

public class Changement{
	public void Changements(int l, int c, Partie grille)
	{
		grille.ChangeVal(l,c);

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