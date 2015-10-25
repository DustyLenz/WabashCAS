package csc338fall2015;

import java.util.*;

public class tester {

    public static void main(String[] args) {
        ArrayList<LongInteger> modList = new ArrayList<LongInteger>();

        modList.add(LongInteger.valueOf(1));
        modList.add(LongInteger.valueOf(1));
        modList.add(LongInteger.valueOf(0));
        modList.add(LongInteger.valueOf(0));
        modList.add(LongInteger.valueOf(0));
        modList.add(LongInteger.valueOf(1));

        LongInteger.changeModulus(LongInteger.valueOf(2));
        
        
        //LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));

        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        
        polyList1.add(LongInteger.valueOf(1));
        polyList1.add(LongInteger.valueOf(1));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(1));
        
        polyList2.add(LongInteger.valueOf(1));
        polyList2.add(LongInteger.valueOf(1));
        polyList2.add(LongInteger.valueOf(1));
        
        
        /*polyList2.add(LongInteger.valueOf(1));
        polyList2.add(LongInteger.valueOf(0));
        polyList2.add(LongInteger.valueOf(0));
        polyList2.add(LongInteger.valueOf(1));*/
        
        /*LongInteger a = LongInteger.valueOf("1924");
        LongInteger b = LongInteger.valueOf("458");

        LongInteger[] expectedResult = new LongInteger[3];
        expectedResult[0] = LongInteger.valueOf("1");
        expectedResult[1] = LongInteger.valueOf("172414");
        expectedResult[2] = LongInteger.valueOf("-143419");

        LongInteger result = b.divide(a);

        System.out.println(result);*/
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        System.out.println(poly1.divide(poly2));
        
        
        LongIntegerPolynomial[] result = poly1.traditionalEEA(poly2);
        
        for(int i = 0; i < 3; i++){
            System.out.println(result[i]);
        }

    }
}