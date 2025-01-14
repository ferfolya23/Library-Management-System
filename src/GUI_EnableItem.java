import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI_EnableItem extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static LibraryDatabase database;
	private static LibraryManager manager;
	
	PhysicalItem item;
	
	JButton btnBack = new JButton("Back");
	JButton btnEnable = new JButton("Enable");
	JButton btnShowEnable = new JButton("Show");
	
	JPanel panel_1 = new JPanel();
	int index;
	
	
	static Account acc1 = null;
	Account acc;
	
	ArrayList<PhysicalItem> enableItems = new ArrayList<>();
	ArrayList<PhysicalItem> userPhysItems = new ArrayList<>();
	
	Vector<String> physicalItemStrings = new Vector<>();
	
	JComboBox<String> listEnableItems;
	JPanel panel_2 = new JPanel();
	JLabel lblNewLabel_2 = new JLabel("There are no items to be enabled.");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_EnableItem frame = new GUI_EnableItem();
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
	public GUI_EnableItem() throws Exception {
		database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		showDisabledItems();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 10, 888, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEnableItem = new JLabel("Enable Item");
		lblEnableItem.setBounds(350, 5, 203, 37);
		lblEnableItem.setForeground(new Color(216, 84, 86));
		lblEnableItem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		panel.add(lblEnableItem);
		
		panel_1.setBounds(6, 76, 888, 474);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEnable = new JLabel("Items which can be enabled: ");
		lblEnable.setForeground(new Color(216, 84, 86));
		lblEnable.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblEnable.setBounds(21, 22, 310, 25);
		panel_1.add(lblEnable);
		
		
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnBack.setBounds(124, 369, 154, 70);
		panel_1.add(btnBack);
		
		
		btnEnable.addActionListener(this);
		btnEnable.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEnable.setBounds(595, 369, 154, 70);
		panel_1.add(btnEnable);
		
		btnShowEnable.addActionListener(this);
		btnShowEnable.setBounds(343, 24, 197, 29);
		panel_1.add(btnShowEnable);
		panel_2.setBounds(109, 129, 557, 200);
		setVisible(true);
	}
	
	public void showDisabledItems() throws Exception {
		database.loadDisabledItems(enableItems);
		
		for (PhysicalItem p : enableItems) {
			physicalItemStrings.add(p.getName());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			manager = new LibraryManager("mgr_access", "mgr_pass");
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		if (e.getSource() == btnShowEnable) {
			
			if (!enableItems.isEmpty()) {
				
				listEnableItems = new JComboBox<String>(physicalItemStrings);
				listEnableItems.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				listEnableItems.setBounds(68, 145, 733, 63);
				panel_1.add(listEnableItems);
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
		
		else if (e.getSource() == btnEnable) {
			String s = (String) listEnableItems.getSelectedItem();
			
			for (int i = 0; i < enableItems.size(); i++) {
				if (enableItems.get(i).getName().equals(s)) {
					item = enableItems.get(i);
					break;
				}
			}
			
			try {
				manager.enablePhysItem(item);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Item has been enabled");
		}
		
		else if (e.getSource() == btnBack) {
			
			dispose();
			
			GUI_LibraryManager_Home window = new GUI_LibraryManager_Home();
			
			window.setVisible(true);
		}
		
		
	}

}
