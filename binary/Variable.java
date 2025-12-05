package binary;
/**
 * Code Template Author: David G. Cooper
 * Purpose: An operation class representing a constant number
 */
public class Variable extends Unop {
    /** the constant value */
    private int index;
    
    public Variable(int i){
      index = i;
    }

    public double eval(double[] values) {
        return values[index];
    }
    public String toString(){
        return "X"+index+" ";
    }
}
