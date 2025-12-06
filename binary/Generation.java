package binary;
import java.util.*;

public class Generation{
  private GPTree[] population;
  private DataSet set;
  private Random rand;
  
  public Generation(int size, int maxDepth, String fileName){
    set = new DataSet(fileName);
    rand = new Random();
    population = new GPTree[size];
    Binop[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
    NodeFactory box = new NodeFactory(ops, set.getNumIndependentVariables());
    for (int i = 0; i<= size-1; i++){
      population[i] = new GPTree(box, maxDepth, rand);  
    }
  }
  public void evalAll(){
    for (int p = 0; p< population.length; p++){
      population[p].evalFitness(set);
    }
    Arrays.sort(population);
  }
  public ArrayList<GPTree> getTopTen(){
    ArrayList<GPTree> list = new ArrayList<>();
    for (int r = 0; r < 10; r++){
      list.add(population[r]);
    }
    return list;
  }
  public void printBestFitness(){
    double best = population[0].getFitness();
    System.out.print(""+best);
  }
  public void printBestTree(){
    System.out.print(population[0].toString());
  }
  public void evolve(){
    GPTree[] newPop = new GPTree[population.length];
    int half = population.length/2;
    for (int i = 0; i < population.length; i += 2) {
      int a = rand.nextInt(half)+half;
      int b = rand.nextInt(half)+half;
      GPTree first = (GPTree)population[a].clone();
      GPTree second = (GPTree)population[b].clone();

            first.crossover(second, rand);

            newPop[i] = first;
            if (i + 1 < population.length) {
                newPop[i + 1] = second;
            }
        }
        this.population = newPop;
    }

}
