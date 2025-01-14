

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

public class GUI_LibraryManager_Home extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JPanel panel_3 = new JPanel();
	JPanel panel_4 = new JPanel();
	
	JLabel lblWelcomeTitle = new JLabel("Welcome Library Manager ");
	
	JButton btnAddPhysItem = new JButton("Add Physical Item");
	JButton btnAddDigItem = new JButton("Add Digital Item");
	JButton btnEnableItem = new JButton("Enable Item");
	JButton btnDisableItem = new JButton("Disable Item");
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_LibraryManager_Home frame = new GUI_LibraryManager_Home();
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
	public GUI_LibraryManager_Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel.setBounds(6, 6, 888, 68);
		contentPane.add(panel);
		
		
		lblWelcomeTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblWelcomeTitle.setForeground(new Color(216, 84, 86));
		panel.add(lblWelcomeTitle);
		
		
		panel_1.setBounds(6, 86, 888, 113);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnAddPhysItem.addActionListener(this);
		btnAddPhysItem.setForeground(new Color(216, 84, 86));
		btnAddPhysItem.setBounds(298, 6, 227, 101);
		panel_1.add(btnAddPhysItem);
		
		
		panel_2.setBounds(6, 211, 888, 113);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnAddDigItem.addActionListener(this);
		btnAddDigItem.setForeground(new Color(216, 84, 86));
		btnAddDigItem.setBounds(300, 6, 227, 107);
		panel_2.add(btnAddDigItem);
		
		
		panel_3.setBounds(6, 336, 888, 113);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		btnEnableItem.addActionListener(this);
		btnEnableItem.setForeground(new Color(216, 84, 86));
		btnEnableItem.setBounds(300, 5, 225, 102);
		panel_3.add(btnEnableItem);
		
		
		panel_4.setBounds(6, 461, 888, 105);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnDisableItem.addActionListener(this);
		btnDisableItem.setForeground(new Color(216, 84, 86));
		btnDisableItem.setBounds(297, 5, 226, 100);
		panel_4.add(btnDisableItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPhysItem) {
			dispose();
			GUI_AddPhysicalItem window;
			
			try {
				
				window = new GUI_AddPhysicalItem();
				window.setVisible(true);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		else if (e.getSource() == btnAddDigItem) {
			dispose();
			GUI_AddDigitalItem window;
			
			try {
				
				window = new GUI_AddDigitalItem();
				window.setVisible(true);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnEnableItem) {
			dispose();
			
			GUI_EnableItem window;
			
			try {
				
				window = new GUI_EnableItem();
				window.setVisible(true);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		else if (e.getSource() == btnDisableItem) {
			dispose();
			
			GUI_DisableItem window; 
			
try {
				
				window = new GUI_DisableItem();
				window.setVisible(true);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
	}

}
