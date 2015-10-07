/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.algo.settings;


import java.util.Random;



/**
 *
 * @author kristof
 */
public class basicSettings {
    
    //protected int episodesPerStep;
    protected double learningRate;
    protected double discountFactor;
    protected double lambda;
    protected double epsilon = 0.1;
    // convergence criteria
    protected double delta = 0.01;

    
    public basicSettings() {
        //episodesPerStep=50;
        learningRate = 0.1;
        discountFactor = 0.9;
        lambda = 0.8;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public double getDelta() {
        return delta;
    }
    
    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double d) {
        learningRate=d;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double d) {
        lambda=d;
    }
    /*
    public int getEpisodesPerStep() {
        return episodesPerStep;
    }

    public void setEpisodesPerStep(int i) {
        episodesPerStep = i;
    }
    */
    public double getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(double i) {
        discountFactor = i;
    }


    public String toString() {
        return "learning rate: "+learningRate + " discount factor: " + discountFactor;
    }

    public void printSettings() {
        
        //System.out.println("# episodes per step: " + episodesPerStep);
        System.out.println("Learning rate:" + learningRate);
        System.out.println("Discount factor: " + discountFactor);

        
    }


}
