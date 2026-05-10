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
    private int northStarGoalCount = 0;
    private int numOfHabits = 0;
    private JButton addHabitButton;
    private JButton viewReportButton;
    private JButton settingsButton;
    private JButton signOutButton;
    private JButton deleteHabitButton;
    private JTextField habitInputField;
    JProgressBar progressBar;
    private void updateProgressBar(JPanel habitsList) {
        Component[] habits = habitsList.getComponents();
        if (habits.length == 0) {
            progressBar.setValue(0);
            return;
        }
    int selectedHabits = 0;
    for (Component habit : habits) {
        if (habit instanceof JCheckBox cb && cb.isSelected()) {
            selectedHabits++;
        }
    }
        int newValue = (int) ((double) selectedHabits / habits.length * 100);
        progressBar.setValue(newValue);
    }
    public Dashboard(MainFrame app) {
        //Set dashboard layout
        setLayout(new BorderLayout(10, 10)); 
        //WEST Panel -> Habits list
        JPanel habitsPanel = new JPanel(new BorderLayout());
        habitsPanel.setBorder(BorderFactory.createTitledBorder("Habits"));
        JPanel habitsList = new JPanel();
        habitsList.setLayout(new BoxLayout(habitsList, BoxLayout.Y_AXIS)); //show list vertically
        habitsPanel.add(new JScrollPane(habitsList), BorderLayout.CENTER);
        habitsPanel.setPreferredSize(new Dimension(200, 0));

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        JPanel dailyProgress = new JPanel(new BorderLayout());
        dailyProgress.setBorder(BorderFactory.createTitledBorder("Daily Progress"));
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0); //initial value of progress bar = 0
        progressBar.setStringPainted(true);
        
        dailyProgress.add(progressBar, BorderLayout.CENTER);

        /* NORTH STAR PANEL */
        JPanel northStar = new JPanel();
        northStar.setBorder(BorderFactory.createTitledBorder("North Start Goals"));
        northStar.setLayout(new BoxLayout(northStar, BoxLayout.Y_AXIS));

        JPanel northStarList = new JPanel(); //to hold north star goals
        northStarList.setLayout(new BoxLayout(northStarList, BoxLayout.Y_AXIS)); //when new checkbox's are added they are along the y axis
        northStarList.setAlignmentX(Component.CENTER_ALIGNMENT); //centers the list

        JCheckBox testGoal = new JCheckBox("Test Goal");
        testGoal.setAlignmentX(Component.LEFT_ALIGNMENT); //center checkbox
        northStarList.add(testGoal);

        JPanel centeredList = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredList.add(northStarList);

        //for goal input and add button
        JPanel northstarInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField northStarTextField = new JTextField(15);
        JButton addNorthStarButton = new JButton("Add");
        northstarInput.add(northStarTextField);
        northstarInput.add(addNorthStarButton);
        
        northStar.add(centeredList, BorderLayout.CENTER);
        northStar.add(northstarInput, BorderLayout.SOUTH);

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

        //add habit to list
        addHabitButton.addActionListener(e -> { //for daily habits
            String habitToText = habitInputField.getText().trim();
            if (habitToText.isEmpty()) {
                JOptionPane.showMessageDialog(this,"Cannot have empty field.");
                return;
            } else {
                habitInputField.setText("");
                JCheckBox newHabit = new JCheckBox(habitToText);
                newHabit.addItemListener(e2 -> updateProgressBar(habitsList));
                habitsList.add(newHabit);
                numOfHabits += 1;
            
                updateProgressBar(habitsList);
                habitsList.revalidate();
                habitsList.repaint();
                System.out.println("Habit added.");
            }
        });
        signOutButton.addActionListener(e -> { //send back to sign up page
            app.showScreen(MainFrame.SIGNUP);
        });
        deleteHabitButton.addActionListener(e -> {
            Component[] northStarGoals = northStarList.getComponents();
            Component[] habits = habitsList.getComponents();
            for (int i = habits.length - 1; i >= 0; i--) {  // iterate backwards to safely remove
                if (habits[i] instanceof JCheckBox cb && cb.isSelected()) {
                    habitsList.remove(cb);
                }
            }
            updateProgressBar(habitsList);
            for (int i = northStarGoals.length - 1; i >= 0; i--) {  // iterate backwards to safely remove
                if (northStarGoals[i] instanceof JCheckBox cb && cb.isSelected()) {
                    northStarList.remove(cb);
                }
            }
            habitsList.revalidate();
            habitsList.repaint();
            northStarList.revalidate();
            northStarList.repaint();
        });
        addNorthStarButton.addActionListener(e -> {
            String northStarUserInput = northStarTextField.getText().strip();
            if (northStarUserInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cannot have empty input field.");
                return;
            } else { //has value in textfield box
                if (northStarGoalCount >= 3) {
                    System.out.println("Max goals reached.");
                    JOptionPane.showMessageDialog(this, "Max goals reached.");
                    northStarTextField.setText("");
                    return;
                } else {
                    JCheckBox newGoal = new JCheckBox(northStarUserInput);
                    newGoal.setAlignmentX(Component.LEFT_ALIGNMENT);
                    northStarList.add(newGoal);
                    System.out.println("North star goal added.");
                    northStarGoalCount += 1;
                    northStarTextField.setText("");
                    northStarList.revalidate();
                    northStarList.repaint();
                }
            }
        });
    }
}