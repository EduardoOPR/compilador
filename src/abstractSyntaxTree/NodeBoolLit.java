/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractSyntaxTree;

/**
 *
 * @author Allan
 */
public class NodeBoolLit extends NodeLiteral {

    public String booleano;

    public NodeBoolLit(String booleano) {
        this.booleano = booleano;
    }

    public NodeBoolLit(byte kind, String booleano, int line, int column) {
        this.kind = kind;
        this.booleano = booleano;
        this.line = line;
        this.column = column;
    }

    public String getBooleano() {
        return booleano;
    }

    public void setBooleano(String booleano) {
        this.booleano = booleano;
    }

    @Override
    public byte getKind() {
        return kind;
    }

    @Override
    public void setKind(byte kind) {
        this.kind = kind;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitBoolLit(this);
    }
}
