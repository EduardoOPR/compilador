package abstractSyntaxTree;

public class NodeAtribuicao extends NodeComando {

    public NodeVariavel nodeVariavel;
    public NodeExpressao nodeExpressao;
    public byte kind;

    @Override
    public void visit(Visitor v) {
        v.visitAtribuicao(this);

    }
}
