import java.util.LinkedList;

public class DataStorage {
    private LinkedList<User> users;
    private static DataStorage instance = new DataStorage();
    private DataStorage() {
        users = new LinkedList<>();
        users.add(new User("Dom", "p1"));
    }

    public static DataStorage getInstance() {
        return instance;
    }
    public void addUser(User t_user) {
        users.add(t_user);
    }
    public LinkedList<User> getUsers() {
        if (this.users.isEmpty()) {
            System.out.println("List is empty.");
        }
        return this.users;
    }

    public boolean findUser(String username, String password) {
        for (int i = 0; i < users.size();i++) {
            if (users.get(i).getUsername().equals(username)) {
                System.out.println("Username found, checking password match...");
                if (users.get(i).getPassword().equals(password)) {
                    System.out.println("Correct username and password, logging in...");
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void removeUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                System.out.println("User [" + username + "] has been removed.");
                return;
            }
        }
        System.out.println("User not found in storage.");
        return;
    }
}
