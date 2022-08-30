package abstractSyntaxTree;

import lexicalAnalysis.Token;

public class NodeOpMul extends Token {

    public NodeOpMul(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public void visit(Visitor v) {
        v.visitOpMul(this);
    }
}
