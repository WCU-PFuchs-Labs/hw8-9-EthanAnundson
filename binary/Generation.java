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
    NodeFactory box = new NodeFactory(ops, set.getNumIndepVars());
    for (int i = 0; i<= size-1; i++){
      population[i] = new GPTree(box, maxDepth, rand);  
    }
  }
  public void evalAll(){
    for (int p = 0; p<= population.length; p++){
      population[p].eval(set);
    }
    Arrays.sort(population);
  }
  public ArrayList<GPTree> getTopTen(){
    ArrayList<GPTree> list = new ArrayList<>();
    for (int r = 0; r < 10; r++){
      list.add(population[size+r-11]);
    }
    return list;
  }
  public void printBestFitness(){
    System.out.println(""+population[size-1].getFitness());
  }
  public void printBestTree(){
    System.out.println(population[size-1].toString());
  }
  public void evolve(){
    for (int i = 0; i < population.length; i += 2) {
      int a = rand.nextInt(population.length/2 -1) + population.length/2;
      int b = rand.nextInt(population.length/2 -1) + population.length/2;
      GPTree first = population[a].clone();
      GPTree second = population[b].clone();
      GPTree[] newPop = GPTree[population.length];

            first.crossover(second, rand);

            newPop[i] = first;
            if (i + 1 < population.length) {
                newPop[i + 1] = second;
            }
        }
        this.population = newPop;
    }

}
