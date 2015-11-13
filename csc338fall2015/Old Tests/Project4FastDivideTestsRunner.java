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
public class Project4FastDivideTestsRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(Project4FastDivideTests.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("\nProject 4 Fast Divide Tests Complete. No Errors: " + result.wasSuccessful() + "\n");
   }
}
