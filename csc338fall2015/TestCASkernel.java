/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author turnerw
 */
public class TestCASkernel {

    public static void testRun( CASkernel kernel ) {

        System.out.println("*** Testing Run: ***");

        // Create list of inputs to run.
        LinkedList< String > inputs = new LinkedList< String >();
        inputs.add("   3+4");
        inputs.add("a = 3; b = 4; c = a; d = a + b");
//        inputs.add("f(3)= x^2 + 3");
        inputs.add("a = (b = 3) + ( (c = 2) + (d = 1) )");

        String input = new String();
        String output;

//        try{

            while( !inputs.isEmpty() ) {

                input = inputs.remove();

                System.out.println( "Running command " + input );
                output = kernel.run( input );
                System.out.printf( "Have now run %d inputs and received %d outputs.\n",
                        kernel.inputCount(), kernel.outputCount() );
                int count = kernel.inputCount();
                System.out.printf( "In[%d]: %s\n", count, input );
                System.out.printf( "Out[%d]: %s\n", count, output );

            }

//        } catch ( Exception exception ) {
//            System.out.println( "Caught exception " + exception.toString() + " when running input " + input);
//        }
    }

    public static void testInputs( CASkernel kernel ) {

        System.out.println("*** Testing Inputs: ***");

        try{

            int count = kernel.inputCount();

            System.out.printf( "There are %d inputs.\n", count );

            for( int i = 0; i < count; ++i )
                System.out.printf( "In[%d]: %s", i, kernel.getInput(i) );

        } catch ( Exception exception ) {
            System.out.println( "Caught exception " + exception.toString() );
        }
    }

    public static void testOutputs( CASkernel kernel ) {

        System.out.println("*** Testing Outputs: ***");

        try{

            int count = kernel.outputCount();

            System.out.printf( "There are %d outputs.\n", count );

            for( int i = 0; i < count; ++i )
                System.out.printf( "In[%d]: %s", i, kernel.getOutput(i) );

        } catch ( Exception exception ) {
            System.out.println( "Caught exception " + exception.toString() );
        }
    }

    public static void main( String[] args ) {

        CASkernel kernel = new CASkernel();

//        testRun( kernel );
//        testInputs( kernel );
//        testOutputs( kernel );

		Expression a = new Value("2");
		Expression b = new Word("b");
		Expression c = new Word("c");
		Expression d = new Word("d");
		Expression e = new Expression(Operator.Type.MULTIPLY,
				new Expression(Operator.Type.ADD, a, b),
				new Expression(Operator.Type.SUBTRACT, c, d) );
		System.out.printf("a = %s\nb = %s\nc = %s\nd = %s\ne = %s\n",
				a.toString(), b.toString(), c.toString(), d.toString(), e.toString() );

		Context context = new Context();
		context.define("b", new Value("3"));
		context.define("c", new Value("10"));
		context.define("d", new Value("11"));

		Expression f = e.evaluate(context);
		System.out.printf("f = %s\n", f.toString());



    }

}
