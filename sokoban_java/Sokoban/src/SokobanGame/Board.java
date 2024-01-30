package SokobanGame;

import control.CaseContent;
public class Board {
	public int level;
	public int numRows;
	public int numColumns;
    private Cell[][] cells;

   
    public Board(int numRows, int numColumns, char[][] content) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cells = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (content[i][j] == '#') {cells[i][j] = new Wall(false,i,j);}
                else if (content[i][j] == '$') {cells[i][j] = new Box(false,i,j);}
                else if (content[i][j] == '.') {cells[i][j] = new EmptyFloor(true,i,j);}
                else if (content[i][j] == '&') {cells[i][j] = new Player(false,i,j);}
                else if (content[i][j] == ' ') {cells[i][j] = new EmptyFloor(false,i,j);}
            }
        }
    }
    public Cell getCell(int i,int j) {return cells[i][j];};
    public int getRows() {
        return numRows;
    }

    public int getColumns() {
        return numColumns;
    }
    public CaseContent getCaseContent (int row, int column) {
    	return cells[row][column].getCaseContent();
    }
    public void setCell(Cell cell, int i,int j) {
    	cells[i][j]=cell;
    }
   
    public Cell getPlayer() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (cells[i][j].getCaseContent() == CaseContent.MAN) {return cells[i][j];}
        }
        }
		return null;
    }
	public boolean existGoal() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (cells[i][j].getCaseContent() == CaseContent.GOAL || (cells[i][j].getCaseContent() == CaseContent.MAN && cells[i][j].isTarget)) {return true;}
            }
        }
        return false;
	}
}