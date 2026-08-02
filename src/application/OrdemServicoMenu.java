package application;

import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.StatusOrdem;
import model.entities.Veiculo;
import model.services.ClienteService;
import model.services.OrdemServicoService;
import model.services.VeiculoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrdemServicoMenu {

    private final Scanner sc;
    private final OrdemServicoService service = new OrdemServicoService();
    private final VeiculoService serviceVeiculo = new VeiculoService();
    private final ClienteService serviceCliente = new ClienteService();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public OrdemServicoMenu(Scanner sc) {
        this.sc = sc;
    }

    private void exibirOrdemDeServicoMenu() {

        int opcao = -1;

        do {

            System.out.println("=== MENU DE ORDENS DE SERVIÇOS ===");
            System.out.println("1 - Abrir ordem");
            System.out.println("2 - Atualizar ordem");
            System.out.println("3 - Excluir ordem de serviço");
            System.out.println("4 - Buscar ordem de serviço");
            System.out.println("5 - Buscar ordens de um veículo");
            System.out.println("6 - Buscar ordens de um cliente");
            System.out.println("7 - Buscar ordens por status");
            System.out.println("8 - Listar todas as ordens");
            System.out.println("0 - Fechar programa");

            try {

                opcao = sc.nextInt();
                sc.nextLine();

               switch (opcao) {

                   case 1:
                       abrirOrdemDeServico();
                       break;

                   case 2:
                       alterarOrdemDeServico();
                       break;

                   case 3:
                       deletarOrdemDeServico();
                       break;

                   case 4:
                       buscarOrdemDeServico();
                       break;

                   case 5:
                       buscarOrdemPorVeiculo();
                       break;

                   case 6:
                       buscarOrdemPorCliente();
                       break;

                   case 7:
                       buscarPorStatus();
                       break;

                   case 8:
                       listarTodos();
                       break;

                   case 0:
                       System.out.println("Fechando programa!");
                       break;

                   default:
                       System.out.println("Opção inválida!");
                       break;
               }
            }
            catch (InputMismatchException e) {
                System.out.println("Digite um número válido: ");
                sc.nextInt();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private void abrirOrdemDeServico() {

        System.out.println("ABRINDO ORDEM DE SERVIÇO: ");
        System.out.println("Digite o id do veículo que deseja abrir uma ordem: ");
        int id = sc.nextInt();
        sc.nextLine();

        Veiculo veiculo = serviceVeiculo.buscarVeiculo(id);

        if(veiculo == null) {

            System.out.println("Id inválido!");
            return;
        }

        System.out.print("Digite a data de entrada do veículo: ");
        LocalDate dataEntrada = LocalDate.parse(sc.next(), dtf);

        System.out.print("Digite a data de saída: ");
        LocalDate dataSaida = LocalDate.parse(sc.next(), dtf);

        System.out.print("Descreva o problema do veículo apresentado: ");
        String problema = sc.nextLine();

        System.out.print("Entre com o diagnóstico do veículo: ");
        String diagnostico = sc.nextLine();

        System.out.print("Digite o valor do conserto do veículo: ");
        double valor = sc.nextDouble();

        System.out.print("Entre com o status da ordem de serviço: ");
        StatusOrdem status = StatusOrdem.valueOf(sc.next().trim().toUpperCase());

        OrdemServico ordemServico = new OrdemServico(null, veiculo, dataEntrada, dataSaida, problema, diagnostico, valor, status);

        System.out.println("Ordem aberta com sucesso!");
        System.out.println("Id: " + ordemServico.getId());

    }

    private void alterarOrdemDeServico() {

        System.out.println("ATUALIZNDO ORDEM DE SERVIÇO: ");
        System.out.println("Digite o id da ordem de serviço que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

       OrdemServico ordemServico = service.buscarOrdemServico(id);

        if(ordemServico == null) {

            System.out.println("Id inválido!");
            return;
        }

        System.out.print("Digite a data de entrada do veículo: ");
        LocalDate dataEntrada = LocalDate.parse(sc.next(), dtf);
        ordemServico.setDataEntrada(dataEntrada);

        System.out.print("Digite a data de saída: ");
        LocalDate dataSaida = LocalDate.parse(sc.next(), dtf);
        ordemServico.setDataSaida(dataSaida);

        System.out.print("Descreva o problema do veículo apresentado: ");
        String problema = sc.nextLine();
        ordemServico.setProblema(problema);

        System.out.print("Entre com o diagnóstico do veículo: ");
        String diagnostico = sc.nextLine();
        ordemServico.setDiagnostico(diagnostico);

        System.out.print("Digite o valor do conserto do veículo: ");
        double valor = sc.nextDouble();
        ordemServico.setValor(valor);

        System.out.print("Entre com o status da ordem de serviço: ");
        StatusOrdem status = StatusOrdem.valueOf(sc.next().trim().toUpperCase());
        ordemServico.setStatusOrdem(status);

        service.atualizarOrdemServico(ordemServico);

        System.out.println("Ordem atualizada com sucesso!");

    }

    private void deletarOrdemDeServico() {

        System.out.println("DELETANDO ORDEM DE SERVIÇO: ");
        System.out.print("Digite o id da ordem de serviço que deseja apagar: ");
        int id = sc.nextInt();

        OrdemServico ordemServico = service.buscarOrdemServico(id);

        if(ordemServico == null) {
            System.out.println("Id inválido!");
            return;
        }

        service.deletarOrdemServico(id);

        System.out.println("Ordem apagada com sucesso!");

    }

    private void buscarOrdemDeServico() {

        System.out.println("BUSCANDO ORDEM DE SERVIÇO: ");
        System.out.print("Digite o id da ordem de serviço que deseja buscar: ");
        int id = sc.nextInt();

        OrdemServico ordemServico = service.buscarOrdemServico(id);

        if(ordemServico == null) {
            System.out.println("Id inválido!");
            return;
        }

        System.out.println(ordemServico);

    }

    private void buscarOrdemPorVeiculo() {

        System.out.println("BUSCANDO ORDEM DE SERVIÇO PELO VEÍCULO: ");
        System.out.print("Digite o id do veículo que está buscando: ");
        int id = sc.nextInt();

        Veiculo veiculo = serviceVeiculo.buscarVeiculo(id);

        if(veiculo == null) {
            System.out.println("Id inválido!");
            return;
        }

        List<OrdemServico> list = service.buscarOrdemPorVeiculo(veiculo);

        if(list.isEmpty()) {
            System.out.println("Lista vazia!");
            return;
        }

        for(OrdemServico ordemServico : list) {
            System.out.println(ordemServico);
        }

    }

    private void buscarOrdemPorCliente() {

        System.out.println("BUSCANDO ORDEM DE SERVIÇO PELO CLIENTE: ");
        System.out.print("Digite o id do cliente que deseja buscar suas ordens: ");
        int id = sc.nextInt();

        Cliente cliente = serviceCliente.buscarCliente(id);

        if(cliente == null) {
            System.out.println("Id inválido!");
            return;
        }

        List<OrdemServico> list = service.buscarOrdemPorCliente(cliente);

        if(list.isEmpty()) {
            System.out.println("Lista vazia!");
        }

        for(OrdemServico ordemServico : list) {
            System.out.println(ordemServico);
        }

    }

    private void buscarPorStatus() {

        System.out.println("BUSCANDO ORDENS POR STATUS: ");
        System.out.print("Digite o status que deseja buscar: ");
        StatusOrdem statusOrdem = StatusOrdem.valueOf(sc.next().trim().toUpperCase());

        if(statusOrdem == null) {
            System.out.println("Status inválido!");
            return;
        }

        List<OrdemServico> list = service.buscarOrdemPorStatus(statusOrdem);

        if(list.isEmpty()) {
            System.out.println("Lista vazia!");
            return;
        }

        for(OrdemServico ordemServico : list) {
            System.out.println(ordemServico);
        }

    }

    private void listarTodos() {

        System.out.println("LISTANDO TODAS AS ORDENS: ");

        List<OrdemServico> list = service.buscarTodos();

        if(list.isEmpty()) {
            System.out.println("Lista vazia!");
            return;
        }

        for(OrdemServico ordemServico : list) {
            System.out.println(ordemServico);
        }

    }
}
