package abstractSyntaxTree;

public class NodeSeletor {

    public NodeExpressao nodeExpressao;
    public NodeSeletor next;
    
    public void visit(Visitor v) {
        v.visitSeletor(this);
    }
}
