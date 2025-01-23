package gui_new;

import java.awt.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    public LoginPanel(MainFrame parentFrame) {
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(250, 150, 100, 30);
        add(lblUsername);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(350, 150, 250, 30);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(250, 200, 100, 30);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(350, 200, 250, 30);
        add(txtPassword);

        JButton btnLogin = new JButton("Log In");
        btnLogin.setBounds(350, 300, 100, 40);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login
                JOptionPane.showMessageDialog(null, "Login successful!");
                parentFrame.switchPanel("Menu");
            }
        });
        add(btnLogin);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(470, 300, 100, 40);
        btnBack.addActionListener(e -> parentFrame.switchPanel("Menu"));
        add(btnBack);
    }
}
