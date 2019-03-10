
import java.util.*;
/**
 * A class that implements a breadth-first search algorithm
 * for finding the Configurations for which the isSolution predicate holds
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.5
 * @date 25-02-2017
 */
public class Solver
{
   // A queue for maintaining graphs that are not visited yet.
    Queue<Configuration> toExamine;
    HashSet<Configuration> visited;

    public Solver( Configuration g ) {
        visited = new HashSet <>();
        toExamine = new LinkedList<>();
        
        toExamine.add(g);
        
    }

    /**
     * A skeleton implementation of the solver
     *
     * @return a string representation of the solution
     */
    public String solve() {
        while ( ! toExamine.isEmpty() ) {
            Configuration next = toExamine.remove();
            visited.add(next);
            if ( next.isSolution() ) {
                showSolution(next); 
                return "Success!";
            } else {
                for ( Configuration succ : next.successors()  ) { 
                    if (!succ.toString().contains("0") && !visited.contains(succ)){
                        toExamine.add(succ); 
                        //System.out.println(succ);
                    }         
                }
            }    
        }
        return "Failure!";
    }
    
    public void showSolution(Configuration sol){
        for(Configuration c : sol.pathFromRoot())
            System.out.println(c);
    }
   
}
  