//Pearson Radu
//CS1027B
//Magazine Collection Class
public class MagazineCollection 
{
	//declaring magazine array, the number of magazines and the default size for the array
	private Magazine [] magazineList;
	private int numberOfMagazines;
	private final int DEFUALT_SIZE = 5;
	
	//magazine collection constructor for a default size
	public MagazineCollection()
	{
		magazineList = new Magazine [DEFUALT_SIZE];
		numberOfMagazines = 0;
	}
	
	//magazine collection constructor with a parameter
	//making sure that the array size will be positive
	public MagazineCollection(int magazines)
	{
		if (magazines > 0)
				magazineList = new Magazine [magazines];
		
		numberOfMagazines = 0;
	}
	
	//add magazine method with three parameters
	//creates a new magazine and expands the array if needed 
	//indexes the amount of magazines 
	public void addMagazine (String name, String format, double price)
	{
		Magazine newMagazine = new Magazine (name, format, price);
		
		if(numberOfMagazines == magazineList.length)
		{
			Magazine [] largerList = new Magazine [magazineList.length * 2];
			
			for(int i = 0; i < magazineList.length; i++)
				largerList[i] = magazineList[i];
			
			magazineList = largerList;
		}
		
		magazineList[numberOfMagazines] = newMagazine;
		numberOfMagazines++;
	}
	
	//search magazine method with two parameters
	//checks to make sure the name and format of the magazine are both correct
	//then will assign the price of the "search" magazine
	public double searchMagazinePrice (String name, String format)
	{		
		final int NOT_FOUND = 0;
		int search = NOT_FOUND;
		
		if (numberOfMagazines == 0)
			return 0.0;
		
		for (int i = 0; i < numberOfMagazines && search == NOT_FOUND; i++)
			if(name.equals(magazineList[i].getMagazineName()) && format.equals(magazineList[i].getMagazineFormat()))
				search = i;
		
		return magazineList[search].getMagazinePrice();
	}
	
	//toString method
	//will properly format the output data for the user to see
	public String toString()
	{
		String magazines = "";
		
		for (int i = 0; i < numberOfMagazines; i++)
			magazines += magazineList[i].toString() + "\n";
		
		return magazines;
	}
	
	//number of magazines getter
	//will return the current number of magazines
	public int getNumberOfMagazines()
	{
		return numberOfMagazines;
	}
}
