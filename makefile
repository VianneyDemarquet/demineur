JF = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JF) $*.java

Classes = \
		Load.class \
		MenuTest.class \
		Partie.class \
		Changement.class \
		Save.class \
		Affichage.class \
		Test.class

Sources = \
		Load.java \
		MenuTest.java \
		Partie.java \
		Changement.java \
		Save.java \
		Affichage.java \
		Test.java

Test.class: Test.java MenuTest.class
		$(JC) Test.java
		
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

Affichage.class: Affichage.java Partie.class
		$(JC) Affichage.java



default: all

test:
	java Test

doc : $(Classes)
	javadoc  $(Sources) 

classes: $(Classes:.class=.java)

clean:
		rm *.class
