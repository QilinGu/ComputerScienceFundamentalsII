import java.io.IOException;

//Pearson Radu
//CS1027B Assignment 2
//This will be the maze solver class that utilizes all the classes and methods 
public class MazeSolver 
{
	public static void main(String[] args)
	{
		try
		{
			if(args.length < 1)
				throw new IllegalArgumentException("Please provide a Maze file as a command line argument");
			else
			{
				String mazeFileName = args[1];												//command line argument either 0 or 1 input if both mazes are in the command line
				Maze maze = new Maze(mazeFileName);											//creating maze object reference 
				maze.setTimeDelay(500);
			
				Hexagon startHex = maze.getStart();											//getting the start hexagon tile from the maze
				Hexagon current = null;														//reference for current tile
				Hexagon neighbour = null;													//reference for neighbor tile
			
				ArrayStack<Hexagon> hexStack = new ArrayStack<Hexagon>();					//creating the Hexagon stack
				hexStack.push(startHex);													//adding the start tile to the stack
				
				boolean found = false;														//boolean to see if we have found the end
				int counter = 0;															//counter the amount of steps the maze will take
			
				while (!(hexStack.isEmpty()) && !found)										//loop through algorithm while the stack has something in it and the end isn't found
				{
					current = hexStack.pop();												//pop the stack to set current
					counter++;																//increment the counter to keep track of steps
																
					if (current.isEnd())													//if current is the end tile exit the while loop
						found = true;
					else
					{
						for (int i = 0; i < 6; i++)											//loop through 6 values (0 - 5) to find the neighbours
						{
							if (i < 0 || i > 5)
								throw new InvalidNeighbourIndexException(i);				//throw exception incase the value of i is not appropriate
						
							else if (current.getNeighbour(i) != null &&						//check to see if the neighbour is null
									!(current.getNeighbour(i).isProcessed()) && 			//check to see if the neighbour is a wall or it could be !(current.getNeighbour(i).isWall())
										!(current.getNeighbour(i).isWall()))				//check to see if the neighbour is unvisited or it could be current.getNeighbour(i).isUnvisited()
							{
								neighbour = current.getNeighbour(i);						//set the neighbor to the current value
								hexStack.push(neighbour);									//push the neighbour
								neighbour.setPushed();										//set the tile to be pushed and change the colour
							}
						}															
							current.setProcessed();											//set the tile to be visited and update the colour 
							maze.repaint();													//refresh the maze
					}	
				}
			
				if (found)																	//when the tile is found
				{
					current.setProcessed();													//set the tile to be visited and update the colour
					maze.repaint();	
					System.out.println("Congratulations you have found the end!\n" + 
							"It took you " + counter + " steps to complete the maze!\n" +
								"There were " + hexStack.size() + " left on the stack.");	//print out a statement saying the end tile has been found														
				}
				else
				{
					System.out.println("You did not find the end of the maze, sorry.");		//print out a statement saying the end tile was never found
				}
			}
		}
		
		//exception that may need to be handled, they print lines explain what they may be for and they exit the program if they are found
		
		catch(IOException e)																//failed or interrupted input/ouput
		{
			System.out.println("Hey, there is a failed or interupted input/output "
					+ "operation " + e.getMessage());
			System.exit(0);
		}
		
		catch(IllegalArgumentException e)													//the command does not work for the argument 
		{
			System.out.println("Hey, this method is using an innapropriate arguement " 
					+ e.getMessage());
			System.exit(0);
		}
		
		catch(NullPointerException e)														//an item you are trying to push or pop is null
		{
			System.out.println("Hey, something may be " + e.getMessage());
			System.exit(0);
		}
		
		catch(EmptyCollectionException e)													//the collection is empty
		{
			System.out.println("Hey, " + e.getMessage());
			System.exit(0);
		}
		
		catch(InvalidNeighbourIndexException e)												//logical error for the amount of neighbours found
		{
			System.out.println("Hey, " + e.getMessage());
			System.exit(0);
		}
		
		catch(UnknownMazeCharacterException e)												//a character found in the file is unknown
		{
			System.out.println("Hey, " + e.getMessage());
			System.exit(0);
		}
		
		catch(ArrayIndexOutOfBoundsException e)												//the value of the argument is not valid
		{
			System.out.println("Hey, the number of arguements must be greater than 0, " 
					+ e.getMessage() + " is not greater that 0");
			System.exit(0);
		}
		
		finally																				//finally block to close everything
		{
			//System.exit(0);
		}
	}
}