/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.firstcomefirstserved.UI;

import edu.co.firstcomefirstserved.models.Process;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;;

/**
 *
 * @author david
 * @author juancsr
 */
public final class GUI extends JFrame {

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
    public JTable tblProcess;
    public JScrollPane scrollTableProcess;
    public JTable diagram;

    private final int screenWidth = 1024;
    private final int screenHeight = 720;

    TableColumn tableColumn;

    public GUI() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("FIRST COME FIRST SERVED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawHeader();
        drawSubheader();
        drawContent();

//        pnlHeader.setBorder(BorderFactory.createLineBorder(Color.black));
//        pnlSubHeader.setBorder(BorderFactory.createLineBorder(Color.black));
//        pnlContent.setBorder(BorderFactory.createLineBorder(Color.black));

        setVisible(true);
        setSize(screenWidth, screenHeight);

    }

    public void drawDiagram(List<Process> processes) {
        pnlDiagram.removeAll();
        pnlDiagram.repaint();

        int totalTime = processes.get(processes.size() - 1).getEndTime() + 1;

        String columns[] = new String[totalTime];
        String data[][] = new String[processes.size()][totalTime];
        columns[0] = "Proceso";

        for (int i = 1; i < totalTime; i++) {
            for (int j = 0; j < processes.size(); j++) {
                if (i == 1) {
                    data[j][0] = processes.get(j).getProcessName();
                } else {
                    data[j][i] = "-";
                }

            }
            columns[i] = Integer.toString(i);
        }

        diagram = new JTable(data, columns);
        diagram.setBorder(BorderFactory.createLineBorder(Color.yellow));
        diagram.setEnabled(false);

        JScrollPane scroll = new JScrollPane(diagram);
        scroll.setBounds(10, 10, screenWidth - 50, 150);
        scroll.setBorder(BorderFactory.createLineBorder(Color.yellow));

        add(pnlDiagram);
        pnlDiagram.add(scroll);
        pnlDiagram.setLayout(null);
        pnlDiagram.setBounds(0, 500, screenWidth, 200);
        pnlDiagram.setBackground(c2);
//        pnlDiagram.setBorder(BorderFactory.createLineBorder(Color.red));
    }

    private void testPaintCell() {
        ColorColumnRenderer cellRender = new ColorColumnRenderer();
        // Get column 0
        TableColumn tableColumn = diagram.getColumnModel().getColumn(0);

        // Set our own table cell renderer for this column.
        tableColumn.setCellRenderer(cellRender);

        // Get column 1
        //TableColumn tableColumn = diagram.getColumnModel().getColumn(0);
        cellRender.setRowToColor(2);
        // Set our own table cell renderer for this column.
        tableColumn.setCellRenderer(cellRender);
    }

    public void paintCell(int column, int row, Color color) {
        ColorColumnRenderer cellRender = new ColorColumnRenderer();
        tableColumn = diagram.getColumnModel().getColumn(column);
        cellRender.setRowToColor(row);
        cellRender.setColor(color);
        tableColumn.setCellRenderer(cellRender);
    }

    private void drawHeader() {
        add(pnlHeader);
        pnlHeader.add(lblTItulo);
        pnlHeader.setLayout(null);
        pnlHeader.setBounds(0, 0, screenWidth, 100);
        pnlHeader.setBackground(c1);
        lblTItulo.setText("FIRST COME FIRST SERVED");
        lblTItulo.setBounds(250, 25, 600, 50);
        lblTItulo.setFont(font);

    }

    private void drawSubheader() {
        add(pnlSubHeader);
        pnlSubHeader.setLayout(null);
        pnlSubHeader.setBounds(0, 100, screenWidth, 100);
        pnlSubHeader.setBackground(c1);

        lblAutorJ.setText("JUAN CAMILO SARMIENTO REYES");
        lblAutorJ.setFont(font2);
        lblAutorJ.setForeground(c3);
        lblAutorJ.setBounds(250, 5, 400, 25);
        pnlSubHeader.add(lblAutorJ);

        lblAutorD.setText("DAVID STEVEN SANTOS SANTOS");
        lblAutorD.setFont(font2);
        lblAutorD.setForeground(c3);
        lblAutorD.setBounds(250, 35, 400, 25);
        pnlSubHeader.add(lblAutorD);
        pnlSubHeader.add(btnStart);

        btnStart.setBounds(850, 60, 100, 40);

    }

    public void drawContent() {
        add(pnlContent);
        pnlContent.setLayout(null);
        pnlContent.setBounds(0, 200, 1024, 300);
        pnlContent.setBackground(c2);
        pnlContent.add(lblNumberOfProcess);
        lblNumberOfProcess.setBounds(800, 5, 150, 25);
        lblNumberOfProcess.setBackground(c2);
        lblNumberOfProcess.setFont(font2);
    }

    public void drawTable(List<Process> processes) {
        tblProcess = new JTable(processes.size(), 7);

        tblProcess.setPreferredScrollableViewportSize(new Dimension(700, 150));
        tblProcess.setFillsViewportHeight(true);
        tblProcess.setBackground(c2);
        tblProcess.setForeground(Color.BLACK);
        tblProcess.setFont(font2);
        tblProcess.setEnabled(false);

        scrollTableProcess = new JScrollPane(tblProcess);
        pnlContent.add(scrollTableProcess);
        scrollTableProcess.setBounds(10, 10, 700, 200);
        scrollTableProcess.setBackground(c2);
        scrollTableProcess.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        for (int i = 0; i < processes.size(); i++) {
            for (int j = 0; j < 7; j++) {
                tblProcess.setValueAt(processes.get(i).getProcessName(), i, 0);
                tblProcess.setValueAt(processes.get(i).getArriveTime(), i, 1);
                tblProcess.setValueAt(processes.get(i).getExecutionTime(), i, 2);
                tblProcess.setValueAt(processes.get(i).getStartTime(), i, 3);
                tblProcess.setValueAt(processes.get(i).getEndTime(), i, 4);
                tblProcess.setValueAt(processes.get(i).getReturnTime(), i, 5);
                tblProcess.setValueAt(processes.get(i).getWaitTime(), i, 6);

            }
        }
        changeColumNames();
    }

    public void changeColumNames() {
        JTableHeader tblHeader = tblProcess.getTableHeader();
        TableColumnModel tcm = tblHeader.getColumnModel();
        TableColumn tblColumn = tcm.getColumn(0);
        tblColumn.setHeaderValue("Proceso");
        TableColumn tblColumn2 = tcm.getColumn(1);
        tblColumn2.setHeaderValue("T. Llegada");
        TableColumn tblColumn3 = tcm.getColumn(2);
        tblColumn3.setHeaderValue("T. Rafaga");
        TableColumn tblColumn4 = tcm.getColumn(3);
        tblColumn4.setHeaderValue("T. Comienzo");
        TableColumn tblColumn5 = tcm.getColumn(4);
        tblColumn5.setHeaderValue("T. Final");
        TableColumn tblColumn6 = tcm.getColumn(5);
        tblColumn6.setHeaderValue("T. Retorno");
        TableColumn tblColumn7 = tcm.getColumn(6);
        tblColumn7.setHeaderValue("T. Espera");
        tblHeader.repaint();
    }

}
