import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Impressora impressora;

    public Menu(Impressora impressora) {
        this.impressora = impressora;
    }

    public int exibirMenu() {
        Scanner ler = new Scanner(System.in);

        System.out.println();
        System.out.println("1. Configurar conexão");
        System.out.println("2. Abrir conexão");
        System.out.println("3. Fechar conexão");
        System.out.println("4. Imprimir texto");
        System.out.println("5. Imprimir QR Code");
        System.out.println("6. Imprimir código de barras");
        System.out.println("7. Imprimir XML SAT");
        System.out.println("8. Imprimir XML de cancelamento SAT");
        System.out.println("9. Abrir gaveta");
        System.out.println("10. Sinal sonoro (bip)");
        System.out.println("11. Avançar papel, consultar status ou cortar");
        System.out.println("12. Sair");
        int op = lerInteiroNoIntervalo(
                ler, "Escolha uma opção entre 1 e 12:", 1, 12);

        switch (op) {
            case 1:
                configurarConexao(ler);
                break;

            case 2:
                System.out.println("Abrindo conexão...");
                impressora.abrirConexao();
                break;

            case 3:
                System.out.println("Fechando conexão...");
                impressora.fecharConexao();
                break;

            case 4:
                imprimirTexto(ler);
                break;

            case 5:
                imprimirQRCode(ler);
                break;

            case 6:
                imprimirCodigoBarras(ler);
                break;

            case 7:
                imprimirXMLSAT(ler);
                break;

            case 8:
                imprimirXMLCancelamentoSAT(ler);
                break;

            case 9:
                abrirGaveta(ler);
                break;

            case 10:
                emitirSinalSonoro(ler);
                break;

            case 11:
                exibirOpcoesExtras(ler);
                break;

            case 12:
                if (impressora.isConexaoAberta()) {
                    impressora.fecharConexao();
                }

                System.out.println("Saindo do programa...");
                break;
        }

        return op;
    }

    private void configurarConexao(Scanner ler) {
        if (impressora.isConexaoAberta()) {
            System.out.println(
                    "Feche a conexão atual antes de configurar outra.");
            return;
        }

        System.out.println("Tipos de conexão:");
        System.out.println("1 - USB");
        System.out.println("2 - Serial");
        System.out.println("3 - Rede");
        System.out.println("4 - Bluetooth");
        int tipo = lerInteiroNoIntervalo(
                ler, "Digite o tipo entre 1 e 4:", 1, 4);

        System.out.println("Digite o modelo da impressora, como i8 ou i9:");
        String modelo = ler.nextLine();

        System.out.println(
                "Digite a conexão, como USB, COM2 ou um endereço IP:");
        String conexao = ler.nextLine();

        System.out.println("Digite o parâmetro.");
        int parametro;

        if (tipo == 1) {
            parametro = lerInteiroNoIntervalo(
                    ler, "Para USB, o parâmetro permitido é 0:", 0, 0);
        } else if (tipo == 2) {
            parametro = lerInteiroMinimo(
                    ler, "Digite a velocidade da conexão serial (mínimo 1):", 1);
        } else if (tipo == 3) {
            parametro = lerInteiroNoIntervalo(
                    ler, "Digite a porta da rede entre 1 e 65535:", 1, 65535);
        } else {
            parametro = lerInteiroMinimo(
                    ler, "Digite o parâmetro do Bluetooth (mínimo 0):", 0);
        }

        impressora.configurarConexao(
                tipo, modelo, conexao, parametro);
    }

    private void imprimirTexto(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        System.out.println("Digite o texto:");
        String texto = ler.nextLine();

        System.out.println("Posição do texto:");
        System.out.println("0 - Esquerda");
        System.out.println("1 - Centro");
        System.out.println("2 - Direita");
        int posicao = lerInteiroNoIntervalo(
                ler, "Digite a posição entre 0 e 2:", 0, 2);

        System.out.println("Estilo do texto:");
        System.out.println("0 - Fonte A");
        System.out.println("1 - Fonte B");
        System.out.println("2 - Sublinhado");
        System.out.println("4 - Invertido");
        System.out.println("8 - Negrito");
        System.out.println("Os estilos podem ser somados.");
        int estilo = lerInteiroNoIntervalo(
                ler, "Digite o estilo ou a soma dos estilos entre 0 e 15:", 0, 15);

        int tamanho = lerInteiroNoIntervalo(
                ler, "Digite o tamanho entre 0 e 7:", 0, 7);

        impressora.imprimirTexto(
                texto, posicao, estilo, tamanho);
    }

    private void imprimirQRCode(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        System.out.println("Digite o conteúdo do QR Code:");
        String dados = ler.nextLine();

        int tamanho = lerInteiroNoIntervalo(
                ler, "Digite o tamanho entre 1 e 6:", 1, 6);

        int nivelCorrecao = lerInteiroNoIntervalo(
                ler, "Digite o nível de correção entre 1 e 4:", 1, 4);

        impressora.imprimirQRCode(
                dados, tamanho, nivelCorrecao);
    }

    private void imprimirCodigoBarras(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        System.out.println("Tipos de código de barras:");
        System.out.println("0 - UPC-A");
        System.out.println("2 - EAN13");
        System.out.println("3 - EAN8");
        System.out.println("4 - CODE 39");
        System.out.println("8 - CODE 128");
        int tipo = lerInteiroPermitido(
                ler, "Digite um dos tipos permitidos: 0, 2, 3, 4 ou 8:",
                new int[] {0, 2, 3, 4, 8});

        System.out.println("Digite os dados do código de barras:");
        String dados = ler.nextLine();

        int altura = lerInteiroNoIntervalo(
                ler, "Digite a altura entre 1 e 255:", 1, 255);

        int largura = lerInteiroNoIntervalo(
                ler, "Digite a largura entre 1 e 6:", 1, 6);

        System.out.println("Posição do texto HRI:");
        System.out.println("1 - Acima");
        System.out.println("2 - Abaixo");
        System.out.println("3 - Acima e abaixo");
        System.out.println("4 - Não imprimir");
        int hri = lerInteiroNoIntervalo(
                ler, "Digite a posição HRI entre 1 e 4:", 1, 4);

        impressora.imprimirCodigoBarras(
                tipo, dados, altura, largura, hri);
    }

    private void imprimirXMLSAT(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        System.out.println("Digite o caminho do arquivo XML SAT:");
        String caminhoArquivo = ler.nextLine();

        impressora.imprimirXMLSAT(caminhoArquivo);
    }

    private void imprimirXMLCancelamentoSAT(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        System.out.println(
                "Digite o caminho do XML de cancelamento SAT:");
        String caminhoArquivo = ler.nextLine();

        impressora.imprimirXMLCancelamentoSAT(
                caminhoArquivo);
    }

    private void abrirGaveta(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        int pino = lerInteiroNoIntervalo(
                ler, "Digite o pino 0 ou 1:", 0, 1);

        int tempoInicio = lerInteiroNoIntervalo(
                ler, "Digite o tempo inicial entre 1 e 255:", 1, 255);

        int tempoFim = lerInteiroNoIntervalo(
                ler, "Digite o tempo final entre 1 e 255:", 1, 255);

        impressora.abrirGaveta(
                pino, tempoInicio, tempoFim);
    }

    private void emitirSinalSonoro(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        int quantidade = lerInteiroNoIntervalo(
                ler, "Digite a quantidade de bips entre 1 e 63:", 1, 63);

        impressora.sinalSonoro(quantidade);
    }

    private void exibirOpcoesExtras(Scanner ler) {
        if (!verificarConexao()) {
            return;
        }

        System.out.println("1 - Avançar papel");
        System.out.println("2 - Consultar status do papel");
        System.out.println("3 - Cortar papel");
        int opcaoExtra = lerInteiroNoIntervalo(
                ler, "Escolha uma opção entre 1 e 3:", 1, 3);

        switch (opcaoExtra) {
            case 1:
                int linhas = lerInteiroMinimo(
                        ler, "Digite a quantidade de linhas (mínimo 1):", 1);

                impressora.avancarPapel(linhas);
                break;

            case 2:
                // O parâmetro 3 consulta o estado do papel.
                impressora.consultarStatus(3);
                break;

            case 3:
                int avanco = lerInteiroMinimo(
                        ler, "Digite o avanço antes do corte (mínimo 0):", 0);

                impressora.cortarPapel(avanco);
                break;
        }
    }

    private int lerInteiro(Scanner ler, String mensagem) {
        while (true) {
            System.out.println(mensagem);

            try {
                int valor = ler.nextInt();
                ler.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Digite somente números inteiros.");
                ler.nextLine();
            }
        }
    }

    private int lerInteiroNoIntervalo(
            Scanner ler, String mensagem, int minimo, int maximo) {
        while (true) {
            int valor = lerInteiro(ler, mensagem);

            if (valor >= minimo && valor <= maximo) {
                return valor;
            }

            if (minimo == maximo) {
                System.out.println("Valor inválido. O valor permitido é " + minimo + ".");
            } else {
                System.out.println("Valor fora do intervalo. Digite um número entre "
                        + minimo + " e " + maximo + ".");
            }
        }
    }

    private int lerInteiroMinimo(Scanner ler, String mensagem, int minimo) {
        while (true) {
            int valor = lerInteiro(ler, mensagem);

            if (valor >= minimo) {
                return valor;
            }

            System.out.println("Valor fora do intervalo. Digite um número maior ou igual a "
                    + minimo + ".");
        }
    }

    private int lerInteiroPermitido(
            Scanner ler, String mensagem, int[] valoresPermitidos) {
        while (true) {
            int valor = lerInteiro(ler, mensagem);

            for (int valorPermitido : valoresPermitidos) {
                if (valor == valorPermitido) {
                    return valor;
                }
            }

            System.out.println("Valor não permitido. Digite uma das opções informadas.");
        }
    }

    private boolean verificarConexao() {
        if (!impressora.isConexaoAberta()) {
            System.out.println(
                    "Conexão fechada. Abra a conexão antes.");
            return false;
        }

        return true;
    }
}
