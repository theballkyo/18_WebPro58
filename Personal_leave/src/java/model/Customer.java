/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author theba
 */
public class Customer {

    private int id;
    private String username;
    private String fName;
    private String lName;
    private String password;
    private String tel;
    private String email;
    private int type;

    public Customer() {
    }

    public Customer(int id, String username, String fName, String lName, String password, String tel, String email, int type) {
        this.id = id;
        this.username = username;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRoleName() {
        switch(type) {
            case 0:
                return "Admin";
            case 1:
                return "Boss";
            case 2:
                return "Normal user";
            default:
                return "Guest";
        }
    }
}
