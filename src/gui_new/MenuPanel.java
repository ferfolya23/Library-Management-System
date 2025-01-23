package gui_new;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public MenuPanel(MainFrame parentFrame) {
        setLayout(null);
        
        // Title label
        JLabel lblMainTitle = new JLabel("YorkU Library Management");
        lblMainTitle.setForeground(new Color(255, 0, 0, 180));
        lblMainTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
        lblMainTitle.setBounds(200, 50, 500, 50);
        add(lblMainTitle);

        // Login/Sign Up button
        JButton btnLoginSignup = new JButton("Log In");
        btnLoginSignup.setBounds(370, 200, 150, 50);
        btnLoginSignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLoginSignup.addActionListener(e -> parentFrame.switchPanel("Log In"));
        add(btnLoginSignup);

        // Exit button
        JButton btnExit = new JButton("Sign Up");
        btnExit.setBounds(370, 280, 150, 50);
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExit.addActionListener(e -> parentFrame.switchPanel("Sign Up"));
        add(btnExit);
    }
}
