package jheschl17.java.rec_parser;

/**
 * 
 * @author Jonas Heschl
 */
public class Number extends Token {

    @Override
    protected boolean parseCharactar(String s) {
        // Logic
        if (s.length() == 0) {
            return true;
        }

        // Boilerplate
        Token nextToken = Token.getInstance(s.charAt(0));
        String nextString = s.substring(1, s.length());
        char nextChar = s.charAt(0);
        
        // Logic
        boolean legal = true;
        
        if (new Variable().correspondsTo(nextChar)) legal = false;
        if (nextChar == '(') legal = false;
        
        return legal && nextToken.parseCharactar(nextString);
    }

    @Override
    protected boolean correspondsTo(char c) {
        // check for correspondance of c to Number by using the ASCII table
        boolean corresponds = false;
        
        corresponds = c >= 48 && c <= 57; // check whether c is a digit
        
        return corresponds; // if c is a digit, a lower case letter or a upper case letter, returns true. Ohterwise returns false.
    }

    @Override
    protected boolean canBeFirst() {
        return true;
    }
}
