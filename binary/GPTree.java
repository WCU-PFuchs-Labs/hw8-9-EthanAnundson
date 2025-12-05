package binary;
import java.util.ArrayList;
import java.util.Random;
public class GPTree implements Collector {
    private Node root;
    private double fitness = 0.0;
    private ArrayList<Node> crossNodes;
    
    
    /**
     * @param - node The node to be collected.
     * 
     */
    public void collect(Node node) {
        if (node.getoperation() instanceof Binop) {
        crossNodes.add(node);
        } 
    }
    public void evalFitness(DataSet dataset){
        ArrayList<DataRow> hello = dataset.getRows();
        for(int i = 0; i<= hello.size(); i++){
            fitness += Math.pow(eval(hello.get(i).getIndependentVariables())-hello.get(i).getDependentVariable(), 2)
        }
    }
    public double getFitness(){
        return fitness;
    }
    public int compareTo(GPTree t){
        if (fitness < t.getFitness()){ return -1;}
        if (fitness == t.getFitness()){ return 0;}
        if (fitness > t.getFitness()){ return 1;}
    }
    public boolean equals(Object o){
        if (o == null) { return false;}
        if (!(o instanceof GPTree)){ return false;}
        if (compareTo((GPTree)o) == 0){ return true;}
        else { return false;}
    }
    public Object clone() {
        try {
            GPTree cloned = (GPTree) super.clone();
            if (this.root != null) {
                cloned.root = (Node) this.root.clone();
            }
            return cloned;

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed for GPTree", e);
        }
    }
    
    
    
    // DO NOT EDIT code below for Homework 8. 
    // If you are doing the challenge mentioned in 
    // the comments above the crossover method
    // then you should create a second crossover
    // method above this comment with a slightly 
    // different name that handles all types
    // of crossover.
    
    
    /**
     * This initializes the crossNodes field and
     * calls the root Node's traverse method on this
     * so that this can collect the Binop Nodes.
     */
    public void traverse() {
        crossNodes = new ArrayList<Node>();
        root.traverse(this);
    }
    
    /**
     * This returns a String with all of the binop Strings
     * separated by semicolons
     */
    public String getCrossNodes() {
        if (crossNodes.isEmpty()) return "";
        StringBuilder string = new StringBuilder();
        int lastIndex = crossNodes.size() - 1;
        for(int i = 0; i < lastIndex; ++i) {
            Node node = crossNodes.get(i);
            string.append(node.toString());
            string.append(";");
        }
        string.append(crossNodes.get(lastIndex));
        return string.toString();
    }
   
    
    /**
     * this implements left child to left child
     * and right child to right child crossover.
     * Challenge: additionally implement left to 
     * right child and right to left child crossover.
     */
    public void crossover(GPTree tree, Random rand) {
        // find the points for crossover
        this.traverse();
        tree.traverse();
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(tree.crossNodes.size());
        boolean left = rand.nextBoolean();
        // get the connection points
        Node thisTrunk = crossNodes.get(thisPoint);
        Node treeTrunk = tree.crossNodes.get(treePoint);

        
        if(left) {
            thisTrunk.swapLeft(treeTrunk);
            
        } else {
            thisTrunk.swapRight(treeTrunk);
        }
        
    }

    GPTree() { 
        root = null; 
    }    
    
    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.depth = 0;
        root.addRandomKids(n, maxDepth, rand);
    }
    
    public String toString() { 
        return root.toString(); 
    }
    
    public double eval(double[] data) { 
        return root.eval(data); 
    }
    
}
