package SokobanGame;

import javax.swing.SwingUtilities;

import gui.SokobanWindow;

/**
 * Application de test de l'IHM du jeu Sokoban en utilisant le controleur bouchon
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public class runGame implements Runnable {

	@Override
	public void run() {
    	new SokobanWindow(new Controller());
	}

    public static void main(String[] args) {
    	SwingUtilities.invokeLater((Runnable) new runGame());
    }
}
