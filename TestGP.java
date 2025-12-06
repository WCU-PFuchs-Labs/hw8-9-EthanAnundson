import java.util.*;
import binary.*; // GPTree, NodeFactory, DataSet, Binop, etc.

public class TestGP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter data file name: ");
        String dataFile = scanner.nextLine();

        DataSet dataset = new DataSet(dataFile);

        // Number of independent variables
        int numVars = dataset.getNumIndependentVariables();

        // Operators for GP
        Binop[] operators = new Binop[]{ new Plus(), new Minus(), new Mult(), new Divide() };

        // NodeFactory to generate nodes
        NodeFactory nodeFactory = new NodeFactory(operators, numVars);

        int populationSize = 500;
        int maxDepth = 5;
        GPTree[] population = new GPTree[populationSize];

        // Initialize population
        for (int i = 0; i < populationSize; i++) {
            population[i] = new GPTree(nodeFactory, maxDepth, rand);
        }

        int generations = 50;

        for (int gen = 1; gen <= generations; gen++) {
            // Evaluate fitness
            for (GPTree tree : population) {
                tree.evalFitness(dataset);
            }

            // Sort population by fitness (lower is better)
            Arrays.sort(population);

            // Print generation info
            System.out.println("Generation " + gen + ":");
            System.out.println("Best Tree: " + population[0]);
            System.out.printf("Best Fitness: %.2f%n", population[0].getFitness());

            System.out.print("Top Ten Fitness Values:\n");
            for (int i = 0; i < 10 && i < population.length; i++) {
                System.out.printf("%.2f", population[i].getFitness());
                if (i < 9 && i < population.length - 1) System.out.print(", ");
            }
            System.out.println("\n");

            // Evolve population
            population = evolvePopulation(population, nodeFactory, maxDepth, rand);
        }

        scanner.close();
    }

    // Evolve population using tournament selection + crossover
    public static GPTree[] evolvePopulation(GPTree[] oldPop, NodeFactory nodeFactory, int maxDepth, Random rand) {
        int populationSize = oldPop.length;
        GPTree[] newPop = new GPTree[populationSize];

        for (int i = 0; i < populationSize; i++) {
            GPTree parent1 = tournamentSelection(oldPop, rand);
            GPTree parent2 = tournamentSelection(oldPop, rand);

            // Use copy constructor instead of clone
            GPTree child = new GPTree(parent1);
            child.crossover(parent2, rand);

            newPop[i] = child;
        }

        return newPop;
    }

    // Tournament selection (lower fitness wins)
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
