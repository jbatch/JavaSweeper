JFLAGS = -g -cp $(BIN) -d $(BIN)
JC = javac
BIN = bin
SRC = src

Minesweeper : $(BIN)/Skeleton.class $(BIN)/Board.class
	echo "java -cp bin Skeleton" > Minesweeper
	chmod 755 Minesweeper

$(BIN)/Skeleton.class : $(SRC)/Skeleton.java $(BIN)/Board.class
	$(JC) $(JFLAGS) $(SRC)/Skeleton.java

$(BIN)/Board.class : $(SRC)/Board.java
	$(JC) $(JFLAGS) $(SRC)/Board.java

clean:
	        $(RM) $(BIN)/*.class
		rm Minesweeper
