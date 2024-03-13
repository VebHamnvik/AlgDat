import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import static java.lang.System.in;

// WordBST: Binært søketre med ord og ordfrekvenser
public class WordBST
{

    private TreeMap<String,Integer> tre; // Roten i hele søketreet


    // WordBST(): Konstruktør som lager et tomt søketre
    public WordBST()
    {
	tre = new TreeMap<>();
    }

    // size(): Antall ord som er lagret i treet
    public int size()
    {
	    return tre.size();
    }

    // insert(): Setter inn ny forekomst av et ord
    public void insert(String ord)
    {
        //Hvis ordet er i treet fra før så vil verdien øke med 1
        if (tre.containsKey(ord)){
            Integer n = tre.get(ord);
            n++;
            tre.replace(ord, n);
        }
        else {
            tre.put(ord, 1);
        }

    }

    // search(): Søk etter et ord. Skriv ut ordet og ordfrekvensen
    // hvis det finnes i søketreet.
    public void search(String ord)
    {
        if (tre.containsKey(ord))
            System.out.println(ord + " appears " + tre.get(ord) + " times in the text.");
    }
    
    // print(): Alfabetisk utskrift av hele søketreet. Kaller en
    // rekursiv metode som gjør selve utskriften.
    public void print()
    {
	print(tre);
    }

    // print(): Rekursiv utskrift av hele søketreet med rot i "rot"
    private void print(TreeMap<String, Integer> tre)
    {
        for (Map.Entry<String, Integer> entry : tre.entrySet()){
            System.out.println(entry.getKey() + " appears " + entry.getValue() + " times in the text");
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
    // kan være denne må endres på for å få den til å finne txt filen
	WordReader wR = new WordReader(path+fileName);
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
