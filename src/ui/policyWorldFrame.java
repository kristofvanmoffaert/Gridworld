/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.grids.Editablegrid;
import ui.grids.wind;
import core.gridMatrix;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.grids.Policygrid;

/**
 *
 * @author robocup
 */
public class policyWorldFrame extends JFrame {



    public policyWorldFrame(gridMatrix matrix, int it) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("View policy of iteration " + it);

        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        Policygrid g = new Policygrid(matrix);
        pan.add(g);

        final wind w = new wind(matrix.getWind());
        w.disableInput();
        //pan.add(w);

        setContentPane(pan);
        
        pack();


        setVisible(true);
    }
}
