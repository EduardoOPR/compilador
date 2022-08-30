package TreeDrawer;

import abstractSyntaxTree.NodeAtribuicao;
import abstractSyntaxTree.NodeBoolLit;
import abstractSyntaxTree.NodeComando;
import abstractSyntaxTree.NodeComandoComposto;
import abstractSyntaxTree.NodeCondicional;
import abstractSyntaxTree.NodeCorpo;
import abstractSyntaxTree.NodeDeclaracao;
import abstractSyntaxTree.NodeDeclaracaoDeVariavel;
import abstractSyntaxTree.NodeDeclaracoes;
import abstractSyntaxTree.NodeExpressao;
import abstractSyntaxTree.NodeExpressaoSimples;
import abstractSyntaxTree.NodeExpressaoSimplesComplemento;
import abstractSyntaxTree.NodeFator;
import abstractSyntaxTree.NodeFloatLit;
import abstractSyntaxTree.NodeId;
import abstractSyntaxTree.NodeIntLit;
import abstractSyntaxTree.NodeIterativo;
import abstractSyntaxTree.NodeListaDeComandos;
import abstractSyntaxTree.NodeListaDeIds;
import abstractSyntaxTree.NodeLiteral;
import abstractSyntaxTree.NodeOpAd;
import abstractSyntaxTree.NodeOpMul;
import abstractSyntaxTree.NodeOpRel;
import abstractSyntaxTree.NodePrograma;
import abstractSyntaxTree.NodeSeletor;
import abstractSyntaxTree.NodeTermo;
import abstractSyntaxTree.NodeTermoComplemento;
import abstractSyntaxTree.NodeTipo;
import abstractSyntaxTree.NodeTipoAgregado;
import abstractSyntaxTree.NodeTipoSimples;
import abstractSyntaxTree.NodeVariavel;
import abstractSyntaxTree.Visitor;

public class Printer implements Visitor {

    public int i = 0;

    public void print(NodePrograma nodePrograma) {
        System.out.println("---> Imprimindo a arvore\n");
        nodePrograma.visit(this);
    }

    public void indent() {
        for (int j = 0; j < i; j++) {
            System.out.print("|");
        }
    }

