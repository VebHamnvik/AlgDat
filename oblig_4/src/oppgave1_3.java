import java.util.Scanner;

public class oppgave1_3 {

    public static long C_rekursiv(int n, int m){
        // Antar at n > m, og 0<m<n så legger ikke inn noen sjekker
        if (m == 0 || m == n) {
            return 1;
        }

        return C_rekursiv(n-1, m) + C_rekursiv(n-1, m-1);
    }

    public static void pascal_rad(int n) {
        for (int m = 0; m <= n; m++){
            System.out.println("C(" + n + "," + m +"): " + C_rekursiv(n, m));
        }
    }

    public static void main(String[] args) {
        System.out.println(C_rekursiv(6,4));

        Scanner input = new Scanner(System.in);
        System.out.print("n: ");
        int inputTall = input.nextInt();
        pascal_rad(inputTall);
    }
}
