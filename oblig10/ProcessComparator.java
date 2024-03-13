import java.util.Comparator;

public class ProcessComparator implements Comparator<Process>
{
    @Override
    public int compare(Process p1, Process p2)
    {
        if (p1.runTime > p2.runTime)
            return 1;
        if (p1.runTime < p2.runTime)
            return -1;
        return 0;
    }

    /* Dette er compare før oppgave 3:
    @Override
    public int compare(Process p1, Process p2)
    {
        if (p1.priority > p2.priority)
            return -1;
        if (p1.priority < p2.priority)
            return 1;
        return 0;
    }
     */
}
