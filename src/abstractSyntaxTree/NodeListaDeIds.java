package abstractSyntaxTree;

public class NodeListaDeIds {

    public NodeId nodeId;
    public NodeListaDeIds next;
    private int dimensao;

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    
    public NodeListaDeIds(NodeId nodeId, NodeListaDeIds next) {
        this.nodeId = nodeId;
        this.next = next;
    }

    public void visit(Visitor v) {
        v.visitListaDeIds(this);
    }
}
