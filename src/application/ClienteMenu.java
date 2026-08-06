package application;

import model.entities.Cliente;
import model.services.ClienteService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClienteMenu {

    private Scanner sc;
    private final ClienteService service = new ClienteService();

    public ClienteMenu(Scanner sc) {
        this.sc = sc;
    }

    public void exibirMenuCliente() {

        int opcao = -1;

        do {
            System.out.println("==========================================");
            System.out.println("        MENU DO CLIENTE          ");
            System.out.println("==========================================");
            System.out.println();
            System.out.println("[1] Cadastrar");
            System.out.println("[2] Alterar cadastro");
            System.out.println("[3] Excluir cadastro");
            System.out.println("[4] Buscar cliente por id");
            System.out.println("[5] Listar todos os clientes cadastrados");
            System.out.println("[0] Sair");
            System.out.println();
            System.out.println("------------------------------------------");

            try {

                System.out.print("Entre com uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrar();
                        break;

                    case 2:
                        alterarCadastro();
                        break;

                    case 3:
                        excluirCadastroDeCliente();
                        break;

                    case 4:
                        buscarCliente();
                        break;

                    case 5:
                        listarClientes();
                        break;

                    case 0:
                        System.out.println("Fechando programa!");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private void cadastrar() {

        System.out.println("EFETUANDO CADASTRO: ");
        System.out.print("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        System.out.print("Digite o cpf: ");
        String cpf = sc.nextLine();
        System.out.print("Entre com o telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Entre com o email: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(null, nome, cpf, telefone, email);

        service.cadastrarCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Id: " + cliente.getId());
    }

    private void alterarCadastro() {

        System.out.println("ALTERANDO CADASTRO: ");

        System.out.println("Digite o id do cliente que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cliente = service.buscarCliente(id);

        System.out.print("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        cliente.setNome(nome);

        System.out.print("Digite o cpf: ");
        String cpf = sc.nextLine();
        cliente.setCpf(cpf);

        System.out.print("Entre com o telefone: ");
        String telefone = sc.nextLine();
        cliente.setTelefone(telefone);

        System.out.print("Entre com o email: ");
        String email = sc.nextLine();

        cliente.setEmail(email);

        service.atualizarCliente(cliente);

        System.out.println("Cliente atualizado com sucesso!");
    }

    private void excluirCadastroDeCliente() {

        System.out.println("EXCLUINDO CADASTRO: ");
        System.out.print("Digite o id do cadastro que deseja deletar: ");
        int id = sc.nextInt();
        sc.nextLine();

        service.apagarCliente(id);

        System.out.println("Cadastro apagado com sucesso!");

    }

    private void buscarCliente() {

        System.out.println("BUSCANDO CLIENTE: ");
        System.out.print("Digite o id do cliente que deseja buscar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cliente = service.buscarCliente(id);

        System.out.println(cliente);

    }

    private void listarClientes() {

        List<Cliente> list = service.buscarTodos();

        for(Cliente cliente : list) {
            System.out.println(cliente);
        }
    }
}
