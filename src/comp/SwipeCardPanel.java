package comp;


import com.eltima.components.ui.DatePicker;
import comp.base.JPanelEnh;
import database.Database;
import org.apache.commons.lang3.StringUtils;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Objects;


public class SwipeCardPanel extends JPanelEnh {
    public SwipeCardPanel(){
        //设置透明
        this.setTransparent();

        this.setLayout(new BorderLayout());

        JPanelEnh top = new JPanelEnh();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanelEnh jPanelEnh = new JPanelEnh(new FlowLayout(FlowLayout.LEFT));

        DatePicker datePicker = new DatePicker(null, "yyyy-MM-dd",null, null);

        jPanelEnh.add(datePicker);
        DatePicker datePicker2 = new DatePicker(null, "yyyy-MM-dd",null, null);

        jPanelEnh.add(datePicker2);


        top.add(jPanelEnh);

        JButton deleteButton = new JButton("删除全部");

        JButton downButton = new JButton("下载");


        JButton queryButton = new JButton("查询");

        JButton exceptionButton = new JButton("查看未刷出记录");

        top.add(deleteButton);
        top.add(downButton);
        top.add(queryButton);
        top.add(exceptionButton);

        this.add(top, BorderLayout.NORTH);

        JScrollPane scrollPane=new JScrollPane();
        this.add(scrollPane,BorderLayout.CENTER);

        JTable table=new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table);


        DefaultTableModel tableModel=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };


        Object[] head = new Object[]{"卡号","卡名","刷入时间", "刷出时间", "总时长(分钟)"};
        tableModel.setColumnIdentifiers(head);
        queryButton.addActionListener(e -> {
            String startTime = datePicker.getText();
            String endTime = datePicker2.getText();
            flush(tableModel, startTime, endTime);
        });

        table.setRowHeight(30);
        table.setModel(tableModel);


        downButton.addActionListener(e -> {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
            jfc.setDialogTitle("选择下载目录");
            jfc.showDialog(new JLabel(), "下载");
            File file=jfc.getSelectedFile();
            StringBuilder sb = new StringBuilder();

            sb.append(StringUtils.join(head, ",")).append("\n");
            String startTime = datePicker.getText();
            String endTime = datePicker2.getText();
            for (String[] strings : Database.queryICCardHistory(startTime, endTime)) {
                sb.append(StringUtils.join(strings, ","));
                sb.append("\n");
            }

            FileUtils.write(file.getAbsolutePath()+"/刷卡记录.csv", sb.toString());

        });

        exceptionButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            List<String[]> list = Database.queryICCardHistoryTemp();
            for (String[] l : list) {
                tableModel.addRow(l);
            }
        });
        deleteButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确定要删除所有数据吗？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(Objects.equals(result, JOptionPane.YES_OPTION)) {
                Database.deleteAllHistory();
                String startTime = datePicker.getText();
                String endTime = datePicker2.getText();
                flush(tableModel, startTime, endTime);
            }
        });


        this.setVisible(true);
    }
    private void flush(DefaultTableModel tableModel, String startTime, String endTime){
        tableModel.setRowCount(0);
        List<String[]> list = Database.queryICCardHistory(startTime, endTime);
        for (String[] l : list) {
            tableModel.addRow(l);
        }
    }

}
