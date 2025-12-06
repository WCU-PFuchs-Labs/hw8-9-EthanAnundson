import java.util.*;
import java.io.*;
import binary.*;

public class TestGeneration {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        //System.out.print("Enter data file name: ");
        String fileName = kb.nextLine();

        // Number of trees and depth (you can change depth for debugging)
        int numTrees = 500;
        int maxDepth = 5;

        // Create the generation
        Generation gen = new Generation(numTrees, maxDepth, fileName);

        // Evaluate and sort
        gen.evalAll();

        // Print best GPTree
        //System.out.println("===== BEST TREE =====");
        gen.printBestTree();
        gen.printBestFitness();

        // Get top 10 trees
        ArrayList<GPTree> topTen = gen.getTopTen();

        // Print formatted fitness values
        System.out.println("Top Ten Fitness Values:");
        printTopTenFitness(topTen);
    }

    /**
     * Prints the list of top-ten fitness values formatted like:
     *
     *    Top Ten Fitness Values:
     *    1.23, 1.57, 2.44, 3.10, ...
     *
     * Exactly two decimal places, comma-separated.
     */
    private static void printTopTenFitness(ArrayList<GPTree> top) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < top.size(); i++) {
            double fit = top.get(i).getFitness();

            // Format: exactly two decimals
            sb.append(String.format("%.2f", fit));

            if (i < top.size() - 1)
                sb.append(", ");
        }

        System.out.print(sb.toString());
    }
}
