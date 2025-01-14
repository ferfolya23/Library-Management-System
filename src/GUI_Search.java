import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI_Search extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static LibraryDatabase database;
	
	JLabel lblSearch;
	JLabel lblBookName;
	
	JTextField inSearch;
	
	JButton btnSearch; 
	JButton btnBack;
	
	static Account account;
	Account acc;
	
	ArrayList<Item> itemList;
	ArrayList<String> genreList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Search frame = new GUI_Search(account);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public GUI_Search(Account acc) throws Exception {
		super("Search Books");
		database = LibraryDatabase.getInstance();
		this.acc = acc;
		
		getContentPane().setLayout(null);

		// Set up panel
		contentPane = new JPanel();
		//contentPane.setBackground(Color.GREEN);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Add header
		lblSearch = new JLabel("Search For a Book Below: ");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblSearch.setForeground(new Color(255, 0, 0, 150));
		lblSearch.setBounds(250, 100, 372, 30);
		contentPane.add(lblSearch);

		// Add label for user name
		lblBookName = new JLabel("Name of Book: ");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblBookName.setForeground(new Color(255, 0, 0, 150));
		lblBookName.setBounds(234, 201, 113, 17);
		contentPane.add(lblBookName);

		
		// Add text field for user name
		inSearch = new JTextField(100);
		inSearch.setBounds(351, 200, 248, 20);
		contentPane.add(inSearch);
		inSearch.setColumns(10);

		

		// Add log in button
		btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(216, 191, 216));
		btnSearch.addActionListener(this);	// Add action listener
		btnSearch.setBounds(270, 330, 89, 23);
		contentPane.add(btnSearch);

		// Add back button
		btnBack = new JButton("Back");
		btnBack.setBackground(new Color(216, 191, 216));
		btnBack.setBounds(490, 330, 89, 23);
		btnBack.addActionListener(this);	// Add action listener
		contentPane.add(btnBack);

		// Set size and location of frame
		setSize(900, 600);  
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			
			try {
				
				itemList = database.getRecommendations(inSearch.getText());
				setVisible(false);
				GUI_Search_Results window = new GUI_Search_Results(itemList, acc);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			
			
			
			
		}
		
		else if (e.getSource() == btnBack) {
			dispose();
			try {
				GUI_Home_VisNonFaculty window = new GUI_Home_VisNonFaculty(acc);
				window.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
