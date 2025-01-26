package gui_new;

import javax.swing.*;

import lms_func.Account;
import lms_func.LibraryDatabase;
import lms_func.LibraryHomePage;

import java.awt.*;
import java.util.Vector;

public class SignupPanel extends JPanel {
	
	LibraryDatabase database;
	MainFrame mainFrame;
	private JTextField email;
	private JPasswordField password;
	final Vector<String> accTypeNames;
	final JComboBox<String> accTypeList;
	
    public SignupPanel(MainFrame parentFrame) throws Exception {
        setLayout(null);
        this.database = LibraryDatabase.getInstance();;
        
        JLabel lblMainTitle = new JLabel("Sign Up", SwingConstants.CENTER);
        lblMainTitle.setForeground(new Color(255, 0, 0, 180));
        lblMainTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
        lblMainTitle.setBounds(200, 50, 500, 50);
        add(lblMainTitle);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(250, 150, 100, 30);
        add(lblEmail);

        email = new JTextField();
        email.setBounds(350, 150, 250, 30);
        add(email);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(250, 200, 100, 30);
        add(lblPassword);

        password = new JPasswordField();
        password.setBounds(350, 200, 250, 30);
        add(password);
        
        accTypeNames = new Vector<String>();
		accTypeNames.add("Visitor");
		accTypeNames.add("Student");
		accTypeNames.add("Faculty");
		accTypeNames.add("NonFaculty");
        accTypeList = new JComboBox<String>(accTypeNames);
        accTypeList.setBounds(350, 250, 250, 30);
        //petList.addActionListener(this);
        add(accTypeList);

        JButton btnLogin = new JButton("Sign Up");
        btnLogin.setBounds(350, 300, 100, 40);
        btnLogin.addActionListener(e -> {
			try {
				signUp(accTypeList.getSelectedItem().toString());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        add(btnLogin);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(470, 300, 100, 40);
        btnBack.addActionListener(e -> parentFrame.switchPanel("Menu"));
        add(btnBack);
    }

	private void signUp(String accType) throws Exception {
		System.out.println("Action listener triggered!");
		
		boolean isValid = false;
		boolean isStrongPassword = false;
		boolean isValidAccType = false;
		String txtEmail = email.getText();
		String txtPassword = password.getText();
		
		
    	LibraryHomePage newAccount = new LibraryHomePage();
    	boolean verifiedByManager = newAccount.additionalValidation(txtEmail);
    	String emailMessage = "Please enter a valid email.";
    	String passwordMessage = "Password is not strong enough. Please make a new password with the following requirements: "
    							+ "\n" + "- At least 8 characters long" + "\n" + "- At least one uppercase letter" 
    							+ "\n" + "- At least one lowercase letter" + "\n" + "- At least one digit" 
    							+ "\n" + "- At least one symbol";
    	String validationMessage = "Your account could not be validated. Please try signing up as a Visitor instead.";
    	String messageAccountExists = "You already have an account. Please try logging in instead!";
    	String regSuccess = "Registration successful! Please login.";
        
    	//checks email is valid
        if (newAccount.isValidEmail(txtEmail)) {
        	isValid = true;
        }
		
        else {
        	JOptionPane.showMessageDialog(null, emailMessage);
        }         
        
        //checks pass is strong
        if (newAccount.isStrongPassword(txtPassword)) {
        	isStrongPassword = true;
        }
        
        else {
        	JOptionPane.showMessageDialog(null, passwordMessage);
        }
        
        //check validated by manager (if not visitor)
		if ((verifiedByManager == false) && !(accType.equals("Visitor"))) {
        	JOptionPane.showMessageDialog(null, validationMessage);
            
        }
        
        else if ((verifiedByManager == false) && (accType.equals("Visitor"))) {
        	isValidAccType = true;
        }
        
        else if (verifiedByManager == true) {
        	isValidAccType = true;
        }
        
        Account accountExists = database.iterateDB(txtEmail, txtPassword);
        
        if (accountExists != null) {
        	JOptionPane.showMessageDialog(null, messageAccountExists);
        }
        
        else {
        	// TODO - Add some validation method prior to account creation if not Visitor.
        	if ((isValid == true) && (isStrongPassword == true) && (isValidAccType == true)) {
        		database.accountGenerator(txtEmail, txtPassword, accType, 0, 0, false);
        		database.updateAccounts();
        		JOptionPane.showMessageDialog(null, regSuccess);
        		}
        	else {
        		JOptionPane.showMessageDialog(null, "Error in creating account. Try Again!");
        		}
	    		
	        }
       }
}