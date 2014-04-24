import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Skeleton extends JFrame {
	Board myBoard;
    JPanel statusBar;
    JLabel statusLabel;

	
    public Skeleton() {
    	myBoard = new Board();
    	statusBar = new JPanel();
    	setLayout(new BorderLayout());
        add(myBoard);
              
        
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusLabel = new JLabel("my status");
        statusLabel.setSize(10,10);
        statusBar.setSize(10,10);
        statusBar.add(statusLabel);
        add(statusBar, BorderLayout.SOUTH);
        
        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(32*myBoard.map[0].length + 6, 32*myBoard.map.length + 81);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem exitGame = new JMenuItem("Exit");
		newGame.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
            	setTitle("Minesweeper");
            	myBoard.resetMap();           	
            }
        });
		exitGame.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
            	System.exit(0);           	
            }
        });
		file.add(newGame);
		file.add(exitGame);
		menubar.add(file);
		setJMenuBar(menubar);

    }
    public static void main(String[] args) {
        new Skeleton();
    }
}