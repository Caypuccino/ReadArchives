import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public Container mainPanel;

    // Navigation Components
    private JButton BProfile;
    private JButton BLibrary;
    private JButton BExit;

    // Color Scheme
    private Color primaryColor = new Color(51, 61, 87);
    private Color secondaryColor = new Color(249, 206, 146);
    private Color backgroundColor = Color.WHITE;
    private Color panelBackground = new Color(245, 247, 250);

    public Dashboard() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(panelBackground);

        // ========================= HEADER =========================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel headerTitle = new JLabel("ReadArchive");
        headerTitle.setForeground(secondaryColor);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 24));

        headerPanel.add(headerTitle, BorderLayout.WEST);
        ((JPanel) mainPanel).add(headerPanel, BorderLayout.NORTH);

        // ========================= MAIN CONTENT =========================
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setBackground(panelBackground);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // NAVIGATION TABS - DI TENGAH ATAS (SEPERTI DI PROFILEPAGE)
        JPanel navPanel = createNavigationPanel();
        mainContentPanel.add(navPanel, BorderLayout.NORTH);

        // ====================== CONTENT WRAPPER ======================
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(panelBackground);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // ====================== BLUE CONTENT PANEL ======================
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(primaryColor);
        bluePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));

        bluePanel.setPreferredSize(new Dimension(850, 350));

        // ===================== ROW ATAS: TOTAL ITEMS + BUTTON =====================
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        // kiri = total items
        JLabel totalLabel = new JLabel("Total: XX Items");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topRow.add(totalLabel, BorderLayout.WEST);

        // kanan = tombol add new collections
        JButton addBtn = createAddButton();
        topRow.add(addBtn, BorderLayout.EAST);

        bluePanel.add(topRow);
        bluePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ========================= GRID 4 KOTAK =========================
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setOpaque(false);

        grid.add(createCard("Title", "author?", "Chapter XX/XX", "Reading"));
        grid.add(createCard("Title", "author?", "Chapter XX/XX", "Completed", "4.5/5"));
        grid.add(createCard("Title", "author?", "Chapter XX/XX", "Plan to Read"));
        grid.add(createCard("Title", "author?", "Chapter XX/XX", "Reading"));

        bluePanel.add(grid);

        // Center the blue panel
        JPanel centerHolder = new JPanel(new GridBagLayout());
        centerHolder.setBackground(panelBackground);
        centerHolder.add(bluePanel);

        contentWrapper.add(centerHolder, BorderLayout.CENTER);

        // ========================= FOOTER PAGINATION =========================
        JPanel footerPanel = createFooterPanel();
        contentWrapper.add(footerPanel, BorderLayout.SOUTH);

        // Add content wrapper to main content
        mainContentPanel.add(contentWrapper, BorderLayout.CENTER);

        // Add main content to main panel
        ((JPanel) mainPanel).add(mainContentPanel, BorderLayout.CENTER);
    }

    // ===== NAVIGATION PANEL (DI TENGAH SEPERTI DI PROFILEPAGE) =====

    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        navPanel.setOpaque(false);
        navPanel.setBackground(panelBackground);

        // Container untuk tabs dengan background putih
        JPanel tabsContainer = new JPanel();
        tabsContainer.setLayout(new BoxLayout(tabsContainer, BoxLayout.X_AXIS));
        tabsContainer.setBackground(Color.WHITE);
        tabsContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        tabsContainer.setMaximumSize(new Dimension(450, 45));

        BProfile = createNavButton("PROFILE");
        BLibrary = createNavButton("LIBRARY");
        BExit = createNavButton("EXIT");

        // Set Library as active (karena ini Dashboard)
        BLibrary.setBackground(primaryColor);
        BLibrary.setForeground(secondaryColor);

        tabsContainer.add(BProfile);
        tabsContainer.add(BLibrary);
        tabsContainer.add(BExit);

        navPanel.add(tabsContainer);

        setupNavigationEvents();

        return navPanel;
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(Color.WHITE);
        button.setForeground(primaryColor);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Dimension buttonSize = new Dimension(150, 45);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        // Border antar button
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Hapus border kanan untuk button terakhir
        if (text.equals("EXIT")) {
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        return button;
    }

    private void setupNavigationEvents() {
        // Profile Button
        BProfile.addActionListener(e -> {
            resetNavButtons();
            BProfile.setBackground(primaryColor);
            BProfile.setForeground(secondaryColor);
            MainControl.showProfilePage();
        });

        // Library Button (Dashboard) - ACTIVE
        BLibrary.addActionListener(e -> {
            resetNavButtons();
            BLibrary.setBackground(primaryColor);
            BLibrary.setForeground(secondaryColor);
            // Tetap di halaman dashboard
        });

        // Exit Button
        BExit.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void resetNavButtons() {
        JButton[] navButtons = {BProfile, BLibrary, BExit};
        for (JButton button : navButtons) {
            button.setBackground(Color.WHITE);
            button.setForeground(primaryColor);
        }
    }

    // ===== ADD BUTTON =====

    private JButton createAddButton() {
        JButton addBtn = new JButton("Add New Collections");
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        addBtn.setBackground(secondaryColor);
        addBtn.setForeground(primaryColor);
        addBtn.setFocusPainted(false);
        addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBtn.setPreferredSize(new Dimension(180, 35));

        // Add hover effect
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addBtn.setBackground(new Color(239, 196, 136));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addBtn.setBackground(secondaryColor);
            }
        });

        // Action listener untuk add button
        addBtn.addActionListener(e -> {
            MainControl.showAddNewCollections();
        });

        return addBtn;
    }

    // ===== CARD COMPONENTS =====

    private JPanel createCard(String title, String author, String chapter, String status) {
        return createCard(title, author, chapter, status, null);
    }

    private JPanel createCard(String title, String author, String chapter, String status, String rate) {
        JPanel card = new JPanel();
        card.setBackground(secondaryColor);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setPreferredSize(new Dimension(180, 150));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel(author);
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel chapterLabel = new JLabel(chapter);
        chapterLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusLabel = new JLabel("Status: " + status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(authorLabel);
        card.add(chapterLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(statusLabel);

        if (rate != null) {
            JLabel rateLabel = new JLabel("Rate: " + rate);
            rateLabel.setFont(new Font("Arial", Font.BOLD, 12));
            rateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(rateLabel);
        }

        // Add hover effect untuk card
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(239, 196, 136));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(secondaryColor);
            }
        });

        return card;
    }

    // ===== FOOTER PANEL =====

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(panelBackground);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel paginationPanel = new JPanel(new BorderLayout());
        paginationPanel.setBackground(primaryColor);
        paginationPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel prev = createPaginationLabel("[Prev]");
        JLabel next = createPaginationLabel("[Next]");
        JLabel pageInfo = new JLabel("Page 1 of X", SwingConstants.CENTER);
        pageInfo.setForeground(Color.WHITE);
        pageInfo.setFont(new Font("Arial", Font.PLAIN, 14));

        paginationPanel.add(prev, BorderLayout.WEST);
        paginationPanel.add(pageInfo, BorderLayout.CENTER);
        paginationPanel.add(next, BorderLayout.EAST);

        footerPanel.add(paginationPanel, BorderLayout.CENTER);

        return footerPanel;
    }

    private JLabel createPaginationLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println(text + " clicked!");
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setForeground(secondaryColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setForeground(Color.WHITE);
            }
        });

        return label;
    }

    // Getter untuk mainPanel
    public JPanel getMainPanel() {
        return (JPanel) mainPanel;
    }

    // Untuk testing standalone
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Dashboard().getMainPanel());
        frame.setVisible(true);
    }
}