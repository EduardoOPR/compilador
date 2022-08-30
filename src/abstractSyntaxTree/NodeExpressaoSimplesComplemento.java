package abstractSyntaxTree;

public class NodeExpressaoSimplesComplemento {
    
    public NodeOpAd nodeOpAd;
    public NodeTermo nodeTermo;
    public NodeExpressaoSimplesComplemento next;
    
    public void visit(Visitor v) {
        v.visitExpressaoSimplesComplemento(this);
    }
}
