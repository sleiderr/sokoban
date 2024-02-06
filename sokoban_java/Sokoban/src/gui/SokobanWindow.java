package gui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.IController;
import entity.Direction;


/**
 * Fenêtre de l'IHM pour le jeu Sokoban
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
@SuppressWarnings("serial")
public class SokobanWindow extends JFrame implements KeyListener {

	static final int IMAGE_SIZE = 32;
    
    private static final int WINDOW_WIDTH = 10 * IMAGE_SIZE;
	private static final int WINDOW_HEIGHT = 10 * IMAGE_SIZE;
    private static final int WINDOW_TITLE_HEIGHT = 20;
    private IController controller;

    public SokobanWindow( IController controller ) {
        this.controller = controller;

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setPreferredSize( new Dimension( WINDOW_WIDTH, WINDOW_HEIGHT + WINDOW_TITLE_HEIGHT ));
        this.setTitle( "Sokoban" );

        this.add( new SokobanPanel( controller ));
        this.addKeyListener( this );

        this.pack();
        this.setVisible( true );
    }

    @Override
    public void keyTyped( KeyEvent e ) {
        // nothing
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        Direction direction = switch( e.getKeyCode() ) {
            case KeyEvent.VK_UP    -> Direction.UP;
            case KeyEvent.VK_DOWN  -> Direction.DOWN;
            case KeyEvent.VK_LEFT  -> Direction.LEFT;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            default                -> Direction.NULL;
        };
        
        if( direction == null ) {
        	System.out.println("error");
        }
        
        controller.action( direction );
        if( controller.isFinished() ) {
        	repaint();
            JOptionPane.showMessageDialog( this, "Vous avez gagné !" );
            System.exit( 0 );
        }
        repaint();
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        // nothing
    }

}
