package lms_gui;
import lms_func.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI_Menu extends JFrame implements ActionListener {

	JFrame frame1, frame2;
	private JButton btnLogIn, btnSignUp;
	private static LibraryDatabase database;
	
	public GUI_Menu() throws Exception {
		database = LibraryDatabase.getInstance();
		initalize();
	}
	
	private void initalize() {
		// set parameters for a frame
		
		frame1 = new JFrame();
		//frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame1.setBackground(Color.WHITE);

		// set the label appTitle parameters
		JLabel appTitle = new JLabel("YorkU Library Management");
		appTitle.setForeground(new Color(255, 0, 0, 150));
		appTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
		appTitle.setBounds(200, 23, 800, 56);
		frame1.getContentPane().add(appTitle);

		// set parameters for the menu button that proceeds to the login & sign up screen
		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(this);
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(300, 250, 130, 56);
		frame1.getContentPane().add(btnLogIn);

		// set parameters for the exit button
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(this);
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.setBounds(450, 250, 107, 56);
		frame1.getContentPane().add(btnSignUp);

		// set the bounds of the frame, make it visible and set its layout
		frame1.setBounds(100, 100, 450, 300);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		frame1.setLocationRelativeTo(null);
		frame1.getContentPane().setVisible(true);
		

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			GUI_Menu window = new GUI_Menu();// making window to new menu
			
			window.frame1.setSize(900, 600);
			window.frame1.setVisible(true);// setting the frame as visible
			database.loadDigItems(database.digItemsDB, null);
			database.loadPhysItems(database.physItemsDB, null);
			database.loadCourses(database.coursesDB, null);
			database.loadAccounts();
		} catch (Exception e) {// catching any errors
			e.printStackTrace();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogIn ) {

			frame1.dispose();// get rid of old frame
			GUI_SignUP_Login window;
			try {
				window = new GUI_SignUP_Login();
				
				//System.out.println("creating window works");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		else if(e.getSource() == btnSignUp) {
			try {
				frame2 = new GUI_SignUp();
				frame1.setVisible(false);
				frame2.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
