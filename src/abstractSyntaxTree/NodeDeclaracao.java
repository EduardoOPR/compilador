package abstractSyntaxTree;

public class NodeDeclaracao {
    
    public NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel;
    
    public NodeDeclaracao(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel) {
        this.nodeDeclaracaoDeVariavel = nodeDeclaracaoDeVariavel;
    }
    
    public void visit(Visitor v) {
        v.visitDeclaracao(this);
    }
}
