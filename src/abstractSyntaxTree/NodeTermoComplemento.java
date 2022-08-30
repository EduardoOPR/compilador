package abstractSyntaxTree;

public class NodeTermoComplemento {

    public NodeOpMul nodeOpMul;
    public NodeFator nodeFator;
    public NodeTermoComplemento next;
    
    public void visit(Visitor v) {
        v.visitTermoComplemento(this);
    }
}
