package application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    Scanner sc = new Scanner(System.in);
    ClienteMenu clienteMenu = new ClienteMenu(sc);
    VeiculoMenu veiculoMenu = new VeiculoMenu(sc);
    OrdemServicoMenu ordemServicoMenu = new OrdemServicoMenu(sc);

    public void exibirMenuPrincipal() {

       int opcao = -1;

       do {

           System.out.println("=== MENU PRINCIPAL ===");
           System.out.println();
           System.out.println("1 - Exibir menu de clientes");
           System.out.println("2 - Exibir menu de veículos");
           System.out.println("3 - Exibir menu de ordens de serviços");
           System.out.println("0 - Fechar programa");
           System.out.println();

           try {

               System.out.println("Escolha uma opção: ");
               opcao = sc.nextInt();
               sc.nextLine();

               switch (opcao) {

                   case 1:

                       clienteMenu.exibirMenuCliente();
                       break;

                   case 2:

                       veiculoMenu.exibirVeiculoMenu();
                       break;

                   case 3:

                       ordemServicoMenu.exibirOrdemDeServicoMenu();
                       break;

                   case 0:

                       System.out.println("Fechando sistema!");
                       break;

                   default:
                       System.out.println("Opção inválida!");
                       break;
               }
           }
           catch (InputMismatchException e) {
               System.out.println("Digite um número válido: ");
               sc.nextLine();
               opcao = -1;
           }
       } while (opcao != 0);
    }
}
