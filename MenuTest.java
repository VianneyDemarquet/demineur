import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import java.io.*;


public class MenuTest extends JFrame implements ActionListener, MouseListener{
  private JFrame fenetre;
  private JFrame choix;
  private int nb_ligne = 4;
  private int nb_colonne = 4;
  private int nb_bombe= 1;
  private JLabel ligne;
  private JLabel colonne;
  private JLabel bombe; 

  public MenuTest()
  {
    super("MenuTest");
    fenetre = new JFrame();
    GridLayout gestionnaire = new GridLayout(0,1);
    gestionnaire.setHgap(35); 
    gestionnaire.setVgap(35);   
    boolean var=Continuer();
    fenetre.setLayout(gestionnaire);
    JButton b1 = new JButton("Nouvelle partie");
    JButton b2 = new JButton("Continuer la partie");
    JButton b3 = new JButton("Quitter");
    b1.addActionListener(this);
    if (var == false){
      b2.setBackground(new Color(230, 230, 230));
    }else
    {
      b2.addActionListener(this);
    }

    b3.addActionListener(this);
    fenetre.add(b1);
    fenetre.add(b2);
    fenetre.add(b3);
    fenetre.setSize(800, 800);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fenetre.setVisible(true);
  }

  private boolean Continuer()
  {
    try
    {
      DataInputStream flux = new DataInputStream(new FileInputStream(new File("foo.bin")));
      try{
        flux.close();
      }catch(IOException exception)
      {
        return false;
      }
    }catch(FileNotFoundException exception)
    {
      return false;
    }
    return true;
  }

  public void nouvellePartie(){
    choix = new JFrame();
    GridLayout gestionnaire = new GridLayout(4,5);
    choix.setLayout(gestionnaire);
    ligne = new JLabel(nb_ligne+" ligne",JLabel.CENTER); 
    colonne = new JLabel(nb_colonne+" colonne",JLabel.CENTER); 
    bombe = new JLabel(nb_bombe+" bombe",JLabel.CENTER); 


    JButton plus_colonne = new JButton("+");
    JButton moins_colonne = new JButton("-");
    plus_colonne.setName("plus_colonne");
    moins_colonne.setName("moins_colonne");

    JButton plus_ligne = new JButton("+");
    JButton moins_ligne = new JButton("-");
    plus_ligne.setName("plus_ligne");
    moins_ligne.setName("moins_ligne");

    JButton max_colonne = new JButton("Max");
    JButton min_colonne = new JButton("Min");
    max_colonne.setName("max_colonne");
    min_colonne.setName("min_colonne");

    JButton max_ligne = new JButton("Max");
    JButton min_ligne = new JButton("Min");
    max_ligne.setName("max_ligne");
    min_ligne.setName("min_ligne");

    JButton plus_bombe = new JButton("+");
    JButton moins_bombe = new JButton("-");
    plus_bombe.setName("plus_bombe");
    moins_bombe.setName("moins_bombe");

    JButton max_bombe = new JButton("Max");
    JButton min_bombe = new JButton("Min");
    max_bombe.setName("max_bombe");
    min_bombe.setName("min_bombe");

    JButton valider = new JButton("Valider");
    valider.setName("Valider");
    valider.addMouseListener(this);
    JLabel l1 = new  JLabel("");
    JLabel l2 = new  JLabel("");
    JLabel l3 = new  JLabel("");
    JLabel l4 = new  JLabel("");

    

    plus_colonne.addMouseListener(this);


    moins_colonne.addMouseListener(this);



    plus_ligne.addMouseListener(this);
    

    moins_ligne.addMouseListener(this);
    

    min_colonne.addMouseListener(this);
    max_colonne.addMouseListener(this);

    min_ligne.addMouseListener(this);
    max_ligne.addMouseListener(this);

    min_bombe.addMouseListener(this);
    max_bombe.addMouseListener(this);
    plus_bombe.addMouseListener(this);
    moins_bombe.addMouseListener(this);

    choix.add(min_colonne);
    choix.add(moins_colonne);
    choix.add(colonne);
    choix.add(plus_colonne);
    choix.add(max_colonne);

    choix.add(min_ligne);
    choix.add(moins_ligne);
    choix.add(ligne);
    choix.add(plus_ligne);
    choix.add(max_ligne);

    choix.add(min_bombe);
    choix.add(moins_bombe);
    choix.add(bombe);
    choix.add(plus_bombe);
    choix.add(max_bombe);

    choix.add(l1);
    choix.add(l2);
    choix.add(valider);
    choix.add(l3);
    choix.add(l4);

    choix.setSize(800, 800);
    choix.setVisible(true);
    choix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  private void rename(){
    if(nb_ligne>30){
      nb_ligne=30;
    }else if(nb_ligne<4){
      nb_ligne=4;
    }else if(nb_colonne>30){
      nb_colonne=30;
    }else if(nb_colonne<4){
      nb_colonne=4;
    }else if(nb_bombe>(nb_ligne*nb_colonne)/3){
      nb_bombe=(nb_ligne*nb_colonne)/3;
    }else if(nb_bombe<1){
      nb_bombe=1;
    }
    ligne.setText(nb_ligne+" ligne");
    bombe.setText(nb_bombe+" bombe");
    colonne.setText(nb_colonne+" colonne");
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand()=="Nouvelle partie")
    {
      fenetre.dispose();
      nouvellePartie();
    }
    else if(e.getActionCommand()=="Continuer la partie")
    {
      fenetre.dispose();
      new Partie();
    }
    else if(e.getActionCommand()=="Quitter")
    {
      System.exit(0);
    }

  }

  /**
  *Ne fais rien.
  *
  */
  public void mouseClicked(MouseEvent e){
  }     // un bouton cliqué

  /**
  *Ne fais rien.
  *
  */
  public void mouseEntered(MouseEvent e){
  }     // debut du survol

  /**
  *Ne fais rien.
  *
  */
  public void mouseExited(MouseEvent e){
  }     // fin du survol

  /**
  *Ne fais rien.
  *
  */
  public void mousePressed(MouseEvent e){
  }     // un bouton appuyé

  /**
  * Passe la case de cach&eacute; &agrave; r&eacute;v&eacute;l&eacute;
  * si on relache le clic gauche ou ajoute, change, retire le marqueur 
  * si on relache le clic droit.
  *
  * @param e case sur la quelle on a cliqu&eacute;
  */
  public void mouseReleased(MouseEvent e)
  {
    String x=e.getComponent().getName();
    if (x=="min_ligne") {
      nb_ligne=4;      
    }else if(x=="max_ligne"){
      nb_ligne=30;
    }else if(x=="moins_ligne"){
      nb_ligne--;
    }else if(x=="plus_ligne"){
      nb_ligne++;
    }else if (x=="min_colonne") {
      nb_colonne=4;      
    }else if(x=="max_colonne"){
      nb_colonne=30;
    }else if(x=="moins_colonne"){
      nb_colonne--;
    }else if(x=="plus_colonne"){
      nb_colonne++;
    }else if (x=="min_bombe") {
      nb_bombe=1;      
    }else if(x=="max_bombe"){
      nb_bombe=(nb_ligne*nb_colonne)/3;
    }else if(x=="moins_bombe"){
      nb_bombe--;
    }else if(x=="plus_bombe"){
      nb_bombe++;
    }else if (x=="Valider") {
      choix.dispose();
      new Partie(nb_ligne,nb_colonne,nb_bombe);
    }
    rename();
  }
  public static void main(String[] args)
  {
    new MenuTest();
  }

}

