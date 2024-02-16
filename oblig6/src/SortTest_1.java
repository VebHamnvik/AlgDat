
import java.util.*;

public class SortTest_1
    {
        public static int[] makeArray(int n){
            int A[] = new int[n];
            Random r = new Random();
            for (int i = 0; i < n; i++)
                A[i] = r.nextInt(2*n);
            return A;
        }

        public static LinkedList makeList(int n, int A[]){
            LinkedList L = new LinkedList();
            for (int i = n-1; i >= 0; i--)
                L.addFirst(Integer.valueOf(A[i]));
            return L;
        }

        public static void sortArrayAndList(int n, int A[], LinkedList L) {
            long tA, tL, t;
            // Tar tiden på sortering av array
            t = System.currentTimeMillis();
            Arrays.sort(A);
            tA = System.currentTimeMillis() - t;

            // Tar tiden på sortering av liste
            t = System.currentTimeMillis();
            Collections.sort(L);
            tL = System.currentTimeMillis() - t;

            // Skriver ut resultater
            System.out.printf("%d    %-5d   %-5d   %-5.1f\n",
                    n, tA, tL, (float) tL / (float) tA);
        }


        public static void main(String argv[])
        {
            System.out.println("n          tA      tL     tL/tA");
            int v = 1000000;
            for (int i = 0; i <  10; i++){
                int A[] = makeArray(v);
                LinkedList L = makeList(v, A);
                sortArrayAndList(v, A, L);
                v += 1000000;
            }

        }
    }

