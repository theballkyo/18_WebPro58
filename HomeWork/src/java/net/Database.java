/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author theba
 */
public class Database {

    private static Database db = null;
    
    private Connection conn;
    
    private Database() {
        init();
    }
    
    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://database.it.kmitl.ac.th:3306/it_18?autoReconnect=true";
            String user = "it_18";
            String pwd = "YHnRzQ75";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }
    
    public Connection getConn() {
        try {
            if (conn.isClosed())
                init();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
