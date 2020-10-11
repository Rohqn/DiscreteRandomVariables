package com.rohan.maths;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Calculates the Expected Value, E(X) when n fair six-sided dice are rolled. X is defined as the largest of the three values.
 */
public class DiceExpectedValue {

    /** @noinspection MismatchedQueryAndUpdateOfCollection*/
    public static void main(String[] args) {
        int numOfDice;
        List<int[]> X = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of dice: ");
        try {
            numOfDice = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println("You must enter a valid integer!");
            System.out.println("Defaulting to 3 dice.");
            ex.printStackTrace();
            numOfDice = 3;
        }

        System.out.println("Number of dice: " + numOfDice);
        long time = System.currentTimeMillis();

        long a = Long.parseLong("1".repeat(numOfDice));
        long b = Long.parseLong("6".repeat(numOfDice));

        for (long i = a; i <= b; i++) {
            String strI = String.valueOf(i);
            if (strI.contains("0") || strI.contains("7") || strI.contains("8") || strI.contains("9")) {
                continue;
            }

            char[] chars = strI.toCharArray();
            int[] arr = new int[numOfDice];

            for (int c = 0; c < chars.length; c++) {
                arr[c] = Integer.parseInt(String.valueOf(chars[c]));
            }
            X.add(arr);
        }

        System.out.println();
        System.out.println("Looped through all " + X.size() + " combinations.");
        System.out.println();
        HashMap<Integer, List<int[]>> map = new HashMap<>();

        for (int[] array : X) {
            int x = getLargest(array);
            if (map.containsKey(x)) {
                map.get(x).add(array);
            }
            else {
                ArrayList<int[]> list = new ArrayList<>();
                list.add(array);
                map.put(x, list);
            }
        }

        for (int i : map.keySet()) {
            System.out.println("Highest: " + i);
            for (int[] j : map.get(i)) {
                StringBuilder sb3 = new StringBuilder();
                for (int k = 0; k < j.length; k++) {
                    sb3.append(j[k]);
                    if (k != j.length - 1) {
                        sb3.append(" ");
                    }
                }
                System.out.println(sb3.toString());
            }
            System.out.println();
        }

        DecimalFormat format = new DecimalFormat("#,###.##");

        HashMap<Integer, Double> probabilities = new HashMap<>();
        double expectedValue = 0;
        for (int i : map.keySet()) {
            double d = ((double) map.get(i).size() / Math.pow(6, numOfDice));
            System.out.println("P(X=" + i + ") = " + format.format(d) + " (" + d + ")");
            probabilities.put(i, d);
            expectedValue += d * i;
        }

        System.out.println();
        System.out.println("E(X) = \u03A3xP(X=x)");

        System.out.println("E(X) = " + expectedValue);
        System.out.println("Q.E.D :)");
        System.out.println("Done! (in " + (System.currentTimeMillis() - time) + " ms)");
    }

    private static int getLargest(int[] array) {
        int highest = 0;

        for (int i : array) {
            if (i > highest) {
                highest = i;
            }
        }
        return highest;
    }
}
