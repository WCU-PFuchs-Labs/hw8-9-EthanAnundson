package binary;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class DataSet{
   ArrayList data = new ArrayList();
   static int numIndepvariables;
   public DataSet(String filename) {
      try{
       File file = new File(filename+"");
       Scanner sc = new Scanner(file);
       String lengthline = sc.nextLine();
       String[] length = lengthline.split(",");
       numIndepvariables = length.length -1 ;
       //System.out.println(numIndepvariables);
       while (sc.hasNextLine() == true){
          String line = sc.nextLine();
          String[] row = line.split(",");
          double independant= Double.parseDouble(row[0]);
          double[] dependant = new double[row.length-1];
          for (int i=0; i<= row.length-2; i++){
             dependant[i] = Double.parseDouble(row[i+1]);
          }
          DataRow newrow = new DataRow(independant, dependant);
          data.add(newrow);
          //numIndepvariables++;
        }
        } catch (FileNotFoundException e){
           System.out.println("File Not Found");
        }
             
        
    }

       
   
   public ArrayList getRows(){
      return data;
   }
   public static int getNumIndependentVariables(){
      return numIndepvariables;
   }
}
