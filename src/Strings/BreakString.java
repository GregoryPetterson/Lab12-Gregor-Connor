package Strings;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class BreakString {
    Hashtable<String, Integer> memo = new Hashtable<String, Integer>();
    
    public static void main(String[] args) {
        // int len = Integer.parseInt(args[0]);
        // int breakPoints = Integer.parseInt(args[1]);
        BreakString potato = new BreakString();

        int len = 30;
        int numberOfBreaks = 6;
        List<Integer> breaks = new ArrayList<Integer>();
        breaks.add(2);
        breaks.add(8);
        breaks.add(10);  
        breaks.add(20); 
        breaks.add(26); 
        breaks.add(28); 
        System.out.println(potato.breakStringsMemo(len, numberOfBreaks, breaks));
        System.out.println(potato.memo);

        int[] p = {0, 1, 5, 8, 9, 12};

        
        //System.out.println(breakRod(p, 5));

        
        //BreakString str = new BreakString(len, breakPoints);
    }

    /**
     * This is our algorithm without memoization. breakStrings takes a string length, number of breaks, and list of breaks
     * and determines the best possible cost for breaking it into the specified breaks.
     * @param n n is the length of the theoretical string
     * @param numberofBreaks numberOfBreaks is the number of breaks to consider in the list of breaks
     * @param listOfBreaks listOfBreaks is a List<Integer> of indicies we want to cut a string at
     * @return Returns the best value possible for cutting a string of length n at the specified break points. (As an integer).
     */
    public static int breakStrings(int n, int numberofBreaks, List<Integer> listOfBreaks)
    {
        if(n <= 0 || numberofBreaks == 0) return 0;

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

    /**
     * This is our algorithm with a Memoization implementation. breakStringsMemotakes a string length, number of breaks, and list of breaks
     * and determines the best possible cost for breaking it into the specified breaks. This method checks to see if a subproblem has been solved before
     * and if it has, it references the classes hashTable to find the value. If it doesn't find a value, then it does all the work for the subproblem and
     * stores the best value in the Hashtable, then it returns the answer.
     * 
     * Current Issue: 
     * This is eventually supposed to output the solution for finding the best value (order to make the breaks).
     * @param n N is the length of the theoretical String
     * @param numberofBreaks numberOfBreaks is the amount of breaks in listOfBreaks
     * @param listOfBreaks listOfBreaks is a List<Integer> of indicies we want to cut a string at
     * @return Returns the best value possible for cutting a string of length n at the specified break points. (As an integer).
     */
    public int breakStringsMemo(int n, int numberofBreaks, List<Integer> listOfBreaks)
    {
        if(n <= 0 || numberofBreaks == 0) return 0;
        if(memo.get(n + listOfBreaks.toString()) != null){
            //System.out.println("Skipping subtree..." + n + listOfBreaks.toString());
            return memo.get(n + listOfBreaks.toString());


        } 
        int q = Integer.MAX_VALUE;
        for (int i = 0; i < numberofBreaks; i++) {    
                 
            int currentBreak = listOfBreaks.get(i);
            List<Integer> p2 = new ArrayList<>(listOfBreaks.subList(0, i));
            List<Integer> p3 =  new ArrayList<>(listOfBreaks.subList(i+1, numberofBreaks));
            for (int j = 0; j < p3.size(); j++) {
                p3.set(j, p3.get(j) - currentBreak);
            }
            
            
            q = Math.min(q , n + breakStringsMemo(currentBreak, p2.size(), p2) + breakStringsMemo(n - currentBreak, p3.size(), p3));
            
            // System.out.println("String Length : " + n);
            // System.out.println("Current Cuts : " + listOfBreaks);
            // System.out.println("Cutting at: " + currentBreak);
            // System.out.println("Two halves: " + currentBreak + " and " + (n - currentBreak));
            // System.out.println("Two halves: " + p2 + " and " + p3);
            // System.out.println("Resulting Value: " + q);
            // System.out.println("-------------------------------------------------------------------");
        }
        this.memo.put(n + listOfBreaks.toString(), q);
        System.out.println("Best outcome for: " + n + " " + listOfBreaks + "---> " + q);
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