package gui_new;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame() {
        setTitle("Library Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add panels
        mainPanel.add(new MenuPanel(this), "Menu");
        mainPanel.add(new LoginPanel(this), "Log In");
        mainPanel.add(new SignupPanel(this), "Sign Up");

        add(mainPanel);
        setVisible(true);
    }

    public void switchPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
