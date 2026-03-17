package LexicalAnalizer;

public class LexicalException extends Exception {

    private int row;
    private int column;
    private String lexeme;

    public LexicalException(String message, int row, int column, String lexeme) {
        super(message);
        this.row = row;
        this.column = column;
        this.lexeme = lexeme;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String getMessage() {
        return "Error léxico en fila " + row + ", columna " + column + 
               ": " + super.getMessage() + " (lexema: '" + lexeme + "')";
    }
}
