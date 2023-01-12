/*
 * Author: Chandler Smith
 * Date: August 11, 2021
 * CS5001
 * File description: This is the DatabaseHelper file which provides ability to access and edit a database
 */

import java.io.FileInputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// citing https://www.postgresql.org/docs/9.2/ecpg-sql-connect.html - extremely helpful documentation
// Same with https://zetcode.com/java/postgresql/ which helped me understand the importance of the JDBC
// https://www.postgresqltutorial.com/ - for research with closing adding things to postgres tables

/**
 * Database helper creates the methods which enable the GUI to edit and return the database contents
 */
public class DatabaseHelper {
    // connects to my database and proves we can add players - it is extremely important to open the connection once for the GUI,
        // otherwise the GUI would not be able to handle multiple changes or calls to the database
    public Connection connect;

    /**
     * Database helper method which confirms the connection and the JDBC driver
     * @throws Exception
     */
    public DatabaseHelper() throws Exception {
        // initialized the JDBC driver which allows the opening of database connections
        Class.forName("org.postgresql.Driver");
        // Java Util program which calls on my Player Properties file to login. This is standard practice so I don't hard code in my passwords :/
        Properties props = new Properties();
        props.load(new FileInputStream("player.properties"));
        // database properties - logging into the database
        String user = props.getProperty("user");
        String password = props.getProperty("password"); // don't want to hard code this in haha
        String dburl = props.getProperty("dburl");

        // connect to my database
        // TEST: this tests my data base is successful!
        connect = DriverManager.getConnection(dburl, user, password);
        System.out.println("DB connection successful to " + dburl);
    }

    // short test to confirm the actual connection and methods
    public static void main(String[] args) throws Exception {
        DatabaseHelper d1 = new DatabaseHelper();
        // TEST: getplayers()
        d1.getPlayers();
        //Player p2 = new Player("Steve", "Smith", 24, 14);
        // TEST: insertPlayer
        //d1.insertPlayer(p2);
        //d1.getPlayers();
    }

    /////// More complex methods regarding editing the database ////////
    /**
     * @return the current database table Athletes - containing the Liverpool squad
     * ResultSet is a table and Statement is an object. Both are specific to enabling the JDBC driver
     * @throws Exception
     */
    public List<Player> getPlayers() throws Exception {
        // Need to simplify this to just return all players
        List<Player> list = new ArrayList<>();

        // Sprinkle in a little SQL query
        String SQL = "SELECT * FROM athlete";
        // Connect to the database
        try (
             Statement stmt = connect.createStatement(); // note, important this utilizes the same connection.
             // Execute the query
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                Player tempPlayer = convertRowToPlayer(rs);
                list.add(tempPlayer);
            }
            // display actor information
            displayPlayer(rs);
            //TEST: System.out.println(rs.toString());  - this was a test, returns a memory address
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    /**
     * The purpose of this is a helper method for the table population, otherwise, the player object don't become Jtable rows
     * @param rs
     * @return tempPlayer list
     * @throws SQLException
     */
// this helper method is necessary for the population of a table - RS = result set specified in method getPlayers()
    // Without this helper method the Players would not return as the table format in the GUI
    private Player convertRowToPlayer(ResultSet rs) throws SQLException {
        int ID = rs.getInt("id");
        String lastName = rs.getString("last");
        String firstName = rs.getString("first");
        int age = rs.getInt("age");
        int number = rs.getInt("number");

        Player tempPlayer = new Player(ID, lastName, firstName, age, number);
        return tempPlayer;
    }

    /**
     * Display Player is necessary because otherwise it returns a memory address so had to add
     * a method and a toString to my Player class
     * @param rs
     * @throws SQLException
     */
    private void displayPlayer(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("first") + "\t"
                    + rs.getString("last") + "\t"
                    + rs.getString("age")+ "\t"
                    + rs.getString("number"));
        }
    }

    /**
     * Method inserts a player object into the database
     * @param tempPlayer
     * @return confirmation the player was added
     */
    public String insertPlayer(Player tempPlayer) {

            String SQL = "INSERT INTO athlete(first,last,age,Number)"
                    + "VALUES( ?, ?, ?, ?)";

            try (
                 PreparedStatement pstmt = connect.prepareStatement(SQL)) {
                // set everything equal to the gathered data
                pstmt.setString(1, tempPlayer.getFirstName());
                pstmt.setString(2, tempPlayer.getLastName());
                pstmt.setInt(3, tempPlayer.getAge());
                pstmt.setInt(4, tempPlayer.getNumber());

                pstmt.executeUpdate();
                // TEST: this is a test.
                System.out.println("the player was added, wohoo!");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return "the player was added";
        }}
