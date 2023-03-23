import javax.swing.*;
import java.sql.SQLException;

public class Menu {
    public static void menu() throws SQLException {

        AppController.showAllContacts();

        int result = JOptionPane.showConfirmDialog(null, "Do you want to make any actions?");
        String chosenOption = null;
        do {
            if (result == JOptionPane.YES_OPTION) {
                chosenOption = AppController.displayingAllActionsAvailable();

                switch (chosenOption) {
                    case "Add new contact":
                        String name = JOptionPane.showInputDialog("Please enter contact name");
                        String surname = JOptionPane.showInputDialog("Please enter contact surname");
                        String phoneNumber = JOptionPane.showInputDialog("Please enter contact Phone Number");
                        String email = JOptionPane.showInputDialog("Please enter contact email");

                        JOptionPane.showMessageDialog(null, AppController.addNewContact(name, surname, phoneNumber, email));

                        break;
                    case "Find a contact":
                        String partOfNameOrSurname = JOptionPane.showInputDialog("Please enter part of name or surname you want to find");
                        System.out.println();
                        System.out.println("===========                =============");
                        System.out.println();
                        AppController.findingExistingContact(partOfNameOrSurname);
                        break;
                    case "Update a contact":
                        name = JOptionPane.showInputDialog("Please enter contact name you want to update");
                        surname = JOptionPane.showInputDialog("Please enter contact surname you want to update");
                        String column = JOptionPane.showInputDialog("Enter the column to update (name, surname, phoneNumber, or email):");
                        String newValue = JOptionPane.showInputDialog("Please enter a new value");
                        JOptionPane.showMessageDialog(null, AppController.updatingExistingContact(column, newValue, name, surname));
                        break;
                    case "Remove contact":
                        name = JOptionPane.showInputDialog("Please enter contact name you want to delete");
                        surname = JOptionPane.showInputDialog("Please enter contact surname you want to delete");
                        JOptionPane.showMessageDialog(null, AppController.removingContact(name,surname));
                        break;
                    case "Import contacts from csv":
                        AppController.importingContacts();
                        break;
                    case "Export contacts to csv":
                        AppController.exportingContacts();
                        break;
                    case "Exit application":
                        JOptionPane.showMessageDialog(null, "GoodBye");
                        System.exit(0);
                }

            } else {
                System.out.println("Goodbye");
                System.exit(0);
            }
        } while (!chosenOption.equals("Exit application"));
    }
}
