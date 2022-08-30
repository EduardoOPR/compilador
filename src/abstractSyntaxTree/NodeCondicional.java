package abstractSyntaxTree;

public class NodeCondicional extends NodeComando {

    public NodeExpressao nodeExpressao;
    public NodeComando nodeComandoIf, nodeComandoElse;

    @Override
    public void visit(Visitor v) {
        v.visitCondicional(this);
    }
}
