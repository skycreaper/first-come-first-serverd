/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.firstcomefirstserved.UI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author juancamilo
 */
/**
* Applied background and foreground color to single column of a JTable
* in order to distinguish it apart from other columns.
*/
class ColorColumnRenderer extends DefaultTableCellRenderer {
    
    private int rowToColor = 0;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {
      JLabel label = new JLabel();
      label.setOpaque(true);
      //label.setFont(new Font(?Dialog?, Font.PLAIN, 12));
      if (value != null) label.setText(value.toString());

      // set row 0 to red on yellow background
      if (row == this.rowToColor)
      {
         label.setForeground(Color.red);
         label.setBackground(Color.yellow);
      }

      return label;
   }

    public void setRowToColor(int rowToColor) {
        this.rowToColor = rowToColor;
    }

}
