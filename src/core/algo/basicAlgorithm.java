/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algo;

import core.algo.settings.basicSettings;
import core.gridMatrix;
import core.logger;
import core.states.Action;
import core.states.gridState;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author robocup
 */
public abstract class basicAlgorithm {

    protected gridMatrix matrix;
    protected Random r = new Random();
    protected basicSettings settings;
    protected int it;
    protected String output;
    protected logger theLogger;

    public basicAlgorithm(gridMatrix m) {
        matrix = m;
        settings = new basicSettings();
        output = "";
        theLogger = logger.getInstance();
    }

    public basicSettings getSettings() {
        return settings;
    }

    public void setSettings(basicSettings s) {
        settings = s;
    }

    public int getIterationCount() {
        return it;
    }
    
    protected boolean convergence2(gridMatrix oldM, gridMatrix newM) {
        for (int i = 0; i < oldM.getRows(); i++) {
            for (int j = 0; j < oldM.getColumns(); j++) {
                gridState oldElement = oldM.getElement(i, j);
                gridState newElement = newM.getElement(i, j);
                for (int l = 0; l < oldElement.getActions().size(); l++) {
                    Vector<Action> oldActions = oldElement.getActions();
                    Vector<Action> newActions = newElement.getActions();

                    //if (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue()) > maxDifference) {
                    //    maxDifference = Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue());
                    //}
                    if (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue()) > settings.getDelta()) {

                        //System.out.println("diff = " + (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue())));
                        return false;
                    }
                }
            }
        }
        //System.out.println("                " + maxDifference + " t:" + tau);
        return true;
    }

    protected boolean convergence(gridMatrix oldM, gridMatrix newM) {
        double maxDifference = -1;

        for (int i = 0; i < oldM.getRows(); i++) {
            for (int j = 0; j < oldM.getColumns(); j++) {
                gridState oldElement = oldM.getElement(i, j);
                gridState newElement = newM.getElement(i, j);
                if (oldElement.getPolicy() != null && oldElement.getPolicy().getDirection() != newElement.getPolicy().getDirection()) {
                    //System.out.println(oldElement + " " + oldElement.getPolicy().getDirection() + " " + newElement.getPolicy().getDirection());
                    return false;
                }
                //for (int l=0; l < oldElement.getActions().size(); l++) {
                //Vector<Action> oldActions = oldElement.getActions();
                //Vector<Action> newActions = newElement.getActions();
                //if (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue()) > maxDifference)
                //    maxDifference = Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue());
                //if (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue()) > tau)   {

                //      System.out.println("diff = " + (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue())));
                //     return false;
                //}
                //}
            }
        }
        //System.out.println("                " + maxDifference + " t:" + tau);
        return true;

    }

    protected Action getGreedyAction(gridState s, Action defaultAction) {
        double maximumQ = defaultAction.getQvalue();
        Action maximumAction = defaultAction;
        //System.out.println(s + " " + s.getActions().size());
        for (int i = 0; i < s.getActions().size(); i++) {
            if (s.getActions().get(i).getQvalue() > maximumQ) {
                maximumAction = s.getActions().get(i);
                maximumQ = s.getActions().get(i).getQvalue();
            }
        }
        return maximumAction;
    }


    protected Action getGreedyAction(gridState s) {
        double maximumQ = -1 * Double.MAX_VALUE;
        Action maximumAction = null;
        //System.out.println(s + " " + s.getActions().size());
        for (int i = 0; i < s.getActions().size(); i++) {
            if (s.getActions().get(i).getQvalue() > maximumQ) {
                maximumAction = s.getActions().get(i);
                maximumQ = s.getActions().get(i).getQvalue();
            }
        }
        return maximumAction;
    }

    protected Action getEpsilonGreedyAction(gridState s) {

        if (r.nextDouble() > settings.getEpsilon()) {
            return getGreedyAction(s);
        } else {
            //int size = s.getActions().size();
            //int randomIndex = r.nextInt(size);
            //return s.getActions().get(randomIndex);
            return getRandomAction(s);
        }
    }

    protected Action getRandomAction(gridState s) {
        
        int size = s.getActions().size();
        int randomIndex = r.nextInt(size);
        return s.getActions().get(randomIndex);
    }

    public abstract String toString();

    public abstract boolean runOnce();

    public abstract void runAll();

    public abstract void runAction(gridState currentPostion, Action a);

    public String formula() {
        return "Updating Q-values";
    }
}
