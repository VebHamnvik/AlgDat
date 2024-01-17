import java.sql.SQLOutput;
import java.util.*;
import java.util.Scanner;


class oblig_2 {
    public static String krypter(String S){
        System.out.println("Dette er ordet etter ROT13: " + S + "\n");
        int n = S.length();
        String T = "";

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < n/2; i++){
            char x = S.charAt(i);
            queue.offer(x);
        }

        Stack<Character> stack = new Stack<>();
        for (int j = n/2; j < n; j++){
            char x = S.charAt(j);
            stack.push(x);
        }
        System.out.println("Dette er queue: " + queue);
        System.out.println("Dette er stack: " + stack + "\n");

        while (!stack.isEmpty()) {
            char stackChar = stack.pop();
            T += stackChar;
            if (!queue.isEmpty()) {
                char queueChar = queue.poll();
                T += queueChar;
            }
        }


        System.out.println("Dette er ordet etter kryptering: " + T + "\n");

        return T;
    }

    public static String ROT13(String S)
    {
        char[] C = S.toCharArray();
        for (int i = 0; i < S.length(); i++)
        {
            char c = C[i];
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            C[i] = c;
        }
        return String.valueOf(C);
    }

    public static String dekrypter(String S){
        String T = "";
        int n = S.length();

        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for (int i = 1; i<=n-1; i+=2){
            queue.offer(S.charAt(i));
        }
        System.out.println("Dette er queue: " + queue);

        for (int j = n/2, index = 0; j < n; j++, index+=2){
            stack.push(S.charAt(index));
        }
        System.out.println("Dette er stack: " + stack);

        while (!queue.isEmpty()) {
            char queueChar = queue.poll();
            T += queueChar;
        }

        while (!stack.isEmpty()) {
            char stackChar = stack.pop();
            T += stackChar;
        }

        String result = ROT13(T);
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Skriv inn ordet du vil kryptere: ");
        String inputOrd = input.nextLine();

        String ordROT13 = ROT13(inputOrd);
        String ordKryptert = krypter(ordROT13);
        System.out.println("Dette er originalordet: " + dekrypter(ordKryptert));
    }
}
