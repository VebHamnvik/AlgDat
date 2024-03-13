import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class oblig10
{
    public static PriorityQueue<Process> read(String fileName)
    {
        Comparator<Process> pC = new ProcessComparator();
        PriorityQueue<Process> pq = new PriorityQueue<>(pC);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] values = line.split("\\s+");
                if (values.length == 3){
                    String name = values[0];
                    int priority = Integer.parseInt(values[1]);
                    int runTime = Integer.parseInt(values[2]);
                    pq.add(new Process(name, priority, runTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pq;
    }

    public static void run(PriorityQueue<Process> pQ)
    {
        System.out.printf("%-4s %-4s %-5s %-4s %s%n", "Pri.", "CPU", "Start", "End", "Name");
        int start = 0;
        int end = 0;
        int sumTime = 0;
        int count = 0;
        while (!pQ.isEmpty()){
            Process process = pQ.poll();
            end += process.runTime;
            sumTime += end;
            System.out.printf("%-4d %-4d %-5d %-4d %s%n", process.priority, process.runTime, start, end, process.name);
            start = end;
            count++;
        }
        float average = (float)sumTime/count;
        System.out.println("\nAverage turnaround time was: " + average);
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("File? ");
        String fileName = scan.nextLine();

        PriorityQueue<Process> pQ = read(fileName);
        System.out.println("Read " + pQ.size() +
                " processes from " + fileName + "\n");
        run(pQ);
    }
}


