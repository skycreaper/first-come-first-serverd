/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.firstcomefirstserved.UI;

import edu.co.firstcomefirstserved.models.Process;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 *
 * @author david
 * @author juancsr
 */
public class GUI extends JFrame{
    public Color c1 = new Color(85, 230, 193);  // rgb(85, 230, 193)
    public Color c2 = new Color(154, 236, 219);  // rgb(154, 236, 219)
    public Color c3 = new Color(223, 249, 251);
    
    public Font font = new Font("Agency FB", Font.BOLD, 34);
    public Font font2 = new Font("Agency FB", Font.BOLD, 20);
    
    public JPanel pnlHeader = new JPanel();
    public JPanel pnlSubHeader = new JPanel();
    public JPanel pnlContent = new JPanel();
    public JPanel pnlDiagram = new JPanel();
            
    public JLabel lblTItulo = new JLabel();
    public JLabel lblAutorJ = new JLabel();
    public JLabel lblAutorD = new JLabel();
    public JLabel lblNumberOfProcess = new JLabel();
    
    public JButton btnStart = new JButton("INICIAR");
    public JTableHeader tblHeader;
    public JTable tblProcess;
    
    private int screenWidth = 1024;
    private int screenHeight = 720;
    
    public GUI() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("FIRST COME FIRST SERVED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        drawHeader();
        drawSubheader();
        drawContent();
        
        pnlHeader.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlSubHeader.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlContent.setBorder(BorderFactory.createLineBorder(Color.black));
        
        setVisible(true);
        setSize(screenWidth, screenHeight);
                
    }
    
    public void drawDiagram(List<Process> processes) {
        int totalTime = processes.get(processes.size() -1 ).getEndTime();
        JTable diagram = new JTable(processes.size(), totalTime);
        
        add(pnlDiagram);
        pnlDiagram.setLayout(null);
        pnlDiagram.setBounds(0, 520, screenWidth, 200);
        pnlDiagram.setBackground(c2);
        pnlDiagram.setBorder(BorderFactory.createLineBorder(Color.black));
        
        processes.forEach((process) -> {
            
        });
        
    }
    
    private void drawHeader(){
        add(pnlHeader);
        pnlHeader.add(lblTItulo);
        pnlHeader.setLayout(null);
        pnlHeader.setBounds(0, 0, screenWidth, 100);
        pnlHeader.setBackground(c1);
        lblTItulo.setText("FIRST COME FIRST SERVED");
        lblTItulo.setBounds(380, 25, 400, 50);
        lblTItulo.setFont(font);
        
    }
    private void drawSubheader(){
        add(pnlSubHeader);
        pnlSubHeader.setLayout(null);
        pnlSubHeader.setBounds(0, 100, screenWidth, 100);
        pnlSubHeader.setBackground(c1);
        
        lblAutorJ.setText("JUAN CAMILO SARMIENTO REYES");
        lblAutorJ.setFont(font2);
        lblAutorJ.setForeground(c3);
        lblAutorJ.setBounds(pnlSubHeader.getWidth()/2, 5, 220, 25);
        pnlSubHeader.add(lblAutorJ);
        
        lblAutorD.setText("DAVID STEVEN SANTOS SANTOS");
        lblAutorD.setFont(font2);
        lblAutorD.setForeground(c3);
        lblAutorD.setBounds(pnlSubHeader.getWidth()/2, 35, 220, 25);
        pnlSubHeader.add(lblAutorD);
        pnlSubHeader.add(btnStart);
        
        btnStart.setBounds(850, 60, 100, 40);
        

    }
    public void drawContent(){
        add(pnlContent);
        pnlContent.setLayout(null);
        pnlContent.setBounds(0, 200, 1020, 320);
        pnlContent.setBackground(c2);
        pnlContent.add(lblNumberOfProcess);
        lblNumberOfProcess.setBounds(800, 5, 150, 25);
        lblNumberOfProcess.setBackground(c2);
        lblNumberOfProcess.setFont(font2);
//        pnlContent.add(tblHeader);
//        tblHeader.setBounds(15, 100, 300, 50);
    }
    
    
    
}
