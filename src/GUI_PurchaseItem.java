import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GUI_PurchaseItem extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static LibraryDatabase database;
	
	static Account acc1 = null;
	Account acc = null;
	
	ArrayList<PhysicalItem> purchasableItems = new ArrayList<>();
	ArrayList<PhysicalItem> userPhysItems = new ArrayList<>();
	
	Vector<String> physicalItems = new Vector<>();
	
	JComboBox<String> listPurchasableItems;
	
	JButton btnBack = new JButton("Back");
	JButton btnPurchase = new JButton("Purchase");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PurchaseItem frame = new GUI_PurchaseItem(acc1);
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
	public GUI_PurchaseItem(Account acc) throws Exception {
		this.acc = acc;
		database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		showPurchasableItems();
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(216, 84, 86));
		panel.setBounds(6, 6, 888, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Purchase Item");
		lblNewLabel.setForeground(new Color(216, 84, 86));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(338, 6, 234, 50);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 78, 888, 488);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Purchasable Items: ");
		lblNewLabel_1.setForeground(new Color(216, 84, 86));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(32, 18, 208, 43);
		panel_1.add(lblNewLabel_1);
		
		listPurchasableItems = new JComboBox<String>(physicalItems);
		listPurchasableItems.setBounds(32, 154, 750, 27);
		panel_1.add(listPurchasableItems);
		
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBack.setBounds(49, 382, 136, 48);
		panel_1.add(btnBack);
		
		btnPurchase.addActionListener(this);
		btnPurchase.setBounds(626, 382, 136, 48);
		panel_1.add(btnPurchase);
		setVisible(true);
	}
	
	public void showPurchasableItems() throws Exception {
		database.loadPurchasableBooks(purchasableItems);
		
		for (PhysicalItem p : purchasableItems) {
			physicalItems.add(p.getName());
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
		
		else if (e.getSource() == btnPurchase) {
			dispose();
			try {
				GUI_Payment p = new GUI_Payment(acc);
				p.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
	}

}
