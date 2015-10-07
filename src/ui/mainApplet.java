/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import java.awt.Dimension;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author robocup
 */
public class mainApplet extends JApplet {

    /**
     * Initialization method that will be called after the MainApplet is loaded
     * into the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                public void run() {

                createGUI();

                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    private void createGUI() {
        //Create and set up the content pane.
        //MainFrame x = new MainFrame();
        //JPanel newContentPane = (JPanel) x.getContentPane();
        MainPanel newContentPane = new MainPanel();

        int preferedWidth = newContentPane.getWidth();
        int preferedHeight = newContentPane.getHeight();

        //setSize(new Dimension(400, 800)    );
        
        //System.out.println(newContentPane.getPreferredSize());
        newContentPane.setVisible(true);
        newContentPane.setOpaque(true);
        
        setContentPane(newContentPane);
        setVisible(true);
        
    }

}



