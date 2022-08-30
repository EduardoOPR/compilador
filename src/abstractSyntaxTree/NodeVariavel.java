package abstractSyntaxTree;

public class NodeVariavel extends NodeFator {

    public NodeId nodeId;
    public NodeSeletor nodeSeletor;
    private int dimensao;
    
    public int getDimensao() {
        return dimensao;
    }

    @Override
    public byte getKind() {
        return kind;
    }

    @Override
    public void setKind(byte kind) {
        this.kind = kind;
    }
    
    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
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
        v.visitVariavel(this);
    }
}
