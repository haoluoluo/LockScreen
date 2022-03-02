package comp;


import comp.base.JPanelEnh;
import database.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;


public class CardInfoPanel extends JPanelEnh {
    public CardInfoPanel(){
        //设置透明
        this.setTransparent();

        this.setLayout(new BorderLayout());

        JPanelEnh top = new JPanelEnh();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton deleteButton = new JButton("删除");
        JButton saveButton = new JButton("保存");


        JButton queryButton = new JButton("查询");
        top.add(deleteButton);
        top.add(saveButton);
        top.add(queryButton);

        this.add(top, BorderLayout.NORTH);

        JScrollPane scrollPane=new JScrollPane();
        this.add(scrollPane,BorderLayout.CENTER);

        JTable table=new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table);


        DefaultTableModel tableModel=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return column != 0;
            }
        };


        tableModel.setColumnIdentifiers(new Object[]{"卡号","卡名","权限(S管理员,N普通用户)"});

        table.setRowHeight(30);
        table.setModel(tableModel);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setCellEditor(new JBoxCell());



        queryButton.addActionListener(e -> flush(tableModel));

        saveButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if(selectedRow!= -1)
            {
                int result = JOptionPane.showConfirmDialog(null, "确定要保存吗？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(Objects.equals(result, JOptionPane.YES_OPTION)) {
                    String id = String.valueOf(table.getValueAt(selectedRow, 0));
                    String name = String.valueOf(table.getValueAt(selectedRow, 1));
                    String permission = String.valueOf(table.getValueAt(selectedRow, 2));
                    Database.updateCard(id, name, permission);
                    flush(tableModel);
                }
            }
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow!= -1)
            {
                int result = JOptionPane.showConfirmDialog(null, "确定要删除吗？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(Objects.equals(result, JOptionPane.YES_OPTION)) {
                    String id = String.valueOf(table.getValueAt(selectedRow, 0));
                    Database.deleteCard(id);
                    flush(tableModel);
                }
            }
        });
        this.setVisible(true);

    }
    private void flush( DefaultTableModel tableModel){
        tableModel.setRowCount(0);
        List<String[]> list = Database.queryICCard();

        for (String[] l : list) {
            tableModel.addRow(l);
        }
    }
}
class JBoxCell  extends AbstractCellEditor implements TableCellEditor {
    int row;
    JTable table;
    Object val;
    private final JPanel p1;
    private final JComboBox<String> jbxType;
    public JBoxCell() {
        p1 = new JPanel(new BorderLayout());
        String[] interest = { "N", "S"};
        jbxType = new JComboBox<>(interest);

        p1.add(jbxType, BorderLayout.WEST);
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public Object getCellEditorValue() {
        return String.valueOf(jbxType.getSelectedItem());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        val = value;
        if(Objects.equals(value, "N")){
            jbxType.setSelectedIndex(0);
        }else if(Objects.equals(value, "S")){
            jbxType.setSelectedIndex(1);
        }
        return this.p1;
    }
}