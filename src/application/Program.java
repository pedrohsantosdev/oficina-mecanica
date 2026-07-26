package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.OrdemServicoDao;
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        VeiculoDao veiculoDao = DaoFactory.createVeiuloDao();
        OrdemServicoDao ordemServicoDao = DaoFactory.createOrdemServicoDao();

        Veiculo veiculo = veiculoDao.findById(1);

        OrdemServico ordemServico = new OrdemServico(2, veiculo, LocalDate.parse("26/07/2026", dtf), LocalDate.parse("28/07/2026", dtf),
                "Cliente relata ruído na suspensão dianteira ao passar por lombadas e buracos.",
                "Constatado desgaste nas bieletas e nas buchas da barra estabilizadora.", 850.00, StatusOrdem.CONCLUIDO);

        ordemServicoDao.deleteById(2);

        System.out.println("Delete efetuado com sucesso!");

    }
}
