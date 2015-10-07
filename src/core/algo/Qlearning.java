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
public class Qlearning extends basicAlgorithm{

    public Qlearning(gridMatrix m) {
        super(m);
    }

    public double calculateNewQvalue(gridState from,  Action a) {

            Action bestNextAction = getGreedyAction(from);
            //if (bestNextAction == null)
            //    System.out.println("lege " + from + " " + bestNextAction + " " + from.getActions().size());
            
            double newQ =  a.getQvalue() + settings.getLearningRate() * (a.getReward() + settings.getDiscountFactor() * bestNextAction.getQvalue() - a.getQvalue());
            output = "Q([" + from.getX() + ", " + from.getY() + "], " + a + ") \u27F5 " + a.getQvalue() + " + " + settings.getLearningRate() + "\u005B" + a.getReward() + " + " + settings.getDiscountFactor() +
                    " * " + bestNextAction.getQvalue() + " - " + a.getQvalue() + "\u005D = " + newQ ;
            
            return newQ;
    }

    
    public boolean runOnce() {
        //int it=0;
        gridMatrix oldMatrix = matrix.copy();

            it++;
            gridState currentState = matrix.getStartState();

            while (currentState.getType() != stateType.GOAL) {

                Action chosenAction = getEpsilonGreedyAction(currentState);

                //matrix.printActions();
                //System.out.println(chosenAction.getNextState().getType());
               // System.out.println(chosenAction);
                chosenAction.setQvalue(calculateNewQvalue(chosenAction.getNextState(), chosenAction));
                
                currentState = chosenAction.getNextState();
            }

        System.out.println("done, itertation = " + it);
        return convergence2(oldMatrix, matrix);
    
    }

    public void runAll() {
        //int it=0;
        //for (int i=0; i < settings.getEpisodesPerStep(); i++) {//settings.getEpisodesPerStep()
        gridMatrix oldMatrix;
        
        do {
            oldMatrix = matrix.copy();

            it++;
            gridState currentState = matrix.getStartState();

            while (currentState.getType() != stateType.GOAL) {

                Action chosenAction = getEpsilonGreedyAction(currentState);

                chosenAction.setQvalue(calculateNewQvalue(chosenAction.getNextState(), chosenAction));

                currentState = chosenAction.getNextState();
            }
        }
        
        while (!convergence2(oldMatrix, matrix)); //it != 2000);//
        System.out.println("------------------");
        matrix.printActions();
        System.out.println("------------------");
        matrix.printPolicy();
        //oldMatrix.printActions();
        System.out.println("done, itertation = " + it);
    }

    public String toString() {
        return "Q-learning";
    }

    public String formula() {
        return "Q(s, a) \u27F5 Q(s, a) + \u03B1 \u005B r + \u03B3 max Q(s', a') - Q(s, a) \u005D";
    }

    @Override
    public void runAction(gridState currentPostion, Action a) {
        theLogger.addLog(formula());
        
        a.setQvalue(calculateNewQvalue(a.getNextState(), a));
        theLogger.addLog(output);
        
        //return formula() + "\n" + output;
    }

    

}
