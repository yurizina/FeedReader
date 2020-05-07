package algorithmsandconstructs.s2019154;

import java.util.ArrayList;
import java.util.Collection;

import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

//We can access the methods from FeedInterface because we have implemented  
public class Feed implements FeedInterface {

	// These lists are private because there is no need to use outside the class
	// These two list are going to be use inside the methods
	private ArrayList<FeedItem> allFeed = new ArrayList<FeedItem>();

	private ArrayList<String> list;
	// We could create the new instance now, but we can also create inside the
	// methods (new ArrayList<>());

	// The method have been override because it has been inherited from the
	// Interface this method
	// In the interface none implementation is written for any class

	@Override
	public void addItem(FeedItem item) {
		// We have to populate the object feed which was created, this method has only
		// to add the parameter item
		allFeed.add(item);

	}

	@Override
	public Collection<String> listTitles() {

		// create the object from the List type String
		list = new ArrayList<>();

		// Now before we continue we have to check if there is the arraylist allFeed,
		// the method before addItem:
		// make sure you add items to the list
		if (!((allFeed) == null)) {

			// If we want to populate the created object list, we need a loop where the size
			// of the array list,
			// will be the same from the allFeed.

			for (int i = 0; i < allFeed.size(); i++) {
				// Now we need to STATE the list
				list.add(allFeed.get(i).getTitle());
			}
		}

		return list;
	}

	// Now we have this Constructor. The parameter String title, this impose to
	// objects to give these values
	// regardless which value attributed beforehand.
	@Override
	public FeedItem getItem(String title) {

		// First of all we create an array of titles, this array will be populate with
		// the method listTitles()
		// Once the method listTitles is also an array we can transfer the information
		// onto this array
		String[] titles = listTitles().toArray(new String[0]);

		// We want to check if a title is true or not, so we have to use a search
		// method;
		// we learned two methods: linear search and binary search,
		// how we are trying to identify a title and we do not care about the order, we
		// will use the linear search
		// best practice would be binary search, however we have to index the code to be
		// sorted
		int titlefound = linearSearch(title, titles);

		// The linearSearch return a integer bigger than zero if title is found
		if (titlefound >= 0) {

			// now that we have position where the title is, we return this value
			return allFeed.get(titlefound);

		} else {
			return null;
		}

	}

	@Override
	public int numItems() {

		// Simple method .size() to check the size of the list.
		return allFeed.size();

	}

	@Override
	public Collection<FeedItem> findItems(String keyword) {
		// Initially, the linearSearch method tried to be used, but a collection is
		// desired instead a unique member.
		// So, in this case, I tried to create an arraylist and a search method with
		// return of array.
		// Both worked, but to STORE the itemFound from each position in the ArrayList
		// of integers, did not work.

		ArrayList<FeedItem> itemsFound = new ArrayList<FeedItem>();
		// This method wants to return all the items which a keyword has been input from
		// file or user, unfortunately I tried to create a method to check the exact word
		//once if a fragment of the word is search the method will return true, for example:
		//If I put: HERE, HER, HE (all words), the method will return true.
		//I tried to put all the words from the feed in an array and check each word, the 
		//result is true, but unfortunately all objects are printed, several times. 
		String k = keyword.toLowerCase().trim();
	    
		// In case the keyword is written with space, also we manipulate the String to
		// all letters to be lowercase
		// can search in the file without worrying about case sensitive

		for (int i = 0; i < allFeed.size(); i++) {

			// will run this method until the arraylist type FeedItem allFeed has reach its size
			if (allFeed.get(i).title.trim().toLowerCase().contains(k)){
				
				itemsFound.add(allFeed.get(i));
				
			} else if (!(allFeed.get(i).title.trim().toLowerCase().contains(k)) 
							&& (allFeed.get(i).content.trim().toLowerCase().contains(k))) {
						//this condition is, the word is not in the title, but maybe in the content
						//If yes, add to the FeedItem itemsFound; 
						itemsFound.add(allFeed.get(i));
			}
					
				//if the keyword is found in the title, they save, otherwise go to next condition
				//itemsFound.add(allFeed.get(i));
				
			
			}
		        
				// string manipulation is essential here, once we set the title and content to
				// have only Lower Case, we define an non-sensitive scenario
				// the method contains is essential to check the word desired in the allFeed array

		    return itemsFound;

		}

	public int linearSearch(String target, String[] titles) {
		// LinearSearch method used from Amilcar classes;

		boolean found = false;
		int i = 0;
		do {
			// This method will check for a title (String) inside the array of titles[]
			// if the item is there, the boolean flag will return a true sign
			// and then the position of the object is return to linearSearch method
			// i++ check positions inside the array[]

			if (titles[i].equals(target)) {
				found = true;
				return i;

			}
			i++;
		} while (!found && i < titles.length);
		// the loop do-while will not stop until run all items inside the array [] or if
		// the item is not found
		return -1;
		// if in the end, run all positions and not found, method return integer -1,
		// which will never be a position in array

	}

}
