import javax.swing.*;
import java.awt.*; //used for GridLayout


public class SignupPanel extends JPanel {
    private JButton signupButton;
    private JButton toLoginPageButton;
    private JTextField usernameInput;
    private JTextField passwordInput;

    public SignupPanel(MainFrame app) {
        //set layout of the form
        setLayout(new GridBagLayout());
        
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        JLabel signupLabel = new JLabel("Sign Up");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 25));
        signupLabel.setHorizontalAlignment(JLabel.CENTER);
        form.add(signupLabel);
        //initialize input fields
        usernameInput = new JTextField(15);
        passwordInput = new JTextField(15);
        //nest the username fields into one jpanel
        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameRow.add(new JLabel("Username: "));
        usernameRow.add(usernameInput);
        
        //nest password input fields into one jpanel
        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordRow.add(new JLabel("Password: "));
        passwordRow.add(passwordInput);
        
        signupButton = new JButton("Sign Up");
        toLoginPageButton = new JButton("Go to Login");

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.add(signupButton);
        buttonRow.add(toLoginPageButton);
        form.add(usernameRow); //add row to signupPanel
        form.add(passwordRow);
        form.add(buttonRow);

        add(form);
        //add addActionListener here vvv
        signupButton.addActionListener(e ->
            {
                String username = usernameInput.getText().trim();
                String password = passwordInput.getText().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter into both fields.");
                    return;
                } else {
                    if (DataStorage.getInstance().findUser(username, password)) { //check if user already exists
                        JOptionPane.showMessageDialog(this, "Username already exists.");
                        usernameInput.setText("");
                        passwordInput.setText("");
                        return;
                    }
                    DataStorage.getInstance().addUser(new User(username, password));
                    System.out.println("Sign up successful.");
                    usernameInput.setText("");
                    passwordInput.setText("");
                    app.showScreen(MainFrame.DASHBOARD);
                }
            }
        );
        toLoginPageButton.addActionListener(e -> {
            app.showScreen(MainFrame.LOGIN);
        });
    }
}
