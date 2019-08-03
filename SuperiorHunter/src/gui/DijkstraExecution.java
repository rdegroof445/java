package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dijkstra.Dijkstra;
import dijkstra.Utilities;
import dijkstra.Dijkstra.CompletePath;
import dijkstra.Dijkstra.Node;

import java.awt.image.*;

import java.io.*;

public class DijkstraExecution implements ImageObserver, MouseListener {
	
	int rowHeight = 25;
	
	int columnWidth = 25;
	
	int numberRows = -1;
	
	int numberColumns = -1;
	
	static DijkstraExecution execution;
	
	static MainWindow mainWindow = null;
	
	Dijkstra pathAlgorithm = null;
	
	int characterRow = 0;
	
	int characterColumn = 0;
	
	static Color litColor = Color.black;
	
	static Color unlitColor = Color.white;
	
	static boolean[][] displayGrid = null;
	
	static MainPanel mp = null;
	
	public void setDijkstraDistanceMatrix(boolean[][] displayGrid, Dijkstra pathAlgorithm) {
		
		pathAlgorithm.initMatrices();
		
		pathAlgorithm.distanceMatrix = Utilities.initializeDistanceMatrix(pathAlgorithm.distanceMatrix);
		
		int numberRows = displayGrid.length;
		
		int numberColumns = displayGrid[0].length;
		
		execution.numberRows = numberRows;
		
		execution.numberColumns = numberColumns;
		
		for(int rowCounter = 0; rowCounter < numberRows; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < numberColumns; columnCounter++) {
				
				if(!displayGrid[rowCounter][columnCounter]) {
					
					int cellNumber = (rowCounter * numberColumns) + columnCounter;
					
					if(rowCounter >= 1) {
						
						if(!displayGrid[rowCounter-1][columnCounter]) {
							
							int oppositeCellNumber = ((rowCounter - 1) * numberColumns) + columnCounter;
							
							
							pathAlgorithm.distanceMatrix[cellNumber][oppositeCellNumber] = 1;
							
							pathAlgorithm.distanceMatrix[oppositeCellNumber][cellNumber] = 1;
							
						}
						
					}
					
					if(rowCounter < (numberRows - 1)) {
						
						if(!displayGrid[rowCounter + 1][columnCounter]) {
							
							int oppositeCellNumber = ((rowCounter + 1) * numberColumns) + columnCounter;
							
							pathAlgorithm.distanceMatrix[cellNumber][oppositeCellNumber] = 1;
							
							pathAlgorithm.distanceMatrix[oppositeCellNumber][cellNumber] = 1;
							
						}
						
					}
					
					if(columnCounter >= 1) {
						
						if(!displayGrid[rowCounter][columnCounter - 1]) {
							
							int oppositeCellNumber = ((rowCounter) * numberColumns) + (columnCounter - 1);
							
							pathAlgorithm.distanceMatrix[cellNumber][oppositeCellNumber] = 1;
							
							pathAlgorithm.distanceMatrix[oppositeCellNumber][cellNumber] = 1;
							
						}
						
					}
					
					if(columnCounter < (numberColumns - 1)) {
						
						if(!displayGrid[rowCounter][columnCounter + 1]) {
							
							int oppositeCellNumber = ((rowCounter) * numberColumns) + (columnCounter + 1);
							
							pathAlgorithm.distanceMatrix[cellNumber][oppositeCellNumber] = 1;
							
							pathAlgorithm.distanceMatrix[oppositeCellNumber][cellNumber] = 1;
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	DijkstraExecution(File characterImageFile, int rowHeight, int columnWidth, boolean[][] displayGrid){
		
		execution = new DijkstraExecution();
		
		execution.rowHeight = rowHeight;
		
		execution.columnWidth = columnWidth;
		
		execution.pathAlgorithm = new Dijkstra(rowHeight * columnWidth);
		
		if(displayGrid.length > 0) {
			
			execution.displayGrid = new boolean[displayGrid.length][displayGrid[0].length];
			
			for(int rowCounter = 0; rowCounter < displayGrid.length; rowCounter++) {
				
				for(int columnCounter = 0; columnCounter < displayGrid[rowCounter].length; columnCounter++) {
					
					execution.displayGrid[rowCounter][columnCounter] = displayGrid[rowCounter][columnCounter];
					
				}
				
			}
			
		}
		
		try {
		
			execution.characterImage = ImageIO.read(characterImageFile);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		mainWindow = new MainWindow();
		
		setDijkstraDistanceMatrix(displayGrid, execution.pathAlgorithm);
		
	}
	
	private DijkstraExecution(){
		
		
		
	}
	
	BufferedImage characterImage = null;
	
	class MainWindow extends JFrame{
		
		int width = 1000;
		
		int height = 1000;
		
		public MainWindow() {
			
			setLayout(null);
			
			setSize(width, height);
			
			execution.mp = new MainPanel();
			
			execution.mp.setLocation(50,50);
			
			execution.mp.addMouseListener(execution);
			
			add(execution.mp);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
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
			
			g.drawImage(execution.characterImage, execution.columnWidth + (execution.columnWidth * execution.characterColumn), execution.rowHeight + (execution.rowHeight * execution.characterRow), execution.columnWidth * 2 + (execution.columnWidth * execution.characterColumn),execution.rowHeight * 2 + (execution.rowHeight * execution.characterRow), 0,0, execution.characterImage.getWidth(), execution.characterImage.getHeight(), null);
			
			int rowCounter = 0;
			
			for(; rowCounter < execution.displayGrid.length; rowCounter++) {
				
				for(int columnCounter = 0; columnCounter < execution.displayGrid[rowCounter].length; columnCounter++) {
					
					if(rowCounter != execution.characterRow || columnCounter != execution.characterColumn) {
						
						if(execution.displayGrid[rowCounter][columnCounter]) {
							
							g.setColor(litColor);

							g.fillRect(((columnCounter + 1)*execution.columnWidth), ((rowCounter + 1) * execution.rowHeight), execution.columnWidth, execution.rowHeight);
							
						}else {
							
							g.setColor(unlitColor);
							
							g.fillRect(((columnCounter + 1)*execution.columnWidth), ((rowCounter + 1) * execution.rowHeight), execution.columnWidth, execution.rowHeight);
							
						}
						
					}
					
				}
				
			}
			
			g.setColor(Color.BLACK);
			
			rowCounter = execution.rowHeight;
			
			for(; rowCounter < height - execution.rowHeight; rowCounter += execution.rowHeight ) {
				
				int columnCounter = execution.columnWidth;
				
				for(; columnCounter < width - execution.columnWidth; columnCounter += execution.columnWidth) {
					
					g.drawLine(columnCounter, rowCounter, columnCounter + execution.columnWidth, rowCounter);
					
					g.drawLine(columnCounter, rowCounter, columnCounter , rowCounter + execution.rowHeight);
					
					g.drawLine(columnCounter, height - execution.rowHeight, columnCounter + execution.columnWidth, height - execution.rowHeight);
					
				}
				
				g.drawLine(width-execution.columnWidth, rowCounter, width - execution.columnWidth, rowCounter + execution.rowHeight);
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		mainWindow = new DijkstraExecution().new MainWindow();
		
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("Column Width: " + execution.columnWidth + " : " + execution.rowHeight);
		
		int columnNumber = (arg0.getX()-execution.columnWidth) / (execution.columnWidth);
		
		int rowNumber = (arg0.getY()-execution.rowHeight) / (execution.rowHeight);
		
		if(execution.displayGrid[rowNumber][columnNumber]) {
			
			return;
			
		}
		
		System.out.println("Mouse X Location: " + arg0.getX() + " : " + columnNumber + " Mouse Y Location: " + arg0.getY() + " : " + rowNumber);
		
		int originCellNumber = (execution.characterRow * execution.numberColumns) + execution.characterColumn;
		
		int destinationCellNumber = (rowNumber * execution.numberColumns) + columnNumber;
		
		Node originalNode = execution.pathAlgorithm.new Node(originCellNumber,0,destinationCellNumber,0.0,0.0,originCellNumber + "",new int[execution.numberRows*execution.numberColumns]);
		
		CompletePath completePath = execution.pathAlgorithm.getPath(originalNode);
		
		String completePathString = completePath.toString();
		
		completePathString = completePathString.substring(completePathString.indexOf(":") + 1, completePathString.lastIndexOf(" Minimum Path:")).trim();
		
		String[] pathElements = completePathString.split(" : ");
		
		drawCellAnimation(pathElements);
		
		execution.pathAlgorithm.initMatrices();
		
	}
	
	public int[] getXYFromCellNumber(int cellNumber) {
		
		int[] returnValues = new int[2];
		
		returnValues[0] = cellNumber / execution.numberColumns;
		
		returnValues[1] = cellNumber % execution.numberColumns;
		
		return returnValues;
		
	}
	
	public void drawCellAnimation(String[] pathElements) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Graphics g = execution.mp.getGraphics();
				
				for(int pathElementCounter = 0; pathElementCounter < pathElements.length; pathElementCounter++) {
					
					int cellNumber = Integer.parseInt(pathElements[pathElementCounter]);
					
					int[] xyFromCell = getXYFromCellNumber(cellNumber);
					
					g.setColor(Color.white);
					
					int cellRow = xyFromCell[0];
					
					int cellColumn = xyFromCell[1];
					
					execution.characterRow = cellRow;
					
					execution.characterColumn = cellColumn;
					
					execution.mp.repaint();
					
					try {
						
						Thread.sleep(1000);
						
					}catch(Exception e) {
						
						e.printStackTrace();
						
					}
					
				}
				
			}
			
		}).start();
		
	}
	
}
