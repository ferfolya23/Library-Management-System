import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI_AddDigitalItem extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private static LibraryDatabase database;
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JPanel panel_3 = new JPanel();
	JPanel panel_4 = new JPanel();
	JPanel panel_5_1 = new JPanel();
	
	JLabel lblAddPhysItem = new JLabel("Add Digital Item");
	JLabel lblItemType = new JLabel("Item Type: ");
	JLabel lblName = new JLabel("Item Name: ");
	JLabel lblAuthor = new JLabel("Author:  ");
	JLabel lblEdition = new JLabel("Edition: ");
	JLabel lblPublisherName = new JLabel("Publisher Name: ");
	JLabel lblGenre = new JLabel("Genre ");
	
	private JTextField inName;
	private JTextField inAuthor;
	private JTextField inEdition;
	private JTextField inPubName;
	private JTextField inGenre;
	
	String name;
	String author;
	String edition;
	String publisherName;
	String genre;
	String itemType;
	
	JButton btnAdd = new JButton("Add");
	JButton btnBack = new JButton("Back");
	JButton btnSaveInfo = new JButton("Save Info");
	
	Vector<String> itemNames = new Vector<String>();
	
	JComboBox<String> listItemType;
	
	DigitalItem item;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_AddDigitalItem frame = new GUI_AddDigitalItem();
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
	public GUI_AddDigitalItem() throws Exception {
		database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		itemNames.add("RegBook");
		itemNames.add("Textbook");

		
		panel.setBounds(6, 6, 888, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		lblAddPhysItem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblAddPhysItem.setForeground(new Color(216, 84, 86));
		lblAddPhysItem.setBounds(301, 6, 321, 79);
		panel.add(lblAddPhysItem);
		
		
		panel_1.setBounds(6, 102, 888, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblItemType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblItemType.setBounds(55, 27, 91, 23);
		panel_1.add(lblItemType);
		
		listItemType = new JComboBox<String>(itemNames);
		listItemType.setBounds(158, 27, 189, 27);
		panel_1.add(listItemType);
		
		
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblName.setBounds(444, 27, 91, 23);
		panel_1.add(lblName);
		
		inName = new JTextField();
		inName.setBounds(542, 26, 324, 26);
		panel_1.add(inName);
		inName.setColumns(10);
		
		
		panel_2.setBounds(6, 193, 888, 78);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		lblAuthor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblAuthor.setBounds(55, 28, 64, 23);
		panel_2.add(lblAuthor);
		
		inAuthor = new JTextField();
		inAuthor.setColumns(10);
		inAuthor.setBounds(124, 27, 251, 26);
		panel_2.add(inAuthor);
		
		
		lblEdition.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEdition.setBounds(445, 28, 64, 23);
		panel_2.add(lblEdition);
		
		inEdition = new JTextField();
		inEdition.setColumns(10);
		inEdition.setBounds(508, 27, 357, 26);
		panel_2.add(inEdition);
		
		
		panel_3.setBounds(6, 283, 888, 79);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		
		lblPublisherName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPublisherName.setBounds(49, 25, 121, 23);
		panel_3.add(lblPublisherName);
		
		inPubName = new JTextField();
		inPubName.setColumns(10);
		inPubName.setBounds(171, 24, 251, 26);
		panel_3.add(inPubName);
		
		
		lblGenre.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGenre.setBounds(449, 25, 64, 23);
		panel_3.add(lblGenre);
		
		inGenre = new JTextField();
		inGenre.setColumns(10);
		inGenre.setBounds(510, 24, 251, 26);
		panel_3.add(inGenre);
		
		
		panel_4.setBounds(6, 383, 888, 79);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		btnSaveInfo.setBounds(48, 24, 117, 29);
		panel_4.add(btnSaveInfo);
		
		btnSaveInfo.addActionListener(this);
		
		
		panel_5_1.setBounds(6, 474, 888, 84);
		contentPane.add(panel_5_1);
		panel_5_1.setLayout(null);
		
		btnAdd.addActionListener(this);
		btnAdd.setBounds(50, 24, 117, 29);
		panel_5_1.add(btnAdd);
		
		btnBack.addActionListener(this);
		btnBack.setBounds(635, 24, 117, 29);
		panel_5_1.add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSaveInfo) {
			name = inName.getText();
			author = inAuthor.getText();
			edition = inEdition.getText();
			publisherName = inPubName.getText();
			genre = inGenre.getText();
			itemType = listItemType.getSelectedItem().toString();
		}
		
		else if (e.getSource() == btnAdd) {
			try {
				
				LibraryManager manager = new LibraryManager("mgr_access", "mgr_access");
				item = new DigitalItem(itemType, genre, name, author, edition, publisherName);
				manager.addDigitalItem(item);
				JOptionPane.showMessageDialog(null, "Item has been added!");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnBack) {
			dispose();
			GUI_LibraryManager_Home window = new GUI_LibraryManager_Home();
			window.setVisible(true);
		}
		
	}

}
