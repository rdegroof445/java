package dijkstra;

public class Utilities {

	public static double[][] clone2DDoubleArray(double[][] array){
		
		double[][] returnArray = new double[array.length][array[0].length];
		
		for(int rowCounter = 0; rowCounter < array.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < array[rowCounter].length; columnCounter++) {
				
				returnArray[rowCounter][columnCounter] = array[rowCounter][columnCounter];
				
			}
			
		}
		
		return returnArray;
		
	}
	
	public static double[] cloneDoubleArray(double[] array){
		
		double[] returnArray = new double[array.length];
		
		for(int rowCounter = 0; rowCounter < array.length; rowCounter++) {
			
			returnArray[rowCounter] = array[rowCounter];
			
		}
		
		return returnArray;
		
	}
	
	public static int[] cloneIntArray(int[] array) {
		
		int[] returnArray = new int[array.length];
		
		for(int rowCounter = 0; rowCounter < array.length; rowCounter++) {
			
			returnArray[rowCounter] = array[rowCounter];
			
		}
		
		return returnArray;
		
	}
	
	public String[][] cloneStringArray(String[][] array){
		
		String[][] returnArray = new String[array.length][array[0].length];
		
		for(int rowCounter = 0; rowCounter < array.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < array[rowCounter].length; columnCounter++) {
				
				returnArray[rowCounter][columnCounter] = array[rowCounter][columnCounter];
				
			}
			
		}
		
		return returnArray;
		
	}
	
	public static String[][] createPathMatrix(String[][] minimumPaths){
		
		for(int rowCounter = 0; rowCounter < minimumPaths.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < minimumPaths[rowCounter].length; columnCounter++) {
				
				minimumPaths[rowCounter][columnCounter] = "";
				
			}
			
		}
		
		return minimumPaths;
		
	}
	
	public static double[][] createPathDistanceMatrix(double[][] pathDistanceMatrix){
		
		for(int rowCounter = 0; rowCounter < pathDistanceMatrix.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < pathDistanceMatrix[rowCounter].length; columnCounter++) {
				
				pathDistanceMatrix[rowCounter][columnCounter] = Double.MAX_VALUE;
				
			}
			
		}
		
		return pathDistanceMatrix;
		
	}
	
	public static double[] createPathDistanceMatrix(double[] pathDistanceMatrix){
		
		for(int rowCounter = 0; rowCounter < pathDistanceMatrix.length; rowCounter++) {
			
			pathDistanceMatrix[rowCounter] = Double.MAX_VALUE;
			
		}
		
		return pathDistanceMatrix;
		
	}
	
	public static double[][] createDistanceMatrix(double[][] distanceMatrix) {
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < distanceMatrix[rowCounter].length; columnCounter++) {
				
				distanceMatrix[rowCounter][columnCounter] = -1.0;
				
			}
			
		}
		
		distanceMatrix[0][0] = 0.0;
		
		distanceMatrix[1][1] = 0.0;
		
		distanceMatrix[2][2] = 0.0;
		
		distanceMatrix[3][3] = 0.0;
		
		distanceMatrix[4][4] = 0.0;
		
		//A-B
		distanceMatrix[0][1] = 3.0;
		
		//A-C
		distanceMatrix[0][2] = 1.0;
		
		//B-A
		distanceMatrix[1][0] = 3.0;
		
		//B-E
		distanceMatrix[1][4] = 1.0;
		
		//B-C
		distanceMatrix[1][2] = 7.0;
		
		//B-D
		distanceMatrix[1][3] = 5.0;
		
		//C-A
		distanceMatrix[2][0] = 1.0;
		
		//C-B
		distanceMatrix[2][1] = 7.0;
		
		//C-D
		distanceMatrix[2][3] = 2.0;
		
		//D-C
		distanceMatrix[3][2] = 2.0;
		
		//D-B
		distanceMatrix[3][1] = 5.0;
		
		//D-E
		distanceMatrix[3][4] = 7.0;
		
		//E-B
		distanceMatrix[4][1] = 1.0;
		
		//E-D
		distanceMatrix[4][3] = 7.0;
		
		return distanceMatrix;
		
	}
	
	public static double[][] createDistanceMatrix2(double[][] distanceMatrix) {
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < distanceMatrix[rowCounter].length; columnCounter++) {
				
				distanceMatrix[rowCounter][columnCounter] = -1.0;
				
			}
			
		}
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			distanceMatrix[rowCounter][rowCounter] = 0.0;
			
		}
		
		
		//A-B
		distanceMatrix[0][1] = 3.0;
		
		//A-C
		distanceMatrix[0][2] = 2.0;
		
		//B-A
		distanceMatrix[1][0] = 3.0;
		
		//B-E
		distanceMatrix[1][4] = 6.0;
		
		//B-C
		distanceMatrix[1][2] = 1.0;
		
		//B-D
		//distanceMatrix[1][3] = 5.0;
		
		//C-A
		distanceMatrix[2][0] = 2.0;
		
		//C-B
		distanceMatrix[2][1] = 1.0;
		
		//C-D
		distanceMatrix[2][3] = 4.0;
		
		//D-C
		distanceMatrix[3][2] = 2.0;
		
		//D-B
		//distanceMatrix[3][1] = 5.0;
		
		//D-E
		distanceMatrix[3][4] = 5.0;
		
		//E-B
		distanceMatrix[4][1] = 6.0;
		
		//E-D
		distanceMatrix[4][3] = 5.0;
		
		//E-G
		distanceMatrix[4][6] = 3.0;
		
		//G-E
		distanceMatrix[6][4] = 3.0;
		
		//D-G
		distanceMatrix[3][6] = 7.0;
		
		//G-D
		distanceMatrix[6][3] = 7.0;
		
		//G-H
		distanceMatrix[6][7] = 5.0;
		
		//H-G
		distanceMatrix[7][6] = 5.0;
		
		//D-H
		distanceMatrix[3][7] = 2.0;
		
		//H-D
		distanceMatrix[7][3] = 2.0;
		
		//D-F
		distanceMatrix[3][5] = 9.0;
		
		//F-D
		distanceMatrix[5][3] = 9.0;
		
		//D-I
		distanceMatrix[3][8] = 1.0;
		
		//I-D
		distanceMatrix[8][3] = 1.0;
		
		//H-I
		distanceMatrix[7][8] = 4.0;
		
		//I-H
		distanceMatrix[8][7] = 4.0;
		
		return distanceMatrix;
		
	}
	
	public static double[][] initializeDistanceMatrix(double[][] distanceMatrix){
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < distanceMatrix[rowCounter].length; columnCounter++) {
				
				distanceMatrix[rowCounter][columnCounter] = -1.0;
				
			}
			
		}
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			distanceMatrix[rowCounter][rowCounter] = 0.0;
			
		}
		
		return distanceMatrix;
		
	}
	
	public static double[][] createDistanceMatrix3(double[][] distanceMatrix) {
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			for(int columnCounter = 0; columnCounter < distanceMatrix[rowCounter].length; columnCounter++) {
				
				distanceMatrix[rowCounter][columnCounter] = -1.0;
				
			}
			
		}
		
		for(int rowCounter = 0; rowCounter < distanceMatrix.length; rowCounter++) {
			
			distanceMatrix[rowCounter][rowCounter] = 0.0;
			
		}
		
		
		//A-B
		distanceMatrix[0][1] = 1.0;
		
		//A-C
		distanceMatrix[0][2] = 1.0;
		
		//B-A
		distanceMatrix[1][0] = 1.0;
		
		//B-E
		distanceMatrix[1][4] = 1.0;
		
		//B-C
		distanceMatrix[1][2] = 100.0;
		
		//B-D
		//distanceMatrix[1][3] = 5.0;
		
		//C-A
		distanceMatrix[2][0] = 1.0;
		
		//C-B
		distanceMatrix[2][1] = 100.0;
		
		//C-D
		distanceMatrix[2][3] = 100.0;
		
		//D-C
		distanceMatrix[3][2] = 100.0;
		
		//D-B
		//distanceMatrix[3][1] = 5.0;
		
		//D-E
		distanceMatrix[3][4] = 1.0;
		
		//E-B
		distanceMatrix[4][1] = 1.0;
		
		//E-D
		distanceMatrix[4][3] = 1.0;
		
		//E-G
		distanceMatrix[4][6] = 100.0;
		
		//G-E
		distanceMatrix[6][4] = 100.0;
		
		//D-G
		distanceMatrix[3][6] = 100.0;
		
		//G-D
		distanceMatrix[6][3] = 100.0;
		
		//G-H
		distanceMatrix[6][7] = 100.0;
		
		//H-G
		distanceMatrix[7][6] = 100.0;
		
		//D-H
		distanceMatrix[3][7] = 1.0;
		
		//H-D
		distanceMatrix[7][3] = 1.0;
		
		//D-F
		distanceMatrix[3][5] = 100.0;
		
		//F-D
		distanceMatrix[5][3] = 100.0;
		
		//D-I
		distanceMatrix[3][8] = 100.0;
		
		//I-D
		distanceMatrix[8][3] = 100.0;
		
		//H-I
		distanceMatrix[7][8] = 1.0;
		
		//I-H
		distanceMatrix[8][7] = 1.0;
		
		return distanceMatrix;
		
	}
	
	public static void setAStarDistanceMatrix(boolean[][] displayGrid, AStar pathAlgorithm) {
		
		pathAlgorithm.initMatrices();
		
		pathAlgorithm.distanceMatrix = Utilities.initializeDistanceMatrix(pathAlgorithm.distanceMatrix);
		
		int numberRows = displayGrid.length;
		
		int numberColumns = displayGrid[0].length;
		
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
	
	public static void setHunterAStarDistanceMatrix(boolean[][] displayGrid, HunterAStar pathAlgorithm) {
		
		pathAlgorithm.initMatrices();
		
		pathAlgorithm.distanceMatrix = Utilities.initializeDistanceMatrix(pathAlgorithm.distanceMatrix);
		
		int numberRows = displayGrid.length;
		
		int numberColumns = displayGrid[0].length;
		
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
	
	public static boolean arrayContainsInt(int[] array, int value) {
		
		for(int rowCounter = 0; rowCounter < array.length; rowCounter++) {
			
			if(value == array[rowCounter]) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
