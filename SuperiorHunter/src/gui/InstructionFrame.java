package gui;

import javax.swing.*;

import java.awt.*;

public class InstructionFrame extends JFrame{

	private int width = 725;
	
	private int height = 600;
	
	public InstructionFrame( ) {
		
		
		String instructionalContent = "1. File Menu Item will allow the user to load, save and run.\n" +
				
			"2. The program will initially begin with a blank grid.\n" +
			
			"3. Click on any empty square to fill it and vice versa.\n" +
			
			"4. Select the \"save\" menu item to give the grid a name \n        and save it with the .obj filename.\n" +
			
			"5. Load a saved .obj grid with the \"load\" menu item\n        and \"Dijkstra\" or \"A*\" search algorithms.\n" +
			
			"6. \"Dijkstra\" is more thorough but slower for larger grids.\n" +
			
			"7. Select \"run\" to run a loaded grid.\n" +
			
			"8. For \"Dijkstra\" select one .jpg image file and 2 for A*.\n" +
			
			"9. Help Menu Item from the Help Menu will display this\n        screen.\n" +
			
			"\n";
		
		
		setLayout( null );
	
		setSize( width, height );
		
		MainPanel mainPanel = new MainPanel( width - 50, height - 65, instructionalContent );
		
		JScrollPane mainScrollPane = new JScrollPane( mainPanel );
		
		mainScrollPane.setSize( width - 50, height - 65 );
		
		mainScrollPane.setLocation( 15, 10 );
		
		mainScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
		
		mainScrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		
		add( mainScrollPane );
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
		setVisible( true );
		
	}
	
	public static void main(String[] args) {
	
		InstructionFrame instructFrame = new InstructionFrame( );
		
	}

	private class MainPanel extends JPanel {
		
		private int width;
		
		private int height;
		
		private String[] instructionContentArray;
		
		public MainPanel( int width, int height, String instructionContent ) {
			
			this.width = width;
			
			this.height = height;
			
			setSize( width, height );
			
			//setLocation( 15, 10 );
			
			instructionContentArray = instructionContent.split( "\n" );
			
			setLayout( new GridLayout( instructionContentArray.length, 1 ) );
			
			for(int labelCounter = 0; labelCounter < instructionContentArray.length; labelCounter++ ) {
				
				JLabel testLabel = new JLabel( instructionContentArray[ labelCounter ] );
				
				testLabel.setFont( new Font( "Arial", Font.PLAIN, 25 ) );
				
				testLabel.setSize(100, 50 * ( labelCounter + 1 ) );
				
				add( testLabel );
				
			}
			
		}
		
	}
	
}
