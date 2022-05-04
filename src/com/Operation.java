package com;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Operation {
    private LinkedList<Contact> phonebook;

    public Operation(LinkedList<Contact> phonebook){
        this.phonebook = phonebook;
    }

    public void createContact(String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark, int id){
        Contact contact = new Contact();
        contact.setAttribute(name,gender,dobDay,dobMonth,dobYear,mobilePhone,workPhone,homePhone,email,address,remark);
        contact.setId(phonebook.size());
        phonebook.add(contact);
        System.out.println("hihi");
    }

    public void editContact(int index,String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark, int id){
        Contact contact = new Contact();
        contact.setAttribute(name,gender,dobDay,dobMonth,dobYear,mobilePhone,workPhone,homePhone,email,address,remark);
        contact.setId(id);
        phonebook.set(index, contact);
    }

    public void deleteContact(int index){
        LinkedList<Contact> newPhonebook = new LinkedList<>();
        for (int i = 0; i < index; i++){
            Contact contact = new Contact();
            contact = phonebook.get(i);
            newPhonebook.add(contact);
        }
        phonebook.remove(index);
        for (int i = index; i < phonebook.size();i++){
            Contact contact = new Contact();
            contact = phonebook.get(i);
            contact.setID(i);
            newPhonebook.add(contact);
        }
        this.phonebook = newPhonebook;
    }

    public void deleteAllContact(){
        phonebook.clear();
    }

    public void exportContact(String fileFormat, String fileLocation) throws IOException {;
        String filePath = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy") ;
        if (fileFormat.equals("txt")){
            filePath = fileLocation+"\\"+dateFormat.format(date)+" contacts.txt";
        } else if (fileFormat.equals("csv")) {
            filePath = fileLocation+"\\"+dateFormat.format(date)+" contacts.csv";;
        }

        if (fileLocation.equals(".\\src\\com\\save data\\autosave.txt")){
            filePath = ".\\src\\com\\save data\\autosave.txt";
        }

        File contactTxt = new File(filePath);
        FileWriter fileWriter = new FileWriter(filePath);

        for (int i = 0; i < phonebook.size(); i++){
            fileWriter.write("\""+phonebook.get(i).getId()+"\",\""+phonebook.get(i).getName()+"\",\""+phonebook.get(i).getGender()+"\",\"");
            fileWriter.write("\""+phonebook.get(i).getDobDate()+"\",\""+phonebook.get(i).getMobilePhone()+"\",\""+phonebook.get(i).getWorkPhone()+"\",\"");
            fileWriter.write(phonebook.get(i).getHomePhone()+"\",\""+phonebook.get(i).getEmail()+"\",\""+phonebook.get(i).getAddress()+"\",");
            fileWriter.write("\""+phonebook.get(i).getRemark()+"\"\n");
        }
        fileWriter.close();
    }

    public void importContact(String fileLocation, TableModel tableModel){
        String splitBy = "\",\"";
        String line = "";

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
            System.out.println("hi");
            while ((line = bufferedReader.readLine()) != null){
                String[] contact = line.split(splitBy);
                contact[0] = contact[0].substring(1);
                contact[9] = contact[9].substring(0,contact[9].length()-1);

                int[] date = dateSeparator(contact[3]);
                createContact(contact[1],contact[2],date[0],date[1],date[2],contact[4],contact[5],contact[6],contact[7],contact[8],contact[9], Integer.parseInt(contact[0]));

                tableModel.setRowCount();
                tableModel.fireTableStructureChanged();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public LinkedList<Contact> returnLinkedList(){
        return this.phonebook;
    }

    public int[] dateSeparator(String date){
        int[] dayMonthYear = new int[3];
        String[] temp = new String[3];
        String splitBy = "/";
        date = date.substring(1,date.length());

        temp = date.split(splitBy);
        for (int i=0; i<3; i++){
            dayMonthYear[i] = Integer.parseInt(temp[i]);
        }

        return dayMonthYear;
    }

}
