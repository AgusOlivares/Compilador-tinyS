package LexicalAnalizer;

/**
 * CLase utilizada para identificar cada uno de los tokens para clasificar
 */

public enum TokenTypes {

//IDENTIFICADORES
    /**
     * Identificador de clase.
     */
    ID_CLASS,
    /**
     * Identificador de objeto.
     */
    ID_VAR,

    //Clases predefinidas
    /**
     * Tipo de dato array.
     */
    ARRAY,

    //CONSTANTES y VARIABLES
    /**
     * Tipo de dato cadena (string).
     */
    LITERAL_STR,
    /**
     * Tipo de dato entero (integer).
     */
    LITERAL_INT,
    /**
     * Tipo de dato booleano (boolean).
     */
    LITERAL_BOOL,

    //PALABRAS CLAVE
    //start no es palabra clave aunque sea el método inicializador.

    /**
     * Palabra clave "class".
     */
    KW_CLASS,
    /**
     * Palabra clave "NIL".
     */
    KW_NIL,
    /**
     * Palabra clave "impl".
     */
    KW_IMPL,
    /**
     * Palabra clave "else".
     */
    KW_ELSE,
    /**
     * Palabra clave "if".
     */
    KW_IF,
    /**
     * Palabra clave "ret".
     */
    KW_RET,
    /**
     * Palabra clave "while".
     */
    KW_WHILE,
    /**
     * Palabra clave "true".
     */
    KW_TRUE,
    /**
     * Palabra clave "false".
     */
    KW_FALSE,
    /**
     * Palabra clave "new".
     */
    KW_NEW,
    /**
     * Palabra clave "fn".
     */
    KW_FN,
    /**
     * Palabra clave "st".
     */
    KW_ST,
    /**
     * Palabra clave "pub".
     */
    KW_PUB,
    /**
     * Palabra clave "self".
     */
    KW_SELF,
    /**
     * Palabra clave "div".
     */
    KW_DIV,
    /**
     * Palabra clave "for".
     */
    KW_FOR,
    /**
     * Palabra clave "in".
     */
    KW_IN,
    /**
     * Palabra clave "void". Aunque no esta en el inciso 6.4 palabras claves, si lo anuncia en el 6.3 como que sera tratado especialmente por tinyS
     */
    KW_VOID,
    //TOKEN SIMBOLOS
    /**
     * Paréntesis izquierdo.
     */
    LEFT_PAREN,
    /**
     * Paréntesis derecho.
     */
    RIGHT_PAREN,
    /**
     * Coma.
     */
    COMMA,
    /**
     * Punto.
     */
    DOT,
    /**
     * Signo de suma.
     */
    PLUS,
    /**
     * Barra (división).
     */
    SLASH,
    /**
     * Signo de resta.
     */
    MINUS,
    /**
     * Llave izquierda.
     */
    LEFT_BRACE,
    /**
     * Llave derecha.
     */
    RIGHT_BRACE,
    /**
     * Punto y coma.
     */
    SEMICOLON,
    /**
     * Corchete izquierdo.
     */
    LEFT_BRACKET,
    /**
     * Corchete derecho.
     */
    RIGHT_BRACKET,
    /**
     * Asterisco (multiplicación).
     */
    OP_MULT,
    /**
     * Doble punto.
     */
    COLON,

    //Tokens operaciones
    // !   !=         =        ==         >           >=       <       <=        ++          --
    /**
     * Operador "not".
     */
    OP_NOT,
    /**
     * Operador "not equal".
     */
    OP_NOT_EQUAL,
    /**
     * Operador de asignación "equal".
     */
    OP_EQUAL,
    /**
     * Operador "equal equal".
     */
    OP_EQUAL_EQUAL,
    /**
     * Operador "greater than".
     */
    OP_GREATER,
    /**
     * Operador "greater than or equal".
     */
    OP_GREATER_EQUAL,
    /**
     * Operador "less than".
     */
    OP_LESS,
    /**
     * Operador "less than or equal".
     */
    OP_LESS_EQUAL,
    /**
     * Operador "plus plus" (incremento).
     */
    OP_PLUS_PLUS,
    /**
     * Operador "minus minus" (decremento).
     */
    OP_MINUS_MINUS,
    /**
     * Operador "and".
     */
    OP_AND,
    /**
     * Operador "or".
     */
    OP_OR,
    /**
     * Operador "percentage".
     */
    OP_PERCENTAGE,

    //Finalizar
    /**
     * Fin de archivo.
     */
    EOF
}

