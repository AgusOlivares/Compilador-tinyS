package LexicalAnalizer;
public class Token {
    final TokenTypes type;
    final String lexema;
    final int line;
    final int column;

    /**
     * Constructor para la clase {@code Token}.
     *
     * @param type   El tipo de token.
     * @param lexema El lexema del token.
     * @param column La columna en la que aparece el token.
     * @param line   La línea en la que aparece el token.
     */

    public Token(TokenTypes type, String lexema, int column, int line) {
        this.type = type;
        this.lexema = lexema;
        this.column = column;
        this.line = line;
    }

    /**
     * Obtiene el tipo de token.
     *
     * @return El tipo de token.
     */

    public TokenTypes getType() {
        return type;
    }

    /**
     * Obtiene el lexema del token.
     *
     * @return El lexema del token.
     */

    public String getLexema() {
        return lexema;
    }

    /**
     * Obtiene el número de línea donde se encuentra el token.
     *
     * @return El número de línea.
     */

    public int getLine() {
        return line;
    }

    /**
     * Obtiene el número de columna donde se encuentra el token.
     *
     * @return El número de columna.
     */

    public int getColumn() {
        return column;
    }

    /**
     * Devuelve una representación en cadena del token.
     *
     * @return Una cadena que representa el token, incluyendo su tipo, lexema, línea y columna.
     */

    public String toString() {
        return "| " +type + " | " + lexema  + " | LINEA " + line + " (COLUMNA " + column + ") |";
    }
}