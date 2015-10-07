/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.states;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author kristof
 */
public abstract class gridState {//implements stateInterface

    protected String imageUrl = "images/empty3.png";
    protected boolean isEmptyCell;
    protected boolean isVisitable;
    protected int x_location;
    protected int y_location;
    protected double stateReward;
    protected Vector<Action> actions = new Vector<Action>();
    protected stateType type;

    public gridState(int x, int y) {
        x_location = x;
        y_location = y;
        isEmptyCell = true;
        isVisitable = true;
        stateReward = 0.0;
    }

    public Action getPolicy() {
        double highestQ = -1;
        Action highestA = null;

        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getQvalue() > highestQ) {
                highestQ = actions.get(i).getQvalue();
                highestA = actions.get(i);
            }
        }
        return highestA;
    }

    public boolean isEqualActionProbability() {
        if (actions.size() > 0) {
            double Q = actions.get(0).getQvalue();

            for (int i = 1; i < actions.size(); i++) {
                if (actions.get(i).getQvalue() != Q) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //public abstract gridState copy();
    public gridState(int x, int y, boolean isE, boolean isV, String im, double StateR, Vector<Action> a, stateType t) {
        x_location = x;
        y_location = y;
        isEmptyCell = isE;
        isVisitable = isV;
        imageUrl = im;
        stateReward = StateR;
        actions = new Vector<Action>();
        for (int i = 0; i < a.size(); i++) {
            actions.add(a.get(i).copy());
        }
        type = t;
    }

    public abstract gridState copy();

    // the reward of the state concerning this statetype
    public double getStateReward() {
        return stateReward;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public stateType getType() {
        return type;
    }

    public int getX() {
        return x_location;
    }

    public int getY() {
        return y_location;
    }

    public boolean isEmptyCell() {
        return isEmptyCell;
    }

    public boolean isVisitable() {
        return isVisitable;
    }

    public Vector<Action> getActions() {
        return actions;
    }

    public void setActions(Vector<Action> a) {
        actions = a;
    }

    public void printActions() {
        String s = "";
        for (int i = 0; i < actions.size(); i++) {
            s += actions.get(i) + " ";
        }
        System.out.println(s);
    }

    public Action canGoInDirection(Direction dir) {
        for (int i = 0; i < actions.size(); i++) {
            Action currentAction = actions.get(i);
            if (currentAction.getDirection() == dir) {
                return currentAction;
            }
        }
        return null;
    }

    public double getDirectoryQ(Direction dir) {
        for (int i = 0; i < actions.size(); i++) {
            Action currentAction = actions.get(i);
            if (currentAction.getDirection() == dir) {
                return currentAction.getQvalue();
            }
        }
        return 0.0;
    }

    public double getDirectoryE(Direction dir) {
        for (int i = 0; i < actions.size(); i++) {
            Action currentAction = actions.get(i);
            if (currentAction.getDirection() == dir) {
                return currentAction.getEligibility();
            }
        }
        return 0.0;
    }

    public String toString() {
        return getX() + " " + getY();
    }
}
