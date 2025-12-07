import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EditProfilePage {
    private JPanel editProfilePanel;
    private JCheckBox cBdisplay;
    private JTextField tFdisplay;
    private JCheckBox cBemail;
    private JTextField tFemail;
    private JCheckBox cBpassword;
    private JPasswordField tFpassword;
    private JButton bTsave;
    private JButton bTcancel;
    private JLabel JEditProfile;
    private JLabel JSelect;

    private Color primaryColor = new Color(51, 61, 87);
    private Color secondaryColor = new Color(249, 206, 146);
    private Color backgroundColor = Color.WHITE;

    public EditProfilePage() {
        createUI();
        applyStyling();
        setupEvents();
    }

    private JPanel createRow(JCheckBox checkBox, JComponent field) {

        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setOpaque(false);

        // Checkbox fix width
        checkBox.setPreferredSize(new Dimension(140, 30));
        checkBox.setMaximumSize(new Dimension(140, 30));
        checkBox.setMinimumSize(new Dimension(140, 30));

        // Field fix width
        field.setPreferredSize(new Dimension(250, 30));
        field.setMaximumSize(new Dimension(250, 30));
        field.setMinimumSize(new Dimension(250, 30));

        row.add(checkBox);
        row.add(Box.createRigidArea(new Dimension(15, 0)));
        row.add(field);

        return row;
    }

    private void createUI() {

        editProfilePanel = new JPanel();
        editProfilePanel.setLayout(new BorderLayout());
        editProfilePanel.setBackground(backgroundColor);

        // TITLE BAR
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(primaryColor);

        JEditProfile = new JLabel("EDIT PROFILE", SwingConstants.CENTER);
        JEditProfile.setForeground(secondaryColor);
        JEditProfile.setFont(new Font("Arial", Font.BOLD, 28));
        titlePanel.add(JEditProfile);

        editProfilePanel.add(titlePanel, BorderLayout.NORTH);

        // CONTENT
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JPanel selectRow = new JPanel();
        selectRow.setLayout(new BoxLayout(selectRow, BoxLayout.X_AXIS));
        selectRow.setOpaque(false);

        selectRow.add(Box.createRigidArea(new Dimension(0, 0))); // sama rata dengan panel kiri
        selectRow.add(Box.createRigidArea(new Dimension(0, 0)));

        JSelect = new JLabel("Check fields you want to edit:");
        JSelect.setFont(new Font("Arial", Font.BOLD, 16));

        selectRow.add(JSelect);

        contentPanel.add(Box.createRigidArea(new Dimension(140, 0)));
        contentPanel.add(selectRow);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Display Name
        cBdisplay = new JCheckBox("Display Name");
        tFdisplay = new JTextField();
        tFdisplay.setBackground(new Color(240, 240, 240));
        contentPanel.add(createRow(cBdisplay, tFdisplay));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Email
        cBemail = new JCheckBox("Email");
        tFemail = new JTextField();
        tFemail.setBackground(new Color(240, 240, 240));
        contentPanel.add(createRow(cBemail, tFemail));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Password
        cBpassword = new JCheckBox("Password");
        tFpassword = new JPasswordField();
        tFpassword.setBackground(new Color(240, 240, 240));
        contentPanel.add(createRow(cBpassword, tFpassword));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        bTsave = new JButton("Save Changes");
        bTcancel = new JButton("Cancel");

        buttonPanel.add(bTsave);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(bTcancel);

        contentPanel.add(buttonPanel);

        editProfilePanel.add(contentPanel, BorderLayout.CENTER);

        // Disable awal
        tFdisplay.setEnabled(false);
        tFemail.setEnabled(false);
        tFpassword.setEnabled(false);
    }

    private void applyStyling() {

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        tFdisplay.setFont(labelFont);
        tFemail.setFont(labelFont);
        tFpassword.setFont(labelFont);

        Border fieldBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );

        tFdisplay.setBorder(fieldBorder);
        tFemail.setBorder(fieldBorder);
        tFpassword.setBorder(fieldBorder);

        bTsave.setBackground(primaryColor);
        bTsave.setForeground(secondaryColor);
        bTsave.setFocusPainted(false);

        bTcancel.setBackground(primaryColor);
        bTcancel.setForeground(secondaryColor);
        bTcancel.setFocusPainted(false);
    }

    private void setupEvents() {
        cBdisplay.addActionListener(e -> {
            boolean selected = cBdisplay.isSelected();
            tFdisplay.setEnabled(selected);

            // Gray jika disabled, White jika enabled
            tFdisplay.setBackground(selected ? Color.WHITE : new Color(240, 240, 240));

            if (selected) {
                tFdisplay.selectAll();
            }
        });

        cBemail.addActionListener(e -> {
            boolean selected = cBemail.isSelected();
            tFemail.setEnabled(selected);
            tFemail.setBackground(selected ? Color.WHITE : new Color(240, 240, 240));

            if (selected) {
                tFemail.selectAll();
            }
        });

        cBpassword.addActionListener(e -> {
            boolean selected = cBpassword.isSelected();
            tFpassword.setEnabled(selected);
            tFpassword.setBackground(selected ? Color.WHITE : new Color(240, 240, 240));
        });

        bTsave.addActionListener(e -> {

            if (cBdisplay.isSelected())
                System.out.println("New display: " + tFdisplay.getText());

            if (cBemail.isSelected())
                System.out.println("New email: " + tFemail.getText());

            if (cBpassword.isSelected())
                System.out.println("New password: " + new String(tFpassword.getPassword()));

            JOptionPane.showMessageDialog(null,
                    "Changes saved successfully!");
        });

        bTcancel.addActionListener(e -> {

            cBdisplay.setSelected(false);
            cBemail.setSelected(false);
            cBpassword.setSelected(false);

            tFdisplay.setEnabled(false);
            tFemail.setEnabled(false);
            tFpassword.setEnabled(false);

            tFdisplay.setText("");
            tFemail.setText("");
            tFpassword.setText("");

            JOptionPane.showMessageDialog(null,
                    "Changes canceled.");
        });
    }

    public JPanel geteditProfilePanel() {
        return editProfilePanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Edit Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setContentPane(new EditProfilePage().geteditProfilePanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
