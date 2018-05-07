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

Test.class: Test.java Partie.class
        $(JC) Test.java

default: all

classes: $(Classes:.class=.java)

clean:
        $(rm) *.class
        .PHONY: clean all