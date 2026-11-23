import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Impressora {
    private int tipo;
    private String modelo;
    private String conexao;
    private int parametro;
    private boolean conexaoConfigurada;
    private boolean conexaoAberta;

    private static final String ASSINATURA_QR_CODE_CANCELAMENTO =
            "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0"
            + "E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQ"
            + "sE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+Z"
            + "fO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

    public void configurarConexao(int tipo, String modelo, String conexao, int parametro) {
        if (tipo < 1 || tipo > 4 || modelo == null || modelo.trim().isEmpty()
                || conexao == null || conexao.trim().isEmpty() || parametro < 0) {
            System.out.println("Dados de conexão inválidos.");
            return;
        }

        this.tipo = tipo;
        this.modelo = modelo;
        this.conexao = conexao;
        this.parametro = parametro;
        this.conexaoConfigurada = true;
        System.out.println("Conexão configurada com sucesso.");
    }

    public void abrirConexao() {
        if (!conexaoConfigurada) {
            System.out.println("Configure a conexão antes de abri-la.");
            return;
        }

        if (conexaoAberta) {
            System.out.println("A conexão já está aberta.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                    tipo, modelo, conexao, parametro);

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                conexaoAberta = false;
                System.out.println("Erro ao abrir a conexão. Código: " + retorno);
            }
        } catch (UnsatisfiedLinkError e) {
            conexaoAberta = false;
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void fecharConexao() {
        if (!verificarConexao()) {
            return;
        }

        int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
        if (retorno == 0) {
            conexaoAberta = false;
            System.out.println("Conexão fechada com sucesso.");
        } else {
            System.out.println("Erro ao fechar a conexão. Código: " + retorno);
        }
    }

    public void imprimirTexto(String dados, int posicao, int estilo, int tamanho) {
        if (!verificarConexao()) {
            return;
        }

        int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(
                dados, posicao, estilo, tamanho);

        // ImpressaoTexto retorna a quantidade enviada; apenas valor negativo é erro.
        if (retorno >= 0) {
            ImpressoraDLL.INSTANCE.Corte(5); // Avança 3 linhas após a impressão
            System.out.println("Texto enviado para impressão com sucesso.");
        } else {
            System.out.println("Erro ao imprimir texto. Código: " + retorno);
        }
    }

    public void cortarPapel(int avanco) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.Corte(avanco), "Papel cortado com sucesso",
                "Erro ao cortar o papel");
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    public void imprimirQRCode(String dados, int tamanho, int nivelCorrecao) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.ImpressaoQRCode(
                dados, tamanho, nivelCorrecao), "QR Code impresso com sucesso",
                "Erro ao imprimir QR Code");
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    public void imprimirCodigoBarras(int tipo, String dados, int altura, int largura, int hri) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                tipo, dados, altura, largura, hri), "Código de barras impresso com sucesso",
                "Erro ao imprimir código de barras");
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    public void imprimirXMLSAT(String caminhoArquivo) {
        if (!verificarConexao()) {
            return;
        }

        try {
            String xml = lerArquivo(caminhoArquivo);
            mostrarResultado(ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0),
                    "XML SAT impresso com sucesso", "Erro ao imprimir XML SAT");
            ImpressoraDLL.INSTANCE.Corte(5);
        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo XML: " + e.getMessage());
        }
    }

    public void imprimirXMLCancelamentoSAT(String caminhoArquivo) {
        if (!verificarConexao()) {
            return;
        }

        try {
            String xml = lerArquivo(caminhoArquivo);
            mostrarResultado(ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(
                    xml, ASSINATURA_QR_CODE_CANCELAMENTO, 0),
                    "XML de cancelamento impresso com sucesso",
                    "Erro ao imprimir XML de cancelamento");
            ImpressoraDLL.INSTANCE.Corte(5);
        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo XML: " + e.getMessage());
        }
    }

    public void abrirGaveta(int pino, int tempoInicio, int tempoFim) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.AbreGaveta(
                pino, tempoInicio, tempoFim), "Gaveta aberta com sucesso",
                "Erro ao abrir a gaveta");
    }

    public void abrirGavetaElgin(int pino, int tempoInicio, int tempoFim) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.AbreGavetaElgin(
                pino, tempoInicio, tempoFim), "Gaveta Elgin aberta com sucesso",
                "Erro ao abrir a gaveta Elgin");
    }

    public void sinalSonoro(int quantidade) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.SinalSonoro(quantidade, 50, 5),
                "Bip!", "Erro no sinal sonoro");
    }

    public void avancarPapel(int linhas) {
        if (!verificarConexao()) {
            return;
        }

        mostrarResultado(ImpressoraDLL.INSTANCE.AvancaPapel(linhas),
                "Papel avançado com sucesso", "Erro ao avançar o papel");
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    public void consultarStatus(int parametro) {
        if (!verificarConexao()) {
            return;
        }

        int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(parametro);
        if (retorno < 0) {
            System.out.println("Erro ao consultar o status. Código: " + retorno);
        } else if (parametro == 3 && retorno == 5) {
            System.out.println("Status do papel: papel disponível.");
        } else if (parametro == 3 && retorno == 6) {
            System.out.println("Status do papel: papel acabando.");
        } else if (parametro == 3 && retorno == 7) {
            System.out.println("Status do papel: sem papel.");
        } else {
            System.out.println("Status da impressora: " + retorno);
        }
    }

    public boolean isConexaoAberta() {
        return conexaoAberta;
    }

    private boolean verificarConexao() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes.");
            return false;
        }
        return true;
    }

    private void mostrarResultado(int retorno, String mensagemSucesso, String mensagemErro) {
        if (retorno == 0) {
            System.out.println(mensagemSucesso + ".");
        } else {
            System.out.println(mensagemErro + ". Código: " + retorno);
        }
    }

    private String lerArquivo(String caminhoArquivo) throws IOException {
        byte[] conteudo = Files.readAllBytes(Paths.get(caminhoArquivo));
        return new String(conteudo, StandardCharsets.UTF_8);
    }
}
