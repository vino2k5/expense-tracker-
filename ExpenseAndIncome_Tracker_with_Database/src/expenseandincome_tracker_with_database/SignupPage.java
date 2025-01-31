/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expenseandincome_tracker_with_database;

/**
 *
 * @author shankari
 */
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public SignupPage() {
        setTitle("Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (signupUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Signup successful! Redirecting to login...");
                    dispose(); // Close the signup window
                    new LoginPage().setVisible(true); // Open the login page
                } else {
                    JOptionPane.showMessageDialog(null, "Signup failed. Username might be taken.");
                }
            }
        });

        JButton loginButton = new JButton("Back to Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the signup window
                new LoginPage().setVisible(true); // Open the login page
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(signupButton);
        panel.add(loginButton); // Add the login button to the panel

        add(panel);
    }

    private boolean signupUser(String username, String password) {
        try (Connection conn = DatabaseConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Consider hashing the password for security
            return stmt.executeUpdate() > 0; // Check if any rows were affected
        } catch (SQLException e) {
            e.printStackTrace(); // Check for specific SQL errors in the console
            return false;
        }
    }

    public static void main(String[] args) {
        new SignupPage().setVisible(true);
    }
}