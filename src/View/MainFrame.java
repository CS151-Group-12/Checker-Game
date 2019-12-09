package View;

import Message.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class MainFrame extends JFrame {
    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private BoardPanel boardPanel;
    private HistoryPanel historyPanel;

    private final int WIDTH = 900;

    /**
     * Initiate the frame
     *
     * @param queue
     * @return
     */
    static MainFrame init(BlockingQueue<Message> queue) {
        if (mainFrame == null) {
            messageQueue = queue;
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    public MainFrame() {
        super("Checkers");

        boardPanel = new BoardPanel(messageQueue);
        historyPanel = new HistoryPanel(messageQueue);

        setupFrame();
    }

    /**
     * Set up the frame that will show all the panels
     */
    private void setupFrame() {
        setLocationPanel();
        setFrameDisplay();
    }

    /**
     * Set up the frame size and its properties
     */
    private void setFrameDisplay() {
        int HEIGHT = 725;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Adding all panel into container that will show on the frame
     */
    private void setLocationPanel() {
        this.setLayout(new BorderLayout());

        //Adding The CenterPanel
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        BoxLayout by = new BoxLayout(center, BoxLayout.Y_AXIS);
        center.setLayout(by);
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        center.setBackground(Color.WHITE);

        //Adding The Header
        JLabel header = new JLabel("Checkers");
        Font font = new Font("integerfont", Font.BOLD, 25);
        header.setFont(font);
        header.setForeground(Color.BLACK);
        header.setBackground(Color.LIGHT_GRAY);
        header.setPreferredSize(new Dimension(WIDTH, 50));

        // Add Board Panel and Header to Center Panel
        center.add(header);
        center.add(boardPanel);
        this.add(center, BorderLayout.CENTER);

        this.add(historyPanel, BorderLayout.EAST);
    }

    void setBoardPanel(GameInfo gameInfo) {
        boardPanel.setBoardPanel(gameInfo);
    }

    void setHistoryPanel(GameInfo gameInfo) {
        historyPanel.setHistoryPanel(gameInfo);
    }
}
