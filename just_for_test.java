/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Wang Xuhui
 */
public class just_for_test 
{
//    int t;
    private static class Comp implements Comparator<Integer>
    {

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public int compare(Integer a, Integer b)
        {
            if (a < b)
                return -1;
            if (a == b)
                return 0;
            else 
                return 1;
        }
    }
    public static void main (String[] args)
    {
        Comp c = new Comp();
        MinPQ my = new MinPQ(c);
        my.insert(3);
        my.insert(5);
        my.insert(4);
        my.insert(2);
        my.insert(6);
        Iterator a = my.iterator();
        
       while(a.hasNext()){
           StdOut.println(a.next());
       }
    }
}
