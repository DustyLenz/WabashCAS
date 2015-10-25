/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;


/** Tokens of parsed input.
 * 
 * Implemented classes for numbers, operators, keywords, etc.
 * 
 * @author turnerw
 */
public interface Token {
    
    /** Test if token equals this.
     * Equal if they are the same type and contain equivalent information.
     * 
     * @param t Token to check for equality
     * @return Whether equal
     */
    public boolean equals( Token t );
    
    /** Access the string of the token.
     * 
     * @return String representing the token.
     */
    public String string();
    
    /** Convert the token to a string appropriate for humans to read.
     * 
     * @return String for output
     */
    @Override
    public String toString();
            
}
