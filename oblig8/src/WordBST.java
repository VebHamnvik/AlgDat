import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.System.in;

// WordBST: Binært søketre med ord og ordfrekvenser
public class WordBST
{
    // WordNode: Indre klasse for en node i søketreet
    class WordNode
    {
        WordNode left = null, right = null;
        String word;

        int count = 0;

        public WordNode(String word){
            this.word = word;
            this.count++;
        }

        public void print(){
            System.out.println("The word: " + this.word + ", occurs " + this.count + " times in the text. The left subnode is: " + this.left.word + " and the right subnode is: " + this.right.word);

        }
    }
    
    private WordNode rot; // Roten i hele søketreet
    private int n;        // Antall noder i hele treet

    // WordBST(): Konstruktør som lager et tomt søketre
    public WordBST()
    {
	rot = null;
	n = 0;
    }

    // size(): Antall ord som er lagret i treet
    public int size()
    {
	return n;
    }

    // insert(): Setter inn ny forekomst av et ord
    public void insert(String ord)
    {
        //Hvis det er første ordet som er lest, så settes det som rot
        if (this.rot == null) {
            this.rot = new WordNode(ord);
            this.n ++;
            return;
        }

        WordNode current = this.rot;

        while (true){
            //Sammenligner ordene
            int alfabetisk = ord.compareTo(current.word);

            //Hvis det er samme ord så øker counten med 1
            if (alfabetisk == 0){
                current.count ++;
                return;
            }

            //Hvis ord kommer før current.word i alfabetisk rekkefølge
            if (alfabetisk < 0){
                //Hvis tomt, så lag en ny node
                if (current.left == null){
                    current.left = new WordNode(ord);
                    this.n++;
                    return;
                }
                //Ellers sett denne noden som current og kjør igjen
                current = current.left;
            }

            //Hvis ord kommer etter current.word i alfabetisk rekkefølge
            if (alfabetisk > 0){
                //Hvis tomt, så lag en ny node
                if(current.right == null){
                    current.right = new WordNode(ord);
                    this.n++;
                    return;
                }
                //Ellers sett denne noden som current og kjør igjen
                current = current.right;
            }
        }
    }

    // search(): Søk etter et ord. Skriv ut ordet og ordfrekvensen
    // hvis det finnes i søketreet.
    public void search(String ord)
    {
        WordNode current = this.rot;

        while(current != null){
            int comparison = ord.compareTo(current.word);
            if (comparison == 0) {
                System.out.println(current.word + " appears " + current.count + " times in the text");
                return;
            }

            else if (comparison < 0)
                current = current.left;

            else
                current = current.right;
        }
    }
    
    // print(): Alfabetisk utskrift av hele søketreet. Kaller en
    // rekursiv metode som gjør selve utskriften.
    public void print()
    {
	print(rot);
    }

    // print(): Rekursiv utskrift av hele søketreet med rot i "rot"
    private void print(WordNode rot)
    {
	    if (rot != null){
            print(rot.left);
            System.out.println(rot.word + ": " + rot.count);
            print(rot.right);
        }
    }
    
    // main(): Testprogram
    public static void main (String argv[])
    {
	// Leser filnavn fra bruker
	Scanner scan = new Scanner(in);
	System.out.print("File? ");
	String fileName = scan.next();
    String path = Paths.get("").toAbsolutePath().toString();

	// Oppretter ordleser og tomt søketre
	WordReader wR = new WordReader(path+"/oblig8/data/"+fileName);
	WordBST wBST = new WordBST();

	// Leser alle ordene på filen og legger inn i treet
	String ord = wR.nextWord();
	while (ord != null)
	{
	    wBST.insert(ord);
	    ord = wR.nextWord();
	}
	// Skriver ut antall ulike ord som fantes i filen
	System.out.println(wBST.size() + " unique words " +
			   "read from file " + fileName);

	// Menyvalg for å teste programmet
	int valg = 0;
	while(valg != 3)
	{
	    System.out.print("\n1:Search, 2:Print, 3:Quit ? ");
	    valg = scan.nextInt();
	    if (valg == 1)
	    {
		System.out.print("Search for? ");
		ord = scan.next();
		wBST.search(ord.toLowerCase());
	    }
	    else if (valg == 2)
		wBST.print();
	}
    }
}
