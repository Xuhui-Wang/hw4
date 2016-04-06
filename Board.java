/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;
import java.util.Stack;
import edu.princeton.cs.algs4.Queue;
/**
 *
 * @author Wang Xuhui
 */
public class Board {
    private final int N;      //size of board;
    private final int[][] b;  //Array presentation of this board
    private int manh; // manhattan function of this board;
    private int ham; // result of hamming function  of this board;
    private class Comp implements Comparator<Board>
    {

        @Override
        public int compare(Board o1, Board o2) 
        {
            if (o1.manhattan() > o2.manhattan())
                return 1;
            if (o1.manhattan() == o2.manhattan())
            {
                if (o1.hamming() > o2.hamming())
                    return 1;
                return -1;
            }
            return -1;
        }        
    }
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    {                                     // (where blocks[i][j] = block in row i, column j)
        if (blocks == null) throw new NullPointerException();
        N = blocks.length;
        b = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.b[i][j] = blocks[i][j];
        manh = countManh();
        ham = countHamming();
    }

//    private int[][] copyArr(int[][] c)
//    {
//        int n = c[0].length;
//        int[][] ret = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int k = 0; k < n; k++)
//                ret[i][k] = c[i][k];
//        return ret;
//    }
    public int dimension()                 // board dimension N
    {
        return N;
    }
    /*
    Counts number of blocks out of place;
    return hamming function number;
    */
    private int countHamming()
    {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int k = 0; k < N; k++)
                if (b[i][k] != 0 && b[i][k] != i * N + k + 1)
                    count++;
        return count;
    }

     
    public int hamming()                   // number of blocks out of place
    {
//        if (ham == -1) 
//            throw new NullPointerException();
        return ham;
    }
    /**
     * Counts value for manhattan index for specific value
     * @param i Y coordinate
     * @param k X coordinate
     * @return Manhattan value
     */
    private int val(int i, int k)
    {
        if (b[i][k] == 0) return 0;
        int i_t = (b[i][k] - 1) / N;
        int j_k = (b[i][k] - 1) % N;
        return Math.abs(i_t - i) + Math.abs(j_k - k);
    }
    /**
     * Counts sum of Manhattan distances between blocks and goal
     * @return Manhattan value of the board
     */
    private int countManh()
    {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int k = 0; k < N; k++)
            {
                count += val(i, k);
//                StdOut.println("count = " + count);             //test;
            }
        return count;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return manh;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return hamming() == 0;
    }
        /**
     * Searches for given point in board
     * @param a Point
     * @return Array of coordinates
     */
    private int[] search(int a)
    {
        for (int i = 0; i < N; ++i)
            for (int k = 0; k < N; ++k)
                if (b[i][k] == a)
                {
                    int[] ret = {i, k};
                    return ret;
                }
        return null;
    }

    /**
     * Exchanges two values on board
     * @param oldx Old X coordinate
     * @param oldy Old Y coordinate
     * @param newx New X coordinate
     * @param newy New Y coordinate
     */
    private void exchange(int oldx, int oldy, int newx, int newy)
    {
        int tmp = b[oldx][oldy]; // Temp variable for copying
        b[oldx][oldy] = b[newx][newy];
        b[newx][newy] = tmp;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        Board ret = new Board(this.b);
        int[] co = search(0);
        if (co[0] == 0)
            ret.exchange(1, 0, 1, 1);
        else
            ret.exchange(0, 0, 0, 1);  //avoid exchanging with "0"
        return ret;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == null) return false;
        return this.toString().equals(y.toString());
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        int[] coo = search(0);                  //Coordinates of null
        List<Board> ret = new ArrayList<Board>();
        if (coo[0] != 0)
        {
            int oldval = this.val(coo[0] - 1, coo[1]);
            this.exchange(coo[0], coo[1], coo[0] - 1, coo[1]);
            ret.add(new Board(this.b));
            this.exchange(coo[0], coo[1], coo[0] - 1, coo[1]);
//            int newval = this.val(coo, N)
        }
        if (coo[1] != 0)
        {
            this.exchange(coo[0], coo[1], coo[0], coo[1] - 1);
            ret.add(new Board(this.b));
            this.exchange(coo[0], coo[1], coo[0], coo[1] - 1);
        }
        if (coo[0] != N - 1)
        {
            this.exchange(coo[0], coo[1], coo[0] + 1, coo[1]);
            ret.add(new Board(this.b));
            this.exchange(coo[0], coo[1], coo[0] + 1, coo[1]);
        }
        if (coo[1] != N - 1)
        {
            this.exchange(coo[0], coo[1], coo[0], coo[1] + 1);
            ret.add(new Board(this.b));
            this.exchange(coo[0], coo[1], coo[0], coo[1] + 1);
        }
        Comp c = new Comp();
        Board[] neigh = ret.toArray(new Board[ret.size()]);
        Arrays.sort(neigh, c);                //转化为数组，排序
        ret = Arrays.asList(neigh);
        Stack<Board> outSt = new Stack<Board> ();
        outSt.addAll(ret);
        return outSt;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
            {
                s.append(String.format("%2d ", b[i][j]));
            }
        s.append("\n");
        }
        return s.toString();    
    }
    public static void main(String[] args) // unit tests (not graded)
    {
        In in = new In(args[0]);
        int N = in.readInt();
//        char a = (char) 1;
//        StdOut.println("a = " + a);
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
//        for (int i = 0; i < N; i++)
//        {
//            for (int j = 0; j < N; j++)
//                StdOut.print(blocks[i][j] + " ");
//            StdOut.println(" ");
//        }
        Board initial = new Board(blocks);
        System.out.println(initial.manhattan());
        StdOut.println(initial.toString());
//        Iterable<Board> m = initial.neighbors();
//        Iterator mm = m.iterator();
//        for (int i = 0; i < 4; i++)
//        {
//            Board qq = (Board) mm.next();
//            StdOut.println("Manhattan: " + qq.manhattan() + "\nnumber " + i + ": \n" + qq);
//        }
//        StdOut.println("neighbors: " + m.toString());
    }
}