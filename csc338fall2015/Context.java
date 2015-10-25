/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import java.util.HashMap;

/** Context for variable names.
 * This class wraps a dictionary of variables and can be passed between
 * functions, etc.
 *
 * @author turnerw
 */
public class Context {

	private HashMap< String, Expression > variables;

	/** Default constructor.
	 *
	 */
	public Context() {
		variables = new HashMap< String, Expression >();
	}

	/** Copy constructor.
	 * Creates a copy of a context so that it can be manipulated without
	 * changing the original.
	 *
	 * @param context Context to be copied.
	 */
	public Context( Context context ) {
		variables = new HashMap< String, Expression >( context.variables );
	}

	/** Returns true if name is known in the context.
	 *
	 * @param name	Variable name
	 * @return	Whether the context contains the name.
	 */
	public boolean contains( String name ) {
//		System.out.println( this.variables );
		return this.variables.containsKey(name);
	}

	/** Returns true if name is known in the context.
	 *
	 * @param name	Variable
	 * @return	Whether the context contains the name.
	 */
	public boolean contains( Word name ) {
//		System.out.println( this.variables );
		return this.variables.containsKey(name.toString());
	}

	/** Define the name as the expression.
	 *
	 * @param name Variable name to define.
	 * @param exp	Expression to which to define the name.
	 * @return Previous definition of the name, or null if none.
	 */
	public Expression define( String name, Expression exp ) {
//		System.out.printf("Defining %s = '%s'\n", name, exp.toString());
		return this.variables.put(name, exp);
	}

	/** Define the name as the expression.
	 *
	 * @param name Variable name to define.
	 * @param exp	Expression to which to define the name.
	 * @return Previous definition of the name, or null if none.
	 */
	public Expression define( Word name, Expression exp ) {
//		System.out.printf("Defining %s = '%s'\n", name.toString(), exp.toString());
		return this.variables.put( name.toString(), exp );
	}

	/** Evaluate the variable in this context.
	 *
	 * @param name	Name of variable to evaluate in this context.
	 * @return	The value of the variable if defined, or the variable name if not.
	 * @throws KernelException
	 */
	public Expression evaluate( String name ) throws KernelException {
//        System.out.println( "Evaluating name " + name );
//		System.out.println( this.variables );
        try {
            Expression result = this.variables.get( name.toString() );
            return result == null ? new Word(name) : result.evaluate(this);
        } catch (KernelException exception) {
            System.out.println("Caught exception " + exception.toString() );
            throw new KernelException("Unable to evaluate variable " + name.toString() );
        }
	}

	/** Evaluate the variable in this context.
	 *
	 * @param name	Name of variable to evaluate in this context.
	 * @return	The value of the variable if defined, or the variable name if not.
	 * @throws KernelException
	 */
	public Expression evaluate( Word name ) throws KernelException {
//        System.out.println( "Evaluating name " + name.toString() );
//		System.out.println( this.variables );
        try {
            Expression result = this.variables.get( name.toString() );
            return result == null ? name : result.evaluate(this);
        } catch (KernelException exception) {
//            System.out.println("Caught exception " + exception.toString() );
            throw new KernelException("Unable to evaluate variable " + name.toString() );
        }
	}

}
