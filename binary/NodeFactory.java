package binary;
import java.util.Random;

public class NodeFactory {
    private int numIndepVars = 0;
    private Binop[] currentOps;
    public NodeFactory(Binop[] b, int numVars) {
        this.currentOps = b;
        numIndepVars = numVars;
    }
    public Node getOperator(Random rand) {
        int idx = rand.nextInt(currentOps.length);
        Binop opClone = (Binop) currentOps[idx].clone();
        return new Node(opClone);
    }
    public int getNumOps() {
        return currentOps.length;
    }
    public Node getTerminal(Random rand) {
        Node ligma = null;
        int lim = rand.nextInt(numIndepVars+1);   //not the problem
        if (lim < numIndepVars){
           Variable var = new Variable(lim);
           ligma = new Node(var);
        }
        if (lim >= numIndepVars){
           double p = rand.nextDouble();
           Const c = new Const(p);
           ligma = new Node(c);
        }
        return ligma;
    }
    public int getNumIndepVars() {
        return numIndepVars;
    }
}
