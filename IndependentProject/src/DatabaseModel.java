/*
 * Author: Chandler Smith
 * Date: August 11, 2021
 * CS5001
 * File description: This is the GUI DatabaseModel file which enables the GUI to populate the data in the table
 */

import java.util.List;
import javax.swing.table.AbstractTableModel;

class DatabaseModel extends AbstractTableModel {

    private static final int LAST_NAME_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int AGE_COL = 2;
    private static final int NUMBER_COL = 3;

    private String[] columnNames = { "Last Name", "First Name", "Age",
            "Number" };
    private List<Player> players;

    public DatabaseModel(List<Player> Players) {
        players = Players;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return players.size();
    }
// Uses index specified above
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Player tempPlayer = players.get(row);

        switch (col) {
            case LAST_NAME_COL:
                return tempPlayer.getLastName();
            case FIRST_NAME_COL:
                return tempPlayer.getFirstName();
            case AGE_COL:
                return tempPlayer.getAge();
            case NUMBER_COL:
                return tempPlayer.getNumber();
            default:
                return tempPlayer.getLastName();
        }
    }
    // overrides the class to return appropriate data type
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
