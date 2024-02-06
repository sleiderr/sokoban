package models;
import control.CaseContent;


public class EmptyFloor extends Cell {
    public EmptyFloor(boolean isTarget, int row, int column) {
		super(isTarget, row, column);
	}
    
    public CaseContent getCaseContent() {
    	if (isTarget()) {
    		return CaseContent.GOAL;
    	} else {
    		return CaseContent.EMPTY_FLOOR;
    	}
    }
}