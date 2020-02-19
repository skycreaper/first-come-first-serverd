/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.firstcomefirstserved.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;
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
    public JLabel lblTItulo = new JLabel();
    public JLabel lblAutorJ = new JLabel();
    public JLabel lblAutorD = new JLabel();
    public JLabel lblNumberOfProcess = new JLabel();
    public JButton btnStart = new JButton("INICIAR");
    public JTableHeader tblHeader;
    public JTable tblProcess;
    
    public GUI() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("FIRST COME FIRST SERVED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        drawHeader();
        drawSubheader();
        drawContent();
        
        setVisible(true);
        setSize(1020, 720);
                
    }
    
    public void drawDiagram(List processes) {
        JTable diagram = new DiagramTable();
        processes.forEach((process) -> {
            
        });
        
    }
    
    private void drawHeader(){
        add(pnlHeader);
        pnlHeader.add(lblTItulo);
        pnlHeader.setLayout(null);
        pnlHeader.setBounds(0, 0, 1020, 100);
        pnlHeader.setBackground(c1);
        lblTItulo.setText("FIRST COME FIRST SERVED");
        lblTItulo.setBounds(380, 25, 400, 50);
        lblTItulo.setFont(font);
        
    }
    private void drawSubheader(){
        add(pnlSubHeader);
        pnlSubHeader.setLayout(null);
        pnlSubHeader.setBounds(0, 100, 1020, 100);
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

    }
    public void drawContent(){
        add(pnlContent);
        pnlContent.setLayout(null);
        pnlContent.setBounds(0, 200, 1020, 620);
        pnlContent.setBackground(c2);
        pnlContent.add(lblNumberOfProcess);
        lblNumberOfProcess.setBounds(800, 5, 150, 25);
        lblNumberOfProcess.setBackground(c2);
        lblNumberOfProcess.setFont(font2);
//        pnlContent.add(tblHeader);
//        tblHeader.setBounds(15, 100, 300, 50);
    }
    
    
    
}
