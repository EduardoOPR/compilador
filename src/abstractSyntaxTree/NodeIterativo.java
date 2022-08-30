package abstractSyntaxTree;

public class NodeIterativo extends NodeComando {

    public NodeExpressao nodeExpressao;
    public NodeComando nodeComando;
    
    @Override
    public void visit(Visitor v) {
        v.visitIterativo(this);
    }
}
