import java.util.Scanner;

public class oppgave5_6 {

    public static long C_iterativ(int n, int m) {
        //Sjekker at verdiene som er sendt inn er valid
        if (m < 0 || m > n) {
            return 0;
        }

        //Lager en 2D array for å simulere trekanten og ta vare på alle
        long[][] pascal = new long[n + 1][m + 1];

        //For alle rader lik n
        for (int i = 0; i <= n; i++) {
            //For alle kolonner lik
            for (int j = 0; j <= Math.min(i, m); j++) {
                //Hvis j= 0(m=0) eller j = i(m=n) så skal elementet være 1. Dette håndterer de ytterste verdiene
                if (j == 0 || j == i) {
                    pascal[i][j] = 1;
                }
                //Hvis det ikke er et ytterelement, summer verdiene i de to verdiene i raden over som har samme m verdi og m-1
                else {

                    pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
                }
            }
        }
        //Printer ut ett og ett element i trekanten med deres plass i trekanten slik at det er lettere å se
        //Kommenterer dette ut når jeg skal prøve med store n verdier
        /*for (int k = 0; k <= n; k++){
            for (int l = 0; l <= Math.min(k, m); l++) {
                System.out.println("Pascal[" + k + "][" + l + "] = " + pascal[k][l]);
            }
        }*/
        return pascal[n][m];
    }

    public static void pascal_rad(int n) {
        for (int m = 0; m <= n; m++){
            System.out.println("C(" + n + "," + m +"): " + C_iterativ(n, m));
        }
    }

    public static void main(String[] args) {
        System.out.println(C_iterativ(6, 4));
        Scanner input = new Scanner(System.in);
        System.out.print("n: ");
        int inputTall = input.nextInt();
        pascal_rad(inputTall);

    }
}
