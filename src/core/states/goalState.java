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
public class goalState extends gridState {//implements stateInterface

    public goalState(int x, int y) {
        super(x, y);
        imageUrl = "images/goal2.png";
        isEmptyCell = false;
        stateReward = 3.0;
        type = stateType.GOAL;
    }

    public goalState(int x, int y, double r) {
        super(x, y);
        imageUrl = "images/goal2.png";
        isEmptyCell = false;
        stateReward = r;
        type = stateType.GOAL;
    }

    public goalState(int x, int y, boolean isE, boolean isV, String im, double StateR, Vector<Action> a, stateType t) {
        super(x, y, isE, isV, im, StateR, a, t);
        type = t;/*
        //x_location = x;
        //y_location = y;
        isEmptyCell = isE;
        isVisitable = isV;
        imageUrl = im;
        stateReward = StateR;
        actions = new Vector<Action>();
        for (int i=0; i < a.size(); i++)
        actions.add(a.get(i).copy());
        type = stateType.GOAL; */
    }

    public goalState copy() {
        return new goalState(x_location, y_location, isEmptyCell, isVisitable, imageUrl, stateReward, actions, type);
    }

    public String toString() {
        return "Goal with "+ stateReward+ " reward on " + super.toString();
    }
}
