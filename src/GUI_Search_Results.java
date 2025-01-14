import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class GUI_Search_Results extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static LibraryDatabase database;
	
	
	
	JPanel listPanel = new JPanel();
	JPanel panelTitle = new JPanel();
	
	JButton btnBack = new JButton("Back");
	
	public JLabel lblSimilarItemsTitle = new JLabel("Similar Items");
	public JLabel lblNoSimilarity = new JLabel("There are no similar items to your search");
	
	static Account account;
	Account acc;
	
	static ArrayList<Item> listItem = null;
	ArrayList<Item> itemList;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Search_Results frame = new GUI_Search_Results(listItem, account);
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
	public GUI_Search_Results(ArrayList<Item> itemList, Account acc) throws Exception {
		this.acc = acc;
		this.itemList = itemList;
		database = LibraryDatabase.getInstance();
		if (itemList.isEmpty()) {
			
		}
		lblSimilarItemsTitle.setForeground(new Color(255, 0, 0, 150));
		lblSimilarItemsTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		listPanel.setBounds(10, 50, 1000, 500);
		panelTitle.setBounds(0, 0, 50, 500);
		btnBack.setBounds(600, 500, 89, 23);
		panelTitle.add(lblSimilarItemsTitle);
		
		add(btnBack);
		btnBack.addActionListener(this);
		showSearchResults(itemList);
		add(panelTitle);
		
		setSize(1000, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		

		
	}
	
	public void showSearchResults(ArrayList<Item> itemList) {
		int lengthOfItemList = itemList.size();
		
		JPanel[] panelItem = new JPanel[lengthOfItemList];
		JLabel[] lblItemName = new JLabel[lengthOfItemList];
		JLabel[] lblItemAuthor = new JLabel[lengthOfItemList];
		JLabel[] lblItemEdition = new JLabel[lengthOfItemList];
		
		for (int i = 0; i < lengthOfItemList; i++) {
			panelItem[i] = new JPanel();

		 }
		
		for (int i = 0; i < lengthOfItemList; i++) {
			lblItemName[i] = new JLabel(itemList.get(i).getName());
			lblItemName[i].setFont(new Font("Verdana", Font.PLAIN, 15));
			lblItemAuthor[i] = new JLabel(itemList.get(i).getAuthor());
			lblItemAuthor[i].setFont(new Font("Verdana", Font.PLAIN, 15));
			lblItemEdition[i] = new JLabel(itemList.get(i).getEdition());
			lblItemEdition[i].setFont(new Font("Verdana", Font.PLAIN, 15));
		}
		
		for (int i = 0; i < lengthOfItemList; i++) {
			panelItem[i].add(lblItemName[i]);
			panelItem[i].add(lblItemAuthor[i]);
			panelItem[i].add(lblItemEdition[i]);
			 
		 }
		
		for (int i = 0; i < lengthOfItemList; i ++) {
			 listPanel.add(panelItem[i]);
		 }
		 
		
		 getContentPane().add(listPanel);
		 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			dispose();
			try {
				GUI_Search window = new GUI_Search(acc);
				window.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}

}
