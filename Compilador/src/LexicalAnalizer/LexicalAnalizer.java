package LexicalAnalizer;

import java.io.IOException;
import java.util.HashMap;

public class LexicalAnalizer {

    private fileReader reader;
    private HashMap<String, TokenTypes> keywords;
    private HashMap<String, TokenTypes> operators;
    private HashMap<String, TokenTypes> symbols;

    public LexicalAnalizer(String filePath) throws LexicalException {
        try {
            this.reader = new fileReader(filePath);
        } catch (IOException e) {
            throw new LexicalException("No se pudo abrir el archivo: " + e.getMessage(), 
                    0, 0, filePath);
        }
        this.keywords = new HashMap<>();
        this.operators = new HashMap<>();
        this.symbols = new HashMap<>();

        initializeKeywords(keywords);
        initializeOperators(operators);
        initializeSymbols(symbols);
    }

    public Token nextToken() throws LexicalException {
        Character c;
        try {
            c = reader.readCharacter();
        } catch (IOException e) {
            throw new LexicalException("Error al leer el archivo", 
                    reader.getRow(), reader.getColumn(), "");
        }

        if (c == null) {
            return new Token(TokenTypes.EOF, "EOF",
                    reader.getColumn(), reader.getRow());
        }

        if (Character.isWhitespace(c)) {
            return nextToken();
        }

        int startColumn = reader.getColumn() - 1;
        int startRow = reader.getRow();

        if (Character.isLetter(c) || c == '_') {
            return readIdentifier(c, startColumn, startRow);
        }

        if (Character.isDigit(c)) {
            return readNumber(c, startColumn, startRow);
        }

        // TODO: Agregar soporte para strings (lexema entre comillas)

        if (isOperatorStart(c)) {
            return readOperator(c, startColumn, startRow);
        }

        if (symbols.containsKey(String.valueOf(c))) {
            return new Token(symbols.get(String.valueOf(c)),
                    String.valueOf(c), startColumn, startRow);
        }

        throw new LexicalException("Carácter no reconocido", 
                startRow, startColumn, String.valueOf(c));
    }

    private Token readIdentifier(char first, int col, int row) throws LexicalException {
        StringBuilder lexeme = new StringBuilder();
        lexeme.append(first);

        while (true) {
            Character peeked;
            try {
                peeked = reader.peek();
            } catch (IOException e) {
                throw new LexicalException("Error al leer identificador", 
                        row, col, lexeme.toString());
            }
            
            if (peeked != null && (Character.isLetterOrDigit(peeked) || peeked == '_')) {
                try {
                    lexeme.append(reader.readCharacter());
                } catch (IOException e) {
                    throw new LexicalException("Error al leer identificador", 
                            row, col, lexeme.toString());
                }
            } else {
                break;
            }
        }

        String lex = lexeme.toString(); //Como el objeto queda fijo lo convierto en un string definitivo (mejor en cuanto a gestion de memoria)
        TokenTypes type = keywords.getOrDefault(lex, TokenTypes.ID_VAR);
        return new Token(type, lex, col, row);
    }

    private Token readNumber(char first, int col, int row) throws LexicalException {
        StringBuilder lexeme = new StringBuilder();
        lexeme.append(first);

        while (true) {
            Character peeked;
            try {
                peeked = reader.peek();
            } catch (IOException e) {
                throw new LexicalException("Error al leer número", 
                        row, col, lexeme.toString());
            }
            
            if (peeked != null && Character.isDigit(peeked)) {
                try {
                    lexeme.append(reader.readCharacter());
                } catch (IOException e) {
                    throw new LexicalException("Error al leer número", 
                            row, col, lexeme.toString());
                }
            } else {
                break;
            }
        }

        return new Token(TokenTypes.LITERAL_INT, lexeme.toString(), col, row);
    }

    private Token readOperator(char first, int col, int row) throws LexicalException {
        StringBuilder lexeme = new StringBuilder();
        lexeme.append(first);

        Character peeked;
        try {
            peeked = reader.peek();
        } catch (IOException e) {
            throw new LexicalException("Error al leer operador", 
                    row, col, lexeme.toString());
        }
        
        if (peeked != null) {
            String twoChar = String.valueOf(first) + peeked;
            if (operators.containsKey(twoChar)) {
                try {
                    reader.readCharacter();
                } catch (IOException e) {
                    throw new LexicalException("Error al leer operador", 
                            row, col, lexeme.toString());
                }
                lexeme.append(peeked);
                return new Token(operators.get(twoChar), lexeme.toString(), col, row);
            }
        }

        return new Token(operators.getOrDefault(String.valueOf(first), TokenTypes.OP_EQUAL),
                lexeme.toString(), col, row);
    }

