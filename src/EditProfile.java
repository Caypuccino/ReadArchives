import javax.swing.*;

public class EditProfile {
    private JTextField tfname;
    private JTextField tfemail;
    private JButton cancelButton;
    private JTextField tfcurrent;
    private JTextField tfnew;
    private JTextField tfconfirm;
    private JButton SaveChangesButton;
    private JPanel panelMain;

    public EditProfile() {
        // Aksi tombol
        SaveChangesButton.addActionListener(e -> saveProfile());
        cancelButton.addActionListener(e -> closeWindow());
    }

    private void saveProfile() {
        String name = tfname.getText();
        String email = tfemail.getText();
        String current = tfcurrent.getText();
        String newPass = tfnew.getText();
        String confirm = tfconfirm.getText();

        JOptionPane.showMessageDialog(null,
                "Nama: " + name + "\nEmail: " + email);
    }

    private void closeWindow() {
        SwingUtilities.getWindowAncestor(panelMain).dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Edit Profile");
        frame.setContentPane(new EditProfile().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}