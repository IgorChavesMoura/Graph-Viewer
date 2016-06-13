JCC = javac
JFLAGS = -g

default: Main.class view/*.class model/*.class view/observer/*.class view/panelCenter/*.class view/panelEast/*.class view/panelSouth/*.class view/panelWest/*.class

Main.class: Main.java
	$(JCC) $(JFLAGS) Main.java

view/*.class: view/*.java
	$(JCC) $(JFLAGS) view/*.java

view/observer/*.class: view/observer/*.java
	$(JCC) $(JFLAGS) view/observer/*.java

view/panelCenter/*.class: view/panelCenter/*.java
	$(JCC) $(JFLAGS) view/panelCenter/*.java

view/panelEast/*.class: view/panelEast/*.java
	$(JCC) $(JFLAGS) view/panelEast/*.java

view/panelSouth/*.class: view/panelSouth/*.java
	$(JCC) $(JFLAGS) view/panelSouth/*.java

view/panelWest/*.class: view/panelWest/*.java
	$(JCC) $(JFLAGS) view/panelWest/*.java

#control/*.class: control/*.java
#	$(JCC) $(JFLAGS) control/*.java

model/*.class: model/*.java
	$(JCC) $(JFLAGS) model/*.java

clean:
	$(RM) *.class