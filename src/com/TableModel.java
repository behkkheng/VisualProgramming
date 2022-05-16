package com;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class TableModel extends AbstractTableModel {

    private final LinkedList<Contact> phonebook;

    private String[][] data;
    private final String[] column ={"Id","Name","Gender","DOB","Mobile Phone","Work Phone","Home Phone","Email","Address"};

    //constructor set the instance variable phonebook and the data of the table
    public TableModel(LinkedList<Contact> phonebook){
        this.phonebook = phonebook;
        setData();
    }

    //set the data of phonebook into a 2 dimensional array call data
    public void setData() {
        String[][] data = new String[phonebook.size()][9];
        for (int i=0; i< phonebook.size(); i++) {
            data[i][0] = phonebook.get(i).getId();
            data[i][1] = phonebook.get(i).getName();
            data[i][2] = phonebook.get(i).getGender();

            if (phonebook.get(i).getDobDate().equals("0/0/0")){
                data[i][3] = " ";
            } else {
                data[i][3] = phonebook.get(i).getDobDate();
            }
            data[i][4] = phonebook.get(i).getMobilePhone();
            data[i][5] = phonebook.get(i).getWorkPhone();
            data[i][6] = phonebook.get(i).getHomePhone();
            data[i][7] = phonebook.get(i).getEmail();
            data[i][8] = phonebook.get(i).getAddress();
        }
        this.data = data;
    }

    //override the abstract method getColumnName, get the name of the column
    @Override
    public String getColumnName(int col) {
        return column[col];
    }

    //override the abstract method getRowCount (must), get the number of row of table (number of contacts)
    @Override
    public int getRowCount() {
        return phonebook.size();
    }

    //method to set the number of row of table
    public void setRowCount(){
        setData();
    }

    //override the abstract method getColumnCount (must), get the number of column of table
    @Override
    public int getColumnCount() {
        return 9;
    }

    //override the abstract method getValueAt (must), get the value at specific cell
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

}
