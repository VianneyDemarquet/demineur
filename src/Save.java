import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * La classe <code>Save</code> est utilis&eacute;e pour effectu&eacute;
 * sauvegarde d'une partie.
 *  
 * @author Vianney Demarquet
 * @version 0.1
 */
public class Save {
	/**
	* Constructeur sauvegarde la partie en cour.
	*
	* @param l le nombre de lignes de la partie
	* @param c le nombre de colonnes de la partie
	* @param grille la grille de jeux
	*
	* @throws FileNotFoundException si fichier non trouver
	* @throws IOException si probl&eacute;me lors de la lecture
	*/
	public Save(int l, int c,Partie grille)
	{
		try
		{
			DataOutputStream flux = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("foo.bin"))));
			try
			{
				flux.writeInt(l);
				flux.writeInt(c);
				flux.writeInt(grille.getCase_libre());
				flux.writeInt(grille.getNbbombes());
				flux.writeInt(grille.getDrapeaux());

				//sauvegarde pos bombe
				for (int i=0; i<l; i++) 
				{
					for (int k=0; k<c; k++) 
					{
						flux.writeInt(grille.getBomb(i,k));
					}
				}
				

				//sauvegarde état partie
				for (int i=0; i<l; i++) 
				{
					for (int k=0; k<c; k++) 
					{
						flux.writeInt(grille.getPartie(i,k));
					}
				}
				flux.close();
			}catch(IOException exception)
			{
				Object[] choix = {"Ok"};
 
				int reponse = JOptionPane.showOptionDialog(null,
				"Erreur lors de l'écriture\n"+exception.getMessage(),
				"Erreur",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				choix,
				null);

				if (reponse == 0)
				{
					System.exit(0);
				}
			}

			
		}catch(FileNotFoundException exception)
		{
			Object[] choix = {"Ok"};
 
			int reponse = JOptionPane.showOptionDialog(null,
			"Fichier non trouver\n"+exception.getMessage(),
			"Erreur",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			choix,
			null);
			if (reponse == 0)
			{
				System.exit(0);
			}
		}
	}

	
}