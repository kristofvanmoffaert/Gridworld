package ui.grids;


import core.gridMatrix;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class Editablegrid extends JPanel {
    //public static final int N = 8;
    public static final int SIZE = 75;
    gridMenu menu;
    gridMatrix matrix;
    //JLabel[][] labelMatrix;

    public Editablegrid(gridMatrix m) {
        super(new GridLayout(m.getRows(), m.getColumns()));
        matrix = m;
        matrix.printActions();
        menu = new gridMenu(this);
        this.setPreferredSize(new Dimension(m.getRows() * SIZE, m.getColumns() * SIZE));
        //for (int i = 0; i < N * N; i++) {
        //    this.add(new editableGridLabel("", menu));
        //}

        //labelMatrix = new JLabel[matrix.getRows()][matrix.getColumns()];

        for (int i=0; i < matrix.getRows(); i++) {
            for (int j=0; j < matrix.getColumns(); j++) {
                editableGridLabel label = new editableGridLabel(matrix.getElement(i, j), menu);
                add(label);
                //labelMatrix[i][j] = label;
            }
        }

    }
/*
         public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame();
                gridMatrix ma = new gridMatrix(8, 8);
                ma.initMatrix();
                Editablegrid m = new Editablegrid(ma);
                f.setContentPane(m);
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(m.getPreferredSize());
                f.setVisible(true);
            }
        });
    }
*/
    




}
