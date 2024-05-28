# Java compiler
JAVAC = javac
# Java documentation tool
JAVADOC = javadoc
# Source directory
SRC_DIR = .
# Output directory for compiled classes
OUT_DIR = ../out/production/HW7_Demo
# Documentation output directory
DOC_DIR = ../doc

# List of all Java source files
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Rule to compile all Java files
all: $(SOURCES)
	@$(JAVAC) -d $(OUT_DIR) $(SOURCES)

# Rule to clean the output directory
clean:
	@rm -rf $(OUT_DIR)/*.class $(DOC_DIR) $(OUT_DIR)/input.txt

# Rule to run the main class with input file
run: all
	java -Xint -cp $(OUT_DIR) Main $(OUT_DIR)/input.txt

# Rule to compile and run
build_and_run: clean all run

# Rule to generate Javadoc
javadoc:
	@$(JAVADOC) -d $(DOC_DIR) $(SOURCES)

# Rule to generate random input file
generate_input: all
	@java -cp $(OUT_DIR) GenerateRandomInput

# Rule to clean, compile, generate documentation, and run
full_build_and_run: clean all javadoc generate_input run

.PHONY: all clean run build_and_run javadoc generate_input full_build_and_run

