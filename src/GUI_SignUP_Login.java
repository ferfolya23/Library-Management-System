import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI_SignUP_Login extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private static LibraryDatabase database;
	private JPanel contentPane;
	private JTextField email, password;
	private JButton btnLogin, btnSignup, btnBack;
	private JLabel lblUsername,lblPassword,lblEnterYourUsername;
	
	ArrayList<PhysicalItem> userPhysicalItems = new ArrayList<>();

	
	public GUI_SignUP_Login() throws Exception {
		super("YorkU Library Management");
		database = LibraryDatabase.getInstance();
		
		
		getContentPane().setLayout(null);

		// Set up panel
		contentPane = new JPanel();
		//contentPane.setBackground(Color.GREEN);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Add header
		lblEnterYourUsername = new JLabel("Enter Your Username and Password");
		lblEnterYourUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblEnterYourUsername.setForeground(new Color(255, 0, 0, 150));
		lblEnterYourUsername.setBounds(250, 100, 372, 30);
		contentPane.add(lblEnterYourUsername);

		// Add label for user name
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setForeground(new Color(255, 0, 0, 150));
		lblUsername.setBounds(250, 200, 100, 17);
		contentPane.add(lblUsername);

		// Add label for password
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPassword.setForeground(new Color(255, 0, 0, 150));
		lblPassword.setBounds(250, 260, 81, 14);
		contentPane.add(lblPassword);

		// Add text field for user name
		email = new JTextField(45);
		email.setBounds(351, 200, 248, 20);
		contentPane.add(email);
		email.setColumns(10);

		// add text field for password
		password = new JTextField(45);
		password.setBounds(351, 260, 248, 20);
		contentPane.add(password);
		password.setColumns(10);

		// Add log in button
		btnLogin = new JButton("Log In");
		btnLogin.setBackground(new Color(216, 191, 216));
		btnLogin.addActionListener(this);	// Add action listener
		btnLogin.setBounds(270, 330, 89, 23);
		contentPane.add(btnLogin);

		// Add back button
		btnBack = new JButton("Back");
		btnBack.setBackground(new Color(216, 191, 216));
		btnBack.setBounds(490, 330, 89, 23);
		btnBack.addActionListener(this);	// Add action listener
		contentPane.add(btnBack);

		// Add sign up button
		btnSignup = new JButton("Sign Up");
		btnSignup.setBackground(new Color(216, 191, 216));
		btnSignup.addActionListener(this);	// Add action listener
		btnSignup.setBounds(380, 330, 89, 23);
		contentPane.add(btnSignup);

		// Set size and location of frame
		setSize(900, 600);  
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws Exception {
		// All loaders for main DB assume that CSV files already exist
		// This can be changed later as needed using a branch of the doesListExist method
		GUI_SignUP_Login window = new GUI_SignUP_Login();
		window.setSize(900, 600);
		database.loadPhysItems(database.physItemsDB, null);
		database.loadDigItems(database.digItemsDB, null);
		database.loadCourses(database.coursesDB, null);
		database.loadAccounts();
		database.purgeFinishedCourses();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			setVisible(false);
			GUI_Menu window;
			try {
				
				window = new GUI_Menu();
				window.setVisible(true);
				window.setSize(900, 600);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
		}
		
		else if (e.getSource() == btnLogin) {
			Account registeredAccount = null;
	    	while (true) {  
	            try {
	            	// iterating through database to find an account with specific email and password passed in. 
	            	
					registeredAccount = database.iterateDB(email.getText(), password.getText());

				} 
	            catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            if (email.getText().equals("mgr_access") && password.getText().equals("mgr_pass")) {
            		setVisible(false);
            		GUI_LibraryManager_Home window = new GUI_LibraryManager_Home();
            		window.setVisible(true);
            		
            		break;
            	}
	            
	            // if it finds it, break the iteration. 
	            if (registeredAccount != null) {
	            	if (registeredAccount.getAccType().equals("Student") || registeredAccount.getAccType().equals("NonFaculty") || registeredAccount.getAccType().equals("Faculty") || registeredAccount.getAccType().equals("Visitor")) {
	            		setVisible(false);
		            	try {
		            		
							GUI_Home_VisNonFaculty window  = new GUI_Home_VisNonFaculty(registeredAccount);
							window.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 String[] splitAcc = new String[2];
						splitAcc = registeredAccount.getEmail().split("@");
						
						try {
							database.loadPhysItems(userPhysicalItems, splitAcc[0]);
						} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
						Date currDate = new Date();
						String due = currDate.toString();
				
						System.out.println(due);
						for (PhysicalItem p : userPhysicalItems) {
							long timeDiff = p.getDueDate().getTime() - currDate.getTime(); // gets the time in miliseconds
							long timeDiffHrs = timeDiff/(60*60*1000); // converts to hours

							try {
								System.out.println(p.warningString(registeredAccount));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try {
								if (p.warningString(registeredAccount).equals(String.format("The book: %s is now lost.", p.name))){
									JOptionPane.showMessageDialog(null,"The book: " + p.name + " IS NOW LOST");
								}
								else if (p.warningString(registeredAccount).equals(String.format("The book: %s is due in %d hours", p.name,timeDiffHrs ))){
									JOptionPane.showMessageDialog(null,"The book: " + p.name + " IS DUE IN " + timeDiffHrs + " HOURS :(");
								}

								else if (p.warningString(registeredAccount).equals(String.format("The book: %s OVERDUE PLEASE RETURN IT", p.name))) {
									JOptionPane.showMessageDialog(null,"The book: " + p.name + " OVERDUE PLEASE RETURN IT");
									
								}
								
			
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

		            	break;
	            	}
	            	
	            	
	            
	            }
	    	}
		}
		
		else if (e.getSource() == btnSignup) {
			
    		setVisible(false);
    		GUI_SignUp frame;

    		try {
	
    			frame = new GUI_SignUp();
    			frame.setVisible(true);
	
    		} catch (Exception e1) {
	
    			e1.printStackTrace();
    		}

    	}
    	
    	else {
    		
    	}
	}
}