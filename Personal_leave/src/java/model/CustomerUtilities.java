/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.Database;

/**
 *
 * @author theba
 */
public class CustomerUtilities {

    public boolean insert(Customer customer) {

        try {
            String sql = "INSERT INTO test_pls_app VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = Database.getInstance().getConn().prepareStatement(sql);

            stmt.setString(1, customer.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Customer auth(String username, String password) {
        try {
            PreparedStatement find = null;

            find = Database.getInstance().getConn().prepareStatement("SELECT * from test_pls_app_user WHERE username=? and password=?");

            find.setString(1, username);
            find.setString(2, password);
            ResultSet n = find.executeQuery();

            while (n.next()) {
                return new Customer(n.getInt("id"), n.getString("username"), n.getString("fName"), n.getString("lName"), n.getString("password"), n.getString("tel"), n.getString("email"), n.getInt("type"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
