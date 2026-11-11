import java.util.Scanner;
public class Menu {
    public int exibirMenu() {
            Scanner ler = new Scanner(System.in);
            int op = 0;
            System.out.println("");
            System.out.println("1. Configurar conexão (tipo, modelo, porta, parâmetro)");
            System.out.println("2. Abrir conexão");
            System.out.println("3. Fechar conexão");
            System.out.println("4. Imprimir texto");
            System.out.println("5. Imprimir QR Code");
            System.out.println("6. Imprimir código de barras");
            System.out.println("7. Imprimir XML SAT");
            System.out.println("8. Imprimir XML de cancelamento SAT");
            System.out.println("9. Abrir gaveta.");
            System.out.println("10. Sinal sonoro (bip)");
            System.out.println("11. Avançar papel, ver status do papel...");
            System.out.println("12. Sair");
            System.out.println("Escolha uma opção: ");
            op = ler.nextInt();

            switch (op) {
                case 1:
                    System.out.println(" Opção 1 selecionada: Configurar conexão (tipo, modelo, porta, parâmetro)");
                    break;
                case 2:
                    System.out.println(" Opção 2 selecionada: Abrir conexão");
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Fechar conexão");
                   
                    break;
                case 4:
                    System.out.println("Opcão 4 selecionada: Imprimir texto");
                    break;
                case 5:
                    System.out.println("Opção 5 selecionada: Imprimir QR Code");
                    break;
                case 6:
                    System.out.println("Opção 6 selecionada: Imprimir código de barras");
                    
                    break;
                case 7:
                    System.out.println("Opção 7 selecionada: Imprimir XML SAT");
                    break;
                case 8:
                    System.out.println("Opção 8 selecionada: Imprimir XML de cancelamento SAT");
                    break;
                case 9:
                    System.out.println("Opção 9 selecionada: Abrir gaveta.");
                    
                    break;
                case 10:
                    System.out.println("Opção 10 selecionada: Sinal sonoro (bip)");
                    
                    break;
                case 11:
                    System.out.println("EXTRA. Avançar papel, ver status do papel...");
                    
                    break;
                case 12:
                    System.out.println("Saindo do programa...");
                    System.exit(0);

                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            return op;
    }
}

