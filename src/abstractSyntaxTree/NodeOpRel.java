package abstractSyntaxTree;

import lexicalAnalysis.Token;

public class NodeOpRel extends Token {

    public NodeOpRel(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public void visit(Visitor v) {
        v.visitOpRel(this);
    }
}
