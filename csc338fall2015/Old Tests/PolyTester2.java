/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import java.util.ArrayList;

/**
 *
 * @author DustyLenz
 */
public class PolyTester2 {
    public static void main(String[] args){
        ArrayList<LongInteger> modList = new ArrayList<LongInteger>();
        modList.add(LongInteger.valueOf(1));
        modList.add(LongInteger.valueOf(0));
        modList.add(LongInteger.valueOf(1));
        LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));
        LongInteger.changeModulus(LongInteger.valueOf("101"));
        
        
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList0 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList2 = new ArrayList<LongInteger>();
             
        polyList1.add(LongInteger.valueOf(2));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(2));
        polyList1.add(LongInteger.valueOf(4));
        
        polyList2.add(LongInteger.valueOf(-6));
        polyList2.add(LongInteger.valueOf(4));
        polyList2.add(LongInteger.valueOf(0));
        polyList2.add(LongInteger.valueOf(3));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        System.out.println(poly1.divide(poly2));
    }
}
