import java.util.InputMismatchException;
import java.util.Scanner;

class Usuario{
    String usuarioADMIN = "admin";
    int senhaADMIN = 1234;
}

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Usuario user = new Usuario();
        int tent_login = 0;
        int op = 0; 
        String usuario;
        int senha = 0;
        boolean loginSucesso = false;

        //parte do login

        while (tent_login < 3) {
            System.out.println("Digite seu login:");
            usuario = ler.nextLine();
            boolean senhaValida = false;

            // loop pra verificar senha
            while (!senhaValida) {
                System.out.println("Digite sua senha (Apenas números):");

                try {
                    senha = ler.nextInt();
                    ler.nextLine();
                    senhaValida = true;

                } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite apenas números na senha.");
                    ler.nextLine();
                    System.out.println("");
                }
            }


            //verificar se a senha e o usuario estão corretos
            if (usuario.equals(user.usuarioADMIN) && senha == user.senhaADMIN) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("---------------------------------");
                loginSucesso = true;
                break;
            } else {
                tent_login++;
                System.out.println("Falha no login, tente novamente. " + tent_login + " de 3.");
                System.out.println("---------------------------------");
            }
        }
        
        //parte do menu

        while(tent_login < 3 && loginSucesso) {
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
        }
        ler.close();
    }
}