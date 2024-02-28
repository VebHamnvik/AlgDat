import java.io.*;
import java.util.Scanner;

// WordReader: Read a text file word-by-word
// Author: Jan Høiberg, 2024
// Note: All non-alphabetic characters are ignored

public class WordReader
{
    private BufferedReader reader; // Line-by-line file reader
    private String S[];            // All words on last read line
    private int numWords;          // Number of words on last line
    private int currentWord;       // Next word to be delievered
    private boolean finished;      // True if all words on file read

    // WordReader(): Constructor, opens file for reading
    public WordReader(String fileName)
    {
	currentWord = numWords = 0;
	finished = false;
	try {reader = new BufferedReader(new FileReader(fileName));}
	catch (IOException e) {e.printStackTrace();}
    }

    // nextWord(): Returns next word on file, null if all words read
    public String nextWord()
    {
	// Finished reading all words on file?
	if (finished)
	    return null;

	// If there are no more words left on the last line read from
	// file, then read a new line and split it into separate words
	if (currentWord == numWords)
	{
	    String line = "";
	    // Read new line from file, skip both blank/empty lines
	    // and lines with no alphabetical characters
	    while (line.length() == 0)
	    {
		// Try reading a line of text
		try {line = reader.readLine();}
		catch (IOException e) {e.printStackTrace();}
		// No more words on file?
		if (line == null)
		{
		    finished = true;
		    return null;
		}
		// Replace non-alphabetic characters with single space
		line = line.replaceAll("[^\\p{IsAlphabetic}]+", " ");
		// Trim off leading and trailing whitespace
		line = line.trim();
	    }
	    // Convert line to lowercase
	    line = line.toLowerCase();
	    // Split line into array of words
	    S = line.split(" ");
	    // Set number of words on line and the index of the next
	    // word to be returned from the method
	    numWords = S.length;
	    currentWord = 0;
	}
	// Return next word from last read line
	String word = S[currentWord];
	currentWord++;
	return word;
    }

    // main(): Test program, prints all words on a given file
    public static void main (String argv[])
    {
	Scanner scan = new Scanner(System.in);
	System.out.print("File? ");  
	String fileName = scan.nextLine();    

	WordReader wR = new WordReader(fileName);
	String word = wR.nextWord();
	while (word != null)
	{
	    System.out.println(word);
	    word = wR.nextWord();
	}
    }
}
