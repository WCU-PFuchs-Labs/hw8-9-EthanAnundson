package binary;
/**
 * Code Template Author: Ethan Anundson
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
import java.util.Random;
import java.text.DecimalFormat;

public class Node implements Cloneable{
    private Node left;
    private Node right;
    private Op operation;
    protected int depth;
    
    public Node(Unop operation) {
        this.operation = operation;
    }
    public Node(Binop operation) {
         this.operation = operation;
    }

    public Node(Binop operation,Node left, Node right) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    public Op getoperation(){
        return operation;
    }
    public double eval(double[] values) {
    if (operation instanceof Unop) {
        return ((Unop) operation).eval(values);
    } else if (operation instanceof Binop) {
        return ((Binop) operation).eval(left.eval(values), right.eval(values));
    } else {
        return 0.0;
    }
    }

    public String toString() {
    if (isLeaf()) {
        return operation.toString().trim();
    } else {
        return "(" 
                + (left != null ? left.toString() : "?") 
                + " " + operation + " " 
                + (right != null ? right.toString() : "?") 
                + ")";
    }
    }
    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
      if (this.depth == maxDepth) {
          // Base case: set both kids to terminals
          left = nf.getTerminal(rand);
          left.depth = this.depth + 1;
          
          right = nf.getTerminal(rand);
          right.depth = this.depth + 1;
          return;
      }
  
      // Otherwise, grow the left child
      int leftChoice = rand.nextInt(nf.getNumOps() + nf.getNumIndepVars());
      if (leftChoice < nf.getNumOps()) {
          left = nf.getOperator(rand);
          left.depth = this.depth + 1;
          left.addRandomKids(nf, maxDepth, rand);  // recursive call
      } else {
          left = nf.getTerminal(rand);
          left.depth = this.depth + 1;
      }
  
      // Grow the right child
      int rightChoice = rand.nextInt(nf.getNumOps() + nf.getNumIndepVars());
      if (rightChoice < nf.getNumOps()) {
          right = nf.getOperator(rand);
          right.depth = this.depth + 1;
          right.addRandomKids(nf, maxDepth, rand);  // recursive call
      } else {
          right = nf.getTerminal(rand);
          right.depth = this.depth + 1;
      }
  }
    public Object clone() {
    Object o = null;
    try {
        o =  super.clone();
    }
    catch(CloneNotSupportedException e) {
        System.out.println("Op can't clone.");
    }
    Node b = (Node) o;
    if(left != null) {
        b.left = (Node) left.clone();
    }
    if(right != null) {
       b.right = (Node) right.clone();
    }
    if(operation != null) {
       b.operation = (Op) operation.clone();
    }
    return o;
  }
  public void swapLeft(Node trunk){
    Node holder = left;
    left = trunk.left;
    trunk.left = holder;

  }
  public void swapRight(Node trunk){
    Node place = right;
    right = trunk.right;
    trunk.right = place;
  }
  public boolean isLeaf() {
    // A node is a leaf if it has no children
    return left == null && right == null;
  }
  public void traverse(Collector c){
    c.collect(this);
    if (left != null) {
        left.traverse(c);
    }
    if (right != null) {
        right.traverse(c);
    }
  }

}
