package SokobanGame;

import control.IController;
import entity.Direction;
import gui.SokobanWindow;
import control.CaseContent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Controller extends JFrame implements KeyListener, IController, Runnable{
    private static final long serialVersionUID = 1L;
	private Board board;
    private int rows=10;
    private int columns=10;

    private char[][] level = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '#'},
            {'#', ' ', '$', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '&', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', '$', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };
    public Controller() {
        board = new Board(columns,rows,level);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                action(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                action(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                action(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                action(Direction.RIGHT);
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    @Override
    public boolean isFinished() {
        return !(board.existGoal());
    }

    @Override
    public int getNbLines() {
        return board.getRows();
    }

    @Override
    public int getNbColumns() {
        return board.getColumns();
    }
    
    @Override
    public CaseContent getContent(int line, int column) {
        return board.getCaseContent(line, column);
    }
	public void updateBoard (int playerRow, int playerColumn, int dx, int dy, boolean isBox) {
		board.setCell(new EmptyFloor(board.getCell(playerRow, playerColumn).isTarget(),playerRow, playerColumn),playerRow, playerColumn);
		board.setCell(new Player(board.getCell(playerRow+dx, playerColumn+dy).isTarget(),playerRow+dx, playerColumn+dy),playerRow+dx, playerColumn+dy);
		if (isBox) {
			board.setCell(new Box(board.getCell(playerRow+2*dx, playerColumn+2*dy).isTarget(),playerRow+2*dx, playerColumn+2*dy),playerRow+2*dx, playerColumn+2*dy);

		};
	}
		
	@Override
	public void action(Direction direction) {
		int dx = 0;
		int dy = 0;
		boolean isBox = false;
		Cell player= board.getPlayer();
		int playerRow = player.getRow();
		int playerColumn = player.getColumn();
		switch (direction) {
		case DOWN: dx=1;break;
		case UP: dx=-1; break;
		case RIGHT: dy=1; break;
		case LEFT: dy=-1; break;
		}
		switch(board.getCaseContent(playerRow+dx, playerColumn+dy))	 {			
		case BOX:
			switch(board.getCaseContent(playerRow+2*dx, playerColumn+2*dy)) {
			case EMPTY_FLOOR: isBox=true;break;
			case GOAL: isBox=true;break;
			default: dx=0;dy=0;break;
			}break;
		case BOX_ON_GOAL:
			switch(board.getCaseContent(playerRow+2*dx, playerColumn+2*dy)) {
			case EMPTY_FLOOR: isBox=true;break;
			case GOAL: isBox=true;break;
			default: dx=0;dy=0;break;
			}
			break;
		case EMPTY_FLOOR:break;
		case GOAL:break;
		case WALL: dx=0;dy=0;break;
		default:break;
		};
		updateBoard(playerRow,playerColumn,dx,dy,isBox);
		/*case DOWN:
			switch(board.getCaseContent(playerRow+1, playerColumn))	 {
			case WALL:
				break;
			case BOX:
				switch(board.getCaseContent(playerRow+2, playerColumn)) {
				case EMPTY_FLOOR:
					dx=1;
					isBox=true;
					break;
				case GOAL:
					dx=1;
					isBox=true;
					break;
				case WALL:
					break;
				default:
					break;
				}
				break;
			case EMPTY_FLOOR:
				dx=1;
				break;
			case GOAL:
				dx=1;
				break;
			default:
				break;
			}
			updateBoard(playerRow,playerColumn,dx,dy,isBox);
			break;
		case UP:
			switch(board.getCaseContent(playerRow-1, playerColumn))	 {
			case WALL:
				break;
			case BOX:
				switch(board.getCaseContent(playerRow-2, playerColumn)) {
				case EMPTY_FLOOR:
					dx=-1;
					isBox=true;
					break;
				case GOAL:
					dx=-1;
					isBox=true;
					break;
				case WALL:
					break;
				default:
					break;
				}
				break;
			
			case EMPTY_FLOOR:
				dx=-1;
				break;
			case GOAL:
				dx=-1;
				break;
			default:
				break;
			}
			updateBoard(playerRow,playerColumn,dx,dy,isBox);
			break;
		case RIGHT:
			switch(board.getCaseContent(playerRow, playerColumn+1))	 {
			case WALL:
				break;
			case BOX:
				switch(board.getCaseContent(playerRow, playerColumn+2)) {
				case EMPTY_FLOOR:
					dy=1;
					isBox=true;
				case GOAL:
					dy=1;
					isBox=true;
					break;
				case WALL:
					break;
				default:
					break;
				}			
				break;

			case EMPTY_FLOOR:
				dy=1;
				break;
			case GOAL:
				dy=1;
				break;
			default:
				break;
			}
			updateBoard(playerRow,playerColumn,dx,dy,isBox);
			break;
		case LEFT:
			switch(board.getCaseContent(playerRow, playerColumn-1))	 {
			case WALL:
				break;
			case BOX:
				switch(board.getCaseContent(playerRow, playerColumn-2)) {
				case EMPTY_FLOOR:
					dy=-1;
					isBox=true;
					break;
				case GOAL:
					dy=-1;
					isBox=true;
					break;
				case WALL:
					break;
				default:
					break;
				}
				break;

			case EMPTY_FLOOR:
				dy=-1;
				break;
			case GOAL:
				dy=-1;
				break;
			default:
				break;
			}
			updateBoard(playerRow,playerColumn,dx,dy,isBox);
			break;
		}	*/
	}
	@Override
	public void run() {
    	new SokobanWindow(new Controller());
	}
    public static void main(String[] args) {
    	SwingUtilities.invokeLater((Runnable) new runGame());
    }

}

