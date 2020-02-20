package edu.co.firstcomefirstserved.models;

/**
 *
 * @author juancsr
 */
public class Process extends Thread {
    private String processName;
    private boolean locked;
    private int timeWasBlocked;
    private int arriveTime;
    private int executionTime; // tiempo de rafaga
    private int startTime;
    private int endTime;
    private int returnTime;
    private int waitTime;
    private volatile boolean running = true;

    /*
    * Processes's constructor 
    * @param name Name of the process
    * @param arriveTime Time it takes to the process arrive into the critial section
    * @param exectionTime: Time it takes for the process to be executed
    */
    public Process(String name, int arriveTime, int executionTime) {
        locked = false;
        this.processName = name;
        this.arriveTime = arriveTime;
        this.executionTime = executionTime;
    }
    
    public void calculateTimes() {
        endTime = executionTime + startTime;
        returnTime = endTime - arriveTime;
        waitTime = returnTime - executionTime;
    }
    
    public void termiante() {
        this.running = false;
    }
    
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public int getEndTime() {
        return this.endTime; 
    }
    
    public int getReturnTime() {
        return this.returnTime;
    }
    
    public int getWaitTime() {
        return this.waitTime;
    }

    public int getTimeWasBlocked() {
        return timeWasBlocked;
    }

    public void setTimeWasBlocked(int timeWasBlocked) {
        this.timeWasBlocked = timeWasBlocked;
    }
}
