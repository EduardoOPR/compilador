package abstractSyntaxTree;

public class NodeExpressaoSimples {
    
    public NodeTermo nodeTermo;
    public NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento;
    public byte kind;
    public int line;
    public int column;

    public NodeTermo getNodeTermo() {
        return nodeTermo;
    }

    public void setNodeTermo(NodeTermo nodeTermo) {
        this.nodeTermo = nodeTermo;
    }

    public NodeExpressaoSimplesComplemento getNodeExpressaoSimplesComplemento() {
        return nodeExpressaoSimplesComplemento;
    }

    public void setNodeExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {
        this.nodeExpressaoSimplesComplemento = nodeExpressaoSimplesComplemento;
    }

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
    
    public void visit(Visitor v) {
        v.visitExpressaoSimples(this);
    }
    
}
