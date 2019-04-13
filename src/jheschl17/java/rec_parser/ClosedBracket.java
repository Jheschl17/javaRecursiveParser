package jheschl17.java.rec_parser;

/**
 *
 * @author Jonas Heschl
 */
public class ClosedBracket extends Token {

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
        if (new Number().correspondsTo(nextChar)) legal = false;
        
        return legal && nextToken.parseCharactar(nextString);
    }

    @Override
    protected boolean correspondsTo(char c) {
        // check for correspondance of c to ClosedBracket
        return c == ')';
    }

    @Override
    protected boolean canBeFirst() {
        return false;
    }

}
