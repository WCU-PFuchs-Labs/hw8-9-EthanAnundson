import java.util.*;
import binary.*; // GPTree, NodeFactory, DataSet, etc.

public class TestGP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter data file name: ");
        String dataFile = scanner.nextLine();

        DataSet dataset = new DataSet(dataFile);

        // Get number of independent variables from dataset
        int numVars = dataset.getNumIndepVars();

        // Create Binop array
        Binop[] operators = new Binop[]{ new Add(), new Sub(), new Mul(), new Div() };

        // Create NodeFactory with operators and number of independent variables
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
            for (GPTree tree : population) {
                tree.evalFitness(dataset);
            }

            Arrays.sort(population);

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

    // Tournament selection + crossover
    public static GPTree[] evolvePopulation(GPTree[] oldPop, NodeFactory nodeFactory, int maxDepth, Random rand) {
        int populationSize = oldPop.length;
        GPTree[] newPop = new GPTree[populationSize];

        for (int i = 0; i < populationSize; i++) {
            GPTree parent1 = tournamentSelection(oldPop, rand);
            GPTree parent2 = tournamentSelection(oldPop, rand);

            GPTree child = (GPTree) parent1.clone();
            child.crossover(parent2, rand);

            newPop[i] = child;
        }

        return newPop;
    }

    // Tournament selection
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
