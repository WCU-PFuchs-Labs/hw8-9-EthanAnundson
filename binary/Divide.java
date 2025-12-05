package binary;
/**
 * Code Template Author: David G. Cooper
 * Purpose: A binary operator for Addition
 */
 public class Divide extends Binop {
     /**
      * @param left the left value
      * @param right the right value
      * @return the result of adding
      * left to right 
      */
    public double eval(double left, double right) {
        if (Math.abs(right)< 0.0001){
          return 1.0;
        }
        return left / right;
    }
    public String toString(){
      return " / ";
    }
}
