import java.util.Scanner;
import java.util.Arrays;

// Springerproblemet
// "Brute-force" løsning, virker bare for små n

public class Springer
{
    private static int n;             // Størrelse på nxn sjakkbrett
    private static int n2;            // Antall ruter på brettet (n^2)
    private static int antFlytt;      // Antall flytt som er gjort
    private static int brett[][];     // nxn-tabell, representerer sjakkbrettet
    private static int LEDIG = 0;     // Verdi som markerer ledig rute

	// Dette har jeg gjort:
	// Definerer ulike måter en springer kan bevege seg. Disse blir kalt i springertur()
	private static final int[] radEndring = {2, 1, -1, -2, -2, -1, 1, 2};
	private static final int[] kolonneEndring = {1, 2, 2, 1, -1, -2, -2, -1};
	// Slutt Vebjørn's kule variabler

    
    // springertur(): Rekursiv metode som prøver å finne en "springertur" med start i
    // rute (i,j).  Returnerer true hvis løsning funnet, false ellers.
    
    public static boolean springertur(int i, int j)
    {
	// Merker av at springer er flyttet til rute (i,j)
	antFlytt++;
	brett[i][j] = antFlytt;

	// Bunn i rekursjonen:
	// Løsningen er funnet hvis vi nå har gjort n² flytt
	if (antFlytt == n2)
	    return true;

	// Prøver rekursivt alle lovlige veier videre, maksimalt åtte
	// rekursive kall på metoden springertur()

	/*** Koden mangler her, skal programmeres i oppgave 1 ***/
	// Dette har jeg gjort:
	for (int k = 0; k < 8; k++) {
		// En for loop som sjekker alle mulige mulige veier en springer kan gå
		int nyI = i + radEndring[k];
		int nyJ = j + kolonneEndring[k];

		// Hvis det nye steget er lovlig ta et skritt til med de nye verdiene for i og j
		if (lovlig(nyI, nyJ)) {
			if (springertur(nyI, nyJ)) {
				return true;
			}
		}
	}
	// Slutt Vebjørn's fantastiske kode
	
	// Hvis vi kommer hit i koden, fantes det ingen springertur
	// med start i rute (i,j). Fjerner flyttet og returnerer false
	// til forrige rekursive nivå
	antFlytt--;
	brett[i][j] = LEDIG;
	return false;
    }

    
    // lovlig(): Hjelpemetode som sjekker om det er lovlig å gå til en
    // rute (i,j)
    
    public static boolean lovlig(int i, int j)
    {
	return (i >= 0 && i < n && j >= 0 && j < n && brett[i][j] == LEDIG);
    }

    
    // skriv(): Metode som skriver ut en løsning på springerproblemet
    
    public static void skriv()
    {
	System.out.println();
	for (int i = 0; i < n; i++)	
	{
	    for (int j = 0; j < n; j++)
		System.out.format(" %2d", brett[i][j]);
	    System.out.println("");
	}
    }

    
    // main(): Hovedprogram som leser størrelse og startposisjon og
    // prøver å finne en springertur.
    
    public static void main(String argv[])
    {
	Scanner in = new Scanner(System.in); 
	System.out.print("Hvor stort brett? - n ? ");
	n = in.nextInt();
	if (n < 1 || n > 9)
	{
	    System.out.println("Feil verdi, bruk 1<=n<=9");
	    System.exit(1);
	}
	 
	System.out.print("Hvilken startposisjon? - i j ? ");
	int i = in.nextInt();
	int j = in.nextInt();
	if (i < 1 || i > n || j < 1 || j > n)
	{
	    System.out.println("Feil verdi, bruk 1<=i|j<=n");
	    System.exit(1);
	}
	
	// Initierer antall flytt til 0 og alle felt på brettet til å
	// være ledige (verdi lik 0)
	antFlytt = 0;
	n2 = n * n;
	brett = new int[n][n];
	for (int a = 0; a < n; a++)
	    Arrays.fill(brett[a], LEDIG);

	// Prøver å finne og skrive ut en springertur
	if (springertur(i-1, j-1))
	    skriv();
	else
	    System.out.println("\nFant ingen springertur");
	System.out.println("");
    }
}
