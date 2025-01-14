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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI_OpenBook extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Account acc;
	static Account acc1;
	
	JComboBox olbList;
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	
	JLabel lblTitle = new JLabel("Read An Online Book ");
	JLabel lblChooseBook = new JLabel("Choose the online book you would like to read below: ");
	
	JButton btnBack = new JButton("Back");
	JButton btnOpen = new JButton("Open");
	private final JButton btnOpen_1 = new JButton("Open");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_OpenBook frame = new GUI_OpenBook(acc1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_OpenBook(Account acc) {
		this.acc = acc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel.setBounds(6, 6, 888, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		lblTitle.setForeground(new Color(216, 84, 86));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblTitle.setBounds(242, 6, 461, 47);
		panel.add(lblTitle);
		
		
		panel_1.setBounds(6, 76, 888, 490);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblChooseBook.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblChooseBook.setBounds(28, 30, 631, 45);
		panel_1.add(lblChooseBook);
		
		
		Vector<String> newsLetterNames = new Vector<>();
		newsLetterNames.add("Charlotte's Web");
		newsLetterNames.add("The Hitchhiker's Guide to the Galaxy");
		newsLetterNames.add("Pride & Prejudice");
		newsLetterNames.sort(null);
		
		olbList = new JComboBox<String>(newsLetterNames);
		
		olbList.setBounds(67, 206, 750, 27);
		
		panel_1.add(olbList);
		
		btnBack.addActionListener(this);
		btnBack.setBounds(67, 374, 128, 45);
		panel_1.add(btnBack);
		btnOpen_1.setBounds(693, 374, 128, 45);
		btnOpen_1.addActionListener(this);
		panel_1.add(btnOpen_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOpen_1) {
			
			if (olbList.getSelectedItem().toString().equals("Charlotte's Web")) {
				dispose();
				GUI_CharlottesWeb window = new GUI_CharlottesWeb(acc);
				window.setVisible(true);
			}
			
			else if (olbList.getSelectedItem().toString().equals("The Hitchhiker's Guide to the Galaxy")) {
				dispose();
				GUI_HitchHikers window = new GUI_HitchHikers(acc);
				window.setVisible(true);
			}
			
			else if (olbList.getSelectedItem().toString().equals("Pride & Prejudice")) {
				dispose();
				GUI_PridePrejudice window = new GUI_PridePrejudice(acc);
				window.setVisible(true);
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
