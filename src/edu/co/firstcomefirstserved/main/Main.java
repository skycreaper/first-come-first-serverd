package edu.co.firstcomefirstserved.main;

import edu.co.firstcomefirstserved.logic.Queue;

/**
 *
 * @author juancamilo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("first come first served");
        Queue q = new Queue();
        q.executeProcesses();
    }
    
}
