/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

/** Interface for what a function must implement
 *
 * @author turnerw
 */
public interface Function {

	/** Apply the function to a given value
	 *
	 * @param value	Value on which to evaluate function
	 * @return Evaluated value of function
	 */
	public Expression apply( Value value );

	/** Apply the function to a given expression in the given context.
	 *
	 * @param exp Expression on which to evaluate the function
	 * @param cont Context in which to evaluate the function
	 * @return
	 */
	public Expression apply( Expression exp, Context cont );


}
