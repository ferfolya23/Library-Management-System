import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class GUI_SignUp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private static LibraryDatabase database;
	
	JButton btnSignUp = new JButton("Sign Up");
	JButton btnBack = new JButton("Back");
	JButton btnSaveInfo = new JButton("Save Info");
	
	JTextField inPassword = new JTextField();
	JTextField inEmail = new JTextField();
	
	String pass;
	String email;
	
	boolean isValid = false;
	boolean isStrongPassword = false;
	boolean isValidAccType = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_SignUp frame = new GUI_SignUp();
					frame.setSize(900, 600);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    Vector<String> accTypeNames = new Vector<String>();
    JComboBox<String> accTypeList = new JComboBox<String>();

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public GUI_SignUp() throws Exception {
		System.out.println("sign up clicked");
		database = LibraryDatabase.getInstance();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		accTypeNames.add("Student");
		accTypeNames.add("Visitor");
		accTypeNames.add("Faculty");
		accTypeNames.add("NonFaculty");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 888, 64);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Sign Up ");
		lblNewLabel.setForeground(new Color(234, 77, 76));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(149, 189, 61, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password: ");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(149, 249, 102, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Account Type:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(149, 324, 154, 36);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Enter the following information:");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(16, 82, 397, 36);
		contentPane.add(lblNewLabel_4);
		
		
		
		inEmail = new JTextField();
		inEmail.setBounds(355, 191, 372, 24);
		contentPane.add(inEmail);
		inEmail.setColumns(10);
		
		
		inPassword.setBounds(354, 251, 373, 26);
		contentPane.add(inPassword);
		inPassword.setColumns(10);
		
		accTypeList =  new JComboBox<String>(accTypeNames);
		accTypeList.setBounds(355, 322, 269, 48);
		contentPane.add(accTypeList);
		
		btnSaveInfo.addActionListener(this);
		btnSaveInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnSaveInfo.setBounds(130, 370, 100, 30);
		contentPane.add(btnSaveInfo);
		
		btnSignUp.addActionListener(this);
		btnSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnSignUp.setBounds(30, 449, 161, 64);
		contentPane.add(btnSignUp);
		
		
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnBack.setBounds(701, 450, 154, 62);
		contentPane.add(btnBack);
		
		setSize(900, 600);  
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void register(String email, String password, String accType) throws Exception{
    	LibraryHomePage newAccount = new LibraryHomePage();
    	GUI_SignUp window = new GUI_SignUp();
    	String emailMessage = "Please enter a valid email.";
    	String passwordMessage = "Password is not strong enough. Please make a new password with the following requirements: "
    							+ "\n" + "- At least 8 characters long" + "\n" + "- At least one uppercase letter" 
    							+ "\n" + "- At least one lowercase letter" + "\n" + "- At least one digit" 
    							+ "\n" + "- At least one symbol";
    	String validationMessage = "Your account could not be validated. Please try signing up as a Visitor instead.";
    	String messageAccountExists = "You already have an account. Please try logging in instead!";
    	String regSuccess = "Registration successful! Please login.";
            
        if (newAccount.isValidEmail(email)) {
        	isValid = true;
        }
            
        else {
        	JOptionPane.showMessageDialog(null, emailMessage);
        }         
            
        if (newAccount.isStrongPassword(password)) {
        	isStrongPassword = true;
        }
        
        else {
        	JOptionPane.showMessageDialog(null, passwordMessage);
        }
                                                       
        boolean verifiedByManager = newAccount.additionalValidation(email);
        
        if ((verifiedByManager == false) && !(accType.equals("Visitor"))) {
        	JOptionPane.showMessageDialog(null, validationMessage);
        	setVisible(false);
            window.setVisible(true);
            
        }
        
        else if ((verifiedByManager == false) && (accType.equals("Visitor"))) {
        	isValidAccType = true;
        }
        
        else if (verifiedByManager == true) {
        	isValidAccType = true;
        }
        
        Account accountExists = database.iterateDB(email, password);
        
        if (accountExists != null) {
        	JOptionPane.showMessageDialog(null, messageAccountExists);
        }
//        boolean isValid = false;
//    	boolean isStrongPassword = false;
//    	boolean isValidAccType = false;
        else {
        	
            // TODO - Add some validation method prior to account creation if not Visitor.
        	if ((isValid == true) && (isStrongPassword == true) && (isValidAccType == true)) {
        		database.accountGenerator(email, password, accType, 0, 0, false);
        		database.updateAccounts();
        		dispose();
        		GUI_SignUP_Login frame = new GUI_SignUP_Login();
        		frame.setVisible(true);
        		JOptionPane.showMessageDialog(null, regSuccess);
        	}
        	
        	else {
        		JOptionPane.showMessageDialog(null, "Error in creating account. Try Again");
        	}
    		
        }
   
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSaveInfo) {
			email = inEmail.getText();
			pass = inPassword.getText();
		}
		else if (e.getSource() == btnSignUp) {
			try {
				
				
				System.out.println("email: " + email);
				System.out.println("password: " + pass);
				System.out.println("acc type: " + accTypeList.getSelectedItem().toString());
				
				register(email, pass, accTypeList.getSelectedItem().toString());
				
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnBack) {
			setVisible(false);
			try {
			
				GUI_SignUP_Login frame1 = new GUI_SignUP_Login();
				frame1.setVisible(true);
				frame1.setSize(900, 600);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
}