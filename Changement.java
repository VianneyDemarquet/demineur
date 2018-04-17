

public class Changement{
	public void Changements(int l, int c, Partie grille)
	{
		System.out.println("\n\n l: "+" c: "+"\n");
		grille.ChangeVal(l,c);

		if (grille.grille_bombe[l][c] == 0) 
		{
			for (int i=-1; i<2; i++) 
			{
				if((i+l)>=0 && (i+l)<grille.lignes)
				{
					for (int k=-1; k<2; k++) 
					{
						System.out.println("\n\n l: "+(l+i)+" c: "+(c+k)+"\n");
						if ((k+c)>=0 && (k+c)<grille.colonnes && grille.grille_partie[l+i][c+k]==0) 
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