package parking;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ParkingLot lot;

    // Colors for the design
    private Color darkBg = new Color(15, 23, 42);
    private Color cardBg = new Color(30, 41, 59);
    private Color green = new Color(34, 197, 94);
    private Color red = new Color(239, 68, 68);
    private Color blue = new Color(59, 130, 246);
    private Color yellow = new Color(234, 179, 8);
    private Color white = Color.WHITE;

    // ADD THESE 3 NEW LINES
    private JLabel totalCard;
    private JLabel availCard;
    private JLabel occCard;

    public MainFrame() {
        lot = new ParkingLot(10);
        setTitle("Smart Parking System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(darkBg);

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        // Main layout
        setLayout(new BorderLayout());

        // Top header
        JPanel header = new JPanel();
        header.setBackground(new Color(20, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel title = new JLabel("Smart Parking System");
        title.setForeground(white);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(darkBg);
        tabs.setForeground(white);
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        tabs.addTab("Dashboard", buildDashboard());
        tabs.addTab("Park Vehicle", buildParkScreen());
        tabs.addTab("Exit Vehicle", buildExitScreen());
        tabs.addTab("View Slots", buildSlotView());

        add(tabs, BorderLayout.CENTER);
    }

    //  DASHBOARD 
    private JPanel buildDashboard() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(darkBg);

        JPanel cards = new JPanel(new GridLayout(1, 3, 20, 0));
        cards.setBackground(darkBg);
        cards.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        totalCard = makeStatCard("Total Slots", String.valueOf(lot.getTotalSlots()), blue);
        availCard = makeStatCard("Available", String.valueOf(lot.getAvailableCount()), green);
        occCard   = makeStatCard("Occupied", String.valueOf(lot.getTotalSlots() - lot.getAvailableCount()), red);

        cards.add(totalCard);
        cards.add(availCard);
        cards.add(occCard);
        panel.add(cards);
        return panel;
    }
    
    private void refreshDashboard() {
        availCard.setText(
            "<html><center><font size='5' color='white'>" + lot.getAvailableCount() +
            "</font><br><font size='3' color='#aaaaaa'>Available</font></center></html>");

        occCard.setText(
            "<html><center><font size='5' color='white'>" + (lot.getTotalSlots() - lot.getAvailableCount()) +
            "</font><br><font size='3' color='#aaaaaa'>Occupied</font></center></html>");
    }

    private JLabel makeStatCard(String label, String value, Color color) {
        JLabel card = new JLabel(
            "<html><center><font size='5' color='white'>" + value +
            "</font><br><font size='3' color='#aaaaaa'>" + label +
            "</font></center></html>", SwingConstants.CENTER);
        card.setOpaque(true);
        card.setBackground(cardBg);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2, true),
            BorderFactory.createEmptyBorder(30, 20, 30, 20)));
        card.setFont(new Font("Segoe UI", Font.BOLD, 18));
        return card;
    }

    //  PARK VEHICLE 
    private JPanel buildParkScreen() {
        JPanel panel = new JPanel(null); // absolute layout
        panel.setBackground(darkBg);

        JLabel heading = styledLabel("Park a Vehicle", 18, white);
        heading.setBounds(50, 30, 300, 35);
        panel.add(heading);

        JLabel plateLabel = styledLabel("Plate Number:", 13, new Color(180, 180, 180));
        plateLabel.setBounds(50, 90, 150, 25);
        panel.add(plateLabel);

        JTextField plateField = styledField();
        plateField.setBounds(50, 118, 280, 40);
        panel.add(plateField);

        JLabel typeLabel = styledLabel("Vehicle Type:", 13, new Color(180, 180, 180));
        typeLabel.setBounds(50, 175, 150, 25);
        panel.add(typeLabel);

        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Car", "Bike"});
        typeBox.setBounds(50, 203, 280, 40);
        typeBox.setBackground(cardBg);
        typeBox.setForeground(white);
        typeBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(typeBox);

        JButton parkBtn = styledButton("Park Vehicle", green);
        parkBtn.setBounds(50, 270, 180, 45);
        panel.add(parkBtn);

        JLabel resultLabel = styledLabel("", 13, green);
        resultLabel.setBounds(50, 330, 500, 25);
        panel.add(resultLabel);

        parkBtn.addActionListener(e -> {
            String plate = plateField.getText().trim();
            String type = (String) typeBox.getSelectedItem();

            if (plate.isEmpty()) {
                resultLabel.setForeground(red);
                resultLabel.setText("Please enter a plate number.");
                return;
            }

            // Check already parked
            if (lot.findVehicle(plate) != null) {
                resultLabel.setForeground(yellow);
                resultLabel.setText("This vehicle is already parked!");
                return;
            }

            Vehicle v = type.equals("Car") ? new Car(plate) : new Bike(plate);
            int slotId = lot.parkVehicle(v);

            if (slotId == -1) {
                resultLabel.setForeground(red);
                resultLabel.setText("Sorry! Parking lot is full.");
            } else {
                resultLabel.setForeground(green);
                resultLabel.setText("Parked in Slot #" + slotId);
                plateField.setText("");
                refreshDashboard();
            }
        });

        return panel;
    }

    // EXIT VEHICLE 
    private JPanel buildExitScreen() {
        JPanel panel = new JPanel(null);
        panel.setBackground(darkBg);

        JLabel heading = styledLabel("Exit Vehicle", 18, white);
        heading.setBounds(50, 30, 300, 35);
        panel.add(heading);

        JLabel plateLabel = styledLabel("Enter Plate Number:", 13, new Color(180, 180, 180));
        plateLabel.setBounds(50, 90, 200, 25);
        panel.add(plateLabel);

        JTextField plateField = styledField();
        plateField.setBounds(50, 118, 280, 40);
        panel.add(plateField);

        JButton exitBtn = styledButton("Calculate & Exit", red);
        exitBtn.setBounds(50, 180, 200, 45);
        panel.add(exitBtn);

        // Receipt area
        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setBackground(cardBg);
        receiptArea.setForeground(white);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        receiptArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(receiptArea);
        scroll.setBounds(50, 250, 400, 200);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(60, 80, 100)));
        panel.add(scroll);

        exitBtn.addActionListener(e -> {
            String plate = plateField.getText().trim();
            if (plate.isEmpty()) {
                receiptArea.setText("Please enter a plate number.");
                return;
            }

            ParkingSlot slot = lot.findVehicle(plate);
            if (slot == null) {
                receiptArea.setText("Vehicle not found: " + plate);
                return;
            }

            Vehicle v = slot.getVehicle();
            long exitTime = System.currentTimeMillis();
            double fee = v.calculateFee(exitTime);

            Receipt r = new Receipt(v.getPlateNumber(), v.getVehicleType(),
                v.getEntryTime(), exitTime, fee, slot.getSlotID());

            slot.clearSlot(); // free the slot
            refreshDashboard();

            receiptArea.setText(
                "============================\n" +
                "     PARKING RECEIPT\n" +
                "============================\n" +
                "Plate   : " + r.getPlateNumber() + "\n" +
                "Type    : " + r.getVehicleType() + "\n" +
                "Slot    : #" + r.getSlotID() + "\n" +
                "Duration: " + r.getDuration() + "\n" +
                "Fee     : Rs. " + r.getFee() + "\n" +
                "============================\n" +
                "   Thank you! Drive safe.\n"
            );
            plateField.setText("");
        });

        return panel;
    }

    //  SLOT VIEW 
    private JPanel buildSlotView() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(darkBg);

        JButton refresh = styledButton("Refresh", blue);
        refresh.setPreferredSize(new Dimension(120, 35));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        top.setBackground(darkBg);
        top.add(styledLabel("Parking Slots:", 14, white));
        top.add(refresh);
        outer.add(top, BorderLayout.NORTH);

        JPanel grid = new JPanel();
        grid.setBackground(darkBg);
        int cols = 5;
        int rows = (lot.getTotalSlots() + cols - 1) / cols;
        grid.setLayout(new GridLayout(rows, cols, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        ParkingSlot[] slots = lot.getSlots();
        for (ParkingSlot slot : slots) {
            JLabel cell = new JLabel(
                "<html><center>Slot " + slot.getSlotID() + "<br>" +
                (slot.isOccupied()
                    ? "<font color='#ff6666'>" + slot.getVehicle().getPlateNumber() + "</font>"
                    : "<font color='#66ff99'>Free</font>") +
                "</center></html>", SwingConstants.CENTER);
            cell.setOpaque(true);
            cell.setBackground(slot.isOccupied() ? new Color(60, 20, 20) : new Color(20, 50, 30));
            cell.setBorder(BorderFactory.createLineBorder(
                slot.isOccupied() ? red : green, 1, true));
            cell.setForeground(white);
            cell.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            grid.add(cell);
        }

        refresh.addActionListener(e -> {
            outer.remove(outer.getComponent(1));
            outer.add(new JScrollPane(buildSlotGrid()), BorderLayout.CENTER);
            outer.revalidate();
            outer.repaint();
        });

        outer.add(new JScrollPane(grid), BorderLayout.CENTER);
        return outer;
    }

    private JPanel buildSlotGrid() {
        JPanel grid = new JPanel();
        grid.setBackground(darkBg);
        int cols = 5;
        int rows = (lot.getTotalSlots() + cols - 1) / cols;
        grid.setLayout(new GridLayout(rows, cols, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        for (ParkingSlot slot : lot.getSlots()) {
            JLabel cell = new JLabel(
                "<html><center>Slot " + slot.getSlotID() + "<br>" +
                (slot.isOccupied()
                    ? "<font color='#ff6666'>" + slot.getVehicle().getPlateNumber() + "</font>"
                    : "<font color='#66ff99'>Free</font>") +
                "</center></html>", SwingConstants.CENTER);
            cell.setOpaque(true);
            cell.setBackground(slot.isOccupied() ? new Color(60, 20, 20) : new Color(20, 50, 30));
            cell.setBorder(BorderFactory.createLineBorder(
                slot.isOccupied() ? red : green, 1, true));
            cell.setForeground(Color.WHITE);
            cell.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            grid.add(cell);
        }
        return grid;
    }

    // HELPER METHODS 
    private JLabel styledLabel(String text, int size, Color color) {
        JLabel l = new JLabel(text);
        l.setForeground(color);
        l.setFont(new Font("Segoe UI", Font.PLAIN, size));
        return l;
    }

    private JTextField styledField() {
        JTextField f = new JTextField();
        f.setBackground(cardBg);
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 100, 140), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return f;
    }

    private JButton styledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
 // Calling main function
    public static void main(String[] args) {
        new MainFrame();
    }
}



