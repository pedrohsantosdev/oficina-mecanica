package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.StatusOrdem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {

    public static void main(String[] args) {

        ClienteDao clienteDao = DaoFactory.createClienteDao();

        Cliente cliente = clienteDao.findById(1);

        System.out.println(cliente);

    }
}
