/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.grids;

import core.exceptions.invalidAmountOfObjectsException;
import core.states.emptyState;
import core.states.startState;
import core.states.goalState;
import core.states.gridState;
import core.states.wallState;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author kristof
 */
public class gridMenu extends JPopupMenu {

    Editablegrid gridworld;
    editableGridLabel clickedLabel;
    gridMenu copy;

    public gridMenu(Editablegrid g) {
        super();
        gridworld = g;
        initItems();
        copy = this;
    }

    public void show(editableGridLabel clicked, Component c, int x, int y) {
        super.show(c, x, y);
        clickedLabel = clicked;

    }

    public void initItems() {
        JMenu submenu = new JMenu("Add item");

        JMenuItem item = new JMenuItem("Add Goalstate");
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Add goalstate");
                int x = clickedLabel.getObject().getX();
                int y = clickedLabel.getObject().getY();
                
                //goalInputFrame f = new goalInputFrame();
                //f.setVisible(true);
                //f.setAlwaysOnTop(true);
                goalStateInputs d = new goalStateInputs(null, true);
                d.setVisible(true);

                int reward = d.getReward();
                System.out.println("rew "  + reward);
                
                goalState goal = new goalState(x, y, reward);
                try {
                    gridworld.matrix.setElement(goal);
                    clickedLabel.setObject(goal);
                    clickedLabel.repaint();
                } catch (invalidAmountOfObjectsException ex) {
                    //System.out.println(ex.getError());
                    JOptionPane.showMessageDialog(null, ex.getError(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        JMenuItem item2 = new JMenuItem("Add Wall");
        item2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Wall");
                int x = clickedLabel.getObject().getX();
                int y = clickedLabel.getObject().getY();

                try {
                    wallState wall = new wallState(x, y);

                    gridworld.matrix.setElement(wall);
                    clickedLabel.setObject(wall);
                    clickedLabel.repaint();
                    gridworld.matrix.printActions();
                } catch (invalidAmountOfObjectsException ex) {
                    //System.out.println(ex.getError());
                    JOptionPane.showMessageDialog(null, ex.getError(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        JMenuItem item3 = new JMenuItem("Add StartState");
        item3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Start");
                int x = clickedLabel.getObject().getX();
                int y = clickedLabel.getObject().getY();



                try {
                    startState agent = new startState(x, y);
                    gridworld.matrix.setElement(agent);
                    clickedLabel.setObject(agent);
                    clickedLabel.repaint();
                    gridworld.matrix.printActions();
                } catch (invalidAmountOfObjectsException ex) {
                    //System.out.println(ex.getError());
                    JOptionPane.showMessageDialog(null, ex.getError(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        submenu.add(item);
        submenu.add(item2);
        submenu.add(item3);


        JMenuItem item4 = new JMenuItem("Remove Item");
        item4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Remove Item");
                int x = clickedLabel.getObject().getX();
                int y = clickedLabel.getObject().getY();

                gridState obj = new emptyState(x, y);

                gridworld.matrix.removeElement(clickedLabel.getObject());
                clickedLabel.setObject(obj);

                clickedLabel.repaint();
            }
        });

        add(submenu);
        add(item4);
    }
}
