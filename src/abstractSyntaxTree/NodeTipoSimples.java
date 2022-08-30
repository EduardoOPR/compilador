package abstractSyntaxTree;

public class NodeTipoSimples extends NodeTipo {

    public String tipoSimples;
    
    public NodeTipoSimples(String tipoSimples) {
        this.tipoSimples = tipoSimples;
    }

    @Override
    public void visit(Visitor v) {
        v.visitTipoSimples(this);
    }
}