    @Override
    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao) {
        if (nodeAtribuicao != null) {
            if (nodeAtribuicao.nodeVariavel != null) {
                nodeAtribuicao.nodeVariavel.visit(this);
            }
            i++;
            indent();
            System.out.println("=");
            if (nodeAtribuicao.nodeExpressao != null) {
                nodeAtribuicao.nodeExpressao.visit(this);
            }
            i--;
        }
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {
        if (nodeBoolLit != null) {
            indent();
            System.out.print(nodeBoolLit.booleano);
            System.out.println("");
        }
    }

    @Override
    public void visitComando(NodeComando nodeComando) {
        if (nodeComando != null) {
            nodeComando.visit(this);
        }
    }

    @Override
    public void visitComandoComposto(NodeComandoComposto nodeComandoComposto) {
        if (nodeComandoComposto != null) {
            if (nodeComandoComposto.nodeListaDeComandos != null) {
                nodeComandoComposto.nodeListaDeComandos.visit(this);
            }
        }
    }

    @Override
    public void visitCondicional(NodeCondicional nodeCondicional) {
        if (nodeCondicional != null) {
            if (nodeCondicional.nodeExpressao != null) {
                nodeCondicional.nodeExpressao.visit(this);
                // System.out.println("");
            }
            i++;
            if (nodeCondicional.nodeComandoIf != null) {
                nodeCondicional.nodeComandoIf.visit(this);
                System.out.println("");
            }
            if (nodeCondicional.nodeComandoElse != null) {
                nodeCondicional.nodeComandoElse.visit(this);
                System.out.println("");
            }
            i--;
        }
    }

    @Override
    public void visitCorpo(NodeCorpo nodeCorpo) {
        if (nodeCorpo != null) {
            if (nodeCorpo.nodeDeclaracoes != null) {
                nodeCorpo.nodeDeclaracoes.visit(this);
                System.out.println("");
            }
            if (nodeCorpo.nodeComandoComposto != null) {
                nodeCorpo.nodeComandoComposto.visit(this);
            }
        }
    }

    @Override
    public void visitDeclaracao(NodeDeclaracao nodeDeclaracao) {
        if (nodeDeclaracao != null) {
            nodeDeclaracao.nodeDeclaracaoDeVariavel.visit(this);
        }
    }

    @Override
    public void visitDeclaracaoDeVariavel(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel) {
        if (nodeDeclaracaoDeVariavel != null) {
            if (nodeDeclaracaoDeVariavel.nodeTipo != null) {
                nodeDeclaracaoDeVariavel.nodeTipo.visit(this);
            }
            i++;
            if (nodeDeclaracaoDeVariavel.nodeListaDeIds != null) {
                nodeDeclaracaoDeVariavel.nodeListaDeIds.visit(this);
            }
            i--;
        }
    }

    @Override
    public void visitDeclaracoes(NodeDeclaracoes nodeDeclaracoes) {
        if (nodeDeclaracoes != null) {
            if (nodeDeclaracoes.nodeDeclaracao != null) {
                System.out.println("");
                nodeDeclaracoes.nodeDeclaracao.visit(this);
            }
            if (nodeDeclaracoes.next != null) {
                i++;
                nodeDeclaracoes.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitExpressao(NodeExpressao nodeExpressao) {
        if (nodeExpressao != null) {
            if (nodeExpressao.nodeExpressaoSimples1 != null) {
                nodeExpressao.nodeExpressaoSimples1.visit(this);
            }
            if (nodeExpressao.nodeOpRel != null) {
                nodeExpressao.nodeOpRel.visit(this);
            }
            if (nodeExpressao.nodeExpressaoSimples2 != null) {
                nodeExpressao.nodeExpressaoSimples2.visit(this);
            }
        }
    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {
        if (nodeExpressaoSimples != null) {
            if (nodeExpressaoSimples.nodeTermo != null) {
                nodeExpressaoSimples.nodeTermo.visit(this);
            }
            if (nodeExpressaoSimples.nodeExpressaoSimplesComplemento != null) {
                i++;
                nodeExpressaoSimples.nodeExpressaoSimplesComplemento.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {
        if (nodeExpressaoSimplesComplemento != null) {
            if (nodeExpressaoSimplesComplemento.nodeOpAd != null) {
                nodeExpressaoSimplesComplemento.nodeOpAd.visit(this);
            }
            if (nodeExpressaoSimplesComplemento.nodeTermo != null) {
                nodeExpressaoSimplesComplemento.nodeTermo.visit(this);
            }
            if (nodeExpressaoSimplesComplemento.next != null) {
                i++;
                nodeExpressaoSimplesComplemento.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitFator(NodeFator nodeFator) {
        if (nodeFator != null) {
            nodeFator.visit(this);
        }
    }

    @Override
    public void visitFloatLit(NodeFloatLit nodeFloatLit) {
        if (nodeFloatLit != null) {
            indent();
            System.out.println(nodeFloatLit.floatLiteral);
        }
    }

    @Override
    public void visitId(NodeId nodeId) {
        if (nodeId != null) {
            indent();
            System.out.print(nodeId.spelling);
        }
    }

    @Override
    public void visitIntLit(NodeIntLit nodeIntLit) {
        if (nodeIntLit != null) {
            indent();
            System.out.print(nodeIntLit.intLiteral);
            System.out.println("");
        }
    }

    @Override
    public void visitIterativo(NodeIterativo nodeIterativo) {
        if (nodeIterativo != null) {
            if (nodeIterativo.nodeExpressao != null) {
                nodeIterativo.nodeExpressao.visit(this);
                System.out.println("");
            }
            if (nodeIterativo.nodeComando != null) {
                nodeIterativo.nodeComando.visit(this);
            }
        }
    }

    @Override
    public void visitListaDeComandos(NodeListaDeComandos nodeListaDeComandos) {
        if (nodeListaDeComandos != null) {
            if (nodeListaDeComandos.nodeComando != null) {
                nodeListaDeComandos.nodeComando.visit(this);
                System.out.println("");
            }
            if (nodeListaDeComandos.next != null) {
                nodeListaDeComandos.next.visit(this);
            }
        }
    }

    @Override
    public void visitListaDeIds(NodeListaDeIds nodeListaDeIds) {
        if (nodeListaDeIds != null) {
            if (nodeListaDeIds.nodeId != null) {
                indent();
                System.out.print(nodeListaDeIds.nodeId.spelling);
                System.out.println("");
            }
            if (nodeListaDeIds.next != null) {
                i++;
                nodeListaDeIds.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitLiteral(NodeLiteral nodeLiteral) {
        if (nodeLiteral != null) {
            nodeLiteral.visit(this);
        }
    }

    @Override
    public void visitOpAd(NodeOpAd nodeOpAd) {
        if (nodeOpAd != null) {
            indent();
            System.out.println(nodeOpAd.spelling);
        }
    }

    @Override
    public void visitOpMul(NodeOpMul nodeOpMul) {
        if (nodeOpMul != null) {
            indent();
            System.out.println(nodeOpMul.spelling);
        }
    }

    @Override
    public void visitOpRel(NodeOpRel nodeOpRel) {
        if (nodeOpRel != null) {
            indent();
            System.out.println(nodeOpRel.spelling);
        }
    }

    @Override
    public void visitPrograma(NodePrograma nodePrograma) {
        if (nodePrograma != null) {
            indent();
            if (nodePrograma.nodeId != null) {
                System.out.print("programa ");
                nodePrograma.nodeId.visit(this);
                System.out.println("");
            }
            i++;
            if (nodePrograma.nodeCorpo != null) {
                nodePrograma.nodeCorpo.visit(this);
            }
            i--;
        }
    }

    @Override
    public void visitSeletor(NodeSeletor nodeSeletor) {
        if (nodeSeletor != null) {
            if (nodeSeletor.nodeExpressao != null) {
                nodeSeletor.nodeExpressao.visit(this);
            }
            if (nodeSeletor.next != null) {
                i++;
                nodeSeletor.next.visit(this);
                i--;
            }
        }

    }

    @Override
    public void visitTermo(NodeTermo nodeTermo) {
        if (nodeTermo != null) {
            if (nodeTermo.nodeFator != null) {
                nodeTermo.nodeFator.visit(this);
            }
            if (nodeTermo.nodeTermoComplemento != null) {
                i++;
                nodeTermo.nodeTermoComplemento.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitTermoComplemento(NodeTermoComplemento nodeTermoComplemento) {
        if (nodeTermoComplemento != null) {
            if (nodeTermoComplemento.nodeOpMul != null) {
                nodeTermoComplemento.nodeOpMul.visit(this);
            }
            if (nodeTermoComplemento.nodeFator != null) {
                nodeTermoComplemento.nodeFator.visit(this);
            }
            if (nodeTermoComplemento.next != null) {
                i++;
                nodeTermoComplemento.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitTipo(NodeTipo nodeTipo) {
        if (nodeTipo != null) {
            nodeTipo.visit(this);
        }
    }

    @Override
    public void visitTipoAgregado(NodeTipoAgregado nodeTipoAgregado) {
        if (nodeTipoAgregado != null) {
            if (nodeTipoAgregado.nodeTipo != null) {
                nodeTipoAgregado.nodeTipo.visit(this);
            }
            if (nodeTipoAgregado.nodeLiteral1 != null) {
                nodeTipoAgregado.nodeLiteral1.visit(this);
            }
            if (nodeTipoAgregado.nodeLiteral2 != null) {
                nodeTipoAgregado.nodeLiteral2.visit(this);
            }
        }
    }

    @Override
    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples) {
        if (nodeTipoSimples != null) {
            System.out.println(nodeTipoSimples.tipoSimples);
        }
    }

    @Override
    public void visitVariavel(NodeVariavel nodeVariavel) {
        if (nodeVariavel != null) {
            if (nodeVariavel.nodeId != null) {
                nodeVariavel.nodeId.visit(this);
                System.out.println("");
            }
            i++;
            if (nodeVariavel.nodeSeletor != null) {
                nodeVariavel.nodeSeletor.visit(this);
            }
            i--;
        }
    }
}
