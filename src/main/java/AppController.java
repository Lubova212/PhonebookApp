import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;

public class AppController {

    public static String displayingAllActionsAvailable(){
        String [] choice ={"Add new contact", "Find a contact", "Update a contact", "Remove contact",
                "Import contacts from csv", "Export contacts to csv", "Exit application"};
        String chosenOption = (String) JOptionPane.showInputDialog(
                null,
                "Please select next operation",
                "Options:",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]);

        return chosenOption;
    }

    public static void showAllContacts() throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM phonebook";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;

            while (resultSet.next()){

                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phonenumber");
                String email = resultSet.getString("email");

                String output = "%s - %s - %s - %s";
                System.out.println(String.format(output, name, surname, phoneNumber, email));
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    static void exportingContacts() {
        String csvFilePath = "mytable.csv";
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM phonebook";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));

            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.write(resultSet.getMetaData().getColumnName(i));
                if (i < columnCount) {
                    writer.write(",");
                }
            }
            writer.newLine();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(resultSet.getString(i));
                    if (i < columnCount) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            writer.close();

            JOptionPane.showMessageDialog(null, "Contacts were exported successfully");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void importingContacts() {
        String csvFilePath = "mytable.csv";
        String tableName = "mytable";
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();

            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            for (CSVRecord csvRecord : csvParser) {
                String name = csvRecord.get("name");
                String surname = csvRecord.get("surname");
                String phone = csvRecord.get("phonenumber");
                String email = csvRecord.get("email");

                String sql = "INSERT INTO phonebook (name, surname, phonenumber, email) VALUES ('" + name + "', '" + surname + "', '" + phone + "', '" + email + "')";
                statement.executeUpdate(sql);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"Data imported successfully");
    }

    public static String removingContact(String name, String surname) throws SQLException {
        PreparedStatement statement = null;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM phonebook WHERE name = ? AND surname = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (statement.executeUpdate() > 0) {
            return "Contact was deleted successfully";
        } else {
            return "Something went wrong";
        }
    }

    static String updatingExistingContact(String column, String newValue, String name, String surname) throws SQLException {

        PreparedStatement statement = null;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE phonebook SET " + column + "=? WHERE name = ? AND surname =?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, newValue);
            statement.setString(2, name);
            statement.setString(3, surname);


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (statement.executeUpdate() > 0) {
            return "Contact was updated successfully";
        } else {
            return "Something went wrong";
        }
    }

    static void findingExistingContact(String partOfNameOrSurname) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM phonebook WHERE name LIKE '%" + partOfNameOrSurname + "%' OR surname LIKE '%" + partOfNameOrSurname +"%'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;

            while (resultSet.next()){

                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phonenumber");
                String email = resultSet.getString("email");

                String output = "%s - %s - %s - %s";
                System.out.println(String.format(output, name, surname, phoneNumber, email));
            }

        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String addNewContact(String name, String surname, String phoneNumber, String email) {
    int rowInserted = 0;
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO phonebook (name, surname, phonenumber, email) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, phoneNumber);
        statement.setString(4, email);

        rowInserted = statement.executeUpdate();


    } catch (Exception e) {
        System.out.println(e);
    }

    if (rowInserted > 0) {
        return "New contact was inserted successfully";
    } else {
        return "Something went wrong";
    }
}
}
