
import java.util.List;


/**
 *
 * @author Sjaak Smetsers
 */
public class Main
{
    public static void main(String[] args) {
        int [] game = {9,2,3, 4,5,6, 7,1,8};
        SlidingGame root = new SlidingGame("root");
        SlidingGame s = new SlidingGame (game, root);
        
        /*for(Configuration p : s.successors())
            System.out.println(p);
*/
        Solver solver = new Solver(s);
        System.out.println(solver.solve());
    }
}
