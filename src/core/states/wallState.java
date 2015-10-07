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
public class wallState extends gridState{// implements stateInterface


    public wallState(int x, int y) {
        super(x, y);
        imageUrl = "images/wall3.png";
        isEmptyCell= false;
        isVisitable=false;
        type = stateType.WALL;
    }

       public wallState(int x, int y, boolean isE, boolean isV, String im, double StateR, Vector<Action> a, stateType t) {
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
        type = stateType.WALL; */
    }

        public gridState copy() {
        return (wallState) new wallState(x_location, y_location, isEmptyCell, isVisitable, imageUrl, stateReward, actions, type);
    }

    public String toString() {
        return "Wall on " + super.toString();
    }

}
