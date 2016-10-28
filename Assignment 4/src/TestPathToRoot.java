//Pearson Radu
//250785461
//CS1027B
import java.io.*;
import java.util.Iterator;

public class TestPathToRoot 
{
	public static void main(String[] args) 
	{
		try																							//try block
		{
			TreeBuilder <String> theTreeBuilder = null;												//declaring TreeBuilder object 
			LinkedBinaryTree <String> theTree = null;												//declaring the LinkedBinaryTree with TreeBuilder
			Iterator <String> pathFromCurrent = null;												//iterator for pathFromCurrent
			
			String current = "";																	//an empty string to store current in
			
			try																						//try block for buildTree method
			{
				theTreeBuilder = new TreeBuilder <String>(args[1]);									//assigning command line reader for TreeBuilder (0 for small tree, 1 for larger tree)
				theTree = theTreeBuilder.buildTree();												//initializing the build
			}
			catch (MalformedTreeFileException e) 													//print an exception if tree file is not in the correct format
			{
				System.out.println (e.getMessage());
			}
			catch (IOException e)																	//print an exception if the file cannot be read and printed to the user
			{
				System.out.println ("Error reading file: " + args[1] + "\n" + e.getMessage());
			}
			
			Iterator <String> treeElements = theTree.iteratorInOrder();								//iterator for treeElements
			
			while (treeElements.hasNext())															//if there are still more elements
			{
				try																					//try block for pathToRoot method
				{
					current = treeElements.next();													//set the next element to current
					pathFromCurrent = theTree.pathToRoot(current);									//find the path in the tree from current
				
					System.out.print("For element: " + current + " - the path to the root is: ");	//display the path
				
					while (pathFromCurrent.hasNext())												//if there are still more elements
						System.out.print(pathFromCurrent.next() + " ");								//return the next element in the iteration 
				
					System.out.println();															//print a space for nicer form
				}
				catch (ElementNotFoundException e)													//print an exception if an element is not found in the tree
				{
					System.out.println (e.getMessage());
				}
			}
		}
		catch (EmptyCollectionException e)															//print an exception if LinkedBinaryTree is empty
		{
			System.out.println (e.getMessage());
		}
		catch (Exception e)																			//print an exception if no others are caught
		{
			System.out.println (e.getMessage());
		}
	}
}
