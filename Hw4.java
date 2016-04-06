/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Wang Xuhui
 */
public class Hw4 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
//        int[][] a = new int[3][3];
        int[][] a = {{1,3,2},{4, 6, 5}, {9, 7, 8}};        //, {4, 6, 5}, {9, 7, 8}   + " a.length2"+a[0].length
        StdOut.println("a.length1: "+a.length+"\n"
                + "a.length2: "+a[0].length);
        int[][] b = a.clone();
        StdOut.println("b.length1: "+b.length+"\n" + "b.length2: "+b[0].length);   
        int i = 0, j = 0;
        b[2][0] = 99;
//        StdOut.println("b[2][0]" + b[2][0]);
        while (i < 3)
        {
            while(j < 3)
            {
                StdOut.print("a[" + i + "][" + j + "]: " + a[i][j] + " ");
                j++;
            }
            StdOut.println(" ");
            i++;
            j = 0;
        }
        int mm = 9 % 5;
        StdOut.println("test: "+mm);
        
    }
    
}
