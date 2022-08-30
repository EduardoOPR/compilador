package abstractSyntaxTree;

public class NodeFloatLit extends NodeLiteral {

    public String floatLiteral;

    public NodeFloatLit(String floatLiteral) {
        this.floatLiteral = floatLiteral;
    }

    public NodeFloatLit(byte kind, String floatLiteral, int line, int column) {
        this.kind = kind;
        this.floatLiteral = floatLiteral;
        this.line = line;
        this.column = column;
    }

    public String getFloatLiteral() {
        return floatLiteral;
    }

    public void setFloatLiteral(String floatLiteral) {
        this.floatLiteral = floatLiteral;
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
        v.visitFloatLit(this);
    }
}
