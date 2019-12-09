package View;

import Message.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class MainFrame extends JFrame {
    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private BoardPanel boardPanel;
    private HistoryPanel historyPanel;

    final int WIDTH = 900, HEIGHT = 725;

    /**
     * Initiate the frame
     *
     * @param queue
     * @return
     */
    public static MainFrame init(BlockingQueue<Message> queue) {
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
//        BoxLayout by = new BoxLayout(center, BoxLayout.Y_AXIS);
//        center.setLayout(by);
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


        JPanel topbox = new JPanel();
        topbox.setLayout(new BorderLayout());

        topbox.setPreferredSize(new Dimension(WIDTH,50));
        topbox.setBackground(Color.WHITE);
        topbox.add(header,BorderLayout.WEST);

        JButton newGame = new JButton("Options");
        newGame.setPreferredSize(new Dimension(120,20));
        String[] options = { "Rules", "New Game" };

        newGame.addActionListener(e -> {
            int x = JOptionPane.showOptionDialog(null, "Rules or New Game?", " ", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);


            if (x == 1) {
                System.out.println("New Game/Resign Invoked");
                try{
                    messageQueue.put(new NewGameMessage());
                    historyPanel.resetLm();
                } catch (Exception ex){
                    System.out.print("couldn't start new game");
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "The Rules:\n" +
                        "\t\t1. Two players take alternating turns and can only move their own pieces.\n" +
                        "\t\t2. Only the darker squares on the board can be occupied by pieces on the board. The lighter colored tiles must remain empty.\n" +
                        "\t\t3. In each turn only one piece can be moved. The pieces move one space diagonally forward in the unoccupied adjacent squares.\n" +
                        "\t\t4. If the player jumps over their opponent's piece, they have successfully captured the opponent's piece and the piece is removed from the board\n" +
                        "\t\t5. Each piece is initially referred to as a soldier, but if it reaches the furthest side of the board it becomes a King.\n" +
                        "\t\t6. Soldiers can only move diagonally forward. When a piece becomes a King it gains the ability to move backwards as well.\n" +
                        "\t\t7. Multiple pieces may be jumped by both soldiers and Kings provided that there are successive unoccupied squares beyond each piece that is jumped.");
            }

        });


        topbox.add(newGame, BorderLayout.EAST);
        center.add(topbox,BorderLayout.NORTH);

        center.add(boardPanel);
        this.add(center, BorderLayout.CENTER);

        this.add(historyPanel, BorderLayout.EAST);
    }

    public void setBoardPanel(GameInfo gameInfo) {
        boardPanel.setBoardPanel(gameInfo);
    }

    public void setHistoryPanel(GameInfo gameInfo) {
        historyPanel.setHistoryPanel(gameInfo);
    }
}
