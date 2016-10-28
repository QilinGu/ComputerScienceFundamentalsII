//Pearson Radu
//250785461
//CS1027B
//this class contains the insertion and deletion method

public class EventQueue 
{

	final int impossibleNumItemsValue = -1;

	QueueADT<Event> eventQueue, tempQueue;
	
	// Constructor
	public EventQueue() 
	{
		eventQueue = new LinkedQueue<Event>();
		tempQueue = new LinkedQueue<Event>();														//Create a temporary queue 									
	}

	// ///////////////////////////////////////////////////////////////
	// Insert events one at a time in sorted order on eventTime
	// ///////////////////////////////////////////////////////////////
	public void insertEvent(Event insertEvent, boolean debug) 
	{
		String s;

		if (debug) 
		{
			System.out.format("\nINSERT EVENT:\nEvent time: %7.2f Event type: %s", insertEvent.getEventTime(), insertEvent.getEventType());
			
			if (insertEvent.getEventNumItems() != impossibleNumItemsValue)
				System.out.format(" Event number of items: %d",
						insertEvent.getEventNumItems());
			System.out.format("\n");
		}

		if (debug) 
		{
			System.out.println("\nEvent Queue before new event enqueued");
			System.out.println("-------------------------------------");
			s = eventQueue.toString();

			if (!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}

		//Insert your code here to do the insertion///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		if(eventQueue.isEmpty())																	//check to see if the event queue is empty
			eventQueue.enqueue(insertEvent);														//if it is empty add the first event
		else 																						//now if the queue has an item in it
		{
			if (insertEvent.getEventTime() < eventQueue.first().getEventTime())						//check to see if the time is lesser than the first queue time
			{
				tempQueue.enqueue(insertEvent);														//if it is add the lower time to the temp queue so it has the 
																										//lowest time first
				while (!eventQueue.isEmpty())														//empty the contents of the event queue to the temp queue
					tempQueue.enqueue(eventQueue.dequeue());
				
				while (!tempQueue.isEmpty())														//reorganize the data into the event queue so it is arranged properly 
					eventQueue.enqueue(tempQueue.dequeue());
			}
			else 																					//if it makes it here that means the new time is greater than 
			{																							//the first time in the queue
				boolean canWeInsert = false;														//create a boolean to make sure it is the right time to add the new time
				while (!eventQueue.isEmpty() && !canWeInsert)										//while we can't add a new item
				{
					if (insertEvent.getEventTime() >= eventQueue.first().getEventTime())			//check to see if the new time is >= the first time in the queue
					{
						tempQueue.enqueue(eventQueue.dequeue());									//if the first item is larger than the new item dequeue the item 
																										//from event queue to the temp queue so it has the lower value first
						if (eventQueue.isEmpty())
						{
							tempQueue.enqueue(insertEvent);											//if the event queue is empty then it is time to add the new time
							canWeInsert = true;														//set the value to true so we exit the while loop
						}
					}
					else if (insertEvent.getEventTime() < eventQueue.first().getEventTime())		//check if the new time is less than the first time 
					{
						tempQueue.enqueue(insertEvent);												//if it is take the new time and add it to the temp queue
						canWeInsert = true;															//set the value to true so we exit the while loop
					}
				}
				
				while (!eventQueue.isEmpty())														//make sure the rest of the times from event are placed in order in temp
					tempQueue.enqueue(eventQueue.dequeue());
				
				while (!tempQueue.isEmpty())														//then reorganize the items in the event queue 
					eventQueue.enqueue(tempQueue.dequeue());
			}
		}
		
		//end of insertion method/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		if (debug) 
		{
			System.out.println("\nEvent Queue after new event enqueued");
			System.out.println("------------------------------------");
			s = eventQueue.toString();

			if (!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}
	}

	// ///////////////////////////////////////////////////////////////////////
	// Delete all events from order queue whose eventTime >= minAllDoneTime
	// ///////////////////////////////////////////////////////////////////////
	public void deleteEvents(double minAllDoneTime, boolean debug) 
	{
		// this method assumes that the queue is already sorted
		// We dequeue events from eventQueue until the eventTime >=
		// minAllDoneTime
		// and enqueue them in backwards order in tempQueue.
		// The first ALL_DONE event whose eventTime is
		// equal to minAllDoneTime is enqueued in tempQueue as well.
		// The eventQueue is then emptied and lastly tempQueue is copied back
		// into
		// now empty eventQueue in correct sorted order.

		String s;

		if (debug) 
		{
			System.out.println("\nEvent Queue before all events with eventTime > " + minAllDoneTime + " deleted");
			System.out.println("------------------------------------------------------");
			s = eventQueue.toString();

			if (!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}

		//Insert your code here to do the deletion////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		boolean canWeDelete = false;																//boolean to identify when queue has a minAllDoneTime event time
		boolean allDoneFound = false;																//boolean to identify when queue has a ALL_DONE event type
		
		try																							//try loop to make sure we don't delete to many items from the queue										
		{
			while (!allDoneFound)																	//for the period of time that we have not found an ALL_DONE event type in the event queue
			{
				if(eventQueue.first().getEventType().equals("ALL_DONE"))							//check to see if the first item in the queue has an ALL_DONE event type
				{
					while (!eventQueue.isEmpty())													//make sure the rest of time times after ALL_DONE are still added just in case
						tempQueue.enqueue(eventQueue.dequeue());
					
					allDoneFound = true;															//set to true when we have found the ALL_DONE event to exit the loop and start to delete excess items 
				}
				else																				//if we don't find an ALL_DONE event one by one add and check each item in the queue to ALL_DONE is found
					tempQueue.enqueue(eventQueue.dequeue());
				
				if(eventQueue.isEmpty() && !allDoneFound)											//if we do not find an ALL_DONE event type tell the user that it is not located in the data
				{
					System.out.println("Sorry, it appears there is no ALL_DONE event type "
							+ "found in your data!");
				}
			}
			
			if (allDoneFound)																		//when we find the ALL_DONE event type 
			{
				while (!tempQueue.isEmpty())														//reorganize the items in the event queue since it was changed while we could not find the ALL_DONE event type 
					eventQueue.enqueue(tempQueue.dequeue());	
				
				if (eventQueue.first().getEventTime() < minAllDoneTime)								//check the first item in the queue to see if it has the minAllDoneTime
				{
					while (!eventQueue.isEmpty() && !canWeDelete)									//while the queue isn't empty and minAllDoneTime hasn't been found 
					{
						tempQueue.enqueue(eventQueue.dequeue());									//take the lesser value and put it in the temporary queue 
						
						if (eventQueue.first().getEventTime() > minAllDoneTime)						//check again to see if the first value in the queue is greater than minAllDoneTime
						{	
							while (!eventQueue.isEmpty())											//if it is, empty the rest of the event queue because those numbers don't matter anymore
								eventQueue.dequeue();
							
							canWeDelete = true;														//exit the loop because we have found the minAllDoneTime
						}
					}
					
					while (!tempQueue.isEmpty())													//transfer the contents from the temporary queue to the event queue
						eventQueue.enqueue(tempQueue.dequeue());
				}
			}
		}
		catch (EmptyCollectionException e)
		{
			System.out.println("Hey, " + e.getMessage());
			System.exit(0);
		}
		finally
		{
			
		}
		//end of deletion method//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (debug) 
		{
			System.out.println("\nEvent Queue after all events with eventTime > " + minAllDoneTime + " deleted");
			System.out.println("-----------------------------------------------------");
			s = eventQueue.toString();

			if (!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}

	}
}
