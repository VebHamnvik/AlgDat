import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.in;

// WordBST: Binært søketre med ord og ordfrekvenser
public class WordHash
{

    private HashMap<String, Integer> hashMap;


    // WordBST(): Konstruktør som lager et tomt søketre
    public WordHash()
    {
	    hashMap = new HashMap<>();
    }

    // size(): Antall ord som er lagret i treet
    public int size()
    {
	    return hashMap.size();
    }

    // insert(): Setter inn ny forekomst av et ord
    public void insert(String ord)
    {
        if (!hashMap.containsKey(ord)){
            hashMap.put(ord, 1);
        }
        else {
            int tall = hashMap.get(ord);
            hashMap.replace(ord, tall+1);
        }

    }

    // search(): Søk etter et ord. Skriv ut ordet og ordfrekvensen
    // hvis det finnes i søketreet.
    public void search(String ord)
    {
        if (hashMap.containsKey(ord))
            System.out.println(ord + " appears " + hashMap.get(ord) + " times in the text.");
    }
    
    // print(): Alfabetisk utskrift av hele søketreet. Kaller en
    // rekursiv metode som gjør selve utskriften.
    public void print()
    {
	print(hashMap);
    }

    // print(): Rekursiv utskrift av hele søketreet med rot i "rot"
    private void print(HashMap<String, Integer> map)
    {
        TreeMap<String, Integer> sortert = new TreeMap<>(map);

        for (Map.Entry<String, Integer> entry : sortert.entrySet()){
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
	WordHash wHash = new WordHash();

	// Leser alle ordene på filen og legger inn i treet
	String ord = wR.nextWord();
	while (ord != null)
	{
	    wHash.insert(ord);
	    ord = wR.nextWord();
	}
	// Skriver ut antall ulike ord som fantes i filen
	System.out.println(wHash.size() + " unique words " +
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
		wHash.search(ord.toLowerCase());
	    }
	    else if (valg == 2)
		wHash.print();
	}
    }
}
