import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class GUI_RentBook extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static LibraryDatabase database;
	private JPanel contentPane;
	
	JButton btnBack = new JButton("Back");
	JButton btnRent = new JButton("Rent");
	JButton btnShowBooks = new JButton("Show Rentable Books");
	
	JPanel panel_1 = new JPanel();
	int index;
	
	
	static Account acc1 = null;
	Account acc;
	
	ArrayList<PhysicalItem> rentableBooks = new ArrayList<>();
	ArrayList<PhysicalItem> userPhysItems = new ArrayList<>();
	
	Vector<String> physicalItemStrings = new Vector<>();
	
	JComboBox<String> listRentableItems;
	JPanel panel_2 = new JPanel();
	JLabel lblNewLabel_2 = new JLabel("You have more than 3 books overdue, you have lost renting privileges.");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_RentBook frame = new GUI_RentBook(acc1);
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
	public GUI_RentBook(Account acc) throws Exception {
		
		this.acc = acc;
		
		database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 10, 888, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rent A Book");
		lblNewLabel.setBounds(350, 5, 203, 37);
		lblNewLabel.setForeground(new Color(216, 84, 86));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		panel.add(lblNewLabel);
		
		
		panel_1.setBounds(6, 76, 888, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Rentable Books:");
		lblNewLabel_1.setForeground(new Color(216, 84, 86));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(21, 22, 178, 25);
		panel_1.add(lblNewLabel_1);
		
		
		
		
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnBack.setBounds(124, 369, 154, 70);
		panel_1.add(btnBack);
		
		
		btnRent.addActionListener(this);
		btnRent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnRent.setBounds(595, 369, 154, 70);
		panel_1.add(btnRent);
		
		btnShowBooks.addActionListener(this);
		btnShowBooks.setBounds(239, 24, 197, 29);
		panel_1.add(btnShowBooks);
		panel_2.setBounds(109, 129, 557, 200);
		setVisible(true);
		
		
		
	}
	
	public void showRentableBooks() throws Exception {
		database.loadRentableBooks(rentableBooks);
		
		for (PhysicalItem p : rentableBooks) {
			physicalItemStrings.add(p.getName());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			database = LibraryDatabase.getInstance();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (e.getSource() == btnBack) {
			dispose();
			try {
				GUI_Home_VisNonFaculty window = new GUI_Home_VisNonFaculty(acc);
				window.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		else if (e.getSource() == btnShowBooks) {
			String[] splitAcc = new String[2];
			splitAcc = acc.getEmail().split("@");
			try {
				database.loadPhysItems(userPhysItems, splitAcc[0]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int count = 0;
			Date currDate = new Date();
			String due = currDate.toString();
			System.out.println(due);
			for (PhysicalItem p : userPhysItems) {
				try {
					if (p.warningString(acc).equals(String.format("The book: %s OVERDUE PLEASE RETURN IT", p.name))) {
						count++;
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if (count < 3) {
				
				try {
					showRentableBooks();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				listRentableItems = new JComboBox<String>(physicalItemStrings);
				listRentableItems.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				listRentableItems.setBounds(68, 145, 733, 63);
				panel_1.add(listRentableItems);
				setVisible(true);
			}
			
			else {
				panel_1.add(panel_2);
				panel_2.setLayout(null);
				lblNewLabel_2.setForeground(new Color(216, 84, 86));
				lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(32, 6, 498, 168);
				
				panel_2.add(lblNewLabel_2);
				setVisible(true);
			}
			
		}
		
		else if (e.getSource() == btnRent) {
			String s = (String) listRentableItems.getSelectedItem();
			
			for (int i = 0; i < rentableBooks.size(); i ++) {
				if (rentableBooks.get(i).getName().equals(s)) {
					index = i;
				}
			}
			
			try {
				rentableBooks.get(index).rentCopy(acc);
				JOptionPane.showMessageDialog(null, rentableBooks.get(index).getName() + " has been rented");
				dispose();
				GUI_Home_VisNonFaculty newFrame = new GUI_Home_VisNonFaculty(acc);
				newFrame.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
