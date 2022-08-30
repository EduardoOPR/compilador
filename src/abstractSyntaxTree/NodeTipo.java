package abstractSyntaxTree;

public abstract class NodeTipo {

    public byte kind;
    private int dimensao;

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    
    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }
    
    public abstract void visit(Visitor v);
}
