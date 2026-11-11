public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        Menu menu = new Menu();
        System.out.println("Bem-vindo ao sistema de impressão da Elgin!");
        System.out.println("Grupo composto por: Vitor Teodósio, Gabriel Schultz, Hyago Bringel.");
        
        //verificar se a senha e o usuario estão corretos
        boolean loginSucesso = login.exibirLogin();
            
        
        //parte do menu
        if (!loginSucesso) {
            System.out.println("Acesso negado. Encerrando o programa.");
        }
        while(loginSucesso) {
            int opcao= menu.exibirMenu();
            if(opcao == 12){
                System.out.println("Saindo do programa...");
                break;
            }
        }
    }
}
