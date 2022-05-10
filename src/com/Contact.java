package com;

//data should in a single linked list node
public class Contact {
    private int id;
    private String name;
    private String gender;
    private String mobilePhone;
    private String workPhone;
    private String homePhone;
    private String email;
    private String address;
    private String remark;
    private String dobDate;


    //method to combine dobDay, dobMonth and dobYear to a date (String)
    public String generateDOBDate(int dobDay, int dobMonth, int dobYear) {
        String day = Integer.toString(dobDay);
        String month = Integer.toString(dobMonth);
        String year = Integer.toString(dobYear);

        //if day or month or year is unknown then the date will be set to 0/0/0
        if (dobDay == 0 || dobMonth == 0 || dobYear ==0){
            return "0/0/0";
        } else {
            //else it will be set to dd/MM/YYYY format
            return day + "/" + month + "/" + year;
        }
    }

    //method to set all attribute into the contacts
    public void setAttribute(String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark) {
        this.name = name;
        this.gender = gender;
        //the birthday day, month and year is combined become birthday date
        this.dobDate = generateDOBDate(dobDay, dobMonth, dobYear);
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.homePhone = homePhone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.id = 1;
    }

    //setter of id
    public void setID(int id){
        this.id = id;
    }

    //getter for the birthday date
    public String getDobDate() {
        return dobDate;
    }

    //getter for the address
    public String getAddress() {
        return address;
    }

    //getter for the email
    public String getEmail() {
        return email;
    }

    //getter for the gender
    public String getGender() {
        return gender;
    }

    //getter for the name
    public String getName() {
        return name;
    }

    //getter for the home phone
    public String getHomePhone() {
        return homePhone;
    }

    //getter for the mobile phone
    public String getMobilePhone() {
        return mobilePhone;
    }

    //getter for the work phone
    public String getWorkPhone() {
        return workPhone;
    }

    //getter for the remark
    public String getRemark() {
        return remark;
    }

    //getter for the id (in String)
    public String getId() {
        return Integer.toString(id);
    }

}
