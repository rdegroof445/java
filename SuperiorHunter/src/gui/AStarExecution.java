package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dijkstra.AStar;
import dijkstra.AStar;
import dijkstra.Utilities;
import dijkstra.AStar.CompletePath;
import dijkstra.AStar.Node;
import dijkstra.HunterAStar;

import java.awt.image.*;

import java.io.*;

public class AStarExecution implements ImageObserver, MouseListener {

	int rowHeight = 25;
	
	int columnWidth = 25;
	
	int numberRows = -1;
	
	int numberColumns = 0;
	
	static AStarExecution execution;
	
	static MainWindow mainWindow = null;
	
	AStar playerPathAlgorithm = null;
	
	HunterAStar hunterPathAlgorithm = null;
	
	int hunterCharacterRow = 0;
	
	int hunterCharacterColumn = 0;
	
	int playerCharacterRow = 0;
	
	int playerCharacterColumn = 0;
	
	static Color litColor = Color.black;
	
	static Color unlitColor = Color.white;
	
	static boolean[][] displayGrid = null;
	
	static MainPanel mp = null;
	
	public void setHunterAStarDistanceMatrix(boolean[][] displayGrid, HunterAStar pathAlgorithm) {
		
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
	
	public void setPlayerAStarDistanceMatrix(boolean[][] displayGrid, AStar pathAlgorithm) {
		
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
	
	AStarExecution(File hunterCharacterImageFile, File playerCharacterImageFile, int rowHeight, int columnWidth, boolean[][] displayGrid){
		
		execution = new AStarExecution();
		
		execution.rowHeight = rowHeight;
		
		execution.columnWidth = columnWidth;
		
		execution.numberRows = displayGrid.length;
		
		if(displayGrid.length > 0) {
			
			execution.numberColumns = displayGrid[0].length;
			
		}
		
		//set player sprite into bottom right corner!
		
		execution.playerCharacterRow = (displayGrid.length - 1);
		
		execution.playerCharacterColumn = (displayGrid[0].length - 1);
		
		execution.playerPathAlgorithm = new AStar(rowHeight * columnWidth, execution.numberRows, execution.numberColumns, null);
		
		execution.playerPathAlgorithm.x = new AStar(rowHeight * columnWidth, execution.numberRows, execution.numberColumns, null);
		
		execution.hunterPathAlgorithm = new HunterAStar(rowHeight * columnWidth, execution.numberRows, execution.numberColumns, null);
		
		execution.hunterPathAlgorithm.x = new HunterAStar(rowHeight * columnWidth, execution.numberRows, execution.numberColumns, null);
		
		if(displayGrid.length > 0) {
			
			execution.displayGrid = new boolean[displayGrid.length][displayGrid[0].length];
			
			for(int rowCounter = 0; rowCounter < displayGrid.length; rowCounter++) {
				
				for(int columnCounter = 0; columnCounter < displayGrid[rowCounter].length; columnCounter++) {
					
					execution.displayGrid[rowCounter][columnCounter] = displayGrid[rowCounter][columnCounter];
					
				}
				
			}
			
		}
		
		try {
		
			execution.hunterCharacterImage = ImageIO.read(hunterCharacterImageFile);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			execution.playerCharacterImage = ImageIO.read(playerCharacterImageFile);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		mainWindow = new MainWindow();
		
		setPlayerAStarDistanceMatrix(displayGrid, execution.playerPathAlgorithm);
		
		setHunterAStarDistanceMatrix(displayGrid, execution.hunterPathAlgorithm);
		
	}
	
	private AStarExecution(){
		
		
		
	}
	
	BufferedImage hunterCharacterImage = null;
	
	BufferedImage playerCharacterImage = null;
	
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
			
			g.drawImage(execution.hunterCharacterImage, execution.columnWidth + (execution.columnWidth * execution.hunterCharacterColumn), execution.rowHeight + (execution.rowHeight * execution.hunterCharacterRow), execution.columnWidth * 2 + (execution.columnWidth * execution.hunterCharacterColumn),execution.rowHeight * 2 + (execution.rowHeight * execution.hunterCharacterRow), 0,0, execution.hunterCharacterImage.getWidth(), execution.hunterCharacterImage.getHeight(), null);
			
			g.drawImage(execution.playerCharacterImage, execution.columnWidth + (execution.columnWidth * execution.playerCharacterColumn), execution.rowHeight + (execution.rowHeight * execution.playerCharacterRow), execution.columnWidth * 2 + (execution.columnWidth * execution.playerCharacterColumn),execution.rowHeight * 2 + (execution.rowHeight * execution.playerCharacterRow), 0,0, execution.playerCharacterImage.getWidth(), execution.playerCharacterImage.getHeight(), null);
			
			int rowCounter = 0;
			
			for(; rowCounter < execution.displayGrid.length; rowCounter++) {
				
				for(int columnCounter = 0; columnCounter < execution.displayGrid[rowCounter].length; columnCounter++) {
					
					if((rowCounter != execution.hunterCharacterRow || columnCounter != execution.hunterCharacterColumn) && (rowCounter != execution.playerCharacterRow || columnCounter != execution.playerCharacterColumn)) {
						
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

		mainWindow = new AStarExecution().new MainWindow();
		
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
		
		if(!squareIsPlayerReachable(rowNumber, columnNumber, execution.playerCharacterRow, execution.playerCharacterColumn)) {
			
			return;
			
		}
		
		System.out.println("Mouse X Location: " + arg0.getX() + " : " + columnNumber + " Mouse Y Location: " + arg0.getY() + " : " + rowNumber);
		
		int originCellNumber = (execution.playerCharacterRow * execution.numberColumns) + execution.playerCharacterColumn;
		
		int destinationCellNumber = (rowNumber * execution.numberColumns) + columnNumber;
		
		Node originalNode = execution.playerPathAlgorithm.new Node(originCellNumber,0,destinationCellNumber,0.0,0.0,originCellNumber + "",new int[execution.numberRows*execution.numberColumns]);
		
		Node goalNode = execution.playerPathAlgorithm.new Node(destinationCellNumber,0,destinationCellNumber,0.0,0.0,destinationCellNumber + "",new int[execution.numberRows*execution.numberColumns]);
		
		execution.playerPathAlgorithm.goalNode = execution.playerPathAlgorithm.new Node(goalNode);
		
		CompletePath completePath = execution.playerPathAlgorithm.getPath(originalNode);
		
		String completePathString = completePath.toString();
		
		completePathString = completePathString.substring(completePathString.indexOf(":") + 1, completePathString.lastIndexOf(" Minimum Path:")).trim();
		
		String[] pathElements = completePathString.split(" : ");
		
		drawPlayerCellAnimation(pathElements);
		
		execution.playerPathAlgorithm.initMatrices();
		
		/**TODO: will calculation of hunter path delay hunter movement? Hunter Movement occurs after player movement? Same time?**/
		
		animateHunter(rowNumber, columnNumber);
		
	}
	
	public boolean squareIsPlayerReachable(int squareRow, int squareColumn, int playerRow, int playerColumn) {
		
		if(Math.abs(squareRow - playerRow) > 1) {
			
			// square is unreachable...inform the user
			
			JOptionPane.showMessageDialog(execution.mp, "Square is unreachable!");
			
			return false;
			
		}else if(Math.abs(squareColumn - playerColumn) > 1) {
			
			// square is unreachable...inform the user
			
			JOptionPane.showMessageDialog(execution.mp, "Square is unreachable!");
			
			return false;
			
		}else {
			
			// square is reachable
			
			return true;
			
		}
		
	}
	
	public void animateHunter(int rowNumber, int columnNumber) {
		
		int originCellNumber = (execution.hunterCharacterRow * execution.numberColumns) + execution.hunterCharacterColumn;
		
		int destinationCellNumber = (rowNumber * execution.numberColumns) + columnNumber;
		
		HunterAStar.Node originalNode = execution.hunterPathAlgorithm.new Node(originCellNumber,0,destinationCellNumber,0.0,0.0,originCellNumber + "",new int[execution.numberRows*execution.numberColumns]);
		
		HunterAStar.Node goalNode = execution.hunterPathAlgorithm.new Node(destinationCellNumber,0,destinationCellNumber,0.0,0.0,destinationCellNumber + "",new int[execution.numberRows*execution.numberColumns]);
		
		execution.hunterPathAlgorithm.goalNode = execution.hunterPathAlgorithm.new Node(goalNode);
		
		HunterAStar.CompletePath completePath = execution.hunterPathAlgorithm.getPath(originalNode);
		
		String completePathString = completePath.toString();
		
		completePathString = completePathString.substring(completePathString.indexOf(":") + 1, completePathString.lastIndexOf(" Minimum Path:")).trim();
		
		System.out.println("Animate Hunter Complete Path String: " + completePathString);
		
		String[] pathElements = completePathString.split(" : ");
		
		drawHunterCellAnimation(pathElements);
		
		execution.hunterPathAlgorithm.initMatrices();
		
	}
	
	public int[] getXYFromCellNumber(int cellNumber) {
		
		int[] returnValues = new int[2];
		
		returnValues[0] = cellNumber / execution.numberColumns;
		
		returnValues[1] = cellNumber % execution.numberColumns;
		
		return returnValues;
		
	}
	
	public void drawHunterCellAnimation(String[] pathElements) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Graphics g = execution.mp.getGraphics();
				
				for(int pathElementCounter = 0; pathElementCounter < 2; pathElementCounter++) {
					
					System.out.println("Path Element: " + pathElements[pathElementCounter]);
					
					int cellNumber = Integer.parseInt(pathElements[pathElementCounter]);
					
					int[] xyFromCell = getXYFromCellNumber(cellNumber);
					
					g.setColor(Color.white);
					
					int cellRow = xyFromCell[0];
					
					int cellColumn = xyFromCell[1];
					
					execution.hunterCharacterRow = cellRow;
					
					execution.hunterCharacterColumn = cellColumn;
					
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
	
	public void drawPlayerCellAnimation(String[] pathElements) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Graphics g = execution.mp.getGraphics();
				
				for(int pathElementCounter = 0; pathElementCounter < 2; pathElementCounter++) {
					
					System.out.println("Path Element: " + pathElements[pathElementCounter]);
					
					int cellNumber = Integer.parseInt(pathElements[pathElementCounter]);
					
					int[] xyFromCell = getXYFromCellNumber(cellNumber);
					
					g.setColor(Color.white);
					
					int cellRow = xyFromCell[0];
					
					int cellColumn = xyFromCell[1];
					
					execution.playerCharacterRow = cellRow;
					
					execution.playerCharacterColumn = cellColumn;
					
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
