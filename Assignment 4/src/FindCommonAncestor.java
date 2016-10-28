//Pearson Radu
//250785461
//CS1027B
import java.io.*;

public class FindCommonAncestor 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader consoleReader  = new BufferedReader(new InputStreamReader(System.in));													//allows the computer to read user information

		LinkedBinaryTree<String> linkedBinaryTree = null;																						//declaring the LinkedBinaryTree
		TreeBuilder<String> theTreeBuilder = null;																								//declaring the TreeBuilder
		
		String firstElement = "";																												//declaring first element for line reader
		String secondElement = "";																												//declaring second element for line reader

		try																																		//try block
		{
			try																																	//try block for buildTree method
			{
				theTreeBuilder = new TreeBuilder<String>(args[0]);																				//initializing the LinkedBinaryTree (0 for small tree, 1 for larger tree)
				linkedBinaryTree = theTreeBuilder.buildTree();																					//initializing the TreeBuilder
			}
			catch (MalformedTreeFileException e)																								//print an exception if tree file is not in the correct format
			{
				System.out.println (e.getMessage());
			}
			catch (IOException e)																												//print an exception if the file cannot be read and printed to the user
			{
				System.out.println ("Error reading file: " + args[0] + "\n" + e.getMessage());
			}
			
			System.out.println("The tree contains: " + linkedBinaryTree.toString());															//printing out the contents of the tree
			
			try																																	//try block for readLine method
			{
				System.out.print("Enter first element: ");																						//prompt user for first element	
				firstElement = consoleReader.readLine().toUpperCase();																			//storing element (forcing it to be read as a capital)
				System.out.print("Enter second element: ");																						//prompt user for second element
				secondElement = consoleReader.readLine().toUpperCase();																			//storing element (forcing it to be read as a capital)
			}
			catch (IOException e)																												//print an exception if the file cannot be read and printed to the user
			{
				System.out.println ("Error reading file: " + args[0] + "\n" + e.getMessage());
			}
			
			try																																	//try block for LCA
			{
				System.out.println("The lowest common ancestor is: " + linkedBinaryTree.lowestCommonAncestor(firstElement, secondElement));		//utilize the LCA method and print out a statement to the user
			}
			catch (ElementNotFoundException e)																									//print an exception if an element is not found in the tree
			{
				System.out.println (e.getMessage());
			}
		}	
		catch (Exception e)																														//print an exception if no others are caught
		{
			System.out.println (e.getMessage());
		} 
		finally																																	//finally block
		{
			consoleReader.close();																												//close the console reader for clean up at the end of the program		
		}	
	}
}
