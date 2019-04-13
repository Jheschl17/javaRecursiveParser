package jheschl17.java.rec_parser;

/**
 * 
 * @author Jonas Heschl
 */
public class OpenBracket extends Token {

    @Override
    protected boolean parseCharactar(String s) {
        // Logic
        if (s.length() == 0) {
            return false;
        }

        // Boilerplate
        Token nextToken = Token.getInstance(s.charAt(0));
        String nextString = s.substring(1, s.length());
        char nextChar = s.charAt(0);
        
        // Logic
        boolean legal = true;
        
        if (nextChar == '*') legal = false;
        if (nextChar == '/') legal = false;
        
        return legal && nextToken.parseCharactar(nextString);
    }

    @Override
    protected boolean correspondsTo(char c) {
        // check for correspondance of c to OpenBracket
        return c == '(';
    }

    @Override
    protected boolean canBeFirst() {
        return true;
    }

}