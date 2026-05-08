import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    static final String SIGNUP = "SIGNUP";
    static final String DASHBOARD = "DASHBOARD";
    static final String LOGIN = "LOGIN";
    
    public MainFrame() {
        setTitle("Habit Tracker");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new SignupPanel(this), SIGNUP);
        cardPanel.add(new Dashboard(this), DASHBOARD);
        cardPanel.add(new LoginPanel(this), LOGIN);

        add(cardPanel); //add cardPanel to main window
        setLocationRelativeTo(null); //put to middle of screen
        setVisible(true);
    }
    public void showScreen(String screenName) {
        cardLayout.show(cardPanel, screenName);
        cardPanel.revalidate(); //recalculate size and position of all components
        cardPanel.repaint(); // redraw pixels on the screen to ensure proper card swap
    }
}
