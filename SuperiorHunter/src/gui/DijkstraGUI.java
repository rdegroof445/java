package gui;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DijkstraGUI {

	//int numberRows = 100;
	//int numberColumns = 100;
	
	int rowHeight = 25;
	
	int columnWidth = 25;
	
	class MainWindow extends JFrame{
		
		int width = 1000;
		
		int height = 1000;
		
		public MainWindow() {
			
			setLayout(null);
			
			setSize(width, height);
			
			MainPanel mp = new MainPanel();
			
			mp.setLocation(50,50);
			
			add(mp);
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			setVisible(true);
			
		}
		
	}
	
	class MainPanel extends JPanel{
		
		int width = 800;
		
		int height = 800;
		
		MainPanel(){
			
			setLayout(null);
			
			setSize(width,height);
			
		}
		
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);
			
			g.setColor(Color.white);
			
			g.fillRect(0, 0, width, height);
			
			g.setColor(Color.BLACK);
			
			int rowCounter = 25;
			
			for(; rowCounter < height - rowHeight; rowCounter += rowHeight ) {
				
				int columnCounter = 25;
				
				for(; columnCounter < width - columnWidth; columnCounter += columnWidth) {
					
					g.drawLine(columnCounter, rowCounter, columnCounter + columnWidth, rowCounter);
					
					g.drawLine(columnCounter, rowCounter, columnCounter , rowCounter + rowHeight);
					
					g.drawLine(columnCounter, height - rowHeight, columnCounter + columnWidth, height - rowHeight);
					
				}
				
				System.out.println("Row Counter: " + rowCounter + " Column Counter: " + columnCounter);
					
				g.drawLine(width-columnWidth, rowCounter, width - columnWidth, rowCounter + rowHeight);
				
			}
			
			//g.drawString("Hello World", 125, 125);

		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new DijkstraGUI().new MainWindow();
		
	}

}
