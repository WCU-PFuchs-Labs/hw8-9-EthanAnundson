import java.util.*;

public class TestGP {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        System.out.print("Enter data file name: ");
        String fileName = kb.nextLine();

        final int POP_SIZE = 500;
        final int MAX_DEPTH = 5;
        final int NUM_GENERATIONS = 50;

        // Create the initial population
        Generation gen = new Generation(POP_SIZE, MAX_DEPTH, fileName);

        // Evaluate initial generation
        gen.evalAll();

        // Run 50 generations
        for (int g = 1; g <= NUM_GENERATIONS; g++) {

            System.out.println("\nGeneration " + g + ":");
            gen.printBestTree();
            gen.printBestFitness();

            // Evolve popula
