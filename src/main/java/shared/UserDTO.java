package shared;
import java.util.List;
import java.util.ArrayList;

public class UserDTO {
    private String username;
    private String password;
    private List<String> habits;
    private List<String> northStarGoals;

    //CONSTRUCTORS
    public UserDTO(String u, String p) {
        this.username = u;
        this.password = p;
        this.habits = new ArrayList<String>();
        this.northStarGoals = new ArrayList<String>();
    }
    public UserDTO() { //empty constructor
        this.habits = new ArrayList<String>();
        this.northStarGoals = new ArrayList<String>();
    }

    //GETTERS
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public List<String> getHabits() {
        return this.habits;
    }
    public List<String> getNorthStarGoals() {
        return this.northStarGoals;
    }

    //SETTERS
    public void setUsername(String u) {
        this.username = u;
    }
    public void setPassword(String p) {
        this.password = p;
    }
    public void setHabit(int idx, String habit) {
        this.habits.set(idx, habit);
    }
    public void setNorthStarGoal(int idx, String northGoal) {
            this.northStarGoals.set(idx, northGoal);
    }

    //adding habits and goals
    public void addHabit(String habit) {
        this.habits.add(habit);
    }
    public void AddGoal(String goal) {
        this.northStarGoals.add(goal);
    }
}
