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

        OrdemServicoDao ordemServicoDao = DaoFactory.createOrdemServicoDao();

        List<OrdemServico> list = ordemServicoDao.findByStatus(StatusOrdem.CONCLUIDO);

        System.out.println(list);

    }
}
