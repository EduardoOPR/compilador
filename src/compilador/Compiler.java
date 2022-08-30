package compilador;

import File.ManipulationFile;
import TreeDrawer.Printer;
import abstractSyntaxTree.NodePrograma;
import contextAnalysis.Checker;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexicalAnalysis.Token;
import syntaxAnalysis.Parser;

public class Compiler {
    
        public static final char EOT = '\u0000';

        public static void main(String[] args) throws IOException {
            
        Scanner ler = new Scanner(System.in);
        String programaFonte;
        int opcao;
        System.out.print("Caminho do código fonte: ");
        programaFonte = ler.nextLine();
        ManipulationFile file = new ManipulationFile(programaFonte);
        do {
            System.out.println("Selecione até qual fase deseja executar o compilador: ");
            System.out.println("1 - Análise Léxica");
            System.out.println("2 - Análise Sintática");
            System.out.println("3 - Impressão da Árvore");
            System.out.println("4 - Análise de Contexto");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(ler.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
        } while (opcao < 1 || opcao > 6);
        Parser parser = new Parser(file);
        NodePrograma nodePrograma;
        switch (opcao) {
            case 1:
                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------- ANÁLISE LÉXICA -------------------------");
                System.out.println("----------------------------------------------------------------");
                Token token;
                while (parser.scanner.getCurrentChar() != EOT) {
                    token = parser.scanner.scan();
                    if (parser.scanner.getCurrentKind() == -1) {
                        System.out.print("Token inválido! = ");
                    } else {
                        System.out.print("Token válido = ");
                    }
                    System.out.println("Spelling: " + token.getSpelling()
                            + "   Kind: " + token.getKind()
                            + "   Column: " + token.getColumn()
                            + "   Line: " + token.getLine());
                }
                file.source.close();
                break;
            case 2:
                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------- ANÁLISE SINTÁTICA ----------------------");
                System.out.println("----------------------------------------------------------------");
                parser.parse();
                file.source.close();
                break;
            case 3:
                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------- IMPRESSÃO DA ÁRVORE---------------------");
                System.out.println("----------------------------------------------------------------");
                Printer printer = new Printer();
                nodePrograma = parser.parse();
                printer.print(nodePrograma);
                file.source.close();
                break;
            case 4:
                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------- ANÁLISE DE CONTEXTO --------------------");
                System.out.println("----------------------------------------------------------------");
                Checker checker = new Checker();
                nodePrograma = parser.parse();
                checker.Check(nodePrograma);
                checker.ImpimeIdentificationTable();
                file.source.close();
                break;
            case 5:
                file.source.close();
                System.exit(0);
        }
    }
}