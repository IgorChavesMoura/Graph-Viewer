JCC = javac
JAVA = java
JFLAGS = -g
COL = -Xlint

default: Main.class view/*.class model_control/*.class observer/*.class thread/*.class

Main.class: Main.java
	$(JCC) $(JFLAGS) Main.java

model_control/*.class: model_control/*.java
	$(JCC) $(JFLAGS) model_control/*.java

view/*.class: view/*.java
	$(JCC) $(JFLAGS) view/*.java

observer/*.class: observer/*.java
	$(JCC) $(JFLAGS) observer/*.java

thread/*.class: thread/*.java
	$(JCC) $(JFLAGS) thread/*.java

clean:
	$(RM) *.class
