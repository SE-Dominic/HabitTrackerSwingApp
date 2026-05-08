import javax.swing.*;
/*
-> 'javac *.java' to compile enter folder
-> 'java Main' to run the main file
*/
class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}