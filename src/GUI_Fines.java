

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class GUI_Fines extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtFine;
    private static LibraryDatabase database;

    ArrayList<PhysicalItem> userPhysItems = new ArrayList<>();

    public JButton btnNewButton = new JButton("Back");
    public JButton btnPayHere = new JButton("Pay Here");


    
    static Account account = null;
    Account acc;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public GUI_Fines(Account acc) throws Exception {
        this.acc = acc;
        database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(121, 102, 195, 93);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtFine = new JTextField();
		txtFine.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtFine.setHorizontalAlignment(SwingConstants.CENTER);
		txtFine.setText("FINE AMOUNT:");
		txtFine.setBounds(121, 56, 195, 36);
		contentPane.add(txtFine);
		txtFine.setColumns(10);
        txtFine.setEditable(false);
        textField.setEditable(false);
        userPhysItems = database.physItemsDB;
		

		btnNewButton.setBounds(10, 213, 102, 40);
		contentPane.add(btnNewButton);
        btnNewButton.addActionListener(this);
		
		btnPayHere.setBounds(324, 213, 102, 40);
		contentPane.add(btnPayHere);
        btnPayHere.addActionListener(this);
	}

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Fines frame = new GUI_Fines(account);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        

        if (e.getSource() == btnNewButton) {
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
