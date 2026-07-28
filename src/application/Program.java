package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.OrdemServicoDao;
import model.dao.VeiculoDao;
import model.dao.impl.OrdemServicoDaoJDBC;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.StatusOrdem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        OrdemServicoDao ordemServicoDao = DaoFactory.createOrdemServicoDao();
        VeiculoDao veiculoDao = DaoFactory.createVeiuloDao();

        System.out.println("Digite o id do veículo que deseja buscar suas ordens de pedido: ");
        int id = sc.nextInt();

        Veiculo veiculo = veiculoDao.findById(id);

        List<OrdemServico> list = ordemServicoDao.findByVeiculo(veiculo);

        System.out.println(list);

    }
}
