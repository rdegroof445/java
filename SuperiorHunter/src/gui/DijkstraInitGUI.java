package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DijkstraInitGUI implements MouseListener {

	static Color litColor = Color.black;
	
	static Color unlitColor = Color.white;
	
	static DijkstraInitGUI digui = new DijkstraInitGUI();
	
	static String startingFileChooserDirectory = "D:\\eclipse\\DijkstraGUI\\";
	
	static DijkstraExecution dijkstraDisplay = null;
	
	static AStarExecution aStarDisplay = null;
	
	int rowHeight = 100;
	
	int columnWidth = 100;
	
	int mainPanelWidth = 800;
	
	int mainPanelHeight = 800;
	
	int numberRowUnits = (mainPanelHeight - rowHeight * 2)/rowHeight;
	
	int numberColumnUnits = (mainPanelWidth - columnWidth * 2)/columnWidth;
	
	static JRadioButton dijkstraAlgorithm = new JRadioButton("Dijkstra");
	
	static JRadioButton aStarAlgorithm = new JRadioButton("A*");
	
	static ButtonGroup algorithmButtonGroup = new ButtonGroup();
	
	boolean[][] displayGrid = new boolean[numberRowUnits][numberColumnUnits];
	
	MainPanel mp = new MainPanel();
	
	static MainWindow mw = null;
	
	class MainWindow extends JFrame implements ActionListener{
		
		int width = 1250;
		
		int height = 1000;
		
		JMenuBar rootMenuBar = null;
		
		JMenu fileMenu = null;
		
		JMenuItem loadMenuItem = null;
		
		JMenuItem saveMenuItem = null;
		
		JMenuItem runMenuItem = null;
		
		public MainWindow() {

			setLayout(null);
			
			rootMenuBar = new JMenuBar();
			
			fileMenu = new JMenu("File");
			
			loadMenuItem = new JMenuItem("Load");
			
			loadMenuItem.addActionListener(this);
			
			saveMenuItem = new JMenuItem("Save");
			
			saveMenuItem.addActionListener(this);
			
			runMenuItem = new JMenuItem("Run");
			
			runMenuItem.addActionListener(this);
			
			fileMenu.add(loadMenuItem);
			
			fileMenu.add(saveMenuItem);
			
			fileMenu.add(runMenuItem);
			
			rootMenuBar.add(fileMenu);
			
			setJMenuBar(rootMenuBar);
			
			setSize(width, height);
			
			mp.setLocation(50,50);
			
			mp.addMouseListener(digui);
			
			add(mp);
			
			algorithmButtonGroup.add(dijkstraAlgorithm);
			
			algorithmButtonGroup.add(aStarAlgorithm);
			
			aStarAlgorithm.setSelected(true);
			
			dijkstraAlgorithm.setLocation(1000, 250);
			
			aStarAlgorithm.setLocation(1000, 275);
			
			dijkstraAlgorithm.setSize(125, 25);
			
			aStarAlgorithm.setSize(125, 25);
			
			add(dijkstraAlgorithm);
			
			add(aStarAlgorithm);
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			setVisible(true);
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if(arg0.getSource() == loadMenuItem) {
				
				copyBooleanToObject(loadFile());
				
				mp.repaint();
			
			}else if(arg0.getSource() == saveMenuItem) {
				
				System.out.println("Save Menu Item!");
			
				if(saveFile()) {
					
					//JOptionPane.showMessageDialog(this, "File Successfully Saved!");
					
				}else {
					
					JOptionPane.showMessageDialog(this, "Error Saving File!");
					
				}
				
			}else if(arg0.getSource() == runMenuItem) {
				
				try {
					
					int chooserOption = -1;
					
					JFileChooser characterImageFilePathChooser = new JFileChooser();
					
					characterImageFilePathChooser.setCurrentDirectory(new File("."));
					
					characterImageFilePathChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					characterImageFilePathChooser.setMultiSelectionEnabled(true);
					
					chooserOption = characterImageFilePathChooser.showOpenDialog(this);
					
					if(chooserOption == JFileChooser.APPROVE_OPTION) {
						
						File[] characterImageFiles = characterImageFilePathChooser.getSelectedFiles();
						
						if(aStarAlgorithm.isSelected()) {
							
							if(characterImageFiles.length != 2) {
								
								JOptionPane.showMessageDialog(this, "Select two files: File 1 -> Hunter Image File (JPG), File 2 -> Player Image File (JPG)");
								
								return;
								
							}
							
							if(aStarDisplay == null) {
									
									aStarDisplay = new AStarExecution(characterImageFiles[0], characterImageFiles[1], digui.rowHeight, digui.columnWidth, digui.displayGrid);
									
								}else {
									
									aStarDisplay = new AStarExecution(characterImageFiles[0], characterImageFiles[1], digui.rowHeight, digui.columnWidth, digui.displayGrid);
									
								}
							
						}else if(dijkstraAlgorithm.isSelected()) {
							
							if(characterImageFiles.length > 1) {
								
								JOptionPane.showMessageDialog(this, "Select one file: File 1 -> Hunter Image File (JPG)");
								
								return;
								
							}
							
								if(dijkstraDisplay == null) {
									
									//first run of the display window
									
									dijkstraDisplay = new DijkstraExecution(characterImageFiles[0], digui.rowHeight, digui.columnWidth, digui.displayGrid);
									
								}else {
									
									dijkstraDisplay = new DijkstraExecution(characterImageFiles[0], digui.rowHeight, digui.columnWidth, digui.displayGrid);
									
								}
							
						}
						
					}
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		}
		
		public void copyBooleanToObject(boolean[][] src) {
			
			if(src != null) {
				
				digui.rowHeight = digui.mainPanelHeight / (src.length + 2);
				
				if(src.length > 0) {
					
					digui.columnWidth = digui.mainPanelWidth/ (src[0].length + 2);
					
					digui.displayGrid = new boolean[src.length][src[0].length];
					
					digui.numberRowUnits = (digui.mainPanelHeight - digui.rowHeight * 2)/digui.rowHeight;
					
					digui.numberColumnUnits = (digui.mainPanelWidth - digui.columnWidth * 2)/digui.columnWidth;
					
				}else {
					
					return;
					
				}
				
				for(int rowCounter = 0; rowCounter < src.length; rowCounter++) {
					
					System.arraycopy(src, 0, digui.displayGrid, 0, src[rowCounter].length);
					
				}
				
			}
			
		}
		
		public boolean[][] loadFile(){
			
			boolean[][] displayGrid = null;
			
			List<boolean[]> gridList = new ArrayList<boolean[]>();
			
			try {
				
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setCurrentDirectory(new File(startingFileChooserDirectory));
				
				int numberOfColumns = -1;
				
				int chooserResponse = -1;
				
				chooserResponse = fileChooser.showOpenDialog(this);
				
				if(chooserResponse == JFileChooser.APPROVE_OPTION) {
					
					File inputFile = fileChooser.getSelectedFile();
					
					BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
					
					String inputLine = "";
					
					while((inputLine = inputReader.readLine()) != null) {
						
						String[] lineArray = inputLine.split(",");
						
						if(numberOfColumns == -1) {
							
							numberOfColumns = lineArray.length;
							
						}else if(numberOfColumns != lineArray.length) {
							
							System.out.println("Line Columns do not match!");
							
							return null;
							
						}
						
						boolean[] lineBooleanArray = new boolean[lineArray.length]; 
						
						for(int lineBooleanArrayCounter = 0; lineBooleanArrayCounter < lineBooleanArray.length; lineBooleanArrayCounter++) {
							
							if(lineArray[lineBooleanArrayCounter].equals("1")) {
								
								lineBooleanArray[lineBooleanArrayCounter] = true;
								
							}else {
								
								lineBooleanArray[lineBooleanArrayCounter] = false;
								
							}
							
						}
						
						gridList.add(lineBooleanArray);
						
					}
					
					displayGrid = new boolean[gridList.size()][numberOfColumns];
					
					for(int gridListCounter = 0; gridListCounter < gridList.size(); gridListCounter++) {
					
						System.arraycopy(gridList.get(gridListCounter), 0, displayGrid[gridListCounter], 0, displayGrid[gridListCounter].length);
					
					}
					
				}
				
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				
			}
			
			return displayGrid;
			
		}
			
		
		
		public boolean saveFile() {
			
			try {
				
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setCurrentDirectory(new File(startingFileChooserDirectory));
				
				int chooserResponse = -1;
				
				chooserResponse = fileChooser.showSaveDialog(this);
				
				if(chooserResponse == JFileChooser.APPROVE_OPTION) {
					
					File outputFile = fileChooser.getSelectedFile();
					
					BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile));
					
					for(int rowCounter = 0; rowCounter < digui.displayGrid.length; rowCounter++) {
						
						for(int columnCounter = 0; columnCounter < digui.displayGrid[rowCounter].length; columnCounter++) {
							
							if(columnCounter != 0) {
								
								outputWriter.write(",");
								
							}
							
							if(digui.displayGrid[rowCounter][columnCounter]) {
								
								outputWriter.write("1");
								
							}else {
								
								outputWriter.write("0");
								
							}
							
						}
						
						outputWriter.newLine();
						
					}
					
					outputWriter.close();
					
					return true;
					
				}
				
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				
			}
			
			return false;
			
		}
		
	}
	
	class MainPanel extends JPanel{
		
		
		
		MainPanel(){
			
			setLayout(null);
			
			setSize(mainPanelWidth,mainPanelHeight);
			
		}
		
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);
			
			g.setColor(Color.white);
			
			g.fillRect(0, 0, digui.mainPanelWidth, digui.mainPanelHeight);
			
			int rowCounter = 0;
			
			for(; rowCounter < digui.displayGrid.length; rowCounter++) {
				
				for(int columnCounter = 0; columnCounter < digui.displayGrid[rowCounter].length; columnCounter++) {
					
					if(digui.displayGrid[rowCounter][columnCounter]) {
						
						g.setColor(litColor);

						g.fillRect(((columnCounter + 1)*digui.columnWidth), ((rowCounter + 1) * digui.rowHeight), digui.columnWidth, digui.rowHeight);
						
					}else {
						
						g.setColor(unlitColor);
						
						g.fillRect(((columnCounter + 1)*digui.columnWidth), ((rowCounter + 1) * digui.rowHeight), digui.columnWidth, digui.rowHeight);
						
					}
					
				}
				
			}
			
			g.setColor(Color.BLACK);
			
			rowCounter = digui.rowHeight;
			
			for(; rowCounter < digui.mainPanelHeight - digui.rowHeight; rowCounter += digui.rowHeight ) {
				
				int columnCounter = digui.columnWidth;
				
				for(; columnCounter < digui.mainPanelWidth - digui.columnWidth; columnCounter += digui.columnWidth) {
					
					g.drawLine(columnCounter, rowCounter, columnCounter + digui.columnWidth, rowCounter);
					
					g.drawLine(columnCounter, rowCounter, columnCounter , rowCounter + digui.rowHeight);
					
					g.drawLine(columnCounter, digui.mainPanelHeight - digui.rowHeight, columnCounter + digui.columnWidth, digui.mainPanelHeight - digui.rowHeight);
					
				}
				
				g.drawLine(digui.mainPanelWidth-digui.columnWidth, rowCounter, digui.mainPanelWidth - digui.columnWidth, rowCounter + digui.rowHeight);
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		mw = new DijkstraInitGUI().new MainWindow();
		
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
		
		System.out.println("Column Width: " + digui.columnWidth + " : " + digui.rowHeight + " : " + digui.numberColumnUnits + " : " + digui.numberRowUnits);
		
		int columnNumber = (arg0.getX()-digui.columnWidth) / (digui.columnWidth);
		
		int rowNumber = (arg0.getY()-digui.rowHeight) / (digui.rowHeight);
		
		System.out.println("Mouse X Location: " + arg0.getX() + " : " + columnNumber + " Mouse Y Location: " + arg0.getY() + " : " + rowNumber);
		
		digui.displayGrid[rowNumber][columnNumber] = !digui.displayGrid[rowNumber][columnNumber];
		
		mw.repaint();
		
	}

}
