package abstractSyntaxTree;

public class NodeTipoAgregado extends NodeTipo {
    
    public NodeLiteral nodeLiteral1, nodeLiteral2;
    public NodeTipo nodeTipo;
    
    @Override
    public void visit(Visitor v) {
        v.visitTipoAgregado(this);
    }
}
