import java.util.*;
import binary.*; // import your GPTree, NodeFactory, DataSet, DataRow classes

public class TestGP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Prompt for data file
        System.out.print("Enter data file name: ");
        String dataFile = scanner.nextLine();

        // Load dataset
        DataSet dataset = new DataSet(dataFile); // assumes DataSet constructor reads CSV

        // Initialize population
        int populationSize = 500;
        int maxDepth = 5; // example max tree depth
        NodeFactory nodeFactory = new NodeFactory(); // assumes this exists
        GPTree[] population = new GPTree[populationSize];

        for (int i = 0; i < populationSize; i++) {
            population[i] = new GPTree(nodeFactory, maxDepth, rand);
        }

        int generations = 50;
        for (int gen = 1; gen <= generations; gen++) {
            // Evaluate fitness
            for (GPTree tree : population) {
                tree.evalFitness(dataset);
            }

            // Sort population by fitness (lowest is best)
            Arrays.sort(population);

            // Print generation info
            System.out.println("Generation " + gen + ":");
            System.out.println("Best Tree: " + population[0].toString());
            System.out.printf("Best Fitness: %.2f%n", population[0].getFitness());

            // Print top 10 fitness values
            System.out.print("Top Ten Fitness Values:\n");
            for (int i = 0; i < 10 && i < population.length; i++) {
                System.out.printf("%.2f", population[i].getFitness());
                if (i < 9 && i < population.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("\n");

            // Evolve to next generation
            population = evolvePopulation(population, nodeFactory, maxDepth, rand);
        }

        scanner.close();
    }

    /**
     * Evolve the population using selection, crossover, and mutation.
     * This is a simple example; you can customize your GP operators.
     */
    public static GPTree[] evolvePopulation(GPTree[] oldPop, NodeFactory nodeFactory, int maxDepth, Random rand) {
        int populationSize = oldPop.length;
        GPTree[] newPop = new GPTree[populationSize];

        // Tournament selection and crossover
        for (int i = 0; i < populationSize; i++) {
            GPTree parent1 = tournamentSelection(oldPop, rand);
            GPTree parent2 = tournamentSelection(oldPop, rand);

            GPTree child = (GPTree) parent1.clone();
            child.crossover(parent2, rand);

            // Optional: mutation can be added here
            newPop[i] = child;
        }

        return newPop;
    }

    /**
     * Tournament selection: pick 3 random trees and return the best.
     */
    public static GPTree tournamentSelection(GPTree[] population, Random rand) {
        int tournamentSize = 3;
        GPTree best = population[rand.nextInt(population.length)];
        for (int i = 1; i < tournamentSize; i++) {
            GPTree contender = population[rand.nextInt(population.length)];
            if (contender.getFitness() < best.getFitness()) {
                best = contender;
            }
        }
        return best;
    }
}
