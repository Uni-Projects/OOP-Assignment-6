import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.3
 * @date 07-03-2016
 * A template implementation of a sliding game 
 * implementing the Graph interface
 */
public class SlidingGame implements Configuration {

    public static final int N = 3, SIZE = N * N, HOLE = SIZE;
    /**
     * The board is represented by a 2-dimensional array; the position of the
     * hole is kept in 2 variables holeX and holeY
     */
    private int[][] board;
    private int holeX, holeY;
    private Configuration parentState;

    /**
     * A constructor that initializes the board with the specified array
     *
     * @param root: String to indicate this is the parent object of the first
     * instances of the game.
     * 
     * @param start : a one dimensional array containing the initial board. The
     * elements of start are stored row-wise.
     */
    public SlidingGame(String root){

    }
    public int [][] getBoard(){
        return board;
    }
    public SlidingGame(int[] start, SlidingGame parent) {
        board = new int[N][N];
        
        assert start.length == N * N : "Length of specified board incorrect";

        for (int p = 0; p < start.length; p++) {
            board[p % N][p / N] = start[p];
            if (start[p] == HOLE) {
                holeX = p % N;
                holeY = p / N;
            }
        }
        parentState = parent;
    }

    /**
     * Converts a board into a printable representation. The hole is displayed
     * as a space
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int puzzel = board[col][row];
                buf.append(puzzel == HOLE ? "  " : puzzel + " ");
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    @Override
    public boolean equals(Object o) {  
        return this.toString().equals(o.toString());
    }

    @Override
    public boolean isSolution() {
        int x = 1;
        for (int row = 0; row < N; row++){
            for (int col = 0; col < N; col++){
                if(board[col][row] != x)
                    return false;
                x++;
            }
        }
        return true;
    }

    @Override
    public Collection<Configuration> successors() {
        LinkedList<Configuration> successors;
        successors = new LinkedList<>();
        NextConfiguration next = new NextConfiguration(board, this, SIZE);
        successors.add(next.north());
        successors.add(next.east());
        successors.add(next.south());
        successors.add(next.west());
        return successors;
    }

    @Override
    public int compareTo(Configuration g) {
        throw new UnsupportedOperationException("compareTo : not supported yet.");
    }

    @Override
    public Configuration parent() {
        return parentState;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        for ( int x = N-1; x >= 0; x-- ) {
            for ( int y = N-1; y >= 0; y-- ) {
                hash = 31 * hash + board[x][y];
            }
        }
        return hash;
}
    
    @Override
    public List<Configuration> pathFromRoot(){
        LinkedList <Configuration> path = new LinkedList<>();
        if(this.parent()!= null){
            System.out.println(this);
            this.parent().pathFromRoot();
        }
        return path;
    }

}
