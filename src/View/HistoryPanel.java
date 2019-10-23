import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * @author Justin Lo
 */
public class HistoryPanel extends JPanel {
	//set up back and forth arrows
	//set up list
	//set up player turn
	//set up reset game
	
	
	public HistoryPanel(){
		
		BoxLayout by= new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(by);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		
		JPanel PlayerTurn = new JPanel();
		PlayerTurn.setPreferredSize(new Dimension(200,100) );
		BoxLayout pt= new BoxLayout(PlayerTurn,BoxLayout.Y_AXIS);
		PlayerTurn.setLayout(pt);
		
		Font font1 = new Font("playerfont",Font.PLAIN,20);
		
		JLabel playerturn = new JLabel("Player's turn");
		playerturn.setPreferredSize(new Dimension(100,50));
		playerturn.setFont(font1);
		playerturn.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		PlayerTurn.add(playerturn);
		
		
		JPanel ButtonContainer = new JPanel();
		ButtonContainer.setLayout(new GridLayout(1,2));
		
		
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("undo");
			}

		});
		
		JButton redo = new JButton("Redo");
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("redo");
			}

		});
		
		ButtonContainer.add(undo);
		ButtonContainer.add(redo);
		ButtonContainer.setBackground(Color.WHITE);
		
		//PlayerTurn.add(ButtonContainer);
		
		this.add(PlayerTurn);
		//PlayerTurn.setBackground(Color.BLUE);
		
		JPanel UndoRedo = new JPanel();
		UndoRedo.setPreferredSize(new Dimension(200,100) );
		this.add(UndoRedo);
		UndoRedo.setBackground(Color.WHITE);
		
		BoxLayout h= new BoxLayout(UndoRedo,BoxLayout.Y_AXIS);
		UndoRedo.setLayout(h);
		
		JLabel header = new JLabel("Move History");
		header.setPreferredSize(new Dimension(100,50));
		header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Font font = new Font("integerfont",Font.PLAIN,20);
		header.setFont(font);
		
		UndoRedo.add(header);
		
		UndoRedo.add(ButtonContainer);
		
		JList list = new JList();
		//list.setBackground(Color.GREEN);
		list.setPreferredSize(new Dimension(200,500));
		//JList list = new JList(listModel);	
		//PeopleRenderer Render = new PeopleRenderer();
		//list.setCellRenderer(Render);
		list.setFixedCellHeight(100);
		list.setFixedCellWidth(200);
		this.add(list);
		
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200,700);
	}
	@Override
	public void paintComponent(Graphics g)
	   {
		
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
