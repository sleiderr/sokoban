package SokobanGame;

import control.CaseContent;


public class Player extends Movable {
	private int row;
	private int column;
	public Player(boolean target, int row, int column) {
		super(target, row, column);
		// TODO Auto-generated constructor stub
	}
    public CaseContent getCaseContent() {
        return CaseContent.MAN;
    }
}
