import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.util.Random;

import javax.swing.JFrame;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements MouseListener, ActionListener {

    private Timer timer;   
    private Image[] ii = new Image[14];
    private int  mouseX, mouseY, minesFound, minesNum;
    private Random randomGenerator = new Random();
    int randX, randY;
    
    public int[][] map ={
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},			    		
						};
    private int [][] mines = {
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
				    		{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},			    		
						};
    	
   
    public Board() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        timer = new Timer(5, this);
        timer.start();
        ii[0] =  (new ImageIcon(this.getClass().getResource("block.png"))).getImage();
        ii[1] =  (new ImageIcon(this.getClass().getResource("blockPressed.png"))).getImage();
        ii[2] =  (new ImageIcon(this.getClass().getResource("blockFlag.png"))).getImage();
        ii[3] =  (new ImageIcon(this.getClass().getResource("blockQuestionMark.png"))).getImage();
        ii[4] =  (new ImageIcon(this.getClass().getResource("0.png"))).getImage();
        ii[5] =  (new ImageIcon(this.getClass().getResource("1.png"))).getImage();
        ii[6] =  (new ImageIcon(this.getClass().getResource("2.png"))).getImage();
        ii[7] =  (new ImageIcon(this.getClass().getResource("3.png"))).getImage();
        ii[8] =  (new ImageIcon(this.getClass().getResource("4.png"))).getImage();
        ii[9] =  (new ImageIcon(this.getClass().getResource("5.png"))).getImage();
        ii[10] =  (new ImageIcon(this.getClass().getResource("6.png"))).getImage();
        ii[11] =  (new ImageIcon(this.getClass().getResource("7.png"))).getImage();
        ii[12] =  (new ImageIcon(this.getClass().getResource("8.png"))).getImage();
        ii[13] =  (new ImageIcon(this.getClass().getResource("bomb.png"))).getImage();
              
        addMouseListener(this);
        resetMap();

    }
    
    public void resetMap()
    {
    	for (int y=0;y<map.length;y++)
        {	
        	for (int x=0;x<map[0].length;x++)
        	{
        		map[y][x] = 0;
        		mines[y][x] = 0;
        	}
        }
    	
    	//place mines
    	minesNum = 10;
        for (int i=0; i<minesNum; i++)
        {
        	randX = randomGenerator.nextInt(10);
        	randY = randomGenerator.nextInt(10);
        	if (mines[randY][randX] == 0)
        	{
            	mines[randY][randX] = 1;
        	}
        	else i--;
        }
        
       
        mouseX = 0;
        mouseY = 0;
        randX = 0;
        randY = 0;
        minesFound = 0;


    }

    public void mouseEntered (MouseEvent mouseEvent) {} 
    public void mouseClicked (MouseEvent mouseEvent) {}  
    public void mouseExited (MouseEvent mouseEvent) {}  

    public void mousePressed (MouseEvent mouseEvent) 
    {
	    mouseX = mouseEvent.getX();
	    mouseY = mouseEvent.getY();
	   if  (map[mouseY/32][mouseX/32] == 0)
	   {
		    if (mouseEvent.getButton() == 1)
		    	map[mouseY/32][mouseX/32] = 1;
		    
		    if(mouseEvent.getButton() == 3)
		    {
		    	if (map[mouseY/32][mouseX/32] == 0)
		    	{
		    		if (mines[mouseY/32][mouseX/32] == 1)
		    		{
		    			minesFound++;
		    			if (minesFound == minesNum)
		    			{
		    				System.out.println("You win");
		    				((JFrame)(this.getParent().getParent().getParent().getParent())).setTitle("You win!");
	
		    			}
		    		}
		    		map[mouseY/32][mouseX/32] = 2;
		    	}
		    	else if (map[mouseY/32][mouseX/32] == 2)
		    	{
		    		if (mines[mouseY/32][mouseX/32] == 1)
		    			minesFound--;
		    		map[mouseY/32][mouseX/32] = 3;
		    	}
		    	else if (map[mouseY/32][mouseX/32] == 3)
		    	{
		    		map[mouseY/32][mouseX/32] = 0;
		    	}
		    }
	    	repaint();
	   }
    }
    
    public void mouseReleased (MouseEvent mouseEvent) 
    {
    	if (map[mouseY/32][mouseX/32] == 1 &&(((mouseX != mouseEvent.getX() || mouseY != mouseEvent.getY()) && mouseEvent.getButton() == 1) || mouseEvent.getButton() == 1))
    	{
    	    map[mouseY/32][mouseX/32] = 0;
    	}
    	mouseX = mouseEvent.getX();
 	    mouseY = mouseEvent.getY();
	       	
    	if (mines[mouseY/32][mouseX/32] == 1)	//Mine in square
    	{
    		if (mouseEvent.getButton() == 1)
    		{
    			System.out.println("Mine found!");
        		gameOver();
    		}
    	}
    	else									//Not a mine
    	{
    		map[mouseY/32][mouseX/32] = surroundingMines(mouseY/32, mouseX/32) + 4;
    		if (surroundingMines(mouseY/32, mouseX/32) == 0)
    		{
    			uncoverSurroundingZeros(mouseY/32, mouseX/32);
    		}
    	}
    } 
    
    private void uncoverSurroundingZeros(int inY, int inX)
    {
    	int[] yI = {-1,0,1};
    	int[] xI = {-1,0,1};

	
    		for (int xOff : xI)
    		{
    			for (int yOff : yI)
    			{
    				try
    				{
	    				if (surroundingMines(inY+yOff,inX+xOff) == 0)
	    				{
	    		    		if (map[inY+yOff][inX+xOff] == 0)
	    		    		{
	    		    			map[inY+yOff][inX+xOff] = surroundingMines(inY+yOff, inX+xOff) + 4;
	    		    			uncoverSurroundingZeros(inY+yOff,inX+ xOff);
	    		    		}
	    				}
	    		    	else
	    		    	{
	    		    		if (map[inY+yOff][inX+xOff] == 0)
	    		    		{
	    		    			map[inY+yOff][inX+xOff] = surroundingMines(inY+yOff, inX+xOff) + 4;
	    		    		}
	    		    	}
    				}
    				catch (ArrayIndexOutOfBoundsException e)
    		    	{
    		    		
    		    	}
    			}
    		}
    	
    }
    
    private int surroundingMines(int inY, int inX)
    {
    	int mineCount = 0;
    	
    	if (isMine(inY-1,inX-1))
    		mineCount++;
    	if (isMine(inY-1,inX))
    		mineCount++;
    	if (isMine(inY-1,inX+1))
    		mineCount++;
    	if (isMine(inY,inX-1))
    		mineCount++;
    	if (isMine(inY,inX+1))
    		mineCount++;
    	if (isMine(inY+1,inX-1))
    		mineCount++;
    	if (isMine(inY+1,inX))
    		mineCount++;
    	if (isMine(inY+1,inX+1))
    		mineCount++;
    	
    	return mineCount;
    }

    
    private boolean isMine (int inY, int inX)
    {
    	try
    	{
    		if (mines[inY][inX] == 1)
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
    }
    
    private void gameOver()
    {
    	for (int y=0;y<map.length;y++)
        {	
        	for (int x=0;x<map[0].length;x++)
        	{
        		if (map[y][x] == 0)
        		{
        			if (mines[y][x] == 1)
        			{
        				map[y][x] = 13;
        			}
        			else
        			{
		    			map[y][x] = surroundingMines(x,y) + 4;

        			}
        		}
        	}
        }
		((JFrame)(this.getParent().getParent().getParent().getParent())).setTitle("Game Over");
		((Skeleton)(this.getParent().getParent().getParent().getParent())).statusLabel.setText("Game Over");


    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
       
        for (int y=0;y<map.length;y++)
        {	
        	for (int x=0;x<map[0].length;x++)
        	{
        		g2d.drawImage(ii[map[y][x]], x*32 , y*32 , this);        			
        	}
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {
        repaint();  
    }


}