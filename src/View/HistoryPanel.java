package View;

import Message.Message;


import Message.Message;
import Model.Move;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.concurrent.BlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class HistoryPanel extends JPanel {
	//set up back and forth arrows
	//set up list
	//set up player turn
	//set up reset game
	BlockingQueue<Message> messageQueue;
	private DefaultListModel<String> lm;

	private GameInfo gameInfo;

	JButton undo = new JButton("Undo");

	JButton redo = new JButton("Redo");

	JLabel playercolor;
	Font font3;

	JPanel UndoRedo;


	public HistoryPanel(BlockingQueue<Message> queue) {
		messageQueue = queue;
		initPanel();
	}

	public void setHistoryPanel(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
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

		UndoRedo = new JPanel();
		UndoRedo.setPreferredSize(new Dimension(300, 100));
		this.add(UndoRedo);
		UndoRedo.setBackground(Color.WHITE);

		BoxLayout h = new BoxLayout(UndoRedo, BoxLayout.Y_AXIS);
		UndoRedo.setLayout(h);

		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(300, 50));
		container.setBackground(Color.WHITE);
		container.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 1, Color.BLACK));

		JLabel header = new JLabel("Move History");

		header.setPreferredSize(new Dimension(125, 50));
		header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Font font = new Font("integerfont", Font.PLAIN, 20);
		header.setFont(font);
		
		JLabel winner = new JLabel("RED WINS!");
		winner.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Font font4 = new Font("integerfont", Font.PLAIN, 30);
		winner.setForeground(Color.RED);
		
		//to change to black wins
//		winner.setText("BLACK WINS!");
//		winner.setForeground(Color.BLACK);
		
		winner.setFont(font4);
		
		container.add(winner);
		container.add(header);

		UndoRedo.add(playerturn);
		UndoRedo.add(playercolor);
		UndoRedo.add(container);

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

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		//g2.setColor(Color.LIGHT_GRAY);
		// g2.drawRect(0, 0, 600, 600);
//	      for (SceneShape s : shapes)
//	      {
//	         s.draw(g2);
//	         if (s.isSelected())
//	            s.drawSelection(g2);
//	      }
	}

}