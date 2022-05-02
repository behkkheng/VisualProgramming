package com;

//data should in a single linked list node
public class Contact {
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


    //function to combine dobDay, dobMonth and dobYear to a date
    public String generateDOBDate(int dobDay, int dobMonth, int dobYear) {
        String day = Integer.toString(dobDay);
        String month = Integer.toString(dobMonth);
        String year = Integer.toString(dobYear);

        return day + "/" + month + "/" + year;
    }

    public void setId(int id) {
        this.id = id;
    }

    //function to set all attribute
    //I know this is a very bad habit, but I lazy to think other way
    public void setAttribute(String name, String gender, int dobDay, int dobMonth, int dobYear, String mobilePhone, String workPhone, String homePhone, String email, String address, String remark) {
        this.name = name;
        this.gender = gender;
        this.dobDate = generateDOBDate(dobDay, dobMonth, dobYear);
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.homePhone = homePhone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.id = 1;
    }

    public String getDobDate() {
        return dobDate;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getRemark() {
        return remark;
    }

    public String getId() {
        return Integer.toString(id);
    }

    public int getDobDay() {
        return dobDay;
    }

    public int getDobMonth() {
        return dobMonth;
    }

    public int getDobYear() {
        return dobYear;
    }
}
