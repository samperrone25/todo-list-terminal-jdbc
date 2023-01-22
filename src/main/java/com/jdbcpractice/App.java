package com.jdbcpractice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, SQLException {

        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        String url = "jdbc:postgresql://localhost:5432/jdbcDatabase";
        Connection conn = DriverManager.getConnection(url, props); // throws SQLException
        ItemService itemService = new ItemService(conn);

        int inputInt;
        printTerminalInterface();
        while ((input=br.readLine()) != null) { // throws IOException
            inputInt = Integer.parseInt(input);
            if (inputInt == 1) {
                System.out.println("\nEnter the todoItem title and an optional description, as follows: \n$ {Title} {Description}\n");
                input = br.readLine();
                String[] inputs = input.split(" ");
                Item newItem = itemService.createItem(inputs[0], inputs[1]);
                System.out.println("Created " + newItem.toString() + "\n");

            } else if (inputInt == 2) {
                System.out.println("\nEnter the todoItem title that you want to update the description of, and the new description, as follows: \n$ {Title} {NewDescription}\n");
                input = br.readLine();
                String[] inputs = input.split(" ");
                int result = itemService.updateItemDescription(inputs[0], inputs[1]);
                System.out.println("\nUpdated " + result + " item\n");

            } else if (inputInt == 3) {
                System.out.println("\nEnter the todoItem title that you wish to mark as done, as follows: \n$ {Title}\n");
                input = br.readLine();
                int result = itemService.markItemAsDone(input);
                System.out.println("\nUpdated " + result + " item\n");

            } else if (inputInt == 4) {
                System.out.println("\nEnter the todoItem title that you wish to view, as follows: \n$ {Title}\n");
                input = br.readLine();
                Item resultItem = itemService.viewItem(input);
                System.out.println("\n" + resultItem.toString() + "\n");

            } else if (inputInt == 5) {
                System.out.println("\nDB Contains:\n");
                ArrayList<Item> resultList = itemService.viewItems();
                for (Item todoItem: resultList) {
                    System.out.println(todoItem.toString());
                }
                System.out.println();
            
            } else if (inputInt == 6) {
                System.out.println("\nEnter the todoItem title that you wish to delete, as follows: \n$ {Title}\n");
                input = br.readLine();
                int result = itemService.deleteItem(input);
                System.out.println("\nDeleted " + result + " item\n");

            } else if (inputInt == 7) {
                int result = itemService.deleteItems();
                System.out.println("\nDeleted " + result + " item(s)\n");
            } else if (inputInt == 8) {
                System.exit(0);
            } 
            printTerminalInterface();
        } 
    }

    public static void printTerminalInterface() {
        System.out.println("1: createItem");
        System.out.println("2: updateItemDescription");
        System.out.println("3: markItemAsDone");
        System.out.println("4: viewItem");
        System.out.println("5: viewItems");
        System.out.println("6: deleteItem");
        System.out.println("7: deleteItems");
        System.out.println("8: Exit\n");
        System.out.println("Input Command: \n");  
    }
}