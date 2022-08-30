package lexicalAnalysis;

public class Token {
    public byte kind; //Tipo
    public String spelling; //Soletração
    public int line; //Número de linhas
    public int column; //Número de colunas

    public Token(byte kind, String spelling, int line, int column) {
        this.kind = kind;
        this.spelling = spelling;
        this.line = line;
        this.column = column;
        if (kind == ID) {
            for (byte k = INTEGER; k <= OP_MULT_AND; k++) {
                if (spelling.equals(spellings[k])) {
                    this.kind = k;
                    break;
                }
            }
        }
    }
    
    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public final static byte ID = 0,
            INT_LIT = 1,
            FLOAT_LIT = 2,
            INTEGER = 3,
            REAL = 4,
            BOOLEAN = 5,
            BEGIN = 6,
            END = 7,
            IF = 8,
            THEN = 9,
            ELSE = 10,
            WHILE = 11,
            DO = 12,
            VAR = 13,
            ARRAY = 14,
            OF = 15,
            TRUE = 16,
            FALSE = 17,
            PROGRAM = 18,
            OP_AD_OR = 19,
            OP_MULT_AND = 20,
            OP_AD_AD = 21,
            OP_AD_SUB = 22,
            OP_MULT_MULT = 23,
            OP_MULT_DIV = 24,
            OP_REL_BIGGERTHEN = 25,
            OP_REL_LESSTHEN = 26,
            OP_REL_EQUAL = 27,
            OP_REL_DIFFERENT = 28,
            OP_REL_BIGGEROREQUAL = 29,
            OP_REL_LESSOREQUAL = 30,
            DOT = 31,
            DOTDOT = 32,
            LEFTBRACKET = 33,
            RIGHTBRACKET = 34,
            LEFTPARENTHESIS = 35,
            RIGHTPARENTHESIS = 36,
            COMMA = 37,
            COLON = 38,
            SEMICOLON = 39,
            ASSIGNMENT = 40,
            EOT = 41;

    public static final String[] spellings = {
        "<id>",
        "<int-lit>",
        "<float-lit>",
        "integer",
        "real",
        "boolean",
        "begin",
        "end",
        "if",
        "then",
        "else",
        "while",
        "do",
        "var",
        "array",
        "of",
        "true",
        "false",
        "program",
        "or",
        "and",
        "+",
        "-",
        "*",
        "/",
        ">",
        "<",
        "=",
        "<>",
        ">=",
        "<=",
        ".",
        "..",
        "[",
        "]",
        "(",
        ")",
        ",",
        ":",
        ";",
        ":=",
        "<eot>"
    };
       
}
