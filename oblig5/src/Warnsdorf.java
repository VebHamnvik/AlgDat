import java.util.Arrays;
import java.util.Scanner;

public class Warnsdorf
{

    private static int n;             // Størrelse på nxn sjakkbrett
    private static int n2;            // Antall ruter på brettet (n^2)
    private static int antFlytt;      // Antall flytt som er gjort
    private static int brett[][];     // nxn-tabell, representerer sjakkbrettet
    private static int LEDIG = 0;     // Verdi som markerer ledig rute

    private static final int[] radEndring = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] kolonneEndring = {1, 2, 2, 1, -1, -2, -2, -1};

    public static boolean springertur(int i, int j) {
        antFlytt++;
        brett[i][j] = antFlytt;

        if (antFlytt == n2)
            return true;

        int counter = Integer.MAX_VALUE;
        int nesteI = 0, nesteJ = 0;
        int u = i, v = j;

        // Sjekker alle moves fra en gitt posisjon og teller antall
        // lovlige
        for (int k = 0; k < 8; k++) {

            int internal_counter = 0;
            int nyU = u + radEndring[k];
            int nyV = v + kolonneEndring[k];

            if (lovlig(nyU, nyV)) {
                // Teller antall lovlige flytt videre fra (nyU, nyV)
                for (int l = 0; l < 8; l++)
                    if (lovlig(nyU + radEndring[l], nyV + kolonneEndring[l])) {
                        internal_counter++;
                    }

                // Hvis det er færre lovlige moves fra en gitt posisjon enn
                // tidligere funnet vil det antallet bli lagret i counter, og
                // en ny springertur vil kjøres fra den posisjonen
                if (internal_counter < counter) {
                    counter = internal_counter;
                    nesteI = nyU;
                    nesteJ = nyV;
                }
            }
        }

        // Returnerer false hvis ingen mulige veier videre
        if (counter == Integer.MAX_VALUE)
            return false;

        // Ingen backtracking, flytter bare direkte videre til det
        // lovlige feltet med minst flytt videre
        return springertur(nesteI, nesteJ);
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
        // Fjerner n-sjekken slik at man kan bruke store verdier
        /*
        if (n < 1 || n > 9)
        {
            System.out.println("Feil verdi, bruk 1<=n<=9");
            System.exit(1);
        }
        */

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
