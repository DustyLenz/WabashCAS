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
public class LongIntegerPolynomialCRATestsRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(LongIntegerPolynomialCRATests.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("\nProject 2 Tests Complete. No Errors: " + result.wasSuccessful() + "\n");
   }
}
