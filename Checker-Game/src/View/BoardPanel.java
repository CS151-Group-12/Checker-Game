package View;

import Message.*;
import Model.PieceType;
import Model.Tile;

import java.awt.Color;
import java.awt.Dimension;
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


	public BoardPanel(BlockingQueue<Message> queue) {
		messageQueue = queue;
		board = new Tile[8][8];
		updateBoard(board);
	}
	
	public void updateBoard(Tile[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Point p = new Point((75 * i) + 50, (75 * j) + 1);
				boolean b = (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0);

				if (j >= 5 && b)
					board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
				else if (j < 3 && b)
					board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
				else {
					if (b)
						board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
					else
						board[i][j] = new Tile(p, Color.WHITE, 75, false, PieceType.NONE);
				}
			}
		}
	}

	/**
	 * Draw the Board Panel with the TileLogic. TileLogic will draw the piece on top of it.
	 */
	public void updateBoard(GameInfo gameInfo) {
		board = gameInfo.getBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Point p = new Point((75 * i) + 50, (75 * j) + 1);
				boolean b = (i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0);

				if (j >= 5 && b)
					board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE); //true/false determines highlight
				else if (j < 3 && b)
					board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE);
				else {
					if (b)
						board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.NONE); //true/false determines highlight
					else

						board[i][j] = new Tile(p, Color.WHITE, 75, false, PieceType.NONE);



				}
			}
		}
	}

//	public class newGameActionListener implements ActionListener {
//		public void actionPerformed(ActionEvent event) {
//			try {
//				messageQueue.put(new NewGameMessage());
//			} catch (InterruptedException exception) {
//				exception.printStackTrace();
//			}
//		}
//	}

	private class MousePressedListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			try {
				p = e.getPoint();
				if (e.getPoint().x >= 50 && e.getPoint().y >= 1) {
					int x = (e.getPoint().x - 50) / 75;
					int y = (e.getPoint().y - 1) / 75;

					System.out.println(x + " " + y);
					if (x < 8 && y < 8) {
						board[x][y].setHighlight(!board[x][y].getHighlight());
					}
				}
				messageQueue.put(new ShowHighlightMessage());
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 *
	 * @param gameInfo
	 */
	public void setBoardPanel(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		updateBoard(gameInfo);
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
	public Dimension getPreferredSize() {
		return new Dimension(600, 700);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);

		g2.fillRect(0, 0, 3000, 3000);

		g2.setColor(Color.BLACK);
		// g2.drawLine(0, 1, 3000,1);

		//Drawing the Board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].draw(g2);
			}
		}

		//Drawing the Integers
		g2.setColor(Color.DARK_GRAY);
		Font font = new Font("integerfont", Font.BOLD, 24);
		g2.setFont(font);
		g2.drawRect(50, 1, 600, 600);
		for (int x = 1; x < 9; x++) {
			g2.setColor(Color.BLACK);
			g2.drawString(Integer.toString(x), 15, (x * 75) - 25);

		}

		//Drawing the Letters
		int ascciHvalue = 64;
		for (int x = 1; x < 9; x++) {
			g2.setColor(Color.BLACK);
			g2.drawString("" + (char) (ascciHvalue + x), (x * 75) + 5, 635);
		}

		//Drawing the pieces

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void changePiece(Point p) {
		board[p.x][p.y].setStatus(PieceType.BLACKKING);
		this.repaint();
	}

	/**
	 * Refresh the Board
	 */
	public void refresh() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				boolean b = i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0;
				Point p = new Point((75 * i) + 50, (75 * j) + 1);
				if (j < 3 && b)
					board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.REDPIECE); //true/false determines highlight
				if (j > 4 && b) board[i][j] = new Tile(p, Color.LIGHT_GRAY, 75, false, PieceType.BLACKPIECE);
			}
		}
		this.repaint();
	}
}
