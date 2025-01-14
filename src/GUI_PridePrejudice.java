import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI_PridePrejudice extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static Account account;
	Account acc;
	
	JButton btnBack = new JButton("Back");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PridePrejudice frame = new GUI_PridePrejudice(account);
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
	public GUI_PridePrejudice(Account acc) {
		this.acc = acc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 888, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pride & Prejudice");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel.setBounds(288, 137, 413, 273);
		panel.add(lblNewLabel);
		
		
		btnBack.setBounds(673, 477, 117, 29);
		btnBack.addActionListener(this);
		panel.add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			dispose();
			GUI_OpenBook window = new GUI_OpenBook(acc);
			window.setVisible(true);
		}
		
	}

}
