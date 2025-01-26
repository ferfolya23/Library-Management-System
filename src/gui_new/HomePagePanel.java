package gui_new;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lms_func.*;

public class HomePagePanel extends JPanel {
    Account user;
    ArrayList<PhysicalItem> checkedOutBooks;
    JPanel booksPanel;

    public HomePagePanel(Account user, ArrayList<PhysicalItem> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));

        // Title
        JLabel lblTitle = new JLabel("YorkU Libraries", SwingConstants.LEFT);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setForeground(new Color(255, 0, 0, 180));
        topPanel.add(lblTitle);

        // Search bar panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JTextField txtSearch = new JTextField(30);
        JButton btnSearch = new JButton("Go!");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        topPanel.add(searchPanel);
        add(topPanel, BorderLayout.NORTH);

        // Navigation buttons panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(14,1,10, 5));

        JButton btnCheckedOut = new JButton("Checked Out");
        JButton btnSubscriptions = new JButton("Subscriptions");
        JButton btnCourses = new JButton("Courses");
        JButton btnRequests = new JButton("Requests");

        navPanel.add(btnCheckedOut);
        navPanel.add(btnSubscriptions);
        navPanel.add(btnCourses);
        navPanel.add(btnRequests);
        navPanel.add(Box.createVerticalGlue());
        navPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));

        add(navPanel, BorderLayout.WEST);

        // Checked-out books panel
        booksPanel = new JPanel();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(booksPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Checked Out Books"));

        add(scrollPane, BorderLayout.CENTER);

        populateCheckedOutBooks();
    }

    private void populateCheckedOutBooks() {
        booksPanel.removeAll();

        for (PhysicalItem book : checkedOutBooks) {
            JPanel bookPanel = new JPanel(new BorderLayout());
            bookPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            bookPanel.setBackground(Color.WHITE);
            bookPanel.setMaximumSize(new Dimension(730, 80));

            // Book info

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            //Date date = formatter.parse(book.getDueDate().toString());
            DateFormat df = new SimpleDateFormat("E, d MMMM, y.");
            String dueDate = df.format(book.dueDate);

            String bookInfo = String.format("<html><b>Name:</b> %s<br><b>Author:</b> %s<br><b>Due Date:</b> %s</html>",
                    book.getName(), book.getAuthor(), dueDate);
            JLabel lblBookInfo = new JLabel(bookInfo);

            // Fee if overdue
            JLabel lblFee = new JLabel();

            if (book.calculateFine() > 0) {
                lblFee.setText(String.format("Overdue Fee: $%.2f", book.calculateFine()));
                lblFee.setForeground(Color.RED);
            }

            // Return button
            JButton btnReturn = new JButton("Return");
            btnReturn.addActionListener(e -> {
                try {
                    returnBook(book);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });

            // Adding components to the panel
            bookPanel.add(lblBookInfo, BorderLayout.CENTER);
            bookPanel.add(lblFee, BorderLayout.EAST);
            bookPanel.add(btnReturn, BorderLayout.SOUTH);

            booksPanel.add(bookPanel);
        }

        booksPanel.revalidate();
        booksPanel.repaint();
    }

    private void returnBook(PhysicalItem book) throws Exception {
        //book.returnCopy(user);
        JOptionPane.showMessageDialog(this, "You have returned: " + book.getName());
        checkedOutBooks.remove(book);
        populateCheckedOutBooks();
    }
}
