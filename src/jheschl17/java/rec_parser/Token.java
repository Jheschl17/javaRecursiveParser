package jheschl17.java.rec_parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to analyse strings for whether or not they are a legal mathematical expression.
 * 
 * @author Jonas Heschl
 */
public abstract class Token {

    Token() {
    }
    
    /**
     * <pre>
     * Analyses the given String as to whether or not it is a
     * legal mathematical expression. For example, 
     * Token.parseString("2 + 5 * (2 / (-1)) would return true. 
     * Token.parsString("hello world") or
     * Token.parseString("5 + -1") would return false.
     * Expressions with "=" are not supported.
     * Spaces are ignored.
     * 
     * This methode only supports digits, variables and basic
     * arithmetic operations (addition, subtraction, multiplication and division)
     * 
     * This methode will not recognize errors such as "a / 0".
     * </pre>
     * @param s The String to be analysed
     * @return Whether or not the given string is formatted in a mathematically
     * legal way.
     */
    public static boolean parseString(String s) {
        // If one of the characters in the expression is an unsupported character (% for example)
        // Token.getInstance().parseCharacter in method parseCharacter(String s) will throw a
        // nullpointer exception. In this case the expression is not syntactically legal and will return false.
        // Since the first character could also be an unsupported character, this try-catch block must wrap the entire method.
        try {
            s = s.replace(" ", "");
        
            Token firstChar = Token.getInstance(s.substring(0, 1).charAt(0)); //first character of the given string converted to a token object.
            String nextString = s.substring(1, s.length());
        
            if (!checkBrackets(s)) return false; // checks whether brackets are placed correctly
            if (!firstChar.canBeFirst()) return false; // checks whether this character can be the first character.
            
            if (s.length() == 1) return true; // line 43 checks whether the first character can be first, if the first character is the only one, this expression is legal
            
            return firstChar.parseCharactar(nextString);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns an instance of a child of token that corresponds to the given
     * char. Giving an unsupported char will return null.
     *
     * @param c The character to be converted to a Token object
     * @return An instance of a child of token that corresponds to the given
     * char. Null if unsupported char.
     */
    protected static Token getInstance(char c) {
        // A list containing every child of Token
        List<Token> tokenChildren = new LinkedList<>(Arrays.asList(new Token[]{
            new Number(),
            new AddSub(),
            new DivMult(),
            new Variable(),
            new OpenBracket(),
            new ClosedBracket()
        }));
        
        // returns the subclass of token which corresponds to the given parameter c
        // according to the correspondsTo(char c) method of the subclasses
        for (Token t : tokenChildren) {
            if (t.correspondsTo(c)) return t;
        }
        
        // if none of the subclasses of token correspond to c, return null
        return null;
    }
    
    
    /**
     * Analyses the given string for the correct positioning of brackets.
     * 
     * @param s The string to be analysed for the for the positioning of brackets.
     * @return True if the positioning of the brackets is syntactically legal.
     */
    private static boolean checkBrackets(String s) {
        int bracketCounter = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') bracketCounter++;
            else if (c == ')') bracketCounter--;
            
            if (bracketCounter < 0) return false;
        }
        
        return bracketCounter == 0;
    }

    /**
     * Must be overriden by child classes to implement parser support.
     * Is invoked recursively.
     *
     * @param s A string that should be shortened by one character after every
     * recursive invocation.
     * @return A temporary boolean for the recursion.
     */
    protected abstract boolean parseCharactar(String s);

    /**
     * Must be overriden by child classes to implement parser support.
     * This method is invoked by the getInstance method to check whether the given
     * char corresponds to one of the subclasses.
     * 
     * @param c The given caracter to be checked for corresponding to the subclass.
     * @return True if the given character corresponds to the subclass that implements this
     * methode. Otherwise returns false.
     */
    protected abstract boolean correspondsTo(char c); // should be static, however, java does not allow abstract static
    
    
    /**
     * Must be ovrriden by child classes to implement pareser support.
     * Is called by Token.parseString() to determine whether or
     * not this class can be at the beginning of a mathematical expression.
     * 
     * @return Whether or not the class which implements this mehtod can be the first character in a
     * mathematical expression
     */
    protected abstract boolean canBeFirst();
}
