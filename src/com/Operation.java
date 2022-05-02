package com;

import java.util.LinkedList;

public class Operation {
    private LinkedList<Contact> phonebook;
    private int id;
    private String name;
    private String gender;
    private int dobDay;
    private int dobMonth;
    private int dobYear;
    private String mobilePhone;
    private String workPhone;
    private String homePhone;
    private String email;
    private String address;
    private String remark;
    private String dobDate;

    public Operation(LinkedList<Contact> phonebook){
        this.phonebook = phonebook;
    }

    public LinkedList<Contact> createNewLinkedList(){
        return new LinkedList<Contact>();
    }

    public LinkedList<Contact> createContact(String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark, int id){
        Contact contact = new Contact();
        contact.setAttribute(name,gender,dobDay,dobMonth,dobYear,mobilePhone,workPhone,homePhone,email,address,remark);
        contact.setId(phonebook.size());
        phonebook.add(contact);
        //debug
        /*System.out.println("From Operation");
        for (int i=0; i< phonebook.size();i++){
            System.out.println(i+". "+phonebook.get(i).getName());
        }*/
        return phonebook;
    }

    public LinkedList<Contact> editContact(int index,String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark, int id){
        Contact contact = new Contact();
        contact.setAttribute(name,gender,dobDay,dobMonth,dobYear,mobilePhone,workPhone,homePhone,email,address,remark);
        contact.setId(id);
        phonebook.set(index, contact);
        //debug
        /*System.out.println("From Operation");
        for (int i=0; i< phonebook.size();i++){
            System.out.println(i+". "+phonebook.get(i).getName());
        }*/
        return phonebook;
    }

    public void deleteContact(){
        ;
    }

    public LinkedList<Contact> returnLinkedList(){
        return this.phonebook;
    }

}
