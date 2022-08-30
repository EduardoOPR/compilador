package abstractSyntaxTree;

public class NodeExpressao extends NodeFator {

    public NodeExpressaoSimples nodeExpressaoSimples1, nodeExpressaoSimples2;
    public NodeOpRel nodeOpRel;

    public NodeExpressaoSimples getNodeExpressaoSimples1() {
        return nodeExpressaoSimples1;
    }

    public void setNodeExpressaoSimples1(NodeExpressaoSimples nodeExpressaoSimples1) {
        this.nodeExpressaoSimples1 = nodeExpressaoSimples1;
    }

    public NodeExpressaoSimples getNodeExpressaoSimples2() {
        return nodeExpressaoSimples2;
    }

    public void setNodeExpressaoSimples2(NodeExpressaoSimples nodeExpressaoSimples2) {
        this.nodeExpressaoSimples2 = nodeExpressaoSimples2;
    }

    public NodeOpRel getNodeOpRel() {
        return nodeOpRel;
    }

    public void setNodeOpRel(NodeOpRel nodeOpRel) {
        this.nodeOpRel = nodeOpRel;
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
        v.visitExpressao(this);
    }
}
