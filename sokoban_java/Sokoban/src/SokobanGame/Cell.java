package SokobanGame;
import control.CaseContent;


public abstract class Cell {
	private int row;
	private int column;
    public boolean isTarget;

    public Cell(boolean target, int row, int column) {
        this.isTarget=target;
        this.row=row;
        this.column=column;
    }
    public abstract CaseContent getCaseContent();
    public int getRow () {return row;};
    public int getColumn () {return column;}
	public void setRow(int row) {
		this.row = row;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public boolean isTarget() {
		return isTarget;
	}
	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}
	public String toString() {
		return column + " " + row + " " + isTarget;
		}
}