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
                if (content[i][j] == '#') {this.setCell(CaseContent.WALL, i, j);}
                else if (content[i][j] == '$') {this.setCell(CaseContent.BOX, i, j);}
                else if (content[i][j] == '.') {this.setCell(CaseContent.GOAL, i, j);}
                else if (content[i][j] == '&') {this.setCell(CaseContent.MAN, i, j);}
                else if (content[i][j] == ' ') {this.setCell(CaseContent.EMPTY_FLOOR, i, j);}
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
    public void setCell(CaseContent content, int i,int j) {
    	switch(content) {
    	case BOX:
    		this.cells[i][j] = new Box(false,i,j);break;
    	case BOX_ON_GOAL:
    		this.cells[i][j] = new Box(true,i,j);break;
    	case WALL:
    		this.cells[i][j] = new Wall(false,i,j);break;
    	case GOAL:
    		cells[i][j] = new EmptyFloor(true,i,j);break;
    	case EMPTY_FLOOR:
    		cells[i][j] = new EmptyFloor(false,i,j);break;
    	case MAN:
    		cells[i][j] = new Player(false,i,j); break;   	
    	}
    }
    
    
    public boolean isTarget(int i,int j) {
    	return cells[i][j].isTarget();
    }
    public void setTarget(int i, int j, boolean isTarget) {
    	cells[i][j].setTarget(isTarget);
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