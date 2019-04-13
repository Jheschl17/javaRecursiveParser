package jheschl17.java.rec_parser;

/**
 * 
 * @author Jonas Heschl
 */
public class Variable extends Token {

    @Override
    protected boolean parseCharactar(String s) {
        // logic
        if (s.length() == 0) {
            return true;
        }

        // Boilerplate
        Token nextToken = Token.getInstance(s.charAt(0));
        String nextString = s.substring(1, s.length());
        char nextChar = s.charAt(0);
        
        // logic
        boolean legal = true;
        
        if (nextChar == '(') legal = false;
        if (this.correspondsTo(nextChar)) legal = false;
        
        return legal && nextToken.parseCharactar(nextString);
    }

    @Override
    protected boolean correspondsTo(char c) {
        // check for correspondance of c to Variable by using the ASCII table
        boolean corresponds = false;
        
        corresponds = corresponds || c >= 65 && c <= 90; // check whether c is a upper case letter
        corresponds = corresponds || c >= 97 && c <= 122; // check whether c is a lower case letter
        
        return corresponds;
    }

    @Override
    protected boolean canBeFirst() {
        return true;
    }

}