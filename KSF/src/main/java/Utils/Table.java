/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Admin
 */
public class Table extends JTable {

    private TableDarkCell cell;

    public Table() {
        cell = new TableDarkCell();
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        setDefaultRenderer(Object.class, cell);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {

                Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
                com.setBackground(new Color(255, 255, 255));
                com.setForeground(new Color(55, 55, 55));
                com.setFont(com.getFont().deriveFont(Font.BOLD, 13));
                return com;

            }

        });

    }

    private class TableDarkCell extends DefaultTableCellRenderer {

        private Map<Integer, Integer> alignment = new HashMap<>();

        public void setAlignment(int column, int align) {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
            if (isCellSelected(row, column)) {
                if (row % 2 == 0) {
                    com.setBackground(new Color(181, 181, 181));
                } else {
                    com.setBackground(new Color(181, 181, 181));
                }
            } else {
                if (row % 2 == 0) {
                    com.setBackground(new Color(255, 255, 255));
                } else {
                    com.setBackground(new Color(255, 255, 255));
                }
            }
            com.setForeground(new Color(74, 74, 74));
            setBorder(new EmptyBorder(0, 5, 0, 5));
            if (alignment.containsKey(column)) {
                setHorizontalAlignment(alignment.get(column));
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
            return com;
        }
    }

    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }

    public void fixTable(JScrollPane scroll) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
    }
}
