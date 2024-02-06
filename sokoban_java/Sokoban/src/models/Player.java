package models;

import control.CaseContent;


public class Player extends Movable {
	public Player(boolean target, int row, int column) {
		super(target, row, column);
	}
	
    public CaseContent getCaseContent() {
        return CaseContent.MAN;
    }
}
