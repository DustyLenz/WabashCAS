package csc338fall2015;
import java.util.*;
public class CSC338Utils{
  private static String digits = "0123456789";
  
  public static String generateNDigitNumber(int x){
    Random generator = new Random();
    StringBuilder str = new StringBuilder();
    int randDigit = 1+generator.nextInt(9);
    str.append(digits.substring(randDigit, randDigit+1));
    for(int i = 1; i < x; i++){
      randDigit = generator.nextInt(10);
      str.append(digits.substring(randDigit, randDigit+1));
    }
    
    return str.toString();
  }
  public static long generateNDigitNumberL(int x){
    Random generator = new Random();
    StringBuilder str = new StringBuilder();
    int randDigit = 1+generator.nextInt(9);
    str.append(digits.substring(randDigit, randDigit+1));
    for(int i = 1; i < x; i++){
      randDigit = generator.nextInt(10);
      str.append(digits.substring(randDigit, randDigit+1));
    }
    long returnval = Long.parseLong(str.toString());
    return returnval;
  }
}