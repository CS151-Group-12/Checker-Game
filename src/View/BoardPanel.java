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

				if (x < 8 && y < 8) {
					// If click on highlight square => move
					Tile tile = board[y][x];
					CheckersPiece currentPiece = tile.getCp();

					// Check if user already click a piece
					if (gameInfo.getSelectedPiece() != null) {
						// click a new tile that has a checker => turn off the highglight
						if (tile.containsChecker()) {
							messageQueue.put(new ShowHighlightMessage(currentPiece));
						} else {
							// Click a new tile that doensn't have a checker -> move the piece
							messageQueue.put(new MoveMessage(gameInfo.getSelectedPiece(), tile));
						}
					}
					// If user is a firstClick => show highlight of that piece
					else {
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
			g2.drawString(Integer.toString(x - 1), 15, (x * 75) - 25);
		}

		//Drawing the Letters
//		int ascciHvalue = 64;
		for (int x = 1; x < 9; x++) {
			g2.setColor(Color.BLACK);
			g2.drawString("" + (x - 1), (x * 75) + 5, 635);
		}
	}
}
