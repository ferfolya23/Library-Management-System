

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class GUI_Payment extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtFine;
    private static LibraryDatabase database;

    ArrayList<PhysicalItem> userPhysItems = new ArrayList<>();

	Vector<String> pTypeName = new Vector<>();
	JComboBox<String> paymentTypes;

    public JButton btnNewButton = new JButton("Back");
    public JButton btnPayHere = new JButton("Pay Here");


    
    static Account account = null;
    Account acc;
	int index =0;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public GUI_Payment(Account acc) throws Exception {
        this.acc = acc;
        database = LibraryDatabase.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(30, 43, 371, 93);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
        textField.setEditable(false);
        textField.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		textField.setHorizontalAlignment(SwingConstants.CENTER);

		userPhysItems = database.physItemsDB;
		for(PhysicalItem p : userPhysItems){
			textField.setText(p.getName() + " $" +  p.getPrice());
		}

		pTypeName.add("Debit");
		pTypeName.add("Credit");
		pTypeName.add("Mobile Wallet");
		paymentTypes = new JComboBox<String>(pTypeName);
		paymentTypes.setBounds(75,143,281,21);
		paymentTypes.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		contentPane.add(paymentTypes);
		setVisible(true);

		
		

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
					GUI_Payment frame = new GUI_Payment(account);
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
		else if(e.getSource() == btnPayHere){
			String s = (String) paymentTypes.getSelectedItem();

			for (int i = 0; i < pTypeName.size(); i ++) {
				if (pTypeName.get(i).equals(s)) {
					index = i;
				}
			}

			try {
				dispose();
				JOptionPane.showMessageDialog(null,  "Purchased using " + pTypeName.get(index));
				GUI_Home_VisNonFaculty newFrame = new GUI_Home_VisNonFaculty(acc);
				newFrame.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    }
}
