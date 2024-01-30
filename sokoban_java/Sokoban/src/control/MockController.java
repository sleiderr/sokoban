package control;

import entity.Direction;

/**
 * Controleur bouchon pour tester l'IHM du jeu Sokoban
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public class MockController implements IController {

	@Override
	public void action(Direction direction) {
		System.out.println("Direction : " + direction.name());
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public int getNbLines() {
		return 5;
	}

	@Override
	public int getNbColumns() {
		return 6;
	}

	@Override
	public CaseContent getContent(int l, int c) {
		switch(l) {
		case 0:
		    return CaseContent.WALL;
		case 1:
			if ((c == 0) || (c == 5)) return CaseContent.WALL;
			return CaseContent.EMPTY_FLOOR;
		case 2:
			if ((c == 0) || (c == 5)) return CaseContent.WALL;
			if (c == 1) return CaseContent.MAN;
			if (c == 3) return CaseContent.BOX;
			return CaseContent.EMPTY_FLOOR;
		case 3:
			if ((c == 0) || (c == 5)) return CaseContent.WALL;
			if (c == 4) return CaseContent.GOAL;
			return CaseContent.EMPTY_FLOOR;
		case 4:
			return CaseContent.WALL;
		}
		return CaseContent.EMPTY_FLOOR;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
