/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.grids.Editablegrid;
import ui.grids.wind;
import core.gridMatrix;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author robocup
 */
public class editableWorldFrame extends JFrame {


    public editableWorldFrame(gridMatrix matrix) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Edit world");

        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        Editablegrid g = new Editablegrid(matrix);
        pan.add(g);
        final wind w = new wind(matrix.getWind());
        //pan.add(w);

        setContentPane(pan);
        setSize(pan.getPreferredSize());
        //f.setResizable(false);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                w.copyWind();
            }
        });
        pack();
        setVisible(true);
    }
}
