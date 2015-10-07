/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.grids;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author robocup
 */
public class wind extends JPanel {

    public static int N = 7;
    //public static final int SIZE = 75;
    gridMenu menu;
    int[] windVector;
    JSpinner[] models;

    public wind(int[] f) {
        super();
        N = f.length;
        models = new JSpinner[N];
        windVector = f;
        setLayout(new GridLayout(1, N));
        this.setPreferredSize(new Dimension(600, 30));
        setMaximumSize(new Dimension(3000, 30));
        //for (int i = 0; i < N * N; i++) {
        //    this.add(new gridLabel("", menu));
        //}

        //step



        for (int j = 0; j < N; j++) {
            SpinnerModel model = new SpinnerNumberModel(f[j], //initial value
                    0, //min
                    N, //max
                    1);
            
            JSpinner t = new JSpinner(model);
            models[j] = t;
            add(t);
        }
    }

    public void copyWind() {
        for(int i=0; i < models.length; i++) {
            windVector[i] = (Integer)models[i].getValue();
        }
    }

    public void disableInput() {
        for(int i=0; i < models.length; i++) {
            models[i].setEnabled(false);
        }
    }

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame("Grid World");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                int[] a = new int[7];
                f.add(new wind(a));
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);


            }
        });
    }
     
}
