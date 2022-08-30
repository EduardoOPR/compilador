package abstractSyntaxTree;

public class NodeListaDeComandos {

    public NodeComando nodeComando;
    public NodeListaDeComandos next;
    
    public void visit(Visitor v) {
        v.visitListaDeComandos(this);
    }
}
