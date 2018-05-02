import javax.swing.*;
import java.awt.*;

public class Test{
	public static void main(String[] args) {

		if (args.length<3) 
		{
			Partie grille = new Partie();

		}else
		{
			int lignes=Integer.parseInt(args[0]);//nombre lignes
			int colonnes=Integer.parseInt(args[1]);//nombre colonnes
			int nbbombes=Integer.parseInt(args[2]);//nombre bombes

			Partie grille = new Partie(lignes,colonnes,nbbombes);
		}
		
		while(true)
		{}
	}
}