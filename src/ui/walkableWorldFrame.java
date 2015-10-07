/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.algo.basicAlgorithm;
import ui.grids.Editablegrid;
import ui.grids.wind;
import core.gridMatrix;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.grids.Policygrid;
import ui.grids.Walkablegrid;
import ui.grids.keyboardInput;

/**
 *
 * @author robocup
 */
public class walkableWorldFrame extends JFrame {

    

    public walkableWorldFrame(gridMatrix matrix, basicAlgorithm a) {
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Walk on-line");

        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        Walkablegrid g = new Walkablegrid(matrix, a);
        pan.add(g);

        keyboardInput i = new keyboardInput(g);
        pan.add(i);

        setContentPane(pan);
        pack();


        setVisible(true);
    }
}
