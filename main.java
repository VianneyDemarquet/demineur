import javax.swing.*;
import java.awt.*;

public class main{
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		int x=Integer.parseInt(args[0]);//nombre ligne
		int y=Integer.parseInt(args[1]);//nombre colonnes
		int z=Integer.parseInt(args[2]);//nombre bombes

		Partie grille = new Partie(x,y,z);
	}
}