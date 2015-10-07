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
public class startState extends gridState {//implements stateInterface

    public startState(int x, int y) {
        super(x, y);
        imageUrl = "images/agent.png";
        isEmptyCell = false;
        type = stateType.START;

    }

   public startState(int x, int y, boolean isE, boolean isV, String im, double StateR, Vector<Action> a, stateType t) {
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
        type = stateType.START; */
    }

    public startState copy() {
        return new startState(x_location, y_location, isEmptyCell, isVisitable, imageUrl, stateReward, actions, type);
    }

    public String toString() {
        return "Agent on " + super.toString();
    }


}
