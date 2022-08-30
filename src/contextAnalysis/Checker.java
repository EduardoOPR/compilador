package contextAnalysis;

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
import lexicalAnalysis.Token;

public class Checker implements Visitor {

    private final IdentificationTable t = new IdentificationTable();
    private byte tA;
    private int dimensãoVariavel = 0;

    public void Check(NodePrograma nodePrograma) {
        System.out.println("---> Iniciando identificacao de nomes\n");
        nodePrograma.visit(this);
    }

    @Override
    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao) {
        if (nodeAtribuicao != null) {
            byte v = -1, e = -1;
            if (nodeAtribuicao.nodeVariavel != null) {
                nodeAtribuicao.nodeVariavel.visit(this);
                v = nodeAtribuicao.nodeVariavel.kind;
            }
            if (nodeAtribuicao.nodeExpressao != null) {
                nodeAtribuicao.nodeExpressao.visit(this);
                e = nodeAtribuicao.nodeExpressao.kind;
            }
            if (v == e && v != -1 && e != -1) {
                nodeAtribuicao.kind = v;
            } else if (v == 3 && e == 1 || v == 4 && e == 2) {
                nodeAtribuicao.kind = v;
            } else {
                nodeAtribuicao.kind = -1;
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + nodeAtribuicao.nodeVariavel.getLine()
                        + " COLUMN: " + nodeAtribuicao.nodeVariavel.getColumn()
                        + " - Assignment with incompatible types - Type \""
                        + Token.spellings[v] + "\" is not compatible with type \""
                        + Token.spellings[e] + "\".");
                System.exit(0);
            }
        }
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {
        if (nodeBoolLit != null) {
            //System.out.println(nodeBoolLit.kind);
            //nodeBoolLit.kind;
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
            byte c = -1;
            if (nodeCondicional.nodeExpressao != null) {
                nodeCondicional.nodeExpressao.visit(this);
                c = nodeCondicional.nodeExpressao.kind;
                if (c != 5) {
                    System.out.println("CONTEXT ERROR! -"
                            + " LINE: " + nodeCondicional.nodeExpressao.getLine()
                            + " COLUMN: " + nodeCondicional.nodeExpressao.getColumn()
                            + " - \"If Then\" with incompatible type expression"
                            + " - An expression of type \"boolean\" was expected, not type \""
                            + Token.spellings[c] + "\".");
                    System.exit(0);
                }
            }
            if (nodeCondicional.nodeComandoIf != null) {
                nodeCondicional.nodeComandoIf.visit(this);
            }
            if (nodeCondicional.nodeComandoElse != null) {
                nodeCondicional.nodeComandoElse.visit(this);
            }
        }
    }

    @Override
    public void visitCorpo(NodeCorpo nodeCorpo) {
        if (nodeCorpo != null) {
            if (nodeCorpo.nodeDeclaracoes != null) {
                nodeCorpo.nodeDeclaracoes.visit(this);
            }
            if (nodeCorpo.nodeComandoComposto != null) {
                nodeCorpo.nodeComandoComposto.visit(this);
            }
        }
    }

    @Override
    public void visitDeclaracao(NodeDeclaracao nodeDeclaracao) {
        if (nodeDeclaracao != null) {
            if (nodeDeclaracao.nodeDeclaracaoDeVariavel != null) {
                nodeDeclaracao.nodeDeclaracaoDeVariavel.visit(this);
            }
        }
    }

    @Override
    public void visitDeclaracaoDeVariavel(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel) {
        if (nodeDeclaracaoDeVariavel != null) {
            if (nodeDeclaracaoDeVariavel.nodeListaDeIds != null) {
                nodeDeclaracaoDeVariavel.nodeListaDeIds.visit(this);
            }
            if (nodeDeclaracaoDeVariavel.nodeTipo != null) {
                nodeDeclaracaoDeVariavel.nodeTipo.visit(this);
                if (nodeDeclaracaoDeVariavel.nodeTipo instanceof NodeTipoAgregado) {
                    nodeDeclaracaoDeVariavel.nodeTipo.kind = tA;
                }
            }
            nodeDeclaracaoDeVariavel.nodeListaDeIds.setDimensao(dimensãoVariavel);
            dimensãoVariavel = 0;
            t.enter(nodeDeclaracaoDeVariavel);

        }
    }

    @Override
    public void visitDeclaracoes(NodeDeclaracoes nodeDeclaracoes) {
        if (nodeDeclaracoes != null) {
            if (nodeDeclaracoes.nodeDeclaracao != null) {
                nodeDeclaracoes.nodeDeclaracao.visit(this);
            }
            if (nodeDeclaracoes.next != null) {
                nodeDeclaracoes.next.visit(this);
            }
        }
    }

    @Override
    public void visitExpressao(NodeExpressao nodeExpressao) {
        if (nodeExpressao != null) {
            byte es1 = -1, es2 = -1;
            if (nodeExpressao.nodeExpressaoSimples1 != null) {
                nodeExpressao.nodeExpressaoSimples1.visit(this);
                es1 = nodeExpressao.nodeExpressaoSimples1.kind;
                nodeExpressao.setLine(nodeExpressao.nodeExpressaoSimples1.getLine());
                nodeExpressao.setColumn(nodeExpressao.nodeExpressaoSimples1.getColumn());
            }
            if (nodeExpressao.nodeOpRel != null) {
                nodeExpressao.nodeOpRel.visit(this);
            }
            if (nodeExpressao.nodeExpressaoSimples2 != null) {
                nodeExpressao.nodeExpressaoSimples2.visit(this);
                es2 = nodeExpressao.nodeExpressaoSimples2.kind;
                if (es1 == es2 && es1 != -1 && es2 != -1 && es1 != 5 && es2 != 5) {
                    nodeExpressao.kind = 5;
                } else if (es1 == 3 && es2 == 1 || es1 == 4 && es2 == 2 || es1 == 1 && es2 == 3 || es1 == 2 && es2 == 4) {
                    nodeExpressao.kind = 5;
                } else {
                    nodeExpressao.kind = -1;
                }
            } else {
                nodeExpressao.kind = es1;
            }
            if (nodeExpressao.kind == -1) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + nodeExpressao.getLine()
                        + " COLUMN: " + nodeExpressao.getColumn()
                        + " - Malformed expression or incompatible types");
                System.exit(0);
            }
        }
    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {
        if (nodeExpressaoSimples != null) {
            byte t = -1, esc = -1;
            if (nodeExpressaoSimples.nodeTermo != null) {
                nodeExpressaoSimples.nodeTermo.visit(this);
                t = nodeExpressaoSimples.nodeTermo.kind;
                nodeExpressaoSimples.setLine(nodeExpressaoSimples.nodeTermo.getLine());
                nodeExpressaoSimples.setColumn(nodeExpressaoSimples.nodeTermo.getColumn());
            }
            if (nodeExpressaoSimples.nodeExpressaoSimplesComplemento != null) {
                nodeExpressaoSimples.nodeExpressaoSimplesComplemento.visit(this);
                NodeExpressaoSimplesComplemento e = nodeExpressaoSimples.nodeExpressaoSimplesComplemento;
                byte escAux;
                esc = e.nodeTermo.kind;
                do {
                    escAux = e.nodeTermo.kind;
                    if (e.nodeOpAd.getKind() == 19) { //Esse trecho limita a operação OR apenas para operandos boolean
                        if (!(esc == 5 && escAux == 5)) {
                            esc = -1;
                        }                             //O trecho termina aqui                  
                    } else if (esc != escAux && !(esc == 3 && escAux == 1 || esc == 4 && escAux == 2 || esc == 1 && escAux == 3 || esc == 2 && escAux == 4)) {
                        esc = -1;
                    }
                    e = e.next;
                } while (e != null);
                if (t == esc && esc != -1 && esc != -1 && t != 5 && esc != 5) {
                    nodeExpressaoSimples.kind = t;
                } else if (t == 3 && esc == 1 || t == 4 && esc == 2 || t == 1 && esc == 3 || t == 2 && esc == 4) {
                    nodeExpressaoSimples.kind = t;
                } else {
                    nodeExpressaoSimples.kind = -1;
                }
                if (nodeExpressaoSimples.nodeExpressaoSimplesComplemento.nodeOpAd.getKind() == 19) {
                    if (t == 5 && esc == 5) {
                        nodeExpressaoSimples.kind = t;
                    } else {
                        nodeExpressaoSimples.kind = -1;
                    }
                }
            } else {
                nodeExpressaoSimples.kind = t;
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
                nodeExpressaoSimplesComplemento.next.visit(this);
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

        }

    }

    @Override
    public void visitId(NodeId nodeId) {
        if (nodeId != null) {
            
        }
    }

    @Override
    public void visitIntLit(NodeIntLit nodeIntLit) {
        if (nodeIntLit != null) {

        }
    }

    @Override
    public void visitIterativo(NodeIterativo nodeIterativo) {
        if (nodeIterativo != null) {
            byte i = -1;
            if (nodeIterativo.nodeExpressao != null) {
                nodeIterativo.nodeExpressao.visit(this);
                i = nodeIterativo.nodeExpressao.kind;
                if (i != 5) {
                    System.out.println("CONTEXT ERROR! -"
                            + " LINE: " + nodeIterativo.nodeExpressao.getLine()
                            + " COLUMN: " + nodeIterativo.nodeExpressao.getColumn()
                            + " - \"While\" with incompatible type expression"
                            + " - An expression of type \"boolean\" was expected, not type \""
                            + Token.spellings[i] + "\".");
                    System.exit(0);
                }
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
                nodeListaDeIds.nodeId.visit(this);
            }
            if (nodeListaDeIds.next != null) {
                nodeListaDeIds.next.visit(this);
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
        }
    }

    @Override
    public void visitOpMul(NodeOpMul nodeOpMul) {
        if (nodeOpMul != null) {

        }
    }

    @Override
    public void visitOpRel(NodeOpRel nodeOpRel) {
        if (nodeOpRel != null) {

        }
    }

    @Override
    public void visitPrograma(NodePrograma nodePrograma) {
        if (nodePrograma != null) {
            if (nodePrograma.nodeCorpo != null) {
                nodePrograma.nodeCorpo.visit(this);
            }
        }
    }

    @Override
    public void visitSeletor(NodeSeletor nodeSeletor) {
        if (nodeSeletor != null) {
            byte s = -1;
            if (nodeSeletor.nodeExpressao != null) {
                nodeSeletor.nodeExpressao.visit(this);
                s = nodeSeletor.nodeExpressao.kind;
                if (s != 1 && s != 3) {
                    System.out.println("CONTEXT ERROR! -"
                            + " LINE: " + nodeSeletor.nodeExpressao.getLine()
                            + " COLUMN: " + nodeSeletor.nodeExpressao.getColumn() + " -"
                            + " Incompatible type index -"
                            + " An index of type \"integer\" or \"<int-lit>\" was expected, not type \""
                            + Token.spellings[s] + "\".");
                    System.exit(0);
                }
            }
            if (nodeSeletor.next != null) {
                nodeSeletor.next.visit(this);
            }
        }
    }

    @Override
    public void visitTermo(NodeTermo nodeTermo) {
        if (nodeTermo != null) {
            byte f = -1, tc = -1;
            if (nodeTermo.nodeFator != null) {
                nodeTermo.nodeFator.visit(this);
                f = nodeTermo.nodeFator.kind;
                nodeTermo.setLine(nodeTermo.nodeFator.getLine());
                nodeTermo.setColumn(nodeTermo.nodeFator.getColumn());
            }
            if (nodeTermo.nodeTermoComplemento != null) {
                nodeTermo.nodeTermoComplemento.visit(this);
                NodeTermoComplemento t = nodeTermo.nodeTermoComplemento;
                byte tcAux;
                tc = t.nodeFator.kind;
                do {
                    tcAux = t.nodeFator.kind;
                    if (t.nodeOpMul.getKind() == 20) { //Esse trecho limita a operação AND apenas para operandos boolean
                        if (!(tc == 5 && tcAux == 5)) {
                            tc = -1;
                        }                             //O trecho termina aqui                  
                    } else if (tc != tcAux && !(tc == 3 && tcAux == 1 || tc == 4 && tcAux == 2 || tc == 1 && tcAux == 3 || tc == 2 && tcAux == 4)) {
                        tc = -1;
                    }

                    t = t.next;
                } while (t != null);
                if (f == tc && f != -1 && tc != -1 && f != 5 && tc != 5) {
                    nodeTermo.kind = f;
                } else if (f == 3 && tc == 1 || f == 4 && tc == 2 || f == 1 && tc == 3 || f == 2 && tc == 4) {
                    nodeTermo.kind = f;
                } else {
                    nodeTermo.kind = -1;
                }
                if (nodeTermo.nodeTermoComplemento.nodeOpMul.getKind() == 20) {
                    if (f == 5 && tc == 5) {
                        nodeTermo.kind = f;
                    } else {
                        nodeTermo.kind = -1;
                    }
                }
            } else {
                nodeTermo.kind = f;
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
                nodeTermoComplemento.next.visit(this);
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
            dimensãoVariavel++;
            NodeIntLit indiceMenorS = null, indiceMaiorS;
            int indiceMenorI = 0, indiceMaiorI = 0;
            //ehTipoAgregado = true;
            if (nodeTipoAgregado.nodeTipo != null) {
                nodeTipoAgregado.nodeTipo.visit(this);
            }
            if (nodeTipoAgregado.nodeLiteral1 != null) {
                indiceMenorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral1);
                indiceMenorI = Integer.parseInt(indiceMenorS.getIntLiteral());

            }
            if (nodeTipoAgregado.nodeLiteral2 != null) {
                indiceMaiorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral2);
                indiceMaiorI = Integer.parseInt(indiceMaiorS.getIntLiteral());
            }
            if (indiceMenorI >= indiceMaiorI) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + indiceMenorS.getLine()
                        + " COLUMN: " + indiceMenorS.getColumn()
                        + " - Index \"" + indiceMenorI + "\""
                        + " should be lower than index \"" + indiceMaiorI + "\".");
                System.exit(0);
            }
        }
    }

    @Override
    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples) {
        if (nodeTipoSimples != null) {
            switch (nodeTipoSimples.tipoSimples) {
                case "integer":
                    nodeTipoSimples.kind = 3;
                    break;
                case "real":
                    nodeTipoSimples.kind = 4;
                    break;
                case "boolean":
                    nodeTipoSimples.kind = 5;
                    break;
                default:
                    nodeTipoSimples.kind = -1;
                    break;
            }
            tA = nodeTipoSimples.kind;
        }
    }

    @Override
    public void visitVariavel(NodeVariavel nodeVariavel) {
        if (nodeVariavel != null) {
            NodeId id;
            int quantidadeSeletor = 0;
            if (nodeVariavel.nodeId != null) {
                nodeVariavel.nodeId.visit(this);
                id = t.retrieve(nodeVariavel.nodeId);
                //System.out.println(kind);
                nodeVariavel.setKind(id.getKind());
                nodeVariavel.setDimensao(id.getDimensao());
                nodeVariavel.setLine(nodeVariavel.nodeId.getLine());
                nodeVariavel.setColumn(nodeVariavel.nodeId.getColumn());
            }
            if (nodeVariavel.nodeSeletor != null) {
                nodeVariavel.nodeSeletor.visit(this);
                NodeSeletor lS = nodeVariavel.nodeSeletor;
                do {
                    lS = lS.next;
                    quantidadeSeletor++;
                } while (lS != null);
            }
            if (nodeVariavel.getDimensao() != quantidadeSeletor) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + nodeVariavel.nodeId.getLine()
                        + " COLUMN: " + nodeVariavel.nodeId.getColumn()
                        + " - Error in indexing the variable - The variable \""
                        + nodeVariavel.nodeId.getSpelling()
                        + "\" has dimensions \""
                        + nodeVariavel.getDimensao()
                        + "\" and not \""
                        + quantidadeSeletor
                        + "\".");
                System.exit(0);
            }
        }
    }

    public void ImpimeIdentificationTable() {
        t.imprime();
    }

}