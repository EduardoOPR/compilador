package abstractSyntaxTree;

import lexicalAnalysis.Token;

public class NodeOpAd extends Token {

    public NodeOpAd(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public void visit(Visitor v) {
        v.visitOpAd(this);
    }
}
