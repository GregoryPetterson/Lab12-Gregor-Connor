import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BreakString {
    public static void main(String[] args) {
        // int len = Integer.parseInt(args[0]);
        // int breakPoints = Integer.parseInt(args[1]);

        int len = 20;
        int numberOfBreaks = 3;
        List<Integer> breaks = new ArrayList<Integer>();
        breaks.add(2);
        breaks.add(8);
        breaks.add(10);     
        System.out.println(BreakString.breakStrings(len, numberOfBreaks, breaks));

        int[] p = {0, 1, 5, 8, 9, 12};

        
        //System.out.println(breakRod(p, 5));

        
        //BreakString str = new BreakString(len, breakPoints);
    }

    public static int breakStrings(int n, int numberofBreaks, List<Integer> listOfBreaks)
    {
        if(n == 0) return 0;
        if(numberofBreaks == 0) return 0;
        int q = Integer.MAX_VALUE;
        for (int i = 0; i < numberofBreaks; i++) {
            System.out.println("i: " + i);
            System.out.println("Current p (before remove()): " + listOfBreaks);
            System.out.println("Current q: " + q);
            System.out.println("Current n: " + n);
            System.out.println("Current b: " + numberofBreaks);
            
            int currentBreak = listOfBreaks.get(i);
            System.out.println("Current Break: " + currentBreak);
            List<Integer> p2 = listOfBreaks.subList(0, i);
            System.out.println(p2);
            List<Integer> p3 = listOfBreaks.subList(i+1, numberofBreaks);
            System.out.println(p3);
            System.out.println("-------------------------------------------------------------------");
            q = Math.min(q , n + breakStrings(currentBreak, p2.size(), p2) + breakStrings(n - currentBreak, p3.size(), p3));
        }
        return q;
    }


    public static int breakRod(int[] p, int n){
        if (n==0) return 0;
        int q = -12;
        for (int i = 1; i <= n; i++) {
            System.out.println("i: " + i);
            System.out.println("n: " + n);
            System.out.println(("q; " + q));
            System.out.println("-------------------------------------------------------------------");
            q = Math.max(q, p[i] + breakRod(p, n-i));
            
        }
        System.out.println("CR(p, " + n + ") evaluates to: " + q);
        return q;
    }




    
}