package com.jbdcpractice;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;


public class ItemService {
    private Connection con;

    private final String insertSQL = "INSERT INTO \"todoItem\" " +
        " VALUES (?, ?, ?, ?);";

    private final String markDoneSQL = "UPDATE \"todoItem\" " + 
        " SET \"Done\" = true " +
        " WHERE \"Title\" = ?;";
    
    private final String deleteSQL = "DELETE FROM \"todoItem\" WHERE \"Title\" = ?;";

    private final String deleteAllSQL = "DELETE FROM \"todoItem\";";

    private final String changeDescSQL = "UPDATE \"todoItem\" " + 
    " SET \"Description\" = ? " +
    " WHERE \"Title\" = ?;";

    private final String viewSQL = "SELECT * FROM \"todoItem\" WHERE \"Title\" = ?;";

    private final String viewAllSQL = "SELECT * FROM \"todoItem\";";
    
    

    public ItemService(Connection con) {
        this.con = con;
    }

    public Item createItem(String title, String description) throws SQLException {

        int time = (int) java.time.Instant.now().getEpochSecond();
        PreparedStatement stmnt = con.prepareStatement(insertSQL);

        if (description == null) {
            stmnt.setString(1, title);
            stmnt.setInt(2, time);
            stmnt.setString(3, "");
            stmnt.setBoolean(4, false);
            int result = stmnt.executeUpdate();

            System.out.println(result + "\n");
            return new Item(title, time, "", false);

        } else {
            stmnt.setString(1, title);
            stmnt.setInt(2, time);
            stmnt.setString(3, description);
            stmnt.setBoolean(4, false);
            int result = stmnt.executeUpdate();

            System.out.println(result + "\n");
            return new Item(title, time, description, false);
        }
    } 
    
    
    public int markItemAsDone(String title) throws SQLException {
        PreparedStatement stmnt = con.prepareStatement(markDoneSQL);stmnt.setString(1, title);
        stmnt.setString(1, title);
        return stmnt.executeUpdate();
    };

    public int deleteItem(String title) throws SQLException {
        PreparedStatement stmnt = con.prepareStatement(deleteSQL);
        stmnt.setString(1, title);
        return stmnt.executeUpdate();
    };

    public int deleteItems() throws SQLException {
        PreparedStatement stmnt = con.prepareStatement(deleteAllSQL);
        return stmnt.executeUpdate();
    };

    public int updateItemDescription(String title, String description) throws SQLException {
        PreparedStatement stmnt = con.prepareStatement(changeDescSQL);
        stmnt.setString(1, description);
        stmnt.setString(2, title);
        return stmnt.executeUpdate();
    };

    public Item viewItem(String title) throws SQLException {
        PreparedStatement stmnt = con.prepareStatement(viewSQL);
        stmnt.setString(1, title);
        ResultSet rs = stmnt.executeQuery();
        rs.next();
        return new Item(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4));
    };

    public ArrayList<Item> viewItems() throws SQLException {
        PreparedStatement stmnt = con.prepareStatement(viewAllSQL);
        ResultSet rs = stmnt.executeQuery();

        ArrayList<Item> returnMe = new ArrayList<Item>();
        while(rs.next()) {
            returnMe.add(new Item(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4)));
        } 
        return returnMe;
    };
}
