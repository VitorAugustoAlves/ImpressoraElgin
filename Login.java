import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    public boolean exibirLogin(){
        Scanner ler = new Scanner(System.in);
        Usuario user = new Usuario();
        int tent_login = 0;
        String usuario;
        int senha = 0;
        boolean loginSucesso = false;

        //parte do login
        System.out.println("Por favor, faça o login para continuar.");

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

        return loginSucesso;
    }
}
