package edu.co.firstcomefirstserved.logic;

import edu.co.firstcomefirstserved.UI.GUI;
import java.util.List;
import java.util.Random;
import edu.co.firstcomefirstserved.models.Process;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.TableColumn;

/**
 *
 * @author juancsr
 */
public final class Queue extends Thread {

    private GUI gui;
    private int numberOfProcesses;
    private final String alphabet[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Color colors[] = {Color.yellow, Color.pink, Color.CYAN, Color.GREEN, Color.magenta};

    private List<Process> processes;

    public Queue() {
        processes = new ArrayList<>();
        gui = new GUI();
        gui.btnStart.addActionListener(new java.awt.event.ActionListener() {      //Metodo implementado provisional para el actionperformed 
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        initProcesses();
    }

    void initProcesses() {
        Random ran = new Random();
        numberOfProcesses = ran.nextInt(5) + 1;

        Process newProcess;
        for (int i = 0; i < numberOfProcesses; i++) {
            newProcess = new Process(alphabet[i], i, ran.nextInt(9) + 1);
            processes.add(newProcess);
        }
    }

    public void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        gui.lblNumberOfProcess.setText("PROCESOS: " + numberOfProcesses);
        executeProcesses();
        gui.drawDiagram(processes);

        Thread waitThread = new Thread() {
            @Override
            public void run() {
                Random rand = new Random();
                Color color;
                Process actualProcess;
                try {
                    for (int i = 0; i < processes.size(); i++) {
                        float r = rand.nextFloat();
                        float g = rand.nextFloat();
                        float b = rand.nextFloat();
                        color = new Color(r, g, b);

                        actualProcess = processes.get(i);
                        
                        for (int j = actualProcess.getStartTime(); j < actualProcess.getEndTime() - actualProcess.getTimeWasBlocked(); j++) {
                            gui.paintCell(j + 1, i, color);
                            //System.out.println("Coloreando: ["+i+","+j+"]");
                            gui.diagram.repaint();
                            this.sleep(1000);
                        }

                        if (actualProcess.isLocked()) {
                            System.out.println("Proceso: " + actualProcess.getProcessName() + " bloqueado en " + actualProcess.getTimeWasBlocked());
                            for (int j = actualProcess.getTimeWasBlocked(); j < actualProcess.getEndTime(); j++) {
                                gui.paintCell(j, i, Color.red);
                                //System.out.println("Coloreando: ["+i+","+j+"]");
                                gui.diagram.repaint();
                                this.sleep(1000);
                            }
                        }
                    }
                    gui.btnStart.setEnabled(true);
                } catch (InterruptedException e) {
                    System.out.println("Error en waitThread: " + e.getMessage());
                }
            }
        };
        waitThread.start();
        executeProcesses();
        gui.drawTable(processes);
    }

    public void executeProcesses() {
        //printProcessesTable();
        //System.out.println(processes);
        boolean locked;
        Thread t;
        Process duplicateProcess;

        Random ran = new Random();
        int blockedNumber = 0;
        int flagProcess = 0;

        for (int i = 0; i < processes.size(); i++) {
            System.out.println("-----------------------------");
            Process process = processes.get(i);
            process.setStartTime(flagProcess);
            t = new Thread(process);
            t.start();

            locked = ran.nextBoolean();
            System.out.println("locked: " + locked);
            process.calculateTimes();
            flagProcess = process.getEndTime();
            /* if the process was blocked, it's assign a random time between 0
                * and the execution time value, if it's not the value is 0
             */
            if (locked && process.getExecutionTime() > 2) {
                blockedNumber++;
                process.setLocked(locked);
                int remainingTime = process.getExecutionTime() - (ran.nextInt(process.getExecutionTime() - 2) + 1);
                System.out.println("Proceso: "+process.getProcessName()+" bloqueado en "+(process.getEndTime()-remainingTime));
                process.setTimeWasBlocked(process.getEndTime()- remainingTime);
                duplicateProcess = new Process(
                        process.getProcessName().substring(0, 1) + (blockedNumber),
                        processes.size(),
                        remainingTime
                );
                processes.add(duplicateProcess);
                process.calculateTimes();
                flagProcess = process.getEndTime();
            }
        }
    }

    void printProcessesTable() {
        System.out.println("-------------------------------------------"
                + "-------------------------------------------");
        System.out.println("name\tarriveTime\texecutionTime\tstartTime\tendTime"
                + "\treturnTime\twaitTime");
        processes.forEach((process) -> {
            System.out.println(process.getProcessName() + "\t"
                    + process.getArriveTime() + "\t\t"
                    + process.getExecutionTime() + "\t\t"
                    + process.getStartTime() + "\t\t"
                    + process.getEndTime() + "\t\t"
                    + process.getReturnTime() + "\t\t"
                    + process.getWaitTime());
        });
        System.out.println("-------------------------------------------"
                + "-------------------------------------------");
    }

}
