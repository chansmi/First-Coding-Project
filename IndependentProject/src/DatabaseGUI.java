/*
 * Author: Chandler Smith
 * Date: August 11, 2021
 * CS5001
 * File description: This is the GUI file that calls DatabaseModel, DatabaseHelper, and Player
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DatabaseGUI extends Component {
    private JPanel basePanel;
    private JLabel lb_Title;
    private JPanel loadPanel;
    private JTable table1;
    private JButton loadData;
    private JPanel loadDataPanel;
    private JScrollPane playerScrollPane;
    private JPanel addPlayerPanel;
    private JButton Add;
    private JTextField numberTextPane;
    private JLabel numberLabel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField ageTextField;
    private JPanel inputPanel;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel age;

    private DatabaseHelper helper;


    /**
     * The database GUI called in the main function.
     * This GUI is the view which relies on the DatabaseHelper.java and DatabaseModel.java code
     * This was a deliberate abstraction to protect the controller and the database from the view
     */

    public DatabaseGUI() {

        // this creates the helper function by instantiating DatabaseHelper.java
        try {
            helper = new DatabaseHelper();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
        // This is saying that when the "load data" butten is pressed, get all the rows in the table Athlete and
            // return it in a new DatabaseModel instance. Using a list of players helps protect the controller, but
            // that list is created every time so it is always representative of what is in the database.

        loadData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    List<Player> players = null;
                    players = helper.getPlayers();

                    // create the model of the database to throw the displayed players into
                    DatabaseModel model = new DatabaseModel(players);
                    table1.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(DatabaseGUI.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // this is saying - create a new player object and add them to the athlete table as a new player object
        Add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String lastName = lastNameTextField.getText();
                String firstName = firstNameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                int number = Integer.parseInt(numberTextPane.getText());
                Player p1 = new Player(firstName, lastName, age, number);
                helper.insertPlayer(p1);
            }
        });
    }

    /**
     *This is the main command which runs my Intellij GUI.
     *  @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("LFC");
        frame.setContentPane(new DatabaseGUI().basePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

