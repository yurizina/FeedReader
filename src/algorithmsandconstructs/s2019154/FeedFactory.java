package algorithmsandconstructs.s2019154;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import algorithmsandconstructs.FeedFactoryInterface;
import algorithmsandconstructs.FeedInterface;
import algorithmsandconstructs.FeedItem;

public class FeedFactory implements FeedFactoryInterface {
	
	// Set of Attributes

	private String content;
	private String title;
	private int lines = 1;

	// Creating a new object, when we use "new", a new instance of the class is created 
	//This object created to class Feed. 
	Feed feed = new Feed();
	
	// Set of methods

	public FeedInterface createFeed(BufferedReader in) throws IOException {

		
		//Start the method with a try-catch to check if the document to be read is available and exists. 
		try {
			// Check if there is content inside the file or not;
			//The file and the BufferedReader method are in the FeedReader class, there is no need to bring/initiate
			//this method here or bring the file, we can access both here
			
			while ((content = in.readLine()) != null) {

				
				//Is desired to storage every two lines in the file, which will be title and content respectively 
				//The % will return lines which the devision for 2 there is no leftover 
				if (lines % 2 == 0) {

				    //Once the lines are read and there is content, we have to storage in new FeedItem object
					FeedItem feedItem = new FeedItem(title, content);
					//Now that we have the title and content storage we can pass the value for the object feed, or populate the object
					feed.addItem(feedItem);
					

				}
				//To read continuing we have to say that String title is equal to content and jump for the next line, 
				//which will be a new line type String title
				title = content;
				lines++;

			}

		} catch (IOException e) {
			//In case the file does not exist we have to send a message to the console, saying that this file does not exist
			//IOException deals with input/output methods, once we read the file we want to manipulate we want to output the results afterwards 

			throw new FileNotFoundException("File Not Found!");

		}

		//This whole method have to return an instance for the Feed class
		return feed;
	}

}
