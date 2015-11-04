/*
	* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author DustyLenz
 */
public class LongIntegerPolynomialEvalTestsRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(LongIntegerPolynomialEvalTests.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("\nLongInteger Polynomial Eval Tests Complete. No Errors: " + result.wasSuccessful() + "\n");
   }
}

