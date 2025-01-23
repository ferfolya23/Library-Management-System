package gui_new;

import javax.swing.*;

import lms_func.Account;
import lms_func.LibraryDatabase;
import lms_func.PhysicalItem;
import lms_gui.GUI_Home_VisNonFaculty;
import lms_gui.GUI_LibraryManager_Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class LoginPanel extends JPanel {
	
	LibraryDatabase database;
	MainFrame mainFrame;
	private JTextField email;
	private JPasswordField password;
	
    public LoginPanel(MainFrame parentFrame) throws Exception {
        setLayout(null);
        this.database = LibraryDatabase.getInstance();;
        mainFrame = parentFrame;
        
        JLabel lblMainTitle = new JLabel("Log In", SwingConstants.CENTER);
        lblMainTitle.setForeground(new Color(255, 0, 0, 180));
        lblMainTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
        lblMainTitle.setBounds(200, 50, 500, 50);
        add(lblMainTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(250, 150, 100, 30);
        add(lblUsername);

        email = new JTextField();
        email.setBounds(350, 150, 250, 30);
        add(email);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(250, 200, 100, 30);
        add(lblPassword);

        password = new JPasswordField();
        password.setBounds(350, 200, 250, 30);
        add(password);

        JButton btnLogin = new JButton("Log In");
        btnLogin.setBounds(350, 300, 100, 40);
        btnLogin.addActionListener(e -> login());
        add(btnLogin);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(470, 300, 100, 40);
        btnBack.addActionListener(e -> mainFrame.switchPanel("Menu"));
        add(btnBack);
    }
    
	
	private void login() {
		System.out.println("Action listener triggered!");
		Account registeredAccount = null;
		//ArrayList<PhysicalItem> userPhysicalItems = new ArrayList<>();
		
    	try {
			registeredAccount = database.iterateDB(email.getText(), password.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if (registeredAccount != null) {
    		System.out.println("Email: " + registeredAccount.getEmail());
    		System.out.println("PW: " + registeredAccount.getPass());
    		System.out.println("AccType: " + registeredAccount.getAccType());
    		
    		String accEmail = registeredAccount.getEmail();
    		String accPass = registeredAccount.getPass();
    		String accType = registeredAccount.getAccType();
    		
        	JOptionPane.showMessageDialog(null, "Login successful!");
        	
        	if (accType == "Student") {
        		//mainFrame.switchPanel("Student");
        	}
        	
        	else if (accType == "NonFaculty") {
        		//mainFrame.switchPanel("NonFaculty");
        	}
        	
        	else if (accType == "Faculty") {
        		//mainFrame.switchPanel("Faculty");
        	}
        	
        	else {
        		//mainFrame.switchPanel("Visitor");
        	}
    	}
        
        else {
        	JOptionPane.showMessageDialog(null, "Incorrect email or password. Please try again!");
        }
	}
}
