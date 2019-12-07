//package View;
//
//import Message.*;
//import Model.*;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.concurrent.BlockingQueue;
//
//import javax.swing.JPanel;
//
//public class BoardPanel extends JPanel implements MouseListener {
//	private Tile[][] board;
//	private BlockingQueue<Message> messageQueue;
//	private GameInfo gameInfo;
//	private Point p;
//
//
//	/**
//	 * Constructor
//	 * @param queue
//	 */
//	public BoardPanel(BlockingQueue<Message> queue) {
//		messageQueue = queue;
//		board = new Tile[8][8];
//		updateBoard(board);
//		addMouseListener(this);
//	}
//
//	/**
//	 * Update the Board
//	 * @param board
//	 */
//	public void updateBoard(Tile[][] board) {
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				p = new Point((75 * i) + 50, (75 * j) + 1);
//				boolean b = (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0);
//
//				char col = (char) (i + 65);
//
//				if (j >= 5 && b)
//					board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
//				else if (j < 3 && b)
//					board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
//				else {
//					if (b)
//						board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
//					else
//						board[i][j] = new Tile(j, col, p, Color.WHITE, 75, false, PieceType.NONE);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		try {
//			System.out.println("View clicked");
//			p = e.getPoint();
//			if (e.getPoint().x >= 50 && e.getPoint().y >= 1) {
//				int x = (e.getPoint().x - 50) / 75;
//				int y = (e.getPoint().y - 1) / 75;
//
//				System.out.println(x + " " + y);
//				if (x < 8 && y < 8) {
//					board[x][y].setHighlight(!board[x][y].getHighlight());
//				}
//			}
//			messageQueue.put(new ShowHighlightMessage());
//			System.out.println("Controller?");
//
//		} catch (InterruptedException exception) {
//			System.out.println("Failed controller");
//			exception.printStackTrace();
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//
//	}
//
//	/**
//	 *
//	 * @param gameInfo
//	 */
//	public void setBoardPanel(GameInfo gameInfo) {
//		this.gameInfo = gameInfo;
//		board = gameInfo.getBoard();
////		updateBoard(gameInfo);
//		System.out.println("View update the new board with highlight");
//		repaint();
//		System.out.println();
//	}
//
//	/**
//	 *
//	 * @param squares
//	 */
////	public void highlightSquares(ArrayList<Point> squares) {
////		for (Point p : squares) {
////			board[p.x][p.y].setHighlight(true);
////		}
////	}
//	public void highlightSquares(){
//
//	}
//
//	/**
//	 *
//	 * @param p
//	 * @return
//	 */
//	public Point getCenter(Point p) {
//		int x = p.x - (25 / 2);
//		int y = p.y - (25 / 2);
//
//		return new Point(x, y);
//	}
//
//	@Override
//	public void paintComponent(Graphics g) {
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setColor(Color.WHITE);
//
//		g2.fillRect(0, 0, 3000, 3000);
//
//		g2.setColor(Color.BLACK);
//
//		//Drawing the Board
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				board[i][j].draw(g2);
//				System.out.println(board[i][j]);
//				board[i][j].addMouseListener(this);
//			}
//		}
//
//		//Drawing the Integers
//		g2.setColor(Color.DARK_GRAY);
//		Font font = new Font("integerfont", Font.BOLD, 24);
//		g2.setFont(font);
//		g2.drawRect(50, 1, 600, 600);
//		for (int x = 8; x >= 1; x--) {
//			g2.setColor(Color.BLACK);
//			g2.drawString(Integer.toString(x), 15, (x * 75) - 25);
//
//		}
//
//		//Drawing the Letters
//		int ascciHvalue = 64;
//		for (int x = 1; x < 9; x++) {
//			g2.setColor(Color.BLACK);
//			g2.drawString("" + (char) (ascciHvalue + x), (x * 75) + 5, 635);
//		}
//	}
//
//	/**
//	 * Refresh the Board
//	 */
//	public void refresh() {
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board.length; j++) {
//				boolean b = i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0;
//				Point p = new Point((75 * i) + 50, (75 * j) + 1);
//				char col = (char) (i + 65);
//				if (j < 3 && b)
//					board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE); //true/false determines highlight
//				if (j > 4 && b) board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE);
//			}
//		}
//		this.repaint();
//	}
//}
package View;

import Message.*;
import Model.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;

public class BoardPanel extends JPanel implements MouseListener {
	private Tile[][] board;
	private BlockingQueue<Message> messageQueue;
	private GameInfo gameInfo;
	private Point p;


	/**
	 * Constructor
	 * @param queue
	 */
	public BoardPanel(BlockingQueue<Message> queue) {
		messageQueue = queue;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			p = e.getPoint();
			if (e.getPoint().x >= 50 && e.getPoint().y >= 1) {
				int x = (e.getPoint().x - 50) / 75;
				int y = (e.getPoint().y - 1) / 75;

				System.out.println(x + " " + y);
				if (x < 8 && y < 8) {
					// If click on highlight square => move
					CheckersPiece currentPiece = board[x][y].getCp();
					if (board[x][y].isHighlight()) {
						messageQueue.put(new MoveMessage(currentPiece, gameInfo.getSelectedPiece()));
					} else {
						messageQueue.put(new ShowHighlightMessage(currentPiece));
					}
				}
			}
		} catch (InterruptedException exception) {
			System.out.println("Failed controller");
			exception.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	/**
	 *
	 * @param gameInfo
	 */
	public void setBoardPanel(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		board = gameInfo.getBoard();
		repaint();
	}

	/**
	 *
	 * @param squares
	 */
	public void highlightSquares(ArrayList<Point> squares) {
		for (Point p : squares) {
			board[p.x][p.y].setHighlight(true);
		}
	}

	/**
	 *
	 * @param p
	 * @return
	 */
	public Point getCenter(Point p) {
		int x = p.x - (25 / 2);
		int y = p.y - (25 / 2);

		return new Point(x, y);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);

		g2.fillRect(0, 0, 3000, 3000);

		g2.setColor(Color.BLACK);

		//Drawing the Board
		if (board != null) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					board[i][j].draw(g2);
					System.out.println(board[i][j]);
					board[i][j].addMouseListener(this);
				}
			}
		}


		//Drawing the Integers
		g2.setColor(Color.DARK_GRAY);
		Font font = new Font("integerfont", Font.BOLD, 24);
		g2.setFont(font);
		g2.drawRect(50, 1, 600, 600);
		for (int x = 8; x >= 1; x--) {
			g2.setColor(Color.BLACK);
			g2.drawString(Integer.toString(x), 15, (x * 75) - 25);

		}

		//Drawing the Letters
		int ascciHvalue = 64;
		for (int x = 1; x < 9; x++) {
			g2.setColor(Color.BLACK);
			g2.drawString("" + (char) (ascciHvalue + x), (x * 75) + 5, 635);
		}
	}

	/**
	 * Refresh the Board
	 */
	public void refresh() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				boolean b = i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0;
				Point p = new Point((75 * i) + 50, (75 * j) + 1);
				char col = (char) (i + 65);
				if (j < 3 && b)
					board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE); //true/false determines highlight
				if (j > 4 && b) board[i][j] = new Tile(j, col, p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE);
			}
		}
		this.repaint();
	}
}