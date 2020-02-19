package edu.co.firstcomefirstserved.logic;

import java.util.List;
import java.util.Random;
import edu.co.firstcomefirstserved.models.Process;
import java.util.ArrayList;
/**
 *
 * @author juancsr
 */
public final class Queue extends Thread {
    
    private final String alphabet[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
        "N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    private List<Process> processes;
    
    public Queue() {
        processes = new ArrayList<>();
        initProcesses();
    }
    
    void initProcesses() {
        Random ran = new Random();
        int numberOfProcesses = ran.nextInt(5)+1;
        
        Process newProcess;
        for (int i = 0; i < numberOfProcesses; i++) {
            newProcess = new Process(alphabet[i], i, ran.nextInt(9)+1);
            processes.add(newProcess);
        }
    }
    
    public void executeProcesses() {
        printProcessesTable();
        System.out.println(processes);
        boolean locked;
        Thread t;
        Process duplicateProcess;
        
        Random ran = new Random();
        int blockedNumber = 0;
        int flagProcess = 0;
        
        for (int i=0; i < processes.size(); i++) {
            
            System.out.println("-----------------------------");
            Process process = processes.get(i);
            process.setStartTime(flagProcess);
            t = new Thread(process);
            t.start();
            
            
            try {
                locked = ran.nextBoolean();
                System.out.println("locked: "+locked);
                process.calculateTimes();
                flagProcess = process.getEndTime();
                /* if the process was blocked, it's assign a random time between 0
                * and the execution time value, if it's not the value is 0
                */
                if (locked && process.getExecutionTime() > 2) {
                    blockedNumber++;
                    int remainingTime = process.getExecutionTime() - (ran.nextInt(process.getExecutionTime()-2)+1);
                            duplicateProcess = new Process(
                            process.getProcessName().substring(0, 1)+(blockedNumber),
                            processes.size(),
                            remainingTime      
                    );
                    processes.add(duplicateProcess);
                    process.calculateTimes();
                    flagProcess = process.getEndTime();
                }
                Thread.sleep(process.getExecutionTime()*1000);
    
            } catch (InterruptedException x) {
                System.err.println("InterruptedException: "+x.getMessage());
            } finally {
                t.interrupt();
                System.out.println("process status: "+t.isAlive());
            }
            
            //System.out.println("flagProcess: "+flagProcess);
            System.out.println("Terminado proceso..." + process.getProcessName());
            printProcessesTable();
        }
    }
    
    void printProcessesTable() {
        System.out.println("-------------------------------------------"+
                "-------------------------------------------");
        System.out.println("name\tarriveTime\texecutionTime\tstartTime\tendTime"+
                "\treturnTime\twaitTime");
        processes.forEach((process) -> {
            System.out.println(process.getProcessName()+"\t"+
                    process.getArriveTime()+"\t\t"+
                    process.getExecutionTime()+"\t\t"+
                    process.getStartTime()+"\t\t"+
                    process.getEndTime()+"\t\t"+
                    process.getReturnTime()+"\t\t"+
                    process.getWaitTime());
        });
        System.out.println("-------------------------------------------"+
                "-------------------------------------------");
    }
    
}