package src.main.java.client;
import java.awt.*;
import javax.swing.*;
public class LoginPanel extends JPanel {
    private JButton loginButton;
    private JButton backToSignup;
    private JTextField usernameInputField;
    private JTextField passwordInputField;

    public LoginPanel(MainFrame app) {
        setLayout(new GridBagLayout());

        loginButton = new JButton("Login");
        backToSignup = new JButton("Back");
        usernameInputField = new JTextField(15);
        passwordInputField = new JTextField(15);

        JPanel loginForm = new JPanel();
        loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.add(loginLabel);
        loginForm.add(titlePanel);

        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernamePanel.add(new JLabel("Username: "));
        usernamePanel.add(usernameInputField);
        
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Password: "));
        passwordPanel.add(passwordInputField);
        
        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(backToSignup);
        buttonPanel.add(loginButton);

        loginForm.add(usernamePanel);
        loginForm.add(passwordPanel);
        loginForm.add(buttonPanel);
        add(loginForm);

        loginButton.addActionListener(e -> {
            String username = usernameInputField.getText().strip();
            String password = passwordInputField.getText().strip();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cannot have empty inputs.");
            } else {
                boolean checkValidUser = DataStorage.getInstance().findUser(username, password);
                if (checkValidUser) { //true
                    app.showScreen(MainFrame.DASHBOARD); //go to dashboard if signed in
                } else {
                    //false
                    JOptionPane.showMessageDialog(this, "User not found.");
                }
            }
            usernameInputField.setText("");
            passwordInputField.setText("");
        });
        backToSignup.addActionListener(e -> {
            app.showScreen(MainFrame.SIGNUP);
        });
    }
}
