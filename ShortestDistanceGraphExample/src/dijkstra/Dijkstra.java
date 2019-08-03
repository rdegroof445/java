package dijkstra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {

	static int numberOfNodes = 9;
	
	public static double[][] distanceMatrix = new double[numberOfNodes][numberOfNodes];
	
	static double[] pathDistanceMatrix = new double[numberOfNodes];
	
	static boolean[] visited = new boolean[numberOfNodes];
	
	static String[][] minimumPaths = new String[numberOfNodes][numberOfNodes];
	
	static Dijkstra x = new Dijkstra(9);
	
	static List<CompletePath> completePaths = new ArrayList<CompletePath>();
	
	public Dijkstra(int initNumberOfNodes) {
		
		numberOfNodes = initNumberOfNodes;
		
		distanceMatrix = new double[numberOfNodes][numberOfNodes];
		
		pathDistanceMatrix = new double[numberOfNodes];
		
		visited = new boolean[numberOfNodes];
		
		minimumPaths = new String[numberOfNodes][numberOfNodes];
		
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
		
		Node(Node clone){
			
			nodeIndex = clone.nodeIndex;
			
			previousNodes = Utilities.cloneIntArray(clone.previousNodes);
			
			targetNodeIndex = clone.targetNodeIndex;
			
			minimumDistance = clone.minimumDistance;
			
			currentDistance = clone.currentDistance;
			
			minimumPath = clone.minimumPath;
			
		}
		
		public Node(int nodeIndex, int previousNodeIndex, int targetNodeIndex, double minimumDistance, double currentDistance, String minimumPath, int[] previousNodes){
			
			this.nodeIndex = nodeIndex;
			
			this.previousNodes = Utilities.cloneIntArray(previousNodes);
			
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
	
	public static int DijkstraAlgorithm(Node node) {
		
		if(node.nodeIndex == node.targetNodeIndex) {
			
			return -1;
			
		}
		
		int minimumNodeIndex = -1;
		
		double minimumNodePathDistance = Double.MAX_VALUE;
		
		String minimumPathString = "";
		
		for(int neighborCounter = 0; neighborCounter < distanceMatrix[node.nodeIndex].length; neighborCounter++) {
			
			if(neighborCounter != node.nodeIndex) {
				
				if(distanceMatrix[node.nodeIndex][neighborCounter] >= 0.0) {
					
					if(node.nodeIndex == 3 && neighborCounter == 8) {
						
						//System.out.println("Break!");
						
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
						
						System.out.println("Neighbor Counter: " + neighborCounter);
						
						System.out.println(Arrays.toString(pathDistanceMatrix));
						
						System.out.println(Arrays.toString(node.previousNodes));
						
						System.out.println(Arrays.toString(visited));
						
						System.out.println("");
						
					}
					
					double newDistance = distanceMatrix[node.nodeIndex][neighborCounter] + node.currentDistance;
					
					if(newDistance < pathDistanceMatrix[neighborCounter]) {
						
						pathDistanceMatrix[neighborCounter] = newDistance;
						
						if(neighborCounter == node.targetNodeIndex) {
							
							CompletePath completePath = x.new CompletePath();
							
							completePath.path = "Complete Path: " + node.minimumPath + " : " + node.targetNodeIndex + " Minimum Path: " + newDistance;
							
							completePath.pathLength = newDistance;
							
							completePaths.add(completePath);
							
							return 1;
							
						}
						
					}
					
					if(!visited[neighborCounter]) {
						
						if(node.previousNodes[neighborCounter] == 0) {
							
							int[] nextNodePrevious = Utilities.cloneIntArray(node.previousNodes);
							
							nextNodePrevious[node.nodeIndex] = 1;
							
							Node nextNode = x.new Node(neighborCounter, node.nodeIndex, node.targetNodeIndex, Double.MAX_VALUE, newDistance, node.minimumPath + " : " + neighborCounter, nextNodePrevious);
							
							int returnIndex = DijkstraAlgorithm(nextNode);
							
							if(returnIndex >= 0) {
								
									
									minimumNodePathDistance = pathDistanceMatrix[returnIndex];
									
									minimumNodeIndex = neighborCounter;
									
									minimumPathString = nextNode.minimumPath;
									
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if(minimumNodeIndex >= 0) {
			
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
		
		DijkstraAlgorithm(node);
		
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
		
		distanceMatrix = Utilities.createDistanceMatrix3(distanceMatrix);
		
		minimumPaths = Utilities.createPathMatrix(minimumPaths);
		
		pathDistanceMatrix = Utilities.createPathDistanceMatrix(pathDistanceMatrix);
		
		int originNode = 2;
		
		int targetNode = 8;
		
		Node originalNode = x.new Node(originNode,0,targetNode,0.0,0.0,originNode + "",new int[numberOfNodes]);
		
		DijkstraAlgorithm(originalNode);
		
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

	}

}
