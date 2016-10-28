//Pearson Radu
//CS1027B
//Main Class
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// Local variables
		StringTokenizer tokenizer;
		Double magazinePrice;
		String magazineName;
		String magazineFormat;
		Integer customerNumber;
		String customerFirstName;
		String customerLastName;
		Magazine magazine;
		Magazine targetMagazine;
		Integer numberOfMagazines;

		// You will need to change these directories to yours

		InStringFile magazineReader = new InStringFile("src/magazine.txt");
		InStringFile customerReader = new InStringFile("src/customer.txt");

		// Create a magazineCollection object

		MagazineCollection magazineCollection = new MagazineCollection();

		System.out.format("%n%n%40s%n-----------------------------------------%n", "PooPoo Magazines Subscription Information");
		Integer magCt = 0;
		Integer custCt = 0;
		Integer numTokens;
		Double customerBill = 0.0;

		do {
			magCt++;
			String magazineLine = magazineReader.read();
			tokenizer = new StringTokenizer(magazineLine);
			magazineName = tokenizer.nextToken();
			magazineFormat = tokenizer.nextToken();
			magazinePrice = Double.parseDouble(tokenizer.nextToken());

			// Your code: create magazine object with the read data
			// Then add the magazine object to the magazineCollection object
			
			magazine = new Magazine(magazineName, magazineFormat, magazinePrice);

			magazineCollection.addMagazine(magazineName, magazineFormat, magazinePrice);

			System.out.format("%3d%27s %10s %7.2f %n", magCt, magazine.getMagazineName(), magazine.getMagazineFormat(), magazine.getMagazinePrice());

		} while (!magazineReader.endOfFile());

		// Use magazineCollection's getter method to get the number of Magazines
		// and insert that value in the following print statement

		numberOfMagazines = magazineCollection.getNumberOfMagazines();

		System.out.format("%n%d magazines added to Magazine object%n%n", numberOfMagazines);
		magazineReader.close();

		// Use magazineCollection's toString to print out the magazineCollection

		System.out.println(magazineCollection);

		System.out.format("%n%nPooPoo Customer Bills%n%n");
		do {
			String customerLine = customerReader.read();
			tokenizer = new StringTokenizer(customerLine);
			numTokens = tokenizer.countTokens();

			if (numTokens == 3) {
				custCt++;
				customerFirstName = tokenizer.nextToken();
				customerLastName = tokenizer.nextToken();
				customerNumber = Integer.parseInt(tokenizer.nextToken());
				System.out.format("---------------------------------------%n");
				System.out.format("%n%3d%10s %10s %d %n", custCt,
						customerFirstName, customerLastName, customerNumber);
			} else if (numTokens == 2) {
				magazineName = tokenizer.nextToken();
				magazineFormat = tokenizer.nextToken();

				// Given the magazineName and magazineFormat use magazineCollection's
				// searchMagazinePrice method to get magazinePrice.

				magazinePrice = magazineCollection.searchMagazinePrice(magazineName, magazineFormat);

				customerBill += magazinePrice;
				System.out.format("%20s %10s %7.2f %n", magazineName, magazineFormat, magazinePrice);
			} else if (numTokens == 0) {
				System.out.format("Total bill: %7.2f%n%n", customerBill);

				// prepare for next customer
				customerBill = 0.0;
			}

		} while (!customerReader.endOfFile());
		customerReader.close();
		System.out.format("%d customers processed%n", custCt);
	}
}
