### AUTEURS: lerouxdu
###	abreudia  
### VARIABLES ###
 
JC = javac 
EXE = Minesweeper

#.SUFFIXES: .java .class
java = $(CLASSES:.class=.java)

#14 .class
CLASSES = \
		Case.class\
		Checkerboard.class\
		MouseListenerCase.class\
		ListenerGame.class\
		Game.class\
		Score.class\
		MinesweeperView.class\
		Background.class\
		ListenerMenu.class\
		ListenerSlider.class\
		Menu.class\
		Serialization.class\
		ListenerWindow.class\
		Main.class

### BUT PAR DEFAUT ###
 
but : ${EXE}
 
### REGLES DE DEPENDENCE ###
Case.class:

Checkerboard.class: Case.class MouseListenerCase.class Score.class Serialization.class

Score.class: Checkerboard.class Serialization.class

MouseListenerCase.class: Case.class

ListenerGame.class: Scrore.class Checkerboard.class MinesweeperView.class Menu.class

Game.class: Background.class Scrore.class Checkerboard.class

MinesweeperView.class: Game.class ListenerSlider.class

Background.class:

ListenerMenu.class: Menu.class ListenerSlider.class

ListenerSlider.class: ListenerMenu.class MinesweeperView.class

Menu.class: Background.class ListenerMenu.class	

Serialization.class: Checkerboard.class Score.class

ListenerWindow.class: Checkerboard.class MinesweeperView.class

Main.class: Menu.class


 

### REGLES DE COMPILATION ###

${EXE} : $(java)
	$(JC) -g $(java)
 
### REGLES OPTIONNELLES ###
 
clean :
	-rm -f *.class
	-rm -f ../Save/sauvegarde.dat
	clear

test : 
	java Main 

 
### BUTS FACTICES ###
 
.PHONY : but clean test
 