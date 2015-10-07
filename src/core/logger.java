/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author robocup
 */
public class logger {

    private static logger theLogger=null;
    private String currentString="";

    private logger() {
        currentString="";
    }

    public static logger getInstance() {
        if (theLogger == null)
            theLogger = new logger();
        return theLogger;
    }

    public void addLog(String log) {
        currentString += "\n" + log;
    }

    public String getLog() {
        String backup = currentString;
        currentString = "";
        return backup;
    }

}
