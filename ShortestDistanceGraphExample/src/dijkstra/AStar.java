package dijkstra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AStar {

	static int numberOfNodes = 9;
	
	public static double[][] distanceMatrix = new double[numberOfNodes][numberOfNodes];
	
	//static double[][] pathDistanceMatrix = new double[numberOfNodes][numberOfNodes];
	
	static double[] pathDistanceMatrix = new double[numberOfNodes];
	
	static boolean[] visited = new boolean[numberOfNodes];
	
	static String[][] minimumPaths = new String[numberOfNodes][numberOfNodes];
	
	public static AStar x = null;
	
	static List<CompletePath> completePaths = new ArrayList<CompletePath>();
	
	static int numberRows;
	
	static int numberColumns;
	
	public static Node goalNode = null;
	
	public AStar(int initNumberOfNodes, int numberOfRows, int numberOfColumns, Node goalNode) {
		
		if(goalNode != null) {
			
			AStar.goalNode = new Node(goalNode);
			
		}
		
		numberOfNodes = initNumberOfNodes;
		
		distanceMatrix = new double[numberOfNodes][numberOfNodes];
		
		//static double[][] pathDistanceMatrix = new double[numberOfNodes][numberOfNodes];
		
		pathDistanceMatrix = new double[numberOfNodes];
		
		visited = new boolean[numberOfNodes];
		
		minimumPaths = new String[numberOfNodes][numberOfNodes];
		
		numberRows = numberOfRows;
		
		numberColumns = numberOfColumns;
		
	}
	
	public AStar() {
		// TODO Auto-generated constructor stub
	}

	public class CompletePath implements Comparable{
		
		String path;
		
		double pathLength;

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			if(((CompletePath)arg0).pathLength > pathLength) {
				
				return -1;
				
			}else if (((CompletePath)arg0).pathLength < pathLength){
				
				return 1;
				
			}else {
				
				return 0;
				
			}
		}
		
		public String toString() {
			
			return path + " && " + pathLength;
			
		}
		
	}
	
	public class Node{
		
		int nodeIndex = 2;
		
		int[] previousNodes = new int[numberOfNodes];
		
		int targetNodeIndex = 4;
		
		double minimumDistance = Double.MAX_VALUE;
		
		double currentDistance = 0.0;
		
		String minimumPath = "";
		
		public Node(Node clone){
			
			nodeIndex = clone.nodeIndex;
			
			previousNodes = Utilities.cloneIntArray(clone.previousNodes);
			
			targetNodeIndex = clone.targetNodeIndex;
			
			minimumDistance = clone.minimumDistance;
			
			currentDistance = clone.currentDistance;
			
			minimumPath = clone.minimumPath;
			
		}
		
		public double f(Node nextNode) {
			
			return currentDistance + manhattanDistance(goalNode.nodeIndex, nextNode.nodeIndex);
			
		}
		
		public double manhattanDistance(int nodeIndex1, int nodeIndex2) {
			
			int row1 = nodeIndex1 / numberColumns;
			
			int column1 = nodeIndex1 % numberColumns;
			
			int row2 = nodeIndex2 / numberColumns;
			
			int column2 = nodeIndex2 % numberColumns;
			
			System.out.println("Node Index 2: " + nodeIndex2 + " Row 2: " + row2 + " Column 2: " + column2 + " Number Columns: " + numberColumns);
			
			return Math.abs(row1 - row2) + Math.abs(column1 - column2);
			
		}
		
		public double straightLineToGoal(int nodeIndex1, int nodeIndex2) {
			
			int row1 = nodeIndex1 / x.numberColumns;
			
			int column1 = nodeIndex1 % x.numberColumns;
			
			int row2 = nodeIndex2 / x.numberColumns;
			
			int column2 = nodeIndex2 % x.numberColumns;
			
			return Math.abs(row1 - row2) + Math.abs(column1 - column2);
			
		}
		
		public Node(int nodeIndex, int previousNodeIndex, int targetNodeIndex, double minimumDistance, double currentDistance, String minimumPath, int[] previousNodes){
			
			this.nodeIndex = nodeIndex;
			
			this.previousNodes = Utilities.cloneIntArray(previousNodes);
			
			/*
			previousNodes = new int[numberOfNodes];
			
			for(int rowCounter = 0; rowCounter < previousNodes.length; rowCounter++) {
				
				previousNodes[rowCounter] = -1;
				
			}
			*/
			
			this.targetNodeIndex = targetNodeIndex;
			
			this.minimumDistance = minimumDistance;
			
			this.currentDistance = currentDistance;
			
			this.minimumPath = minimumPath;
			
		}
		
		public String toString() {
			
			return "Node Index: " + nodeIndex + " Target Node Index: " + targetNodeIndex + " Minimum Distance: " + minimumDistance + " Current Distance: " + currentDistance + " Minimum Path: " + minimumPath;
			
		}
		
	}
	static boolean print = false;
	
	static long printSleep = 10;
	
	public static int AStarAlgorithm(Node node) {
		
		//System.out.println("Dijkstra Algorithm: " + node.nodeIndex);
		
		if(node.nodeIndex == node.targetNodeIndex) {
			
			//node.minimumPath += "" + node.targetNodeIndex;
			return -1;
			
		}
		
		int minimumNodeIndex = -1;
		
		double minimumNodePathDistance = Double.MAX_VALUE;
		
		String minimumPathString = "";
		
		List<Node> neighborNodes = new ArrayList<Node>();
		
		int addedNodes = 0;
		
		for(int neighborCounter = 0; neighborCounter < distanceMatrix[node.nodeIndex].length; neighborCounter++) {
			
			if(neighborCounter != node.nodeIndex) {
				
				if(distanceMatrix[node.nodeIndex][neighborCounter] >= 0.0) {
					
					if(node.nodeIndex == 3 && neighborCounter == 8) {
						
						//System.out.println("Break!");
						
					}
					
					double newDistance = distanceMatrix[node.nodeIndex][neighborCounter] + node.currentDistance;
					
					if(newDistance < pathDistanceMatrix[neighborCounter]) {
						
						pathDistanceMatrix[neighborCounter] = newDistance;
						
						//System.out.println("Path Distance Matrix: " + pathDistanceMatrix[neighborCounter]);
						
						if(neighborCounter == node.targetNodeIndex) {
							
							//node.minimumPath += "" + node.targetNodeIndex;
							
							System.out.println("Complete Path: " + node.minimumPath + " : " + node.targetNodeIndex + " Minimum Path: " + newDistance);
							
							CompletePath completePath = x.new CompletePath();
							
							completePath.path = "Complete Path: " + node.minimumPath + " : " + node.targetNodeIndex + " Minimum Path: " + newDistance;
							
							completePath.pathLength = newDistance;
							
							completePaths.add(completePath);
							
							return 1;
							
						}
						
					}
					
					//can sometimes miss the shortest distance
					
					if(!visited[neighborCounter]) {
						
						if(node.previousNodes[neighborCounter] == 0) {
						//if(!Utilities.arrayContainsInt(node.previousNodes, neighborCounter)) {
							
							int[] nextNodePrevious = Utilities.cloneIntArray(node.previousNodes);
							
							nextNodePrevious[node.nodeIndex] = 1;
							
							Node nextNode = x.new Node(neighborCounter, node.nodeIndex, node.targetNodeIndex, Double.MAX_VALUE, newDistance, node.minimumPath + " : " + neighborCounter, nextNodePrevious);
							
							//int returnIndex = AStarAlgorithm(nextNode);
							
							System.out.println("Neighbor Counter: " + neighborCounter + " F Value: " + nextNode.f(nextNode));
							
							neighborNodes.add(nextNode);
							
							double nextF = node.f(nextNode);
							
							if(nextF < minimumNodePathDistance) {
								
								//if(pathDistanceMatrix[returnIndex] < minimumNodePathDistance) {
									
									minimumNodePathDistance = nextF;
									
									minimumNodeIndex = addedNodes;
									
									minimumPathString = nextNode.minimumPath;
									
								//}
								
							}
							
							addedNodes++;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if(print) {
			
			try {
				
				Thread.sleep(printSleep);
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
			System.out.println("Node Variables:");
			
			System.out.println(node);
			
			for(int nodeCounter = 0; nodeCounter < distanceMatrix.length; nodeCounter++) {
				
				System.out.println(Arrays.toString(distanceMatrix[nodeCounter]));
				
			}
			
			System.out.println("");
			
			System.out.println("Minimum Distance: " + minimumNodePathDistance);
			
			System.out.println("Neighbor Counter: " + minimumNodeIndex);
			
			System.out.println(Arrays.toString(pathDistanceMatrix));
			
			System.out.println(Arrays.toString(node.previousNodes));
			
			System.out.println(Arrays.toString(visited));
			
			System.out.println("");
			
		}
		
		//visited[node.nodeIndex] = true;
		
		if(minimumNodeIndex >= 0) {
			
			//System.out.println("Minimum Node Index: " + minimumNodeIndex);
			
			int returnIndex = AStarAlgorithm(neighborNodes.get(minimumNodeIndex));
			
			node.minimumPath += minimumNodeIndex + " : " + minimumPathString;
			
			node.minimumDistance = minimumNodePathDistance;
			
			return minimumNodeIndex;
			
		}else {
			
			return -1;
			
		}
		
	}
	
	public void initMatrices() {
		
		minimumPaths = Utilities.createPathMatrix(minimumPaths);
		
		pathDistanceMatrix = Utilities.createPathDistanceMatrix(pathDistanceMatrix);
				
	}
	
	public CompletePath getPath(Node node) {
		
		completePaths.clear();
		
		AStarAlgorithm(node);
		
		CompletePath[] completePathArray = new CompletePath[completePaths.size()];
		
		for(int completePathCounter = 0; completePathCounter < completePathArray.length; completePathCounter++) {
			
			completePathArray[completePathCounter] = completePaths.get(completePathCounter);
			
		}
		
		Arrays.sort(completePathArray);
		
		for(CompletePath cp: completePathArray) {
			
			//System.out.println(cp);
			
		}
		
		return completePathArray[0];
		
	}
	
	public static void main(String[] args) {
		
		//distanceMatrix = Utilities.createDistanceMatrix3(distanceMatrix);
		
		x = new AStar(25, 3, 3, null);
		
		int originNode = 1;
		
		int targetNode = 24;
		
		Node goalNode = x.new Node(targetNode,0,targetNode,0.0,0.0,targetNode + "",new int[numberOfNodes]);
		
		
		x.goalNode = x.new Node(goalNode);
		
		boolean[][] displayGrid = new boolean[(int)(Math.sqrt(numberOfNodes))][(int)Math.sqrt(numberOfNodes)];
		
		Utilities.setAStarDistanceMatrix(displayGrid, x);
		
		minimumPaths = Utilities.createPathMatrix(minimumPaths);
		
		pathDistanceMatrix = Utilities.createPathDistanceMatrix(pathDistanceMatrix);
		
		Node originalNode = x.new Node(originNode,0,targetNode,0.0,0.0,originNode + "",new int[numberOfNodes]);
		
		AStarAlgorithm(originalNode);
		
		if(print) {
			
			try {
				
				Thread.sleep(printSleep);
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
			System.out.println("Node Variables:");
			
			System.out.println(originalNode);
			
			for(int nodeCounter = 0; nodeCounter < distanceMatrix.length; nodeCounter++) {
				
				System.out.println(Arrays.toString(distanceMatrix[nodeCounter]));
				
			}
			
			System.out.println("");
			
			System.out.println(Arrays.toString(pathDistanceMatrix));
			
			System.out.println(Arrays.toString(originalNode.previousNodes));
			
			System.out.println(Arrays.toString(visited));
			
			System.out.println("");
			
		}
		
		System.out.println("Complete Paths:");
		
		CompletePath[] completePathArray = new CompletePath[completePaths.size()];
		
		for(int completePathCounter = 0; completePathCounter < completePathArray.length; completePathCounter++) {
			
			completePathArray[completePathCounter] = completePaths.get(completePathCounter);
			
		}
		
		Arrays.sort(completePathArray);
		
		for(CompletePath cp: completePathArray) {
			
			System.out.println(cp);
			
		}
		
		//System.out.println(Arrays.toString(completePathArray));
		
		//System.out.println("My Best Path is: " + originalNode.minimumPath);
		
	}

}