    /**
     * @param c caracter a evaluar
     * @return true/false
     * funcion utilizada para detectar si el caracter leido es un Operador
     */
    private boolean isOperatorStart(char c) {
        return "+-*/%!&|=<>".indexOf(c) != -1;
    }

    public void close() throws LexicalException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new LexicalException("Error al cerrar el archivo", 
                    reader.getRow(), reader.getColumn(), "");
        }
    }

    private HashMap<String, TokenTypes> initializeKeywords(HashMap<String, TokenTypes> hash) {
        hash.put("class", TokenTypes.KW_CLASS);
        hash.put("nil", TokenTypes.KW_NIL);
        hash.put("impl", TokenTypes.KW_IMPL);
        hash.put("else", TokenTypes.KW_ELSE);
        hash.put("if", TokenTypes.KW_IF);
        hash.put("ret", TokenTypes.KW_RET);
        hash.put("while", TokenTypes.KW_WHILE);
        hash.put("true", TokenTypes.KW_TRUE);
        hash.put("false", TokenTypes.KW_FALSE);
        hash.put("new", TokenTypes.KW_NEW);
        hash.put("fn", TokenTypes.KW_FN);
        hash.put("st", TokenTypes.KW_ST);
        hash.put("pub", TokenTypes.KW_PUB);
        hash.put("self", TokenTypes.KW_SELF);
        hash.put("div", TokenTypes.KW_DIV);
        hash.put("for", TokenTypes.KW_FOR);
        hash.put("in", TokenTypes.KW_IN);
        hash.put("void", TokenTypes.KW_VOID);
        hash.put("Array", TokenTypes.ARRAY);

        return hash;
    }

    private HashMap<String, TokenTypes> initializeOperators(HashMap<String, TokenTypes> hash) {
        hash.put("*", TokenTypes.OP_MULT);
        hash.put("+", TokenTypes.PLUS);
        hash.put("-", TokenTypes.MINUS);
        hash.put("/", TokenTypes.SLASH);
        hash.put("%", TokenTypes.OP_PERCENTAGE);
        hash.put("++", TokenTypes.OP_PLUS_PLUS);
        hash.put("--", TokenTypes.OP_MINUS_MINUS);
        hash.put("&&", TokenTypes.OP_AND);
        hash.put("||", TokenTypes.OP_OR);
        hash.put("!", TokenTypes.OP_NOT);
        hash.put("!=", TokenTypes.OP_NOT_EQUAL);
        hash.put("=", TokenTypes.OP_EQUAL);
        hash.put("==", TokenTypes.OP_EQUAL_EQUAL);
        hash.put(">", TokenTypes.OP_GREATER);
        hash.put(">=", TokenTypes.OP_GREATER_EQUAL);
        hash.put("<", TokenTypes.OP_LESS);
        hash.put("<=", TokenTypes.OP_LESS_EQUAL);

        return hash;
    }

    private HashMap<String, TokenTypes> initializeSymbols(HashMap<String, TokenTypes> hash) {
        hash.put("[", TokenTypes.LEFT_BRACKET);
        hash.put("]", TokenTypes.RIGHT_BRACKET);
        hash.put("(", TokenTypes.LEFT_PAREN);
        hash.put(")", TokenTypes.RIGHT_PAREN);
        hash.put("{", TokenTypes.LEFT_BRACE);
        hash.put("}", TokenTypes.RIGHT_BRACE);
        hash.put(".", TokenTypes.DOT);
        hash.put(",", TokenTypes.COMMA);
        hash.put(";", TokenTypes.SEMICOLON);
        hash.put(":", TokenTypes.COLON);

        return hash;
    }

    // TODO: Revisar/Pendientes:
    // 1. Agregar soporte para strings (lexema entre comillas)
    // 2. Agregar soporte para números decimales/float
    // 3. Agregar soporte para comentarios (// y /* */)
    // 4. Revisar: "int" se tokeniza como ID_VAR, debería ser KEYWORD? (ver TokenTypes)
    // 5. Revisar: operador unario vs binario (ej: -5 vs 3-2)
    // 6. Agregar soporte para caracteres Unicode en identificadores
    // 7. Revisar: detectar overflow en números muy grandes
    // 8. Considerar agregar token para errores (actualmente lanza excepción)
}
