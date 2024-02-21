import java.util.*;

class Trenode
{
    int verdi;
    Trenode venstre, høyre;
    int sum;
    Trenode forelder;

    public Trenode(int verdi, Trenode venstre, Trenode høyre)
    {
        this.verdi = verdi;
        this.venstre = venstre; this.høyre = høyre;
        sum = 0;
        forelder = null;
    }
}

public class Oblig_07
{
    // Oppgave 1
    static void settSum(Trenode rot)
    {
        int venstreSum, høyreSum;
        if (rot.venstre == null)
            venstreSum = 0;
        else {
            settSum(rot.venstre);
            venstreSum = rot.venstre.sum;
        }

        if (rot.høyre == null)
            høyreSum = 0;
        else {
            settSum(rot.høyre);
            høyreSum = rot.høyre.sum;
        }

        rot.sum = rot.verdi + høyreSum + venstreSum;
    }

    // Oppgave 2
    static void settForelder(Trenode rot)
    {
        if (rot.venstre != null) {
            rot.venstre.forelder = rot;
            settForelder(rot.venstre);
        }

        if (rot.høyre != null) {
            rot.høyre.forelder = rot;
            settForelder(rot.høyre);
        }

    }

    // Oppgave 3
    static void skrivUt(Trenode rot)
    {
        System.out.println("Verdi:    Sum:    Forelder:");

        if (rot == null)
            return;

        Queue<Trenode> trenodeQueue = new LinkedList<>();
        trenodeQueue.add(rot);

        while(!trenodeQueue.isEmpty())
        {
            Trenode trenode = trenodeQueue.remove();
            if (trenode.forelder == null)
                //System.out.println(trenode.verdi + "        " + trenode.sum + "         *");
                System.out.printf("%-10d%-10d%-10s%n", trenode.verdi, trenode.sum, "*");
            else
                //System.out.println(trenode.verdi + "        " + trenode.sum + "         " + trenode.forelder.verdi);
                System.out.printf("%-10d%-10d%-10d%n", trenode.verdi, trenode.sum, trenode.forelder.verdi);

            if (trenode.venstre != null)
                trenodeQueue.add(trenode.venstre);
            if (trenode.høyre != null)
                trenodeQueue.add(trenode.høyre);

        }
    }

    // Testprogram
    public static void main(String args[])
    {
        Trenode rot =
                new Trenode(8,
                        new Trenode(4,
                                new Trenode(2, null, null),
                                new Trenode(6, null, null)),
                        new Trenode(16,
                                new Trenode(14,
                                        new Trenode(12, null, null),
                                        new Trenode(15, null, null)),
                                new Trenode(18, null,
                                        new Trenode(20, null, null))));

        settSum(rot);
        settForelder(rot);
        skrivUt(rot);
    }
}

