package control;

import entity.Direction;
import gui.SokobanWindow;
import models.Board;
import models.Cell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;


public class Controller implements KeyListener, IController, Runnable{
	private Board board;
    private int rows = 10;
    private int columns = 10;

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

        Direction dir = switch (keyCode) {
            case KeyEvent.VK_UP -> Direction.UP;
            case KeyEvent.VK_DOWN -> Direction.DOWN;
            case KeyEvent.VK_LEFT -> Direction.LEFT;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            default -> Direction.NULL;
        };
        
        action(dir);
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
		boolean manTarget = board.isTarget(playerRow+dx, playerColumn+dy);
		
		board.setCell(CaseContent.MAN, playerRow+dx, playerColumn+dy);
		board.setTarget(playerRow+dx,playerColumn+dy,manTarget);
		
		if (!(dx==0 && dy==0)) {
			if(board.isTarget(playerRow, playerColumn)) {
			board.setCell(CaseContent.GOAL, playerRow, playerColumn);
				board.setCell(CaseContent.EMPTY_FLOOR,playerRow,playerColumn);
			}
		}
		
		if	 (isBox && board.isTarget(playerRow + 2 * dx, playerColumn + 2 * dy)) {			
			board.setCell(CaseContent.BOX_ON_GOAL, playerRow + 2 * dx, playerColumn + 2 * dy);
		}
		else if (isBox) { 
			board.setCell(CaseContent.BOX, playerRow + 2 * dx, playerColumn + 2 * dy);
		}	
	}
		
	@Override
	public void action(Direction direction) {
		int dx = 0;
		int dy = 0;
		boolean isBox = false;
		Cell player = board.getPlayer();
		int playerRow = player.getRow();
		int playerColumn = player.getColumn();
		
		switch (direction) {
			case DOWN: dx=1;break;
			case UP: dx=-1; break;
			case RIGHT: dy=1; break;
			case LEFT: dy=-1; break;
			default: break;
		}
		
		switch(board.getCaseContent(playerRow+dx, playerColumn+dy))	 {			
		case BOX:
			switch(board.getCaseContent(playerRow+2*dx, playerColumn+2*dy)) {
			case EMPTY_FLOOR: 
				isBox=true;
				break;
			case GOAL: 
				isBox=true;
				break;
			default: 
				dx=0;
				dy=0;
				break;
			}
			break;
			
		case BOX_ON_GOAL:
			switch(board.getCaseContent(playerRow+2*dx, playerColumn+2*dy)) {
			case EMPTY_FLOOR: 
				isBox=true;
				break;
			case GOAL: 
				isBox=true;
				break;
			default: 
				dx=0;
				dy=0;
				break;
			}
			break;
		case WALL: 
			dx=0;
			dy=0;
			break;
		default:
			break;
		};
		
		updateBoard(playerRow,playerColumn,dx,dy,isBox);
	}
	
	@Override
	public void run() {
    	new SokobanWindow(new Controller());
	}
	
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Controller());
    }

}

