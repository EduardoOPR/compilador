package abstractSyntaxTree;

public interface Visitor {

    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao);

    public void visitBoolLit(NodeBoolLit nodeBoolLit);

    public void visitComando(NodeComando nodeComando); //É abstrata

    public void visitComandoComposto(NodeComandoComposto nodeComandoComposto);

    public void visitCondicional(NodeCondicional nodeCondicional);

    public void visitCorpo(NodeCorpo nodeCorpo);

    public void visitDeclaracao(NodeDeclaracao nodeDeclaracao);

    public void visitDeclaracaoDeVariavel(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel);

    public void visitDeclaracoes(NodeDeclaracoes nodeDeclaracoes);

    public void visitExpressao(NodeExpressao nodeExpressao);

    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples);

    public void visitExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento); //Não sei se precisa

    public void visitFator(NodeFator nodeFator);

    public void visitFloatLit(NodeFloatLit nodeFloatLit);

    public void visitId(NodeId nodeId);

    public void visitIntLit(NodeIntLit nodeIntLit);

    public void visitIterativo(NodeIterativo nodeIterativo);

    public void visitListaDeComandos(NodeListaDeComandos nodeListaDeComandos);

    public void visitListaDeIds(NodeListaDeIds nodeListaDeIds);

    public void visitLiteral(NodeLiteral nodeLiteral); //É abstrata

    public void visitOpAd(NodeOpAd nodeOpAd);

    public void visitOpMul(NodeOpMul nodeOpMul);

    public void visitOpRel(NodeOpRel nodeOpRel);

    public void visitPrograma(NodePrograma nodePrograma);

    public void visitSeletor(NodeSeletor nodeSeletor);

    public void visitTermo(NodeTermo nodeTermo);

    public void visitTermoComplemento(NodeTermoComplemento nodeTermoComplemento); //Não sei se precisa

    public void visitTipo(NodeTipo nodeTipo); //É abstrata

    public void visitTipoAgregado(NodeTipoAgregado nodeTipoAgregado);

    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples);

    public void visitVariavel(NodeVariavel nodeVariavel);
}
