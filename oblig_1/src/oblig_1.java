import java.util.Scanner;

class oblig_1
{
    public static void lineær(long n)
    {
        int tmp = 1;
        for (long i = 0; i < n; i++)
            tmp *= 1;
    }

    public static void kvadratisk(long n)
    {
        int tmp = 1;
        for (long i = 0; i < n; i++)
            for (long j = 0; j < n; j++)
                tmp *= 1;
    }

    public static int logaritmisk(long n)
    {
        int tmp = 1, iterasjoner = 0;
        for (long i = n; i > 0; i /= 2, iterasjoner++)
            tmp *= 1;
        return iterasjoner;
    }

    public static void superlineær(long n){
        int tmp = 1, iterasjoner = 0;
        for (long i = 0; i < n; i++)
            for (long j = n; j > 0; j /= 2, iterasjoner++)
                tmp *= 1;
    }

    public static void kubisk(long n){
        int tmp = 1;
        for (long i = 0; i < n; i++)
            for ( long j = 0; j < n; j++)
                for ( long k = 0; k < n; k++)
                    tmp *= 1;
    }

    public static void eksponentiell(long n){
        int tmp = 1;
        double potens = Math.pow(2, n);
        for (long i = 0; i < potens; i++)
            tmp *= 1;
    }

    public static void kombinatorisk(long n){
        int tmp = 1;
        double fakultet = 1;
        for (long i = 1; i <= n; i++)
            fakultet *= i;
        for (long j = 0; j < fakultet; j++)
            tmp *= 1;
    }

    public static void main(String[] args)
    {
        Scanner S = new Scanner(System.in);
        long n, T, T1, T2;
        int valg, iterasjoner = 0;

        System.out.print("1:O(n) 2:O(n²) 3:O(log_n) 4:0(n*log_n) 5:0(n^3) 6:0(2^n) 7:0(n!)? ");
        valg = S.nextInt();
        System.out.print("n? ");
        n = S.nextLong();

        T1 = System.currentTimeMillis();
        if (valg == 1)
            lineær(n);
        else if (valg == 2)
            kvadratisk(n);
        else if (valg == 3)
            iterasjoner = logaritmisk(n);
        else if (valg == 4)
            superlineær(n);
        else if (valg == 5)
            kubisk(n);
        else if (valg == 6)
            eksponentiell(n);
        else if (valg == 7)
            kombinatorisk(n);
        T2 = System.currentTimeMillis();

        T = T2 - T1;
        System.out.print("T = " + T+ " ms");
        if (valg == 3)
            System.out.print(" (" + iterasjoner + ")");
        System.out.println();
    }
}
