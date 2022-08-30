package abstractSyntaxTree;

public class NodeDeclaracoes {

    public NodeDeclaracao nodeDeclaracao;
    public NodeDeclaracoes next;

    public NodeDeclaracoes(NodeDeclaracao nodeDeclaracao, NodeDeclaracoes next) {
        this.nodeDeclaracao = nodeDeclaracao;
        this.next = next;
    }

    public void visit(Visitor v) {
        v.visitDeclaracoes(this);
    }

}
