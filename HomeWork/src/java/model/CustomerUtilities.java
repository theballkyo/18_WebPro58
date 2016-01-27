/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.Database;

/**
 *
 * @author theba
 */
public class CustomerUtilities {

    public boolean insert(Customer cus) {
        try {
            String sql = "INSERT INTO customers  (`firstname`, `lastname`, `company`, `mobile`, `email`, `address`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = Database.getInstance().getConn().prepareStatement(sql);

            ps.setString(1, cus.getFirstName());
            ps.setString(2, cus.getLastName());
            ps.setString(3, cus.getCompany());
            ps.setString(4, cus.getMobile());
            ps.setString(5, cus.getEmail());
            ps.setString(6, cus.getAddr());
            
            if (ps.executeUpdate() == 0) {
                ps.close();
                return false;
            }
            ps.close();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public ResultSet getAll() {
        try {
            PreparedStatement find = null;

            find = Database.getInstance().getConn().prepareStatement("SELECT * from customers");

            ResultSet n = find.executeQuery();

            return n;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
