package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.CaseContent;
import control.IController;


/**
 * Panneau de l'IHM pour le jeu Sokoban
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
@SuppressWarnings("serial")
public class SokobanPanel extends JPanel {

    private static final int IMAGE_SIZE = SokobanWindow.IMAGE_SIZE;

    private static EnumMap< CaseContent, Image > images;

    private IController controller;

    public SokobanPanel( IController controller ) {
        this.controller = controller;
        try {
            images = new EnumMap< CaseContent, Image >(
                Map.of(
                    CaseContent.EMPTY_FLOOR, ImageIO.read( new File( "img/EmptyFloor.jpg" )),
                    CaseContent.WALL       , ImageIO.read( new File( "img/Wall.jpg" )),
                    CaseContent.BOX        , ImageIO.read( new File( "img/Box.jpg" )),
                    CaseContent.BOX_ON_GOAL, ImageIO.read( new File( "img/BoxOnGoal.jpg" )),
                    CaseContent.GOAL       , ImageIO.read( new File( "img/Goal.jpg" )),
                    CaseContent.MAN        , ImageIO.read( new File( "img/Man.jpg" ))
                )
            );
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
    }

    public void paint( Graphics g ) {
        super.paint( g );

        // Le côté métier raisonne en [ligne, colonne]
        // Le côté IHM raisonne en [x, y]
        // Donc x <=> colonne et y <=> ligne
        for( int l = 0; l < controller.getNbLines(); ++l ) {
            for( int c = 0; c < controller.getNbColumns(); ++c ) {
                g.drawImage( images.get( controller.getContent( l, c )), c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null );
            }
        }
    }

}
