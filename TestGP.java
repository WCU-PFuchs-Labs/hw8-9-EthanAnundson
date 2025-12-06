import java.util.*;
import binary.*; // GPTree, NodeFactory, DataSet, Binop, etc.

public class TestGP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Whats your file name: ");
        String filename = sc.next();
        Generation gen = new Generation(500, 4, filename);
        for(int i = 1; i<=50; i++){
            gen.evolve();
            System.out.println("Generation "+i+": ");
            gen.printBestTree();
            gen.printBestFitness();
        }
    }
}
