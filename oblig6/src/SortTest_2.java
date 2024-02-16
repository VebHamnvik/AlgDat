import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;

public class SortTest_2 {

        public static int[] makeArray(int n){
            int A[] = new int[n];
            Random r = new Random();
            for (int i = 0; i < n/3; i++)
                A[i] = i;
            for (int i = n/3; i < (n/3)*2; i++)
                A[i] = r.nextInt(2*n);
            int o = 0;
            for (int i = (n/3)*2; i < n; i++) {
                A[i] = (n/3)+o;
                o++;
            }
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
            int v = 15;
            int A[] = makeArray(v);
            System.out.println("\nArray:");
            for (int i = 0; i < A.length; i++){
                System.out.print(A[i] + ", ");
            }
            LinkedList L = makeList(v, A);
            System.out.println("\nLinkedList");
            for (int i = 0; i < L.size(); i++){
                System.out.print(A[i] + ", ");
            }
            System.out.println("\nn          tA      tL     tL/tA");

            int w = 1000000;
            for (int i = 0; i <  10; i++){
                int B[] = makeArray(w);
                LinkedList G = makeList(w, B);
                sortArrayAndList(w, B, G);
                w += 1000000;
            }



        }
    }
