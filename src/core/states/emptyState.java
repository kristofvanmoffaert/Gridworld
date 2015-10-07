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
public class emptyState extends gridState {

    public emptyState(int x, int y) {

        super(x, y);
        type = stateType.EMPTY;

    }

     public emptyState copy() {
        return new emptyState(x_location, y_location, isEmptyCell, isVisitable, imageUrl, stateReward, actions, type);

    }

     public emptyState(int x, int y, boolean isE, boolean isV, String im, double StateR, Vector<Action> a, stateType t) {
        super(x, y, isE, isV, im, StateR, a, t);
        type = t;
        //System.out.println(actions.size());
        /*
        isEmptyCell = isE;
        isVisitable = isV;
        imageUrl = im;
        stateReward = StateR;
            //Action[] bla = new Action[a.size()];
            //a.copyInto(bla);
            actions = new Vector<Action>();
            for (int i=0; i < a.size(); i++)
                actions.add(a.get(i).copy());

        type = t; */
    }

         public String toString() {
        return "Empty on " + getX() + " " + getY();
    }

}
