// DashboardPanel.java
import java.awt.*;
import java.util.concurrent.Flow;

import javax.swing.*;
import javax.swing.border.Border;
/*
SE 461 Final EXAM Prep
Input Spaced Partioning (ISP), Logic Testing, JUnit (1 question entirely on junit)

*/
public class Dashboard extends JPanel {
    private JButton addHabitButton;
    private JButton viewReportButton;
    private JButton settingsButton;
    private JButton signOutButton;
    private JButton deleteHabitButton;
    private JTextField habitInputField;
    public Dashboard(MainFrame app) {
        //Set dashboard layout
        setLayout(new BorderLayout(10, 10)); 
        //WEST Panel -> Habits list
        JPanel habitsPanel = new JPanel(new BorderLayout());
        habitsPanel.setBorder(BorderFactory.createTitledBorder("Habits"));
        JPanel habitsList = new JPanel();
        habitsList.setLayout(new BoxLayout(habitsList, BoxLayout.Y_AXIS)); //show list vertically
        habitsList.add(new JCheckBox("Habit1"));

        habitsPanel.add(new JScrollPane(habitsList), BorderLayout.CENTER);
        habitsPanel.setPreferredSize(new Dimension(200, 0));

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        JPanel dailyProgress = new JPanel(new BorderLayout());
        dailyProgress.setBorder(BorderFactory.createTitledBorder("Daily Progress"));
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(10);
        dailyProgress.add(progressBar, BorderLayout.CENTER);

        JPanel northStar = new JPanel();
        northStar.setBorder(BorderFactory.createTitledBorder("North Start Goals"));
        northStar.setLayout(new BoxLayout(northStar, BoxLayout.Y_AXIS));
        northStar.add(new JLabel("1. Goal one"));
        northStar.add(new JLabel("2. Goal two"));
        northStar.add(new JLabel("3. Goal three"));

        centerPanel.add(dailyProgress);
        centerPanel.add(northStar);

        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.setBorder(BorderFactory.createTitledBorder("Monthly Completion Chart"));
        eastPanel.setPreferredSize(new Dimension(200, 0));
        //chart and data go here vvv

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        addHabitButton = new JButton("Add Habit");
        viewReportButton = new JButton("View Report");
        settingsButton = new JButton("Settings");
        signOutButton = new JButton("Sign Out");
        deleteHabitButton = new JButton("Delete");
        habitInputField = new JTextField(15);

        southPanel.add(habitInputField);
        southPanel.add(addHabitButton);
        southPanel.add(deleteHabitButton);
        southPanel.add(viewReportButton);
        southPanel.add(settingsButton);
        southPanel.add(signOutButton);

        //add components to dashboard
        add(habitsPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
        
        //action listeners for buttons

        //add habit to list
        addHabitButton.addActionListener(e -> {
            String habitToText = habitInputField.getText().trim();
            if (habitToText.isEmpty()) {
                JOptionPane.showMessageDialog(this,"Cannot have empty field.");
                return;
            } else {
                habitInputField.setText("");
                habitsList.add(new JCheckBox(habitToText));
                habitsList.revalidate();
                habitsList.repaint();
                System.out.println("Habit added.");
            }
        });
        signOutButton.addActionListener(e -> { //send back to sign up page
            app.showScreen(MainFrame.SIGNUP);
        });
        deleteHabitButton.addActionListener(e -> {
            Component[] habits = habitsList.getComponents();
            for (int i = habits.length - 1; i >= 0; i--) {  // iterate backwards to safely remove
                if (habits[i] instanceof JCheckBox cb && cb.isSelected()) {
                    habitsList.remove(cb);
                }
            }
            habitsList.revalidate();
            habitsList.repaint();
        });
    }
}