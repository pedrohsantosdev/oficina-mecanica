package application;

import model.entities.Cliente;
import model.entities.Veiculo;
import model.services.ClienteService;
import model.services.VeiculoService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VeiculoMenu {

    private final Scanner sc;
    private final VeiculoService service = new VeiculoService();
    private final ClienteService serviceCliente = new ClienteService();

    public VeiculoMenu(Scanner sc) {
        this.sc = sc;
    }

    private void exibirVeiculoMenu() {

        int opcao = -1;

        do {

            System.out.println("=== MENU DE VEÍCULOS ===");
            System.out.println();
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Atualizar cadastro");
            System.out.println("3 - Excluir cadastro");
            System.out.println("4 - Buscar por id");
            System.out.println("5 - Buscar por placa");
            System.out.println("6 - Buscar por cliente");
            System.out.println("7 - Listar todos");
            System.out.println("0 - Fechar programa");
            System.out.println();

            try {

                System.out.println("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {

                    case 1:
                        cadastrar();
                        break;

                    case 2:
                        atualizarCadastro();
                        break;

                    case 3:
                        deletarCadastro();
                        break;

                    case 4:
                        buscarPorId();
                        break;

                    case 5:
                        buscarPorPlaca();
                        break;

                    case 6:
                        buscarPeloCliente();
                        break;

                    case 7:
                        listarTodos();
                        break;

                    case 0:
                        System.out.println("Fechando programa!");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;

                }

            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private void cadastrar() {

        System.out.println("CADASTRANDO VEÍCULO: ");
        System.out.print("Digite o id do propietário do veículo: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cliente = serviceCliente.buscarCliente(id);

        if(cliente == null) {
            System.out.println("Id inválido!");
            return;
        }

        System.out.print("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        System.out.print("Entre com a marca: ");
        String marca = sc.nextLine();
        System.out.print("Digite o modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Entre com o ano do veículo: ");
        int ano = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite a cor: ");
        String cor = sc.nextLine();
        System.out.print("Entre com a quilometragem rodada pelo veículo: ");
        int quilometragem = sc.nextInt();

        Veiculo veiculo = new Veiculo(null, cliente, placa, marca, modelo, ano, cor, quilometragem);

        service.cadastrarVeiculo(veiculo);

        System.out.println("Veículo cadastrado com sucesso!");
        System.out.println("Id: " + veiculo.getId());

    }

    private void atualizarCadastro() {

        System.out.println("ATUALIZANDO CADASTRO: ");
        System.out.print("Digite o id do veículo que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Veiculo veiculo = service.buscarVeiculo(id);

        if(veiculo == null) {
            System.out.println("Id inválido!");
            return;
        }

        System.out.print("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        veiculo.setPlaca(placa);
        System.out.print("Entre com a marca: ");
        String marca = sc.nextLine();
        veiculo.setMarca(marca);
        System.out.print("Digite o modelo: ");
        String modelo = sc.nextLine();
        veiculo.setModelo(modelo);
        System.out.print("Entre com o ano do veículo: ");
        int ano = sc.nextInt();
        sc.nextLine();
        veiculo.setAno(ano);
        System.out.print("Digite a cor: ");
        String cor = sc.nextLine();
        veiculo.setCor(cor);
        System.out.print("Entre com a quilometragem rodada pelo veículo: ");
        int quilometragem = sc.nextInt();
        sc.nextLine();
        veiculo.setQuilometragem(quilometragem);

        service.atualizarVeiculo(veiculo);

        System.out.println("Veículo atualizado com sucesso!");

    }

    public void deletarCadastro() {

        System.out.println("DELETANDO CADASTRO: ");
        System.out.print("Digite o id do veículo que deseja deletar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Veiculo veiculo = service.buscarVeiculo(id);

        if(veiculo == null) {
            System.out.println("Id inválido!");
            return;
        }

        service.deletarVeiculo(id);

        System.out.println("Veículo deletado com sucesso!");

    }

    public void buscarPorId() {

        System.out.println("BUSCANDO VEÍCULO: ");
        System.out.print("Digite o id do veículo: ");
        int id = sc.nextInt();

        Veiculo veiculo = service.buscarVeiculo(id);

        if(veiculo == null) {
            System.out.println("Veículo inválido!");
            return;
        }

        System.out.println(veiculo);

    }

    public void buscarPorPlaca() {

        System.out.println("BUSCANDO VEÍCULO PELA PLACA: ");
        System.out.print("Entre com a placa do veículo: ");
        String placa = sc.nextLine();

        Veiculo veiculo = service.buscarPlaca(placa);

        if(veiculo == null) {
            System.out.println("Placa inválida!");
            return;
        }

        System.out.println(veiculo);

    }

    public void buscarPeloCliente() {

        System.out.println("BUSCANDO VEÍCULOS PELO CLIENTE: ");
        System.out.print("Digite o id do cliente que deseja buscar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cliente = serviceCliente.buscarCliente(id);

        if(cliente == null) {
            System.out.println("Id inválido!");
            return;
        }

        List<Veiculo> list = service.buscarCliente(cliente);

        if(list.isEmpty()) {
            System.out.println("Lista vazia!");
            return;
        }

        System.out.println("Veículos do propietário: ");

        for(Veiculo veiculo : list) {
            System.out.println(veiculo);
        }

    }

    public void listarTodos() {

        System.out.println("BUSCANDO TODOS OS VEÍCULOS CADASTRADOS: ");

        List<Veiculo> list = service.buscarTodos();

        if(list.isEmpty()) {
            System.out.println("Lista vazia!");
            return;
        }

        for(Veiculo veiculo : list) {
            System.out.println(veiculo);
        }
    }
}
