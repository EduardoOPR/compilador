package lexicalAnalysis;

import File.ManipulationFile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scanner {
    private char currentChar;
    private byte currentKind;
    private StringBuffer currentSpelling;
    private int endColumn = 0;
    private int column = 1;
    private int contVoidSpace = 0;
    private int line = 0;
    private char p1, p2;
    private ManipulationFile file;
    
    public Scanner(ManipulationFile source) throws IOException {
        file = source;
        currentChar = file.readCurrentChar();
        currentSpelling = new StringBuffer("");
        line++;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getContVoidSpace() {
        return contVoidSpace;
    }

    public void setContVoidSpace(int contVoidSpace) {
        this.contVoidSpace = contVoidSpace;
    }

    public char getCurrentChar() {
        return currentChar;
    }

    public void setCurrentChar(char currentChar) {
        this.currentChar = currentChar;
    }

    public byte getCurrentKind() {
        return currentKind;
    }

    public void setCurrentKind(byte currentKind) {
        this.currentKind = currentKind;
    }

    public StringBuffer getCurrentSpelling() {
        return currentSpelling;
    }

    public void setCurrentSpelling(StringBuffer currentSpelling) {
        this.currentSpelling = currentSpelling;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
    
    private boolean isDigit(char c) {
        for (char i = 48; i < 58; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isLetter(char c) {
        for (char i = 97; i < 123; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        for (char i = 65; i < 91; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;

    }

    private boolean isGraphic(char c) {
        for (char i = 32; i < 127; i++) {         //Imprime caracteres gráficos
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;
    }
    
    public void takeit() throws IOException {
        currentSpelling.append(currentChar);
        if(currentChar=='\n') {
            column = 1;
            line++;
        } else {
            column++;
        }
        if(currentChar == ' ') {
            contVoidSpace++;
        }
        currentChar=file.readCurrentChar();
    }
    
    private void take(char expectedChar) throws IOException {
        if (currentChar == expectedChar) {
            currentSpelling.append(currentChar);
            currentChar = file.readCurrentChar();
        } else {
            //reportar erro lexico
        }
    }
    
    public void scanSeparator() throws IOException {
        switch(currentChar) {
            case '!':
                takeit();
                while(isGraphic(currentChar)) {
                    takeit();
                }
                break;
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                takeit();
                break;
        }
    }
    
    public byte scanToken() throws IOException {
        if(isLetter(currentChar)) {
            takeit();
            while(isLetter(currentChar)||isDigit(currentChar)) {
                takeit();
            }
            return Token.ID;
        } else {
            if(currentChar=='.') {
                takeit();
                while(isDigit(currentChar)) {
                    takeit();
                    return Token.FLOAT_LIT;
                }
                if(currentChar=='.') {
                    takeit();
                    return Token.DOTDOT;
                } else {
                    return Token.DOT;
                }
            } else {
                if(isDigit(currentChar)) {
                    takeit();
                    while(isDigit(currentChar)) {
                        takeit();
                    }
                    if(currentChar=='.') {
                        takeit();
                        if(currentChar=='.') {
                            file.getReader().unread('.');
                            currentSpelling.setLength(currentSpelling.length()-1);
                            return Token.INT_LIT;
                        }
                        while(isDigit(currentChar)) {
                            takeit();
                        }
                        return Token.FLOAT_LIT;
                    } else {
                        return Token.INT_LIT;
                    }
                } else {
                    switch (currentChar) {
                        case '+':
                            takeit();
                            return Token.OP_AD_AD;
                        case '-':
                            takeit();
                            return Token.OP_AD_SUB;
                        case '*':
                            takeit();
                            return Token.OP_MULT_MULT;
                        case '/':
                            takeit();
                            return Token.OP_MULT_DIV;
                        case '<':
                            takeit();
                            if (currentChar == '=') {
                                takeit();
                                return Token.OP_REL_LESSOREQUAL;
                            } else {
                                if (currentChar == '>') {
                                    takeit();
                                    return Token.OP_REL_DIFFERENT;
                                }
                            }
                            return Token.OP_REL_LESSTHEN;
                        case '>':
                            takeit();
                            if (currentChar == '=') {
                                takeit();
                                return Token.OP_REL_BIGGEROREQUAL;
                            }
                            return Token.OP_REL_BIGGERTHEN;
                        case '=':
                            takeit();
                            return Token.OP_REL_EQUAL;
                        case '[':
                            takeit();
                            return Token.LEFTBRACKET;
                        case ']':
                            takeit();
                            return Token.RIGHTBRACKET;
                        case '(':
                            takeit();
                            return Token.LEFTPARENTHESIS;
                        case ')':
                            takeit();
                            return Token.RIGHTPARENTHESIS;
                        case ',':
                            takeit();
                            return Token.COMMA;
                        case ':':
                            takeit();
                            if (currentChar == '=') {
                                takeit();
                                return Token.ASSIGNMENT;
                            }
                            return Token.COLON;
                        case ';':
                            takeit();
                            return Token.SEMICOLON;
                        case '\000':
                            return Token.EOT;
                        default:
                            takeit();
                            return -1;
                    }
                }
            }
        }
    }
    
    public Token scan() throws IOException {
       while (currentChar == '!' || currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r') {
           scanSeparator();
       }
       currentSpelling = new StringBuffer("");
       currentKind = scanToken();
       
       Token token = new Token(currentKind,currentSpelling.toString(),line,column-currentSpelling.length());
       return token;
    }
    
    public void printTokens(Token token) throws IOException {
        if(token.kind<0) {
            System.out.print("Token Inválido! = ");
        } else {
            System.out.print("Token Válido = ");
        }
        System.out.print("[Tipo: "+token.kind+", ");
        System.out.print("Valor: "+token.getSpelling()+", ");
        System.out.print("Linha "+token.getLine()+", ");
        System.out.println("Coluna "+token.getColumn()+"]");
    }
}
