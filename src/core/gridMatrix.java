/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.exceptions.invalidAmountOfObjectsException;
import core.states.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kristof
 */
public class gridMatrix {

    protected gridState[][] matrix; //stateInterface
    protected int nrOfAllowedWalls = 20;
    protected int nrOfAllowedStarts = 1;
    protected int nrOfAllowedGoals = 3;
    protected int nrOfWalls = 0;
    protected int nrOfStarts = 0;
    protected int nrOfGoals = 0;
    protected int[] wind;

    public gridMatrix(int rows, int columns) {
        matrix = new gridState[rows][columns];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                emptyState g = new emptyState(i, j);
                matrix[i][j] = g;
            }
        }

        wind = new int[columns];
        determineAllActions();
    }

    public void setWind(int[] w) {
        wind = w;
    }

    public int[] getWind() {
        return wind;
    }

    public gridMatrix copy() {
        gridMatrix m = new gridMatrix(getRows(), getColumns());

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                try {
                    m.setElement(getElement(i, j).copy());


                } catch (invalidAmountOfObjectsException ex) {
                    Logger.getLogger(gridMatrix.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return m;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public gridState getElement(int x, int y) {
        if (isInMatrix(x, y)) {
            return matrix[x][y];
        } else {
            System.out.println("(GET) Invalid cell!");
            return null;
        }
    }

    public boolean removeElement(gridState o) {
        int x = o.getX();
        int y = o.getY();
        if (isInMatrix(x, y)) {

            gridState g = new emptyState(x, y); //grid
            matrix[x][y] = g;
            decrementObjectCount(o);
            determineAllActions();
            //calculateAllNeighboringCells();
            return true;
        } else {
            System.err.println("(REM) Non-valid cell!");
            return false;
        }
    }

    public void determineAllActions() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                gridState f = getElement(i, j);
                matrix[i][j].setActions(determineActions(f));
            }
        }
    }

    public void printActions() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                System.out.print(getElement(i, j) + " can reach ");
                Vector<Action> act = getElement(i, j).getActions();
                for (int l = 0; l < act.size(); l++) {
                    System.out.print(act.get(l) + " ");
                }
                System.out.println();
            }
        }
    }

    public void resetMatrix() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                //System.out.print(getElement(i, j) + " can reach ");
                Vector<Action> act = getElement(i, j).getActions();
                for (int l = 0; l < act.size(); l++) {
                    act.get(l).resetQValue();
                }
            }
        }
    }

    public void printPolicy() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                Action a = getElement(i, j).getPolicy();
                if (a != null) {
                    System.out.println(getElement(i, j) + " wants to go to " + a.getDirection());
                }

            }
        }
    }

    private boolean isRoomForObject(int currentCount, int max) {
        return currentCount < max;
    }

    private void incrementObjectCount(gridState s) throws invalidAmountOfObjectsException {
        if (s.getType() == stateType.START) {
            if (isRoomForObject(nrOfStarts, nrOfAllowedStarts)) {
                nrOfStarts++;
            } else {
                throw new invalidAmountOfObjectsException("Only " + nrOfAllowedStarts + " start state(s) allowed");
            }
        }
        if (s.getType() == stateType.GOAL) {
            if (isRoomForObject(nrOfGoals, nrOfAllowedGoals)) {
                nrOfGoals++;
            } else {
                throw new invalidAmountOfObjectsException("Only " + nrOfAllowedGoals + " goal state(s) allowed");
            }
        }
        if (s.getType() == stateType.WALL) {
            if (isRoomForObject(nrOfWalls, nrOfAllowedWalls)) {
                nrOfWalls++;
            } else {
                throw new invalidAmountOfObjectsException("Only " + nrOfAllowedWalls + " wall(s) allowed");
            }
        }
    }

    private void decrementObjectCount(gridState s) {
        if (s.getType() == stateType.START) {
            nrOfStarts--;
        }

        if (s.getType() == stateType.GOAL) {
            nrOfGoals--;
        }


        if (s.getType() == stateType.WALL) {
            nrOfWalls--;
        }

    }



    public boolean setElement(gridState o) throws invalidAmountOfObjectsException {
        int x = o.getX();
        int y = o.getY();
        if (isInMatrix(x, y) && isEmptyCell(x, y)) {
            incrementObjectCount(o);
            matrix[x][y] = o;
            determineAllActions();
            //calculateAllNeighboringCells();
            return true;
        } else {
            System.err.println("(SET) Non-empty cell or non-valid cell! ");
            return false;
        }
    }

    public startState getStartState() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (getElement(i, j).getType() == stateType.START) {
                    return (startState) getElement(i, j);
                }
            }
        }
        System.err.println("No Start State found!");
        return null;
    }
 

    public boolean isEmptyCell(int x, int y) {
        return getElement(x, y).isEmptyCell();
    }

    public boolean isVisitable(int x, int y) {
        return getElement(x, y).isVisitable();
    }



    private Vector<Action> determineActions(gridState o) {
        Vector<Action> neighbors = new Vector<Action>();
        int x = o.getX();
        int y = o.getY();
        int windiness = wind[x];
            if (isVisitable(x, y) && getElement(x, y).getType() != stateType.GOAL) {

                if (isInMatrix(x + 1 - windiness, y) && isVisitable(x + 1- windiness, y)) {
                    // get corresponding q-value, will be the default value if direction not present and the previous q if existing
                    Double Q = o.getDirectoryQ(Direction.DOWN);
                    Double E = o.getDirectoryE(Direction.DOWN);
                    neighbors.add(new Action(getElement(x + 1- windiness, y ), Direction.DOWN, getElement(x + 1- windiness, y).getStateReward(), Q, E));
                }

                if (isInMatrix(x - windiness, y + 1) && isVisitable(x- windiness, y + 1)) {
                    Double Q = o.getDirectoryQ(Direction.RIGHT);
                    Double E = o.getDirectoryE(Direction.RIGHT);
                    neighbors.add(new Action(getElement(x- windiness, y + 1), Direction.RIGHT, getElement(x- windiness, y + 1).getStateReward(), Q, E));
                }
                if (isInMatrix(x - 1- windiness, y) && isVisitable(x - 1- windiness, y)) {
                    Double Q = o.getDirectoryQ(Direction.UP);
                    Double E = o.getDirectoryE(Direction.UP);
                    neighbors.add(new Action(getElement(x - 1- windiness, y), Direction.UP, getElement(x - 1- windiness, y).getStateReward(), Q, E));
                }

                if (isInMatrix(x- windiness, y - 1) && isVisitable(x- windiness, y - 1)) {
                    Double Q = o.getDirectoryQ(Direction.LEFT);
                    Double E = o.getDirectoryE(Direction.LEFT);
                    neighbors.add(new Action(getElement(x- windiness, y - 1), Direction.LEFT, getElement(x- windiness, y - 1).getStateReward(), Q, E));
                }

            } //absorbing state
            else if (getElement(x, y).getType() == stateType.GOAL) {
                neighbors.add(new Action(getElement(x, y), Direction.NONE, getElement(x, y).getStateReward()));
            }

        
        return neighbors;
    }

    private boolean isReachable(gridState from, gridState to) {
        int fromX = from.getX();
        int fromY = from.getY();

        int toX = to.getX();
        int toY = to.getY();

        return (fromX + 1 == toX && toY == fromY)
                || (fromX == toX && toY == fromY + 1)
                || (fromX - 1 == toX && toY == fromY)
                || (fromX == toX && toY == fromY - 1)
                || (fromX == toX && fromY == toY)
                ;
    }

    private boolean isInMatrix(int x, int y) {
        return x < getRows() && x > -1 && y < getColumns() && y > -1;
    }

    public void initMatrix() {
        try {
            //new gridMatrix(7, 7);
            //initAllActions();
            //startState agent1 = new startState(2, 2);
            //wallState wall1 = new wallState(2, 4);
            //goalState goal1 = new goalState(5, 5);
            startState agent1 = new startState(0, 0);
            
            goalState goal1 = new goalState(5, 5);
            
            wallState wall1 = new wallState(0, 2);
            wallState wall2 = new wallState(0, 3);
            wallState wall3 = new wallState(1, 3);
            wallState wall4 = new wallState(2, 4);
            wallState wall5 = new wallState(3, 0);
            wallState wall6 = new wallState(4, 4);
            wallState wall7 = new wallState(5, 4);
            wallState wall8 = new wallState(4, 5);
            wallState wall9 = new wallState(6, 4);

            setElement(goal1);
            
            setElement(wall1);
            setElement(wall2);
            setElement(wall3);
            setElement(wall4);
            setElement(wall5);
            setElement(wall6);
            setElement(wall7);
            setElement(wall8);
            setElement(wall9);

            setElement(agent1);
            //determineAllActions();

        } catch (invalidAmountOfObjectsException ex) {
            Logger.getLogger(gridMatrix.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
