/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author study
 */
public class NextConfiguration {
    private final int[][] currentConfig;
    private int[][] copyConfig;
    private int[] next1D;
    private final SlidingGame parentState;
    private int holeX;
    private int holeY;
    private final int length;
    
    public NextConfiguration(int[][] start, SlidingGame parent, int size){
        currentConfig = start.clone();
        parentState = parent;
        length = currentConfig.length;
        next1D = new int[length*length];
        copyConfig = new int[length][length];
        arrayCopy();

        for (int row = 0; row < length; row++){
            for (int col = 0; col < length; col++){
                if (currentConfig[col][row] == size){
                    holeX = col;
                    holeY = row;
                }
            }
        };
    }
    
    private void convert(int[][] config){
        int i = 0;
        for (int row = 0; row < length; row++){
            for (int col = 0; col < length; col++){
                next1D[i] = config[col][row];
                i++;
            }
        }
    }
    
    private void clear(){
        for (int i = 0; i < length * length; i++)
            next1D[i] = 0;
    }
    
    private void arrayCopy(){
        for (int row = 0; row < length; row++){
            for (int col = 0; col < length; col++){
                copyConfig[col][row] = currentConfig[col][row];
            }
        }
    }
    private boolean check(int dir){
        switch(dir){
            // north
            case 1:
               return holeY != 0;
            // east
            case 2:
                return holeX != length - 1;
            // south
            case 3:
                return holeY != length - 1;
            // west
            case 4:
                return holeX != 0;
            default:
                return false;
        }
    }
    public SlidingGame north(){
        clear();
        arrayCopy();
        if (check(1)){
            int [][] nextN = copyConfig.clone();
            nextN[holeX][holeY] = copyConfig[holeX][holeY-1];
            nextN[holeX][holeY-1] = length*length;
            convert(nextN);
            SlidingGame north = new SlidingGame(next1D, parentState);
            return north;
        }
        return new SlidingGame(next1D, parentState);
    }
    
    public SlidingGame east(){
        clear();
        arrayCopy();
        if (check(2)){
            int[][] nextE = copyConfig.clone();
            nextE[holeX][holeY] = copyConfig[holeX+1][holeY];
            nextE[holeX+1][holeY] = length*length;
            convert(nextE);
            SlidingGame north = new SlidingGame(next1D, parentState);
            return north;
        }
        return new SlidingGame(next1D, parentState);
    }

    public SlidingGame south(){
        clear();
        arrayCopy();
        if (check(3)){
            int[][] nextS = copyConfig.clone();
            nextS = copyConfig.clone();
            nextS[holeX][holeY] = copyConfig[holeX][holeY+1];
            nextS[holeX][holeY+1] = length*length;
            convert(nextS);
            SlidingGame north = new SlidingGame(next1D, parentState);
            return north;
        }
        return new SlidingGame(next1D, parentState);
    }

    public SlidingGame west(){
        clear();
        arrayCopy();
        if (check(4)){
            int[][] nextW = copyConfig.clone();
            nextW = copyConfig.clone();
            nextW[holeX][holeY] = copyConfig[holeX-1][holeY];
            nextW[holeX-1][holeY] = length*length;
            convert(nextW);
            SlidingGame north = new SlidingGame(next1D, parentState);
            return north;
        }
        return new SlidingGame(next1D, parentState);
    }
}
