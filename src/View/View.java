package View;

import Message.Message;
import javax.swing.*;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

public class View extends JFrame {
	private static JFrame mainFrame;
	private static BlockingQueue<Message> messageQueue;
	private static View view;

	/**
	 * Constructor: setup the MainFrame of the project with the messageQueue
	 */
	public View() {
		mainFrame = MainFrame.init(messageQueue);
	}

	/**
	 * Setup Frame for the Game
	 */
	public static View init(BlockingQueue<Message> queue) {
		if (view == null) {
			messageQueue = queue;
			view = new View();
		}
		return view;
	}

	/**
	 * Update the Board Panel according to the new Game Info passing from the Controller
	 *
	 * @param gameInfo: new info of the game.
	 */
	public void setBoardPanel(GameInfo gameInfo, String action) {
		SwingUtilities.invokeLater(() -> ((MainFrame) mainFrame).setBoardPanel(gameInfo));
	}

	/**
	 * Update the History Panel according to the new Game Info passing from the Controller
	 *
	 * @param gameInfo: new info of the game.
	 */
	public void setHistoryPanel(GameInfo gameInfo, String action) {
		SwingUtilities.invokeLater(() -> ((MainFrame) mainFrame).setHistoryPanel(gameInfo));
	}
}
