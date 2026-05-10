package shared;
import java.util.*;
public class User {
    private String username;
    private String password;
    private LinkedList<String> userHabitList;
    private String[] northStarGoals; 

    public User(String u, String p) {
        this.username = u;
        this.password = p;
        this.userHabitList = new LinkedList<>();
        this.northStarGoals = new String[3]; //only 3 goals can go here
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public LinkedList<String> getUserHabitList() {
        return this.userHabitList;
    }
    public String[] getNorthStartGoals() {
        return this.northStarGoals;
    }
}
