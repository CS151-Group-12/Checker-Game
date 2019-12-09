package View;

import Message.Message;
import Model.Move;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;

import Message.NewGameMessage;

public class HistoryPanel extends JPanel {
	private DefaultListModel<String> lm;

	private JLabel playercolor;
	private Font font3;
	public static MainFrame mainFrame;
	BlockingQueue<Message> messageQueue;
	private JLabel winner;


	public HistoryPanel(BlockingQueue<Message> queue) {
		messageQueue = queue;
		initPanel();
	}

	void setHistoryPanel(GameInfo gameInfo) {
//		if (gameInfo.isReset()) {
//			lm = new DefaultListModel<>();
//		}
			for(Move m : gameInfo.getMoveList()) {
				String moveString = m.getFromRow() + "-" + m.getFromCol() + " -> " + m.getToRow() + "-" + m.getToCol();
				if (!lm.contains(moveString))
					lm.addElement(moveString);
			}



		String playerTurn;
		Color c;
		if (gameInfo.getPlayerTurn() == 0) {
			playerTurn = "Red's Turn";
			c = Color.RED;
		} else {
			playerTurn = "Black's Turn";
			c = Color.BLACK;
		}

		playercolor.setText(playerTurn);
		playercolor.setForeground(c);

		String winnerString = "";
		Color winnerColor = Color.WHITE;
		if (gameInfo.getWinner() == 0) {
			winnerString = "RED WINS";
			System.out.println(winnerString);
			winnerColor = Color.RED;
		} else if (gameInfo.getWinner() == 1) {
			winnerString = "BLACK WINS";
			winnerColor = Color.BLACK;
		}

		winner.setText(winnerString);
		winner.setForeground(winnerColor);

	}

	public void initPanel() {
		lm = new DefaultListModel<>();
		BoxLayout by = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(by);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		Font font1 = new Font("playerfont", Font.PLAIN, 25);

		JLabel playerturn = new JLabel("Player's turn");
		playerturn.setBackground(Color.BLACK);

		playercolor = new JLabel("Red's Turn");

		playerturn.setPreferredSize(new Dimension(100, 50));
		playerturn.setFont(font1);
		playerturn.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		playercolor.setPreferredSize(new Dimension(100, 50));
		playercolor.setFont(font3);
		playercolor.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		font3 = new Font("colorfont", Font.PLAIN, 20);

		//Determines the color of the player turn

		JPanel undoRedo = new JPanel();
		undoRedo.setPreferredSize(new Dimension(300, 100));
		this.add(undoRedo);
		undoRedo.setBackground(Color.WHITE);



		BoxLayout h = new BoxLayout(undoRedo, BoxLayout.Y_AXIS);
		undoRedo.setLayout(h);

		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(300, 50));
		container.setBackground(Color.WHITE);
		container.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 1, Color.BLACK));

		JLabel header = new JLabel("Move History");
		header.setPreferredSize(new Dimension(125, 50));
		header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Font font = new Font("integerfont", Font.PLAIN, 20);
		header.setFont(font);

		winner = new JLabel();
		winner.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Font font4 = new Font("integerfont", Font.PLAIN, 30);

		winner.setFont(font4);

		JPanel ButtonContainer = new JPanel();
		ButtonContainer.setLayout(new GridLayout(1, 2));
		JButton rules = new JButton("Rules");

		rules.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "The Rules:\n" +
				"\t\t1. Two players take alternating turns and can only move their own pieces.\n" +
				"\t\t2. Only the darker squares on the board can be occupied by pieces on the board. The lighter colored tiles must remain empty.\n" +
				"\t\t3. In each turn only one piece can be moved. The pieces move one space diagonally forward in the unoccupied adjacent squares.\n" +
				"\t\t4. If the player jumps over their opponent's piece, they have successfully captured the opponent's piece and the piece is removed from the board\n" +
				"\t\t5. Each piece is initially referred to as a soldier, but if it reaches the furthest side of the board it becomes a King.\n" +
				"\t\t6. Soldiers can only move diagonally forward. When a piece becomes a King it gains the ability to move backwards as well.\n" +
				"\t\t7. Multiple pieces may be jumped by both soldiers and Kings provided that there are successive unoccupied squares beyond each piece that is jumped."));
		Message newGameMessage;

		JPanel gameButtons = new JPanel();
		gameButtons.setPreferredSize(new Dimension(300,  100));

		JButton newGame = new JButton("New");
		newGame.addActionListener(e -> {
			System.out.println("New Game/Resign Invoked");
			try{
				//Controller.StartNewGameValve.execute(newGameMessage);
////				messageQueue.put(new NewGameMessage());
				messageQueue.put(new NewGameMessage());
				lm.clear();
			}catch (Exception ex){
				System.out.print("couldn't start new game");
			}
		});
		ButtonContainer.add(rules);
		ButtonContainer.add(newGame);
		ButtonContainer.setBackground(Color.WHITE);
		ButtonContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		gameButtons.setOpaque(true);
		gameButtons.add(ButtonContainer);
		this.add(gameButtons);

		container.add(winner);
		container.add(header);


		undoRedo.add(playerturn);
		undoRedo.add(playercolor);
		undoRedo.add(container);

		JList<String> list = new JList<>();
		list.setModel(lm);

		JScrollPane s = new JScrollPane(list);
		s.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 1, Color.BLACK));

		list.setPreferredSize(new Dimension(200, 500));
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(200);



		this.add(s);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 700);
	}

}
