package binary;
import java.util.Random;

public class Generation{
  
  public Generation(int size, int maxDepth, String fileName){
    DataSet set = new DataSet(fileName);
    Random rand = new Random();
    GPTree[] population = new GPTree[size];
    Binop[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
    NodeFactory box = new NodeFactory(ops, set.getNumIndepVars());
    for (int i = 0; i<= size; i++){
      population[i] = new GPTree(box, maxDepth, rand);  
    }
  }

}
