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
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VeiculoDao veiculoDao = DaoFactory.createVeiuloDao();

        System.out.println("Digite o número da placa que deseja buscar: ");
        String placa = sc.next();

        Veiculo veiculo = veiculoDao.findByPlaca(placa.toUpperCase().trim());

        System.out.println(veiculo);

        sc.close();
    }
}
