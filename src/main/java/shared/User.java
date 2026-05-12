package shared;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @ElementCollection //tells JPA to create a seperate join table for this collection
    @Column(name = "habits")
    private List<String> userHabitList;

    @ElementCollection
    @Column(name = "northStar")
    private List<String> northStarGoals;

    public User(String u, String p) {
        this.username = u;
        this.password = p;
        this.userHabitList = new ArrayList<>();
        this.northStarGoals = new ArrayList<String>(); //only 3 goals can go here
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public List<String> getUserHabitList() {
        return this.userHabitList;
    }
    public List<String> getNorthStartGoals() {
        return this.northStarGoals;
    }

    public void setUsername(String u) {
        this.username = u;
    }
    public void setPassword(String p) {
        this.password = p;
    }
    public void setHabit(int idx, String habit) {
        this.userHabitList.set(idx, habit);
    }
    public void setNorthStarGoal(int idx, String northGoal) {
            this.northStarGoals.set(idx, northGoal);
    }

}
