package csc338fall2015;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class TestRunner{
  public static void main(String[] args) {
      Result result = JUnitCore.runClasses(LongIntegerTester.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("\nLong Integer Test Complete. No Errors: " + result.wasSuccessful() + "\n");
      result = JUnitCore.runClasses(LongIntegerPolynomialTester.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("\nLongIntegerPolynomial Test Complete. No Errors: " + result.wasSuccessful());
   }
}