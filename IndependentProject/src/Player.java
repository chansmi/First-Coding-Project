/*
/*
 * Author: Chandler Smith
 * Date: August 11, 2021
 * CS5001
 * File description: This is the Player class which operates similarly to the Character class in battle arena
 */

/**
 * Player class
 */
public class Player {

private String firstName;
private String lastName;
private int age;
private int number;
private int ID;

    /**
     * @param firstName
     * @param lastName
     * @param age
     * @param number
     */
    public Player( int ID, String firstName, String lastName, int age, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.number = number;
        this.ID = ID;
    }
    public Player( String firstName, String lastName, int age, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.number = number;
    }

    /**
     * @return the first name a player
     */
    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the first name of the player
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the last name of the player
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the last name of the player
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return age of player
     */
    public int getAge() {
        return age;
    }

    /**
     * sets the age of a player
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the player's jersey number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets jersey number of the player
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the ID of a player
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the player's ID
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return String
                .format("Employee [lastName=%s, firstName=%s, age=%s, number=%s]",
                        lastName, firstName, age, number);
    }


}
