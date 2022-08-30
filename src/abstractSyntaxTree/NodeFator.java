package abstractSyntaxTree;

public abstract class NodeFator {
    
    public byte kind;
    public int line;
    public int column;

    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
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

    public abstract void visit(Visitor v);
}
