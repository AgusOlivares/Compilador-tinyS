import LexicalAnalizer.LexicalAnalizer;
import LexicalAnalizer.LexicalException;
import LexicalAnalizer.Token;
import LexicalAnalizer.TokenTypes;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "test/input.s";
        
        try {
            LexicalAnalizer lexer = new LexicalAnalizer(filePath);
            
            Token token;
            while ((token = lexer.nextToken()).getType() != TokenTypes.EOF) {
                System.out.println(token.getType() + " | " + token.getLexema() + " | " + token.getLine() + ":" + token.getColumn());
            }
            
            lexer.close();
        } catch (LexicalException e) {
            System.err.println("Error léxico: " + e.getMessage());
        }
    }
}
