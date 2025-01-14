import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GUI_ReturnBook extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static LibraryDatabase database;
	private JPanel contentPane;
	
	static Account acc1 = null;
	Account acc;
	
	JButton btnReturn = new JButton("Return");
	JButton btnBack = new JButton("Back");
	JButton btnShowBooks = new JButton("Show Borrowed Items");
	
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	
	ArrayList<PhysicalItem> borrowedBooks = new ArrayList<>();
	ArrayList<PhysicalItem> userPhysItems = new ArrayList<>();
	
	Vector<String> physicalItemStrings = new Vector<>();
	
	JComboBox<String> listBorrowedItems;
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ReturnBook frame = new GUI_ReturnBook(acc1);
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
	public GUI_ReturnBook(Account acc) throws Exception {
		this.acc = acc;
		
		database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 888, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return A Book");
		lblNewLabel.setBounds(327, 6, 232, 37);
		lblNewLabel.setForeground(new Color(216, 84, 86));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		panel.add(lblNewLabel);
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Books You Have Checked Out:");
		lblNewLabel_1.setForeground(new Color(216, 84, 86));
		lblNewLabel_1.setBounds(41, 29, 312, 33);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_1.add(lblNewLabel_1);
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Book1", "Book2", "Book3"}));
//		comboBox.setBounds(59, 197, 736, 27);
//		panel_1.add(comboBox);
		
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnBack.setBounds(124, 369, 154, 70);
		panel_1.add(btnBack);


		btnReturn.addActionListener(this);
		btnReturn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnReturn.setBounds(595, 369, 154, 70);
		panel_1.add(btnReturn);
		
		btnShowBooks.addActionListener(this);
		btnShowBooks.setBounds(350, 33, 197, 29);
		panel_1.add(btnShowBooks);
		

		
		panel_1.setBounds(6, 83, 888, 483);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		setVisible(true);
	}
	
	public void showBorrowedBooks(Account acc) throws Exception {
		String[] emailSplit = new String[2];
		emailSplit = acc.getEmail().split("@");
		database.loadPhysItems(borrowedBooks, emailSplit[0]);

		for(PhysicalItem p: borrowedBooks){
			physicalItemStrings.add(p.getName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			dispose();
			try {
				GUI_Home_VisNonFaculty newFrame = new GUI_Home_VisNonFaculty(acc);
				newFrame.setVisible(true);
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
			try {
				showBorrowedBooks(acc);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			listBorrowedItems = new JComboBox<String>(physicalItemStrings);
			listBorrowedItems.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			listBorrowedItems.setBounds(68, 145, 733, 63);
			panel_1.add(listBorrowedItems);
			setVisible(true);
		}

		else if (e.getSource() == btnReturn) {
			String s = (String) listBorrowedItems.getSelectedItem();
			
			for (int i = 0; i < borrowedBooks.size(); i ++) {
				if (borrowedBooks.get(i).getName().equals(s)) {
					index = i;
				}
			}	
			try {
				borrowedBooks.get(index).returnCopy(acc);
				JOptionPane.showMessageDialog(null,"Thanks for returning " + borrowedBooks.get(index).getName());
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
