package ui.grids;

import core.algo.basicAlgorithm;
import core.gridMatrix;
import core.logger;
import core.states.Action;
import core.states.Direction;
import core.states.gridState;
import core.states.startState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Walkablegrid extends JPanel {
    //public static final int N = 8;

    public static final int SIZE = 75;
    gridMatrix matrix;
    walkableGridLabel[][] labelMatrix;
    int agentX;
    int agentY;
    protected basicAlgorithm algo;
    protected Vector<walkableGridLabel> toUpdate = new Vector<walkableGridLabel>();
   

    public Walkablegrid(gridMatrix m, basicAlgorithm a) {
        super(new GridLayout(m.getRows(), m.getColumns()));
        algo = a;
        matrix = m;
        matrix.printActions();
        this.setPreferredSize(new Dimension(m.getRows() * SIZE, m.getColumns() * SIZE));
        //for (int i = 0; i < N * N; i++) {
        //    this.add(new editableGridLabel("", menu));
        //}

        labelMatrix = new walkableGridLabel[matrix.getRows()][matrix.getColumns()];

        startState s = matrix.getStartState();
        agentX = s.getX();
        agentY = s.getY();

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                walkableGridLabel label = new walkableGridLabel(matrix.getElement(i, j));
                add(label);
                labelMatrix[i][j] = label;
            }
        }
        labelMatrix[agentX][agentY].agentEntered();
        toUpdate.addElement(labelMatrix[agentX][agentY]);
    }

    public void reset() {
        labelMatrix[agentX][agentY].agentLeft();
        labelMatrix[agentX][agentY].repaint();
        startState s = matrix.getStartState();
        agentX = s.getX();
        agentY = s.getY();
        labelMatrix[agentX][agentY].agentEntered();
        labelMatrix[agentX][agentY].repaint();
        
        toUpdate.removeAllElements();
        toUpdate.addElement(labelMatrix[agentX][agentY]);
    }

    public void agentUp() {
        walkableGridLabel currentLabel = labelMatrix[agentX][agentY];
        Action upAction = currentLabel.getObject().canGoInDirection(Direction.UP);
        boolean availability = (upAction != null);
        String output = null;
        if (availability) {
            currentLabel.agentLeft();

            agentX--;
            labelMatrix[agentX][agentY].agentEntered();

            algo.runAction(currentLabel.getObject(), upAction);

  //          labelMatrix[agentX][agentY].repaint();
  //          currentLabel.repaint();

            toUpdate.addElement(labelMatrix[agentX][agentY]);

            for (int i=0; i < toUpdate.size(); i++)
                toUpdate.get(i).repaint();
/*
            for (int i = 0; i < labelMatrix.length; i++) {
                for (int j = 0; j < labelMatrix.length; j++) {
                    labelMatrix[i][j].repaint();
                }
            }

 */
            //labelMatrix[agentX][agentY].repaint();
            //currentLabel.repaint();

        }
        //return output;
    }

    public void agentDown() {
        walkableGridLabel currentLabel = labelMatrix[agentX][agentY];
        Action downAction = currentLabel.getObject().canGoInDirection(Direction.DOWN);
        boolean availability = (downAction != null);
        String output = null;
        if (availability) {
            currentLabel.agentLeft();
            //currentLabel.repaint();
            agentX++;
            labelMatrix[agentX][agentY].agentEntered();
            //labelMatrix[agentX][agentY].repaint();
            algo.runAction(currentLabel.getObject(), downAction);

  //          labelMatrix[agentX][agentY].repaint();
  //          currentLabel.repaint();

            toUpdate.addElement(labelMatrix[agentX][agentY]);

            for (int i=0; i < toUpdate.size(); i++)
                toUpdate.get(i).repaint();
/*
            for (int i = 0; i < labelMatrix.length; i++) {
                for (int j = 0; j < labelMatrix.length; j++) {
                    labelMatrix[i][j].repaint();
                }
            }

 */
            //labelMatrix[agentX][agentY].repaint();
            //currentLabel.repaint();
        }
        //return output;
    }

    public void agentLeft() {
        walkableGridLabel currentLabel = labelMatrix[agentX][agentY];
        Action leftAction = currentLabel.getObject().canGoInDirection(Direction.LEFT);
        boolean availability = (leftAction != null);
        String output = null;
        if (availability) {
            currentLabel.agentLeft();
            //currentLabel.repaint();
            agentY--;
            labelMatrix[agentX][agentY].agentEntered();
            //labelMatrix[agentX][agentY].repaint();
            algo.runAction(currentLabel.getObject(), leftAction);

  //          labelMatrix[agentX][agentY].repaint();
  //          currentLabel.repaint();

            toUpdate.addElement(labelMatrix[agentX][agentY]);

            for (int i=0; i < toUpdate.size(); i++)
                toUpdate.get(i).repaint();
/*
            for (int i = 0; i < labelMatrix.length; i++) {
                for (int j = 0; j < labelMatrix.length; j++) {
                    labelMatrix[i][j].repaint();
                }
            }

 */
            //labelMatrix[agentX][agentY].repaint();
            //currentLabel.repaint();
        }
        //return output;
    }

    public void agentRight() {
        walkableGridLabel currentLabel = labelMatrix[agentX][agentY];
        Action rightAction = currentLabel.getObject().canGoInDirection(Direction.RIGHT);
        boolean availability = (rightAction != null);
        String output = null;
        if (availability) {
            currentLabel.agentLeft();
            //currentLabel.repaint();
            agentY++;
            labelMatrix[agentX][agentY].agentEntered();
            //labelMatrix[agentX][agentY].repaint();
            algo.runAction(currentLabel.getObject(), rightAction);

  //          labelMatrix[agentX][agentY].repaint();
  //          currentLabel.repaint();

            toUpdate.addElement(labelMatrix[agentX][agentY]);

            for (int i=0; i < toUpdate.size(); i++)
                toUpdate.get(i).repaint();
/*
            for (int i = 0; i < labelMatrix.length; i++) {
                for (int j = 0; j < labelMatrix.length; j++) {
                    labelMatrix[i][j].repaint();
                }
            }

 */

        }
        //return output;
    }

    public gridState getAgentPosition() {
        return matrix.getElement(agentX, agentY);
    }
    /*
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
    JFrame f = new JFrame();
    gridMatrix ma = new gridMatrix(8, 8);
    ma.initMatrix();
    Policygrid m = new Policygrid(ma);
    f.setContentPane(m);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(m.getPreferredSize());
    f.setVisible(true);
    }
    });
    }
     */
}
