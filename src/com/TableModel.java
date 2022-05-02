package com;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class TableModel extends AbstractTableModel {

    private LinkedList<Contact> phonebook;

    private String[][] data;
    private String column[]={"Name","Gender","DOB","Mobile Phone","Work Phone","Home Phone","Email","Address","id"};

    public TableModel(LinkedList<Contact> phonebook){
        this.phonebook = phonebook;
        setData();
    }

    public void setData() {
        this.phonebook = phonebook;
        String[][] data = new String[phonebook.size()][9];
        for (int i=0; i< phonebook.size(); i++) {
            data[i][0] = phonebook.get(i).getName();
            data[i][1] = phonebook.get(i).getGender();
            data[i][2] = phonebook.get(i).getDobDate();
            data[i][3] = phonebook.get(i).getMobilePhone();
            data[i][4] = phonebook.get(i).getWorkPhone();
            data[i][5] = phonebook.get(i).getHomePhone();
            data[i][6] = phonebook.get(i).getEmail();
            data[i][7] = phonebook.get(i).getAddress();
            data[i][8] = phonebook.get(i).getId();
        }
        this.data = data;
    }

    public String getColumnName(int col) {
        return column[col];
    }

    @Override
    public int getRowCount() {
        return phonebook.size();
    }

    public void setRowCount(){
        setData();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

}
