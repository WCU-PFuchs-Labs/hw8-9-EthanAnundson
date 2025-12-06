import java.util.Scanner;
import binary.*;

public class TestGP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for data file
        System.out.print("Enter data file name: ");
        String dataFile = scanner.nextLine();

        // Load the data into your GP system
        // (Assumes you have a method like GPTree.loadData(String filename))
        GPSystem gp = new GPSystem();
        gp.loadData(dataFile);

        // Create initial population of 500 trees
        int populationSize = 500;
        gp.initializePopulation(populationSize);

        // Loop for 50 generations
        int generations = 50;
        for (int gen = 1; gen <= generations; gen++) {
            // Evaluate fitness for the current population
            gp.evaluateFitness();

            // Print the best tree and its fitness
            GPTree bestTree = gp.getBestTree();
            double bestFitness = bestTree.getFitness();

            System.out.println("Generation " + gen + ":");
            System.out.println("Best Tree: " + bestTree);
            System.out.printf("Best Fitness: %.2f%n", bestFitness);
            System.out.println();

            // Evolve to the next generation
            gp.evolve();
        }

        scanner.close();
    }
}
