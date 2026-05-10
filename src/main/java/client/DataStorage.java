package client;
import java.util.HashMap;

import src.main.java.backend.model.User;

public class DataStorage {
    private HashMap<String, User> users;
    private static DataStorage instance = new DataStorage();
    private DataStorage() {
        users = new HashMap<String, User>();
        User admin = new User("Dom", "p1");
        users.put(admin.getUsername(), admin);
    }

    public static DataStorage getInstance() {
        return instance;
    }
    public void addUser(User t_user) {
        users.put(t_user.getUsername(), t_user);
    }
    public User getUser(String key) {
        if (this.users.isEmpty()) { //
            System.out.println("Database empty.");
        }
        return this.users.get(key);
    }

    public boolean findUser(String username, String password) {
        if (users.containsKey(username)) {
            if (users.get(username).getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void removeUser(String username) {
        if (users.remove(username) != null) {
            System.out.println("User removed.");
        } else {
            System.out.println("User not found.");
        }
    }
}
