//Pearson Radu
//CS1027B
//Magazine Class
public class Magazine 
{
	// declaring three private variables
	private String magazineName;
	private String magazineFormat;
	private double magazinePrice;

	// getter for magazine name
	public String getMagazineName() 
	{
		return magazineName;
	}

	// getter for magazine format
	public String getMagazineFormat() 
	{
		return magazineFormat;
	}

	// getter for magazine price
	public double getMagazinePrice() 
	{
		return magazinePrice;
	}

	// constructor for magazine
	// assigning the values in the parameters to the getters
	public Magazine(String name, String format, double price) 
	{
		magazineName = name;
		magazineFormat = format;
		magazinePrice = price;
	}
	
	//toString method to return properties of the magazines
	//formatting the string before it is returned
	public String toString()
	{
		String magazines = String.format("%25s %10s %7.2f", magazineName, magazineFormat, magazinePrice);
		
		return magazines;
	}
}
