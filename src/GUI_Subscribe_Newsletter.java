import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GUI_Subscribe_Newsletter extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Account acc;
	static Account acc1;
	
	JComboBox newsList;
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	
	JLabel lblNewLabel = new JLabel("Subscribe to a Newsletter");
	JLabel lblNewLabel_1 = new JLabel("Select a newsletter to subscribe to below: ");
	
	JButton btnBack = new JButton("Back");
	JButton btnSubscribe = new JButton("Subscribe");
	JButton btnOpen = new JButton("Open");
	JButton btnCancel = new JButton("Cancel Sub");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Subscribe_Newsletter frame = new GUI_Subscribe_Newsletter(acc1);
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
	public GUI_Subscribe_Newsletter(Account account) {
		this.acc = account;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel.setBounds(6, 6, 888, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		lblNewLabel.setForeground(new Color(216, 84, 86));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(242, 6, 461, 47);
		panel.add(lblNewLabel);
		
		
		panel_1.setBounds(6, 76, 888, 490);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(28, 30, 421, 45);
		panel_1.add(lblNewLabel_1);
		
		
		Vector<String> newsLetterNames = new Vector<>();
		newsLetterNames.add("New York Times");
		newsLetterNames.add("The Wall Street Journal");
		newsLetterNames.add("The Washington Post");
		newsLetterNames.sort(null);
		
		newsList = new JComboBox<String>(newsLetterNames);
		
		newsList.setBounds(67, 206, 750, 27);
		
		panel_1.add(newsList);
		
		btnBack.addActionListener(this);
		btnBack.setBounds(67, 374, 128, 45);
		panel_1.add(btnBack);
		
		btnSubscribe.addActionListener(this);
		btnSubscribe.setBounds(674, 374, 143, 45);
		panel_1.add(btnSubscribe);
		
		btnOpen.addActionListener(this);
		btnOpen.setBounds(266, 374, 128, 45);
		

		btnCancel.addActionListener(this);
		panel_1.add(btnOpen);
		btnCancel.setBounds(470, 374, 128, 45);
		
		panel_1.add(btnCancel);
	}
	
	private void openWebPage(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSubscribe) {
			String selection = (String) newsList.getSelectedItem();
			if (selection.equals("New York Times")) {
				openWebPage("https://www.nytimes.com/subscription/all-access");
			}
			
			else if (selection.equals("The Wall Street Journal")) {
				openWebPage("https://store.wsj.com/shop/us/ca/wsjaocan24/");
			}
			
			else if (selection.equals("The Washington Post")) {
				openWebPage("https://subscribe.washingtonpost.com");
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
		
		else if (e.getSource() == btnOpen) {
			String open = (String) newsList.getSelectedItem();
			
			if (open.equals("New York Times")) {
				openWebPage("https://www.nytimes.com");
			}
			
			else if (open.equals("The Wall Street Journal")) {
				openWebPage("https://www.wsj.com");
			}
			
			else if (open.equals("The Washington Post")) {
				openWebPage("https://www.washingtonpost.com");
			}
		}
		
		else if (e.getSource() == btnCancel) {
			String cancel = (String) newsList.getSelectedItem();
			if (cancel.equals("New York Times")) {
				openWebPage("https://help.nytimes.com/hc/en-us/articles/360003499613-Cancel-Your-Subscription");
			}
			
			else if (cancel.equals("The Wall Street Journal")) {
				openWebPage("https://sso.accounts.dowjones.com/login-page?op=localop&scope=openid%20idp_id%20roles%20email%20given_name%20family_name%20djid%20djUsername%20djStatus%20trackid%20tags%20prts%20updated_at%20createTimestamp&client_id=5hssEAdMy0mJTICnJNvC9TXEw3Va7jfO&response_type=code&redirect_uri=https%3A%2F%2Faccounts.wsj.com%2Fauth%2Fsso%2Flogin&mg=ss-ngx&url=https%3A%2F%2Fcustomercenter.wsj.com%2Fsubscriptions%3Fmod%3Dmh_sidebar&auth-zones=SELF-SERV&nonce=402fc4ef-3e54-4acd-9fcb-caf53fe13ab0&ui_locales=en-us-x-wsj-223-2&mars=-1&ns=prod%2Faccounts-wsj&state=Tv8k0RpII-o_-nYh.iLmQwK6k_Lyi4WiCffPZ5xMQR8CmXXfP3XU4qH952XaJfXpn3OrHvrPtxq7hlNvsfOD9WGXPYdwSmoVSI3-gSA&resource=https%253A%252F%252Fcustomercenter.wsj.com%252Fsubscriptions%253Fmod%253Dmh_sidebar&protocol=oauth2&client=5hssEAdMy0mJTICnJNvC9TXEw3Va7jfO#!/signin");
			}
			
			else if (cancel.equals("The Washington Post")) {
				openWebPage("https://www.washingtonpost.com/subscribe/signin/?next_url=https%253A%252F%252Fwww.washingtonpost.com%252Fmy-post%252Faccount%252Fsubscription%252F");
			}
		}
		
		
	}

	
}
