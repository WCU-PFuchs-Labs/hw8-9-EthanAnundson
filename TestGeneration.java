import java.util.*;
import java.io.*;
import binary.*;

public class TestGeneration {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //System.out.print("Enter data file name: ");
        String fileName = sc.nextLine();
        int numTrees = 500;
        int maxDepth = 5;
        Generation gen = new Generation(numTrees, maxDepth, fileName);
        gen.evalAll();
        gen.printBestTree();
        System.out.println("");
        gen.printBestFitness();
        System.out.println("");
        ArrayList<GPTree> topTen = gen.getTopTen();
        System.out.print("Top Ten Fitness Values:");
        printTopTenFitness(topTen);
        for (int g = 0; g< 10; g++){
            System.out.print(topTen.get(g)+" ");
        }
    }
}
