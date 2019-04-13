package jheschl17.java.rec_parser;

/**
 * 
 * @author Jonas Heschl
 */
public class AddSub extends Token {

    @Override
    protected boolean parseCharactar(String s) {
        // logic
        if (s.length() == 0) {
            // a operator must never be the last character in a mathematical expression
            return false;
        }

        // Boilerplate
        Token nextToken = Token.getInstance(s.charAt(0));
        String nextString = s.substring(1, s.length());
        char nextChar = s.charAt(0);
        
        // logic
        boolean legal = true;
        
        if (nextChar == ')') legal = false;
        if (this.correspondsTo(nextChar)) legal = false;
        
        return legal && nextToken.parseCharactar(nextString);
    }

    @Override
    protected boolean correspondsTo(char c) {
        boolean corresponds = false;
        
        if (c == '+') corresponds = true;
        if (c == '-') corresponds = true;
        
        return corresponds;
    }

    @Override
    protected boolean canBeFirst() {
        return true;
    }

}