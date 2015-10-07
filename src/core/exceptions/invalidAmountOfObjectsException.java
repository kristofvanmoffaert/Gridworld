/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.exceptions;

/**
 *
 * @author kristof
 */
public class invalidAmountOfObjectsException extends Exception {

    String mistake;

    public invalidAmountOfObjectsException(String err) {
        super(err);     // call super class constructor
        mistake = err;  // save message
    }

//------------------------------------------------
// public method, callable by exception catcher. It returns the error message.
    public String getError() {
        return mistake;
    }
}
