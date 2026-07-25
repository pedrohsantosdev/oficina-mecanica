package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.VeiculoDao;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.StatusOrdem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ClienteDao clienteDao = DaoFactory.createClienteDao();
        VeiculoDao veiculoDao = DaoFactory.createVeiuloDao();

        Cliente cliente = new Cliente(1, "Pedro", "12345678911", "(33)99999-9988",
                "pedro@gmail.com");

        Veiculo veiculo = new Veiculo(1, cliente, "BBR1B23", "Toyota", "Hilux SRX", 2024,
                "Branco", 100000);

        veiculo.setQuilometragem(80000);

        System.out.println("Atualização concluída com sucesso!");

        veiculoDao.update(veiculo);

    }
}
