package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    // dummied up variable; you will need to change this
    private Hashtable <String, SoccerPlayer> database = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String fullName = firstName + " " + lastName;
        //create the SoccerPlayer with given values
        SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);

        //check whether player is already in database, return false
        if (database.containsKey(fullName)){
            return false;
        }
        //else if not in database, put soccer player in database
        else{
            database.put(fullName, newPlayer);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (database.containsKey(fullName)){
            database.remove(fullName);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (database.containsKey(fullName)){
            return database.get(fullName);
        }
        else {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        // get soccerplayer
        // if does not exist, return false
        // else, bump up goals
        String fullName = firstName + " " + lastName;
        if (!database.containsKey(fullName)){
            return false;
        }
        else{
            database.get(fullName).bumpGoals();
            return true;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (!database.containsKey(fullName)){
            return false;
        }
        else{
            database.get(fullName).bumpYellowCards();
            return true;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (!database.containsKey(fullName)){
            return false;
        }
        else{
            database.get(fullName).bumpRedCards();
            return true;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if(teamName == null){
            return database.size();
        }
        else {
            int numInTeam = 0;

            // count each player with correct team name
            for (SoccerPlayer player : database.values()) {
                // if that player has teamname, increment numInTeam
                if (teamName.equals(player.getTeamName()))
                    numInTeam++;
            }
            return numInTeam;

        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        int inc = 0 ;
        for (SoccerPlayer player : database.values()){
            if (teamName == null || teamName.equals(player.getTeamName())){
                if(inc == idx){
                    return player;
                }
                inc++;
            }
        }
        return null;

    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);

        // open given file

        // write out data to file from data base
        for (SoccerPlayer player : database.values()) {
            pw.write(player.toString());
        }
        // close file
        pw.close();
        return true;

        // return true if successful
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
