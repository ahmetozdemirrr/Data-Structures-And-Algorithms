# Makefile

.SILENCE:

# Compiler
JC = javac

# Flags
JFLAGS = -classpath

JD = javadoc

JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc

RM = rm

JR = java

# Java class files
CLASSES = \
		Main.java \
		Inventory.java \
		ElectronicDevice.java \
		Device.java \
		Monitor.java \
		Laptop.java \
		VRGlass.java \
		Smartphone.java \
		TV.java

all: Main.class

Main.class: Main.java
	$(JC) $(JFLAGS) . $<

run:
	$(JR) Main

classe: $(CLASSES:.java=.class)

%.class : %.java 
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java

clean:
	$(RM) *.class

cleandoc:
	$(RM) -rf javadoc