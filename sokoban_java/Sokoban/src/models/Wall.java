package models;

import control.CaseContent;

public class Wall extends Cell {
	public Wall(boolean target, int row, int column) {
		super(target, row, column);
		// TODO Auto-generated constructor stub
	}

    private boolean isTarget;
    @Override
    public CaseContent getCaseContent() {
        return CaseContent.WALL;
    }
    public boolean isTarget() {return isTarget;};
}