package gui_new;

import lms_func.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static LibraryDatabase database;
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame() throws Exception {
		database = LibraryDatabase.getInstance();
		database.loadDigItems(database.digItemsDB, null);
		database.loadPhysItems(database.physItemsDB, null);
		database.loadCourses(database.coursesDB, null);
		database.loadAccounts();
    	
        setTitle("Library Management System");
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

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

    public static void main(String[] args) throws Exception {
        new MainFrame();
    }
}
