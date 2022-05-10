package com;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Operation {
    private LinkedList<Contact> phonebook;

    //constructor to set the phonebook instance variable
    public Operation(LinkedList<Contact> phonebook){
        this.phonebook = phonebook;
    }

    //create a new contact method, just add the contact into the phonebook linked list
    public void createContact(String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark){
        Contact contact = new Contact();
        contact.setAttribute(name,gender,dobDay,dobMonth,dobYear,mobilePhone,workPhone,homePhone,email,address,remark);
        contact.setID(phonebook.size());
        phonebook.add(contact);
    }

    //edit an existing contact method, edit the specific contact according to id, find the contact by using index number
    public void editContact(int index,String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark){
        Contact contact = new Contact();
        contact.setAttribute(name,gender,dobDay,dobMonth,dobYear,mobilePhone,workPhone,homePhone,email,address,remark);
        contact.setID(index);
        phonebook.set(index, contact);
    }

    //delete the specific contact by knowing the id of the contact, move contact before the deleted contact into temp linked list, the id of contact after the deleted contact -1 and move the contact after the deleted contact into temp linked list
    public void deleteContact(int index){
        LinkedList<Contact> temp = new LinkedList<>();
        //move contact before the deleted contact into new linked list
        for (int i = 0; i < index; i++){
            Contact contact = new Contact();
            contact = phonebook.get(i);
            temp.add(contact);
        }
        //remove the selected contact
        phonebook.remove(index);
        //id of contact after the deleted contact -1 and move the contact after the deleted contact into temp linked list
        for (int i = index; i < phonebook.size();i++){
            Contact contact = new Contact();
            contact = phonebook.get(i);
            contact.setID(i);
            temp.add(contact);
        }
        //set the phonebook linked list with the temp linked list
        this.phonebook = temp;
    }

    //delete all contact method, just clear the whole linked list
    public void deleteAllContact(){
        phonebook.clear();
    }

    //export the contact to a file location
    public void exportContact(String fileFormat, String fileLocation) throws IOException {
        String filePath = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy") ;
        //set the name of file with date and correct extension
        if (fileFormat.equals("txt")){
            filePath = fileLocation+"\\"+dateFormat.format(date)+" contacts.txt";
        } else if (fileFormat.equals("csv")) {
            filePath = fileLocation+"\\"+dateFormat.format(date)+" contacts.csv";
        }

        //check is auto-save address, if yes then rename it to autosave.txt
        if (fileLocation.equals(".\\src\\com\\save data\\autosave.txt")){
            filePath = ".\\src\\com\\save data\\autosave.txt";
        }

        FileWriter fileWriter = new FileWriter(filePath);

        //write the data in csv format (didn't care it is txt format or csv format)
        for (Contact contact : phonebook) {
            fileWriter.write("\"" + contact.getId() + "\",\"" + contact.getName() + "\",\"" + contact.getGender() + "\",\"");
            fileWriter.write("\"" + contact.getDobDate() + "\",\"" + contact.getMobilePhone() + "\",\"" + contact.getWorkPhone() + "\",\"");
            fileWriter.write(contact.getHomePhone() + "\",\"" + contact.getEmail() + "\",\"" + contact.getAddress() + "\",");
            fileWriter.write("\"" + contact.getRemark() + "\"\n");
        }
        fileWriter.close();
    }

    //method to import contact
    public void importContact(String fileLocation, TableModel tableModel){
        String splitBy = "\",\"";
        String line;

        try{
            //get the file and open it
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
            while ((line = bufferedReader.readLine()) != null){
                //split the content if "," is meet
                String[] contact = line.split(splitBy);
                //remove the " in start and end of the string
                contact[0] = contact[0].substring(1);
                contact[9] = contact[9].substring(0,contact[9].length()-1);

                //separate the birthday date into day, month and year
                int[] date = dateSeparator(contact[3]);
                createContact(contact[1],contact[2],date[0],date[1],date[2],contact[4],contact[5],contact[6],contact[7],contact[8],contact[9]);

                //refresh JTable in menu
                tableModel.setRowCount();
                tableModel.fireTableStructureChanged();
            }
        } catch (IOException ignored) {
        }

    }

    //separate the date to day, month and year (return integer array)
    public int[] dateSeparator(String date){
        int[] dayMonthYear = new int[3];
        String splitBy = "/";
        date = date.substring(1);

        //separate the date and save in an int array
        String[] temp = date.split(splitBy);
        for (int i=0; i<3; i++){
            if (temp[i].equals("")){
                temp[i] = "0";
            }
            dayMonthYear[i] = Integer.parseInt(temp[i]);
        }

        return dayMonthYear;
    }

    //check date is valid or not (Except if the date is not filled)
    public boolean checkDate(int day, int month, int year){
        if (!(day == 0 || month == 0 || year == 0)){ //if date is not selected then return true
            try {
                //check date is valid or not, if valid then return true, otherwise false
                LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                return false;
            }
        }

        return true;
    }

}
