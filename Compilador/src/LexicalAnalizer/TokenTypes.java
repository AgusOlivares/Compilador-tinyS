package LexicalAnalizer;

/**
 * CLase utilizada para identificar cada uno de los tokens para clasificar
 */

public enum TokenTypes {

    // Keywords

    KW_CLASS, KW_IMPL, KW_IF, KW_ELSE, KW_FOR, KW_WHILE,

    // id's

    ID_CLASS, ID_VAR,

    // constantes

    LIT_INT, LIT_STRING,

    // Operators

    OP_ADD, OP_DIFF, OP_EQUALS, OP_ASSIGN, OP_MULTIPLY, OP_DIV

}
