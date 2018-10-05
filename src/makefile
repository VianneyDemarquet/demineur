JF = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JF) $*.java

Classes = \
		Controleur.class\
		Load.class \
		MenuTest.class \
		Partie.class \
		Changement.class \
		Save.class \
		Affichage.class \
		Test.class

Sources = \
		Controleur\
		Load.java \
		MenuTest.java \
		Partie.java \
		Changement.java \
		Save.java \
		Affichage.java \
		Test.java

Test.class: Test.java MenuTest.class Affichage.class
		$(JC) Test.java

Controleur.class: Controleur.java
		$(JC) Controleur.java
		
Load.class: Load.java Save.class Partie.class
		$(JC) Load.java

MenuTest.class: MenuTest.java Partie.class
		$(JC) MenuTest.java        

Partie.class: Partie.java
		$(JC) Partie.java

Changement.class: Changement.java Partie.class
		$(JC) Changement.java        

Save.class: Save.java Partie.class
		$(JC) Save.java

Affichage.class: Affichage.java Partie.class Controleur.class
		$(JC) Affichage.java



default: all

test:
	java Test

doc : $(Classes)
	javadoc  $(Sources) 

classes: $(Classes:.class=.java)

clean:
		rm *.class
