import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public Container mainPanel;

    // Navigation Components
    private JButton BProfile;
    private JButton BLibrary;
    private JButton BExit;

    // Filter Components
    private JButton BWebComic;
    private JButton BWebNovel;

    // Color Scheme
    private Color primaryColor = new Color(51, 61, 87);
    private Color secondaryColor = new Color(249, 206, 146);
    private Color backgroundColor = Color.WHITE;
    private Color panelBackground = new Color(245, 247, 250);

    // Components untuk diakses dari luar
    private JPanel cardsContainer;
    private JLabel totalLabel;
    private JButton addBtn;

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

        // NAVIGATION TABS - DI TENGAH ATAS
        JPanel navPanel = createNavigationPanel();
        mainContentPanel.add(navPanel, BorderLayout.NORTH);

        // ====================== CONTENT WRAPPER ======================
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(panelBackground);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // ====================== FILTER TABS ======================
        JPanel filterPanel = createFilterPanel();
        contentWrapper.add(filterPanel, BorderLayout.NORTH);

        // ====================== SCROLLABLE CONTENT AREA ======================
        JScrollPane scrollContent = createScrollableContent();
        contentWrapper.add(scrollContent, BorderLayout.CENTER);

        // Add content wrapper to main content
        mainContentPanel.add(contentWrapper, BorderLayout.CENTER);

        // Add main content to main panel
        ((JPanel) mainPanel).add(mainContentPanel, BorderLayout.CENTER);
    }

    // ===== FILTER PANEL (WEBCOMIC / WEBNOVEL) =====
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filterPanel.setOpaque(false);
        filterPanel.setBackground(panelBackground);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Container untuk filter tabs dengan background putih
        JPanel filterContainer = new JPanel();
        filterContainer.setLayout(new BoxLayout(filterContainer, BoxLayout.X_AXIS));
        filterContainer.setBackground(Color.WHITE);
        filterContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        filterContainer.setMaximumSize(new Dimension(300, 40));

        BWebComic = createFilterButton("WEBCOMIC");
        BWebNovel = createFilterButton("WEBNOVEL");

        // Set WebComic as active secara default
        BWebComic.setBackground(primaryColor);
        BWebComic.setForeground(secondaryColor);

        filterContainer.add(BWebComic);
        filterContainer.add(BWebNovel);

        filterPanel.add(filterContainer);

        setupFilterEvents();

        return filterPanel;
    }

    private JButton createFilterButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(Color.WHITE);
        button.setForeground(primaryColor);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Dimension buttonSize = new Dimension(150, 40);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        // Border antar button
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Hapus border kanan untuk button terakhir
        if (text.equals("WEBNOVEL")) {
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        return button;
    }

    private void setupFilterEvents() {
        // WebComic Button
        BWebComic.addActionListener(e -> {
            resetFilterButtons();
            BWebComic.setBackground(primaryColor);
            BWebComic.setForeground(secondaryColor);
            System.out.println("Filter: WebComic selected");
            // Di sini bisa tambahkan logika untuk filter konten WebComic
        });

        // WebNovel Button
        BWebNovel.addActionListener(e -> {
            resetFilterButtons();
            BWebNovel.setBackground(primaryColor);
            BWebNovel.setForeground(secondaryColor);
            System.out.println("Filter: WebNovel selected");
            // Di sini bisa tambahkan logika untuk filter konten WebNovel
        });
    }

    private void resetFilterButtons() {
        JButton[] filterButtons = {BWebComic, BWebNovel};
        for (JButton button : filterButtons) {
            button.setBackground(Color.WHITE);
            button.setForeground(primaryColor);
        }
    }

    // ===== SCROLLABLE CONTENT AREA =====
    private JScrollPane createScrollableContent() {
        // Panel utama untuk konten yang bisa discroll
        JPanel scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
        scrollablePanel.setBackground(panelBackground);

        // Container untuk blue panel
        JPanel centerHolder = new JPanel(new GridBagLayout());
        centerHolder.setBackground(panelBackground);

        // ====================== BLUE CONTENT PANEL ======================
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(primaryColor);
        bluePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));

        // ===================== ROW ATAS: TOTAL ITEMS + BUTTON =====================
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        // kiri = total items
        totalLabel = new JLabel("Total: 0 Items");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topRow.add(totalLabel, BorderLayout.WEST);

        // kanan = tombol add new collections
        addBtn = createAddButton();
        topRow.add(addBtn, BorderLayout.EAST);

        bluePanel.add(topRow);
        bluePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ========================= CARDS CONTAINER =========================
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setOpaque(false);

        // Grid container untuk menampung card-card
        JPanel gridContainer = new JPanel();
        gridContainer.setLayout(new GridLayout(0, 2, 20, 20)); // 0 rows berarti dinamis
        gridContainer.setOpaque(false);

        cardsContainer.add(gridContainer);
        bluePanel.add(cardsContainer);

        centerHolder.add(bluePanel);
        scrollablePanel.add(centerHolder);

        // Tambahkan padding bawah
        scrollablePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buat JScrollPane dengan scrollablePanel
        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(panelBackground);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Customize scrollbar agar lebih smooth
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        verticalScrollBar.setBackground(panelBackground);
        verticalScrollBar.setForeground(primaryColor);

        return scrollPane;
    }

    // ===== NAVIGATION PANEL =====
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
    public JPanel createCard(String title, String author, String chapter, String status) {
        return createCard(title, author, chapter, status, null);
    }

    public JPanel createCard(String title, String author, String chapter, String status, String rate) {
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

    // ===== PUBLIC METHODS UNTUK MENGUBAH KONTEN DARI BACKEND =====

    /**
     * Mengupdate total item yang ditampilkan
     */
    public void updateTotalItems(int count) {
        totalLabel.setText("Total: " + count + " Items");
    }

    /**
     * Menghapus semua card yang ada
     */
    public void clearCards() {
        Component[] components = cardsContainer.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                ((JPanel) component).removeAll();
            }
        }
        cardsContainer.removeAll();
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    /**
     * Menambahkan card baru ke container
     */
    public void addCard(JPanel card) {
        // Cari grid container di dalam cardsContainer
        for (Component comp : cardsContainer.getComponents()) {
            if (comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof GridLayout) {
                ((JPanel) comp).add(card);
                break;
            }
        }
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    /**
     * Mendapatkan container untuk cards (untuk diisi dari backend)
     */
    public JPanel getCardsContainer() {
        return cardsContainer;
    }

    /**
     * Mendapatkan tombol filter WebComic
     */
    public JButton getWebComicButton() {
        return BWebComic;
    }

    /**
     * Mendapatkan tombol filter WebNovel
     */
    public JButton getWebNovelButton() {
        return BWebNovel;
    }

    /**
     * Mendapatkan tombol add
     */
    public JButton getAddButton() {
        return addBtn;
    }

    /**
     * Mendapatkan label total
     */
    public JLabel getTotalLabel() {
        return totalLabel;
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