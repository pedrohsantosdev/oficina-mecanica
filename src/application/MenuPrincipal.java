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

           System.out.println("==========================================");
           System.out.println("        SISTEMA OFICINA MECÂNICA          ");
           System.out.println("==========================================");
           System.out.println();
           System.out.println("[1] Clientes");
           System.out.println("[2] Veículos");
           System.out.println("[3] Ordens de Serviço");
           System.out.println("[0] Sair");
           System.out.println();
           System.out.println("------------------------------------------");

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
