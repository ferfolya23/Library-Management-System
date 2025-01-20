package lms_gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lms_func.LibraryDatabase;

public class JListCardLayout extends JFrame implements ActionListener {
	
	CardLayout cardLayout = new CardLayout();
	JPanel mainPanel = new JPanel (cardLayout);

	private JButton btnCustomerMenu,btnExit;
	private static LibraryDatabase database;
	
	JListCardLayout() throws Exception {
		
		//database = LibraryDatabase.getInstance();
		
		add(mainPanel);
		setSize(900, 600);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel GUI_Menu = GUI_Menu();
		JPanel GUI_SignUP_Login = GUI_SignUP_Login();
		
		setVisible(true);
	}
	
	public JPanel GUI_Menu() {
		
		JPanel GUI_Menu = new JPanel();
		JPanel card1 = new JPanel();
		
		// set the label JVKBanking parameters
		JLabel lblJVKBanking = new JLabel("YorkU Library Management");
		lblJVKBanking.setForeground(new Color(255, 0, 0, 150));
		lblJVKBanking.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
		lblJVKBanking.setBounds(200, 23, 800, 56);
		card1.add(lblJVKBanking);
		
		// set parameters for the customer menu button
		btnCustomerMenu = new JButton("Login/Sign Up");
		btnCustomerMenu.addActionListener(this);
		btnCustomerMenu.setBackground(Color.WHITE);
		btnCustomerMenu.setBounds(300, 250, 130, 56);
		card1.add(btnCustomerMenu);

		// set parameters for the exit button
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		btnExit.setBackground(Color.WHITE);
		btnExit.setSize(50, 50);
		card1.add(btnExit);
		
		JButton btn1 = new JButton("Go to card 2.");
		
		card1.add(btn1);
		mainPanel.add(card1, "card1");
		
		btn1.addActionListener((e) -> {
			
			cardLayout.show(mainPanel, "card2");
		});
		
		return GUI_Menu;
	}
	
	public JPanel GUI_SignUP_Login() {
		
		JPanel GUI_SignUP_Login = new JPanel();
		JPanel card2 = new JPanel();
		JButton btn2 = new JButton("Go to card 1.");
		
		card2.add(btn2);
		mainPanel.add(card2, "card2");
		
		btn2.addActionListener((e) -> {
			
			cardLayout.show(mainPanel, "card1");
		});
		
		JPanel contentPane;
		JTextField email, password;
		JButton btnLogin, btnSignup, btnBack;
		JLabel lblUsername,lblPassword,lblEnterYourUsername;
		
		// Add header
		lblEnterYourUsername = new JLabel("Enter Your Username and Password");
		lblEnterYourUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblEnterYourUsername.setForeground(new Color(255, 0, 0, 150));
		lblEnterYourUsername.setBounds(250, 100, 372, 30);
		card2.add(lblEnterYourUsername);

		// Add label for user name
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setForeground(new Color(255, 0, 0, 150));
		lblUsername.setBounds(250, 200, 100, 17);
		card2.add(lblUsername);

		// Add label for password
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPassword.setForeground(new Color(255, 0, 0, 150));
		lblPassword.setBounds(250, 260, 81, 14);
		card2.add(lblPassword);

		// Add text field for user name
		email = new JTextField(45);
		email.setBounds(351, 200, 248, 20);
		card2.add(email);
		email.setColumns(10);

		// add text field for password
		password = new JTextField(45);
		password.setBounds(351, 260, 248, 20);
		card2.add(password);
		password.setColumns(10);

		// Add log in button
		btnLogin = new JButton("Log In");
		btnLogin.setBackground(new Color(216, 191, 216));
		btnLogin.addActionListener(this);	// Add action listener
		btnLogin.setBounds(270, 330, 89, 23);
		card2.add(btnLogin);

		// Add back button
		btnBack = new JButton("Back");
		btnBack.setBackground(new Color(216, 191, 216));
		btnBack.setBounds(490, 330, 89, 23);
		btnBack.addActionListener((e) -> {
			
			cardLayout.show(mainPanel, "card1");
		});	// Add action listener
		card2.add(btnBack);

		// Add sign up button
		btnSignup = new JButton("Sign Up");
		btnSignup.setBackground(new Color(216, 191, 216));
		btnSignup.addActionListener(this);	// Add action listener
		btnSignup.setBounds(380, 330, 89, 23);
		card2.add(btnSignup);

		
		return GUI_SignUP_Login;
	}
	
	public static void main(String[] args) throws Exception {
		JListCardLayout window = new JListCardLayout();
		//database.loadDigItems(database.digItemsDB, null);
		//database.loadPhysItems(database.physItemsDB, null);
		//database.loadAccounts();
		//database.loadAccounts();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCustomerMenu ) {
			
		}
		
		else if(e.getSource() == btnExit) {
			dispose();
		}
	}
}
