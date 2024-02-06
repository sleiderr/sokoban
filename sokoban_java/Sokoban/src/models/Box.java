package models;
import control.CaseContent;


public class Box extends Movable {
    public Box(boolean target, int row, int column) {
        super(target, row, column);
    }
    
    public CaseContent getCaseContent() {
    	if (isTarget()) {
    		return CaseContent.BOX_ON_GOAL;
    	} else {
    		return CaseContent.BOX;
    	}
    }
}