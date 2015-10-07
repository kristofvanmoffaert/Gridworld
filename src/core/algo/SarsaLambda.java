/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algo;

import core.algo.settings.basicSettings;
import core.gridMatrix;
import core.states.Action;
import core.states.gridState;
import core.states.startState;
import core.states.stateType;

/**
 *
 * @author robocup
 */
public class SarsaLambda extends basicAlgorithm {

    public SarsaLambda(gridMatrix m) {
        super(m);
    }

    public boolean runOnce() {
        //int it = 0;
        gridMatrix oldMatrix = matrix.copy();


        it++;
        gridState currentState = matrix.getStartState();
        Action currentAction = getRandomAction(currentState);

        while (currentState.getType() != stateType.GOAL) {

            Action nextEpsilonGreedyAction = getEpsilonGreedyAction(currentAction.getNextState());
            double delta = currentAction.getReward() + settings.getDiscountFactor() * nextEpsilonGreedyAction.getQvalue() - currentAction.getQvalue();

            currentAction.accumulateEligibility();



            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    gridState el = matrix.getElement(i, j);
                    for (int l = 0; l < el.getActions().size(); l++) {
                        double q = el.getActions().get(l).getQvalue();
                        double e = el.getActions().get(l).getEligibility();
                        el.getActions().get(l).setQvalue(q + settings.getLearningRate() * delta * e);
                        //if (nextEpsilonGreedyAction == nextGreedyAction)
                        el.getActions().get(l).setEligibility(settings.getDiscountFactor() * settings.getLambda() * e);
                        //else
                        //    el.getActions().get(l).resetEligibility();
                    }
                }
            }
            currentState = currentAction.getNextState();
            currentAction = nextEpsilonGreedyAction;
        }
        //while (!convergence2(oldMatrix, matrix));//it != 2000);//

        System.out.println("done, itertation = " + it);
        return convergence2(oldMatrix, matrix);

    }

    public void runAll() {
        //int it=0;

        gridMatrix oldMatrix;

        do {
            oldMatrix = matrix.copy();

            it++;
            gridState currentState = matrix.getStartState();
            Action currentAction = getRandomAction(currentState);
            //currentState = currentAction.getNextState();


            while (currentState.getType() != stateType.GOAL) {

                Action nextEpsilonGreedyAction = getEpsilonGreedyAction(currentAction.getNextState());
                double delta = currentAction.getReward() + settings.getDiscountFactor() * nextEpsilonGreedyAction.getQvalue() - currentAction.getQvalue();

                currentAction.accumulateEligibility();
                //currentAction.replaceEligibility();

                for (int i = 0; i < matrix.getRows(); i++) {
                    for (int j = 0; j < matrix.getColumns(); j++) {
                        gridState el = matrix.getElement(i, j);
                        for (int l = 0; l < el.getActions().size(); l++) {
                            double q = el.getActions().get(l).getQvalue();
                            double e = el.getActions().get(l).getEligibility();
                            el.getActions().get(l).setQvalue(q + settings.getLearningRate() * delta * e);
                            //if (nextEpsilonGreedyAction == nextGreedyAction)
                            el.getActions().get(l).setEligibility(settings.getDiscountFactor() * settings.getLambda() * e);
                            //else
                            //    el.getActions().get(l).resetEligibility();
                        }
                    }
                }
                currentState = currentAction.getNextState();
                currentAction = nextEpsilonGreedyAction;
            }
        } while (!convergence2(oldMatrix, matrix));

        System.out.println("------------------");
        matrix.printActions();
        System.out.println("------------------");
        matrix.printPolicy();
        //oldMatrix.printActions();
        System.out.println("done, itertation = " + it);
    }

    public String toString() {
        return "Sarsa(\u03BB)";
    }

    public String formula() {
        return "Q(s, a) \u27F5 Q(s, a) + \u03B1 \u03B4 e(s, a)";
    }



    @Override
    public void runAction(gridState currentPostion, Action a) {
        //double delta = currentAction.getReward() + settings.getDiscountFactor() * nextEpsilonGreedyAction.getQvalue() - currentAction.getQvalue();
        Action nextEpsilonGreedyAction = getEpsilonGreedyAction(a.getNextState());
        double delta = a.getReward() + settings.getDiscountFactor() * nextEpsilonGreedyAction.getQvalue() - a.getQvalue();

        a.accumulateEligibility();
        //currentAction.replaceEligibility();

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                gridState el = matrix.getElement(i, j);
                for (int l = 0; l < el.getActions().size(); l++) {
                    Action currentAction = el.getActions().get(l);
                    double q = el.getActions().get(l).getQvalue();
                    double e = el.getActions().get(l).getEligibility();
                    el.getActions().get(l).setQvalue(q + settings.getLearningRate() * delta * e);
                    
                    output = "Q([" + i + ", " + j + "], " + currentAction + ") \u27F5 " + q + " + " + settings.getLearningRate() + " * " + delta + " * " + e + "\u005D = " + currentAction.getQvalue() ;
                    theLogger.addLog(output);
                    //if (nextEpsilonGreedyAction == nextGreedyAction)
                    el.getActions().get(l).setEligibility(settings.getDiscountFactor() * settings.getLambda() * e);
                    //else
                    //    el.getActions().get(l).resetEligibility();
                }
            }
        }
        //return formula();
    }
}
