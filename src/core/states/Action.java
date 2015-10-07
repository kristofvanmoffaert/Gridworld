/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.states;

/**
 *
 * @author robocup
 */
public class Action {

    protected double Qvalue =  0.0;//0.0001;
    protected double reward;
    protected gridState nextState;
    protected Direction dir;
    protected double eligibility;

    public Action(gridState next, Direction d, double reward) { //stateine
        
        this.reward = reward;
        dir = d;
        nextState = next;
        eligibility = 0.0;
    }
    
   public Action(gridState next, Direction d, double reward, double Q, double E) {
        this.reward = reward;
        dir = d;
        nextState = next;
        Qvalue = Q;
        eligibility = E;
    }
        

    public Action copy() {
        return new Action(nextState, dir, reward, Qvalue, eligibility);
    }


    public Direction getDirection() {
        return dir;
    }

    public double getQvalue() {
        return Qvalue;
    }

    public void setQvalue(double p) {
        Qvalue = p;
    }

    public void resetQValue() {
        Qvalue = 0.0;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double p) {
        reward = p;
    }

    public double getEligibility() {
        return eligibility;
    }

    public void accumulateEligibility() {
        eligibility++;
    }

    public void replaceEligibility() {
        eligibility=1.0;
    }


    public void resetEligibility() {
        eligibility = 0.0;
    }

    public void setEligibility(double p) {
        eligibility = p;
    }

    public gridState getNextState() {
        return nextState;
    }



    public String toString() {
        String direct = "";

        switch (dir) {
            case RIGHT:
                direct =  "Right"; break;
            case LEFT:
                direct = "Left";break;
            case UP:
                direct = "Up";break;
            case DOWN:
                direct = "Down";break;
            case NONE:
                direct = "Itself";break;
            default:
                direct = "Illegal direction: " + dir; break;
        }

        return direct;//+"(" + getQvalue() + ","+ getNextState() + ")";

    }
}
